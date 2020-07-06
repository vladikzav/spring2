package ru.geekbrains.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Category;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findByName(String name);

    Page<Product> findByPriceGreaterThanEqual(Integer price, Pageable pageable);

    Page<Product> findByPriceLessThanEqual(Integer price, Pageable pageable);

    Page<Product> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);

    @Query("from Product u where u.name like :pattern")
    List<Product> queryByNamePattern(@Param("pattern") String pattern);

    Optional<Product> findProductByName(String name);

    Page<Product> findByNameContaining(String search, Pageable pageable);
}