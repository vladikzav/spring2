package ru.geekbrains.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.User;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByName(String name);

    Page<User> findByAgeGreaterThanEqual(Integer age, Pageable pageable);

    Page<User> findByAgeLessThanEqual(Integer age, Pageable pageable);

    Page<User> findByAgeBetween(Integer minAge, Integer maxAge, Pageable pageable);

    @Query("from User u where u.name like :pattern")
    List<User> queryByNamePattern(@Param("pattern") String pattern);

    Optional<User> findUserByName(String name);

    Page<User> findByNameContaining(String search, Pageable pageable);
}