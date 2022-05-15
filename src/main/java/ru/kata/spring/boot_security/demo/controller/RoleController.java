package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Controller
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/rolesList")
    public String printRoles(Role role, ModelMap modelMap) {
        modelMap.addAttribute("role", role);
        modelMap.addAttribute("roles", roleService.listRoles());
        return "rolesList";
    }
    @PostMapping(value = "/admin/newRole")
    public String addRole(Role role) {
        roleService.addRole(role);
        return "redirect:/admin/rolesList";
    }
    @GetMapping (value = "/admin/role/delete")
    public String removeRole(int id) {
        roleService.removeRole(id);
        return "redirect:/admin/rolesList";
    }

}
