package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.model.Category;
import ru.geekbrains.service.CategoryService;

import java.util.Optional;

@RequestMapping("/category")
@Controller
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String categoryList(Model model,
                              @RequestParam(name = "page") Optional<Integer> page,
                              @RequestParam(name = "size") Optional<Integer> size,
                              @RequestParam(name = "search", required = false) String search) {

        Page<Category> categoryPage = categoryService.filterByName(search,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("prevPageNumber", categoryPage.hasPrevious() ? categoryPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", categoryPage.hasNext() ? categoryPage.nextPageable().getPageNumber() + 1 : -1);

        return "categories";
    }

    @GetMapping("new")
    public String createCategory(Model model) {
        logger.info("Create category form");

        model.addAttribute("category", new Category());
        return "category";
    }

    @PostMapping
    public String saveCategory(Category category) {
        logger.info("Save category method");

        categoryService.save(category);
        return "redirect:/category";
    }
}
