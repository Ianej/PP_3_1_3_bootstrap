package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value = "/")
    public String loginUser () {
        return "redirect:/login";
    }

    @GetMapping(value = "/admin")
    public String printAdminPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userA = userService.findUserByName(authentication.getName());
        modelMap.addAttribute("userA", userA);
        modelMap.addAttribute("users", userService.listUsers());
        modelMap.addAttribute("roles", roleService.listRoles());
        modelMap.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping(value = "/admin/newUser")
    public String newUser(User user) throws Exception {
        String name = user.getName();
        if (userService.findUserByName(name)!=null) {
            throw new Exception("Имя существует, введите пожалуйста другое имя");
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser (@RequestParam() long id, @ModelAttribute("userEdit") User userEdit) {
        userService.updateUser(id, userEdit);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete")
    public String deleteUser(long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/user")
    public String printUser(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(authentication.getName());
        modelMap.addAttribute("userA", user);
        return "user";
    }
}
