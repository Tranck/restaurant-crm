package com.achernyadvki.restaurant.service;

import com.achernyadvki.restaurant.entity.Role;
import com.achernyadvki.restaurant.form.RoleForm;
import com.achernyadvki.restaurant.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role with id " + id + " does not exist"));
    }

    public void createRole(Model model, RoleForm roleForm) {
        Role role = Role.builder()
                .name(roleForm.getName())
                .build();
        roleRepository.save(role);
    }

    public RoleForm getRoleAsForm(Long id) {
        Role role = getRoleById(id);
        return RoleForm.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
