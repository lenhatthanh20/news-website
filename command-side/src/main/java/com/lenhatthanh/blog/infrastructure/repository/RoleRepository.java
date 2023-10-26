package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.Command;
import com.lenhatthanh.blog.domain.Role;
import com.lenhatthanh.blog.domain.repository.RoleRepositoryInterface;
import com.lenhatthanh.blog.infrastructure.repository.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleRepository implements RoleRepositoryInterface {
    public static final String MESSAGE_QUEUE_TOPIC = "role";

    private RoleJpaRepository roleJpaRepository;
    private KafkaTemplate<String, RoleEntity> kafkaTemplate;

    @Override
    public void save(Role user) {
        RoleEntity roleEntity = new RoleEntity(
                user.getId(),
                user.getName(),
                user.getDescription()
        );

        roleJpaRepository.save(roleEntity);
        this.syncToQuerySide(roleEntity);
    }

    private void syncToQuerySide(RoleEntity role) {
        ProducerRecord<String, RoleEntity> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, Command.CREATED, role);
        this.kafkaTemplate.send(record);
    }

    @Override
    public void saveAll(List<Role> roles) {
        Iterable<RoleEntity> roleEntities = roles.stream().map(role -> new RoleEntity(
                role.getId(),
                role.getName(),
                role.getDescription()
        )).toList();

        roleJpaRepository.saveAll(roleEntities);
        roleEntities.forEach(this::syncToQuerySide);
    }

    @Override
    public Optional<Role> findByName(String name) {
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findByName(name);
        if (roleEntity.isEmpty()) {
            return Optional.empty();
        }

        Role role = new Role(roleEntity.get().getId(), roleEntity.get().getName(), roleEntity.get().getDescription());

        return Optional.of(role);
    }
}
