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
import ru.geekbrains.model.Role;
import ru.geekbrains.service.RoleService;

import java.util.Optional;

@RequestMapping("/role")
@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public String roleList(Model model,
                              @RequestParam(name = "page") Optional<Integer> page,
                              @RequestParam(name = "size") Optional<Integer> size,
                              @RequestParam(name = "search", required = false) String search) {

        Page<Role> rolePage = roleService.filterByName(search,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("rolePage", rolePage);
        model.addAttribute("prevPageNumber", rolePage.hasPrevious() ? rolePage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", rolePage.hasNext() ? rolePage.nextPageable().getPageNumber() + 1 : -1);

        return "roles";
    }

    @GetMapping("new")
    public String createRole(Model model) {
        logger.info("Create role form");

        model.addAttribute("role", new Role());
        return "role";
    }

    @PostMapping
    public String saveRole(Role role) {
        logger.info("Save role method");

        roleService.save(role);
        return "redirect:/role";
    }
}
