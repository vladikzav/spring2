package ru.geekbrains.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    @Query("from Product u where u.name like :pattern")
    List<Category> queryByNamePattern(@Param("pattern") String pattern);

    Optional<Category> findCategoryByName(String name);

    Page<Category> findByNameContaining(String search, Pageable pageable);
}