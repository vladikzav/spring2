package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return repository.findAll();
    }


    public Page<Product> filterByPrice(Integer minPrice, Integer maxPrice, String search, Pageable pageable) {

        if(!(search == null)){
            return repository.findByNameContaining(search, pageable);
        }

        if ((minPrice == null) && (maxPrice==null)) {
            return repository.findAll(pageable);
        }
        if (!(minPrice==null) && maxPrice==null) {
            return repository.findByPriceGreaterThanEqual(minPrice, pageable);
        }
        if (minPrice == null && !(maxPrice==null)) {
            return repository.findByPriceLessThanEqual(maxPrice, pageable);
        }
        return repository.findByPriceBetween(minPrice, maxPrice, pageable);
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional
    public void delete(Product product) {
        repository.delete(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return repository.findById(id);
    }
}
