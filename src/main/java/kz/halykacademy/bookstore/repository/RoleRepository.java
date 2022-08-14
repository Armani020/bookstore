package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Role repository.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
