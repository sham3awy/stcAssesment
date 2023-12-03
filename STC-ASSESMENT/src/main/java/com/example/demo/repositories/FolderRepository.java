package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    // Add custom query methods if needed
}