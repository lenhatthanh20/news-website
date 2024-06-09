package com.lenhatthanh.blog.modules.user.domain.service.implement;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.domain.Email;
import com.lenhatthanh.blog.modules.user.domain.MobilePhone;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.UserName;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.UserDeletedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.UserUpdatedEvent;
import com.lenhatthanh.blog.modules.user.domain.exception.CannotUpdateDeletedUserException;
import com.lenhatthanh.blog.modules.user.domain.service.UserDomainService;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import com.lenhatthanh.blog.shared.UniqueIdGenerator;
import org.springframework.stereotype.Service;

import static com.lenhatthanh.blog.core.domain.AggregateRoot.CONCURRENCY_CHECKING_INITIAL_VERSION;
import static com.lenhatthanh.blog.modules.user.domain.User.ACTIVATED;
import static com.lenhatthanh.blog.modules.user.domain.User.NOT_DELETED;

@Service
public class UserDomainServiceImpl implements UserDomainService {
    @Override
    public User createNewUser(UserDto userDto) {
        User user = User.builder()
                .name(new UserName(userDto.getName()))
                .email(new Email(userDto.getEmail()))
                .mobilePhone(new MobilePhone(userDto.getMobilePhone()))
                .password(userDto.getPassword())
                .isActive(ACTIVATED)
                .isDeleted(NOT_DELETED)
                .roleIds(userDto.getRoleIds().stream().map(Id::new).toList())
                .build();
        user.setId(new Id(UniqueIdGenerator.create()));
        user.setAggregateVersion(CONCURRENCY_CHECKING_INITIAL_VERSION);

        user.registerEvent(new UserCreatedEvent(user));
        return user;
    }

    @Override
    public User updateUser(User currentUser, UserDto newUserDto) {
        if (currentUser.getIsDeleted()) {
            throw new CannotUpdateDeletedUserException();
        }

        currentUser.updateName(new UserName(newUserDto.getName()));
        currentUser.updateEmail(new Email(newUserDto.getEmail()));
        currentUser.updateMobilePhone(new MobilePhone(newUserDto.getMobilePhone()));

        currentUser.registerEvent(new UserUpdatedEvent(currentUser));
        return currentUser;
    }

    @Override
    public User deletedUser(User user) {
        // TODO: Implement permission checking when deleting user
        user.maskAsDelete();
        user.registerEvent(new UserDeletedEvent(user));
        return user;
    }

    @Override
    public User deactivateUser(User user) {
        user.deactivate();
        return user;
    }
}
