package com.lenhatthanh.blog.modules.user.domain.service;

import com.lenhatthanh.blog.modules.user.domain.*;
import com.lenhatthanh.blog.modules.user.domain.exception.UserAlreadyExistsException;
import com.lenhatthanh.blog.modules.user.domain.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.exception.RoleNotFoundException;
import com.lenhatthanh.blog.modules.user.domain.repository.RoleRepository;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import com.lenhatthanh.blog.modules.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserServiceImpl implements UpdateUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public void updateSubscriber(UserDto newUserDto) {
        User user = getUserByIdOrError(newUserDto.getId());
        if (!user.getEmail().getValue().equals(newUserDto.getName())) {
            this.newEmailDoesNotExistOrError(newUserDto.getEmail());
        }

        user.updateName(new UserName(newUserDto.getName()));
        user.updateEmail(new Email(newUserDto.getEmail()));
        user.updateMobilePhone(new MobilePhone(newUserDto.getMobilePhone()));

        userRepository.save(user);
    }

    private User getUserByIdOrError(String userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Role getRoleOrError(String roleId) {
        return roleRepository.findById(roleId).orElseThrow(RoleNotFoundException::new);
    }

    private void newEmailDoesNotExistOrError(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }
}
