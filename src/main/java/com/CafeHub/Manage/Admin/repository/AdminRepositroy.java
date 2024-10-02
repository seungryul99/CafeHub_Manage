package com.CafeHub.Manage.Admin.repository;

import com.CafeHub.Manage.Admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepositroy extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    Boolean existsByUsername(String username);

}
