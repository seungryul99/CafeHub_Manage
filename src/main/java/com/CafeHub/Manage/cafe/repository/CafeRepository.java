package com.CafeHub.Manage.cafe.repository;

import com.CafeHub.Manage.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
