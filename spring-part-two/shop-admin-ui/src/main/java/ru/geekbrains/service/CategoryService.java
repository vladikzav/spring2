package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.Category;
import ru.geekbrains.repo.CategoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }


    public Page<Category> filterByName(String search, Pageable pageable) {

        if(!(search == null)){
            return repository.findByNameContaining(search, pageable);
        }

        return repository.findAll(pageable);
    }

    @Transactional
    public void save(Category category) {
        repository.save(category);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }
}
