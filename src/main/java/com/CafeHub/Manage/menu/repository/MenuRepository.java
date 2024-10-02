package com.CafeHub.Manage.menu.repository;

import com.CafeHub.Manage.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByCafeId(Long cafeId);
}
