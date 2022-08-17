package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.OrderDto;
import kz.halykacademy.bookstore.dto.UserDto.Request;
import kz.halykacademy.bookstore.dto.UserDto.Response;
import kz.halykacademy.bookstore.entity.Role;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.enums.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UserMapperImpl {

    private final OrderMapperImpl orderMapper;

    @Autowired
    public UserMapperImpl(OrderMapperImpl orderMapper) {
        this.orderMapper = orderMapper;
    }

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

    public Response.All toDtoResponseAll(User user) {
        Response.All all = new Response.All();
        if (user == null) return null;
        if (user.getId() != null) all.setId(user.getId());
        if (user.getLogin() != null) all.setLogin(user.getLogin());
        if (user.getStatus() != null) all.setStatus(user.getStatus().name());

        List<Role> roles = new ArrayList<>(user.getRoles().size());
        roles.addAll(user.getRoles());
        if (user.getRoles() != null) all.setRoles(roles);

        List<OrderDto.Response.Slim> orders = new ArrayList<>(user.getOrders().size());
        user.getOrders().forEach(order -> orders.add(orderMapper.toDtoResponseSlim(order)));
        all.setOrders(orders);

        return all;
    }

    public Response.Slim toDtoResponseSlim(User user) {
        Response.Slim slim = new Response.Slim();
        if (user == null) return null;
        if (user.getId() != null) slim.setId(user.getId());
        if (user.getLogin() != null) slim.setLogin(user.getLogin());
        if (user.getStatus() != null) slim.setStatus(user.getStatus().name());
        return slim;
    }

    public List<Response.Slim> toDtoResponseSlim(List<User> users) {
        if (users == null) return null;
        List<Response.Slim> list = new ArrayList<>(users.size());
        users.forEach(user -> list.add(toDtoResponseSlim(user)));
        return list;
    }
}
