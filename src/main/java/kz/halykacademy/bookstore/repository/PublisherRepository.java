package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Publisher repository.
 */
@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Publisher> findPublishersByNameContainingIgnoreCase(String name);
}
