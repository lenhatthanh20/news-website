package com.lenhatthanh.blog.infrastructure.repository;

import com.lenhatthanh.blog.domain.article.Author;
import com.lenhatthanh.blog.domain.repository.AuthorRepositoryInterface;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorRepository implements AuthorRepositoryInterface {
    public static final String MESSAGE_QUEUE_TOPIC = "authors";

    private AuthorJpaRepository authorJpaRepository;
    private KafkaTemplate<String, AuthorDto> kafkaTemplate;

    @Override
    public void save(Author author) {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();

        this.authorJpaRepository.save(authorEntity);
        this.syncToQuerySide(new AuthorDto(author.getId(), author.getName(), author.getEmail()));
    }

    private void syncToQuerySide(AuthorDto author) {
        ProducerRecord<String, AuthorDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, Command.CREATED, author);
        this.kafkaTemplate.send(record);
    }

    @Override
    public Optional<Author> findById(String id) {
        Optional<AuthorEntity> authorEntity = this.authorJpaRepository.findById(id);
        if (authorEntity.isEmpty()) {
            return Optional.empty();
        }

        Author author = new Author(authorEntity.get().getId(), authorEntity.get().getName(), authorEntity.get().getEmail());

        return Optional.of(author);
    }
}
