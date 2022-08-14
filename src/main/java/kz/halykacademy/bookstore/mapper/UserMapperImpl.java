package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.UserDto;
import kz.halykacademy.bookstore.dto.UserDto.*;
import kz.halykacademy.bookstore.entity.Role;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.enums.AccountStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UserMapperImpl {

    public User toEntity(Request.Create userDto) {
        User.UserBuilder user = User.builder();
        if (userDto == null) return null;
        if (userDto.getLogin() != null) user.login(userDto.getLogin());
        if (userDto.getPassword() != null) user.password(userDto.getPassword());
        user.status(AccountStatus.ACTIVE);
        user.roles(new HashSet<>());
        user.orders(new HashSet<>());
        return user.build();
    }

    public Response.Created toDtoResponseCreated(User user) {
        Response.Created created = new Response.Created();
        if (user == null) return null;
        if (user.getId() != null) created.setId(user.getId());
        if (user.getLogin() != null) created.setLogin(user.getLogin());
        if (user.getStatus() != null) created.setStatus(user.getStatus().toString());

        List<Role> roles = new ArrayList<>(user.getRoles().size());
        roles.addAll(user.getRoles());
        if (user.getRoles() != null) created.setRoles(roles);

        return created;
    }
}
