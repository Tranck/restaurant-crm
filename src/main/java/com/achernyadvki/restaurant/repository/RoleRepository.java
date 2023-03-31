package com.achernyadvki.restaurant.repository;

import com.achernyadvki.restaurant.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
