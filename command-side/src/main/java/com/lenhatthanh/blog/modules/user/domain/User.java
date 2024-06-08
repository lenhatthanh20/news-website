package com.lenhatthanh.blog.modules.user.domain;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.AggregateRoot;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.UserUpdatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.UserDeletedEvent;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
@Builder
public class User extends AggregateRoot<Id> {
    private static final Boolean ACTIVATED = true;
    private UserName name;
    private Email email;
    private MobilePhone mobilePhone;
    private String password;
    Boolean isActive;

    // Relationship with Role aggregate via id
    private List<Id> roleIds;

    private void update(Consumer<User> fieldUpdater) {
        fieldUpdater.accept(this);
        this.registerEvent(new UserUpdatedEvent(this));
    }

    public void updateName(UserName name) {
        update(user -> user.name = name);
    }

    public void updateEmail(Email email) {
        update(user -> user.email = email);
    }

    public void updateMobilePhone(MobilePhone mobilePhone) {
        update(user -> user.mobilePhone = mobilePhone);
    }

    public void addRole(Id roleId) {
        if (roleIds.contains(roleId)) {
            return;
        }

        update(user -> user.roleIds.add(roleId));
    }

    public void removeRole(Id roleId) {
        update(user -> user.roleIds.remove(roleId));
    }

    public void activate() {
        update(user -> user.isActive = true);
    }

    public void deactivate() {
        update(user -> user.isActive = false);
    }

    public void delete() {
        this.registerEvent(new UserDeletedEvent(this));
    }

    public static User create(UserDto userDto, Id roleId) {
        User user = User.builder()
                .name(new UserName(userDto.getName()))
                .email(new Email(userDto.getEmail()))
                .mobilePhone(new MobilePhone(userDto.getMobilePhone()))
                .password(userDto.getPassword())
                .isActive(ACTIVATED)
                .roleIds(new ArrayList<>(List.of(roleId)))
                .build();
        user.setId(new Id(UniqueIdGenerator.create()));
        user.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);

        user.registerEvent(new UserCreatedEvent(user));
        return user;
    }
}
