package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleDao.findAll();
    }
    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }
    @Transactional
    @Override
    public void removeRole(long id) {
        roleDao.deleteById(id);
    }
    @Transactional
    @Override
    public Role getRole(long id) {
        return roleDao.findById(id).get();
    }

    @Transactional
    @Override
    public List<User> getUsersByRole(long id) {
        return getRole(id).getUsers();
    }
}
