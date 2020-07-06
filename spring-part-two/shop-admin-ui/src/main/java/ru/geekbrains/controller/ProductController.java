package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Product;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ru.geekbrains.controller.ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String productList(Model model,
                              @RequestParam(name = "minPrice", required = false) Integer minPrice,
                              @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
                              @RequestParam(name = "page") Optional<Integer> page,
                              @RequestParam(name = "size") Optional<Integer> size,
                              @RequestParam(name = "search", required = false) String search) {
        logger.info("Product list. With minPrice = {} and maxPrice = {}", minPrice, maxPrice);

        Page<Product> productPage = productService.filterByPrice(minPrice, maxPrice, search,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("productPage", productPage);
        model.addAttribute("prevPageNumber", productPage.hasPrevious() ? productPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", productPage.hasNext() ? productPage.nextPageable().getPageNumber() + 1 : -1);

        return "products";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        logger.info("Create product form");

        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        logger.info("Save product method");

        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        logger.info("Delete product method");

        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        productService.delete(product);
        model.addAttribute(productService.findAll());

        return "redirect:/product";
    }
}
