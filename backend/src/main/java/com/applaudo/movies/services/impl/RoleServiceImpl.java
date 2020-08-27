package com.applaudo.movies.services.impl;

import com.applaudo.movies.domain.Role;
import com.applaudo.movies.repositories.RoleRepository;
import com.applaudo.movies.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role add(Role obj) {
        return roleRepository.save(obj);
    }

    @Override
    public Role modify(Long id, Role obj) {
        return roleRepository.save(obj);
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElseGet(Role::new);
    }

    @Override
    public boolean remove(Long id) {
        roleRepository.deleteById(id);
        return true;
    }
}
