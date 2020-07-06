package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.Role;
import ru.geekbrains.repo.ProductRepository;
import ru.geekbrains.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return repository.findAll();
    }


    public Page<Role> filterByName(String search, Pageable pageable) {

        if(!(search == null)){
            return repository.findByNameContaining(search, pageable);
        }

        return repository.findAll(pageable);
    }

    @Transactional
    public void save(Role role) {
        repository.save(role);
    }

    @Transactional(readOnly = true)
    public Optional<Role> findById(long id) {
        return repository.findById(id);
    }
}
