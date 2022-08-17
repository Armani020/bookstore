package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.UserDto;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.enums.AccountStatus;
import kz.halykacademy.bookstore.mapper.UserMapperImpl;
import kz.halykacademy.bookstore.repository.RoleRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User service.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapperImpl userMapper;

    @Override
    public UserDto.Response.Created save(UserDto.Request.Create request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new IllegalArgumentException("This login already in use! Login: " + request.getLogin());
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        return userMapper.toDtoResponseCreated(userRepository.save(user));
    }

    @Override
    public UserDto.Response.Slim update(final Long id, final UserDto.Request.Update request) {
        User userFromDb = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found. id: " + id));
        userFromDb.setLogin(request.getLogin());
        userFromDb.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toDtoResponseSlim(userRepository.save(userFromDb));
    }

    @Override
    public UserDto.Response.Slim updateStatus(Long id, UserDto.Request.UpdateStatus request) {
        User userFromDb = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        userFromDb.setStatus(AccountStatus.valueOf(request.getStatus()));
        return userMapper.toDtoResponseSlim(userRepository.save(userFromDb));
    }

    @Override
    public UserDto.Response.All find(final Long id) {
        User userFromDb = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found. id: " + id));
        return userMapper.toDtoResponseAll(userFromDb);
    }

    @Override
    public Page<UserDto.Response.Slim> findAll(final String login, final Pageable pageable) {
        Page<User> usersPage = userRepository.findUsersByLogin(login, pageable);
        Page<UserDto.Response.Slim> usersDtoPage = new PageImpl<>(
                userMapper.toDtoResponseSlim(usersPage.getContent()), pageable, usersPage.getTotalElements());
        return usersDtoPage;
    }

    @Override
    public void delete(final Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User doesn't exists. ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
