package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.UserDto;
import kz.halykacademy.bookstore.entity.Role;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.mapper.UserMapperImpl;
import kz.halykacademy.bookstore.repository.RoleRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserDto.Response.Slim update(Long id, UserDto.Request.Update request) {
        return null;
    }

    @Override
    public UserDto.Response.All find(Long id) {
        return null;
    }

    @Override
    public List<UserDto.Response.All> findAll(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
