package ru.geekbrains.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Query("from Product u where u.name like :pattern")
    List<Role> queryByNamePattern(@Param("pattern") String pattern);

    Optional<Role> findRoleByName(String name);

    Page<Role> findByNameContaining(String search, Pageable pageable);

}