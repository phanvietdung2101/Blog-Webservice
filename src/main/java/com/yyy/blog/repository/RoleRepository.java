package com.yyy.blog.repository;

import com.yyy.blog.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    Optional<Role> findRoleByName(String name);
}
