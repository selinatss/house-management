package com.backend.housemanagement.dao;

import com.backend.housemanagement.model.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {
}
