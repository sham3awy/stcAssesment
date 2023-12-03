package com.example.demo.repositories;

import java.security.Permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Permission;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    Permission findByUserEmailAndGroup_Space_Id(String userEmail, Long spaceId);
    Permission findByUserEmailAndGroup_File_Id(String userEmail, Long fileId);
}