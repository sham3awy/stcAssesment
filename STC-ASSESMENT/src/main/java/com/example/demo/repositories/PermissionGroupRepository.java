package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.PermissionGroup;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    PermissionGroup findByGroupName(String groupName);
    // Add other custom query methods if required
}