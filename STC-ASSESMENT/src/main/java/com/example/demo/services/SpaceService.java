package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.SpaceRequestDTO;
import com.example.demo.models.PermissionGroup;
import com.example.demo.models.Space;
import com.example.demo.repositories.PermissionGroupRepository;
import com.example.demo.repositories.SpaceRepository;

@Service
public class SpaceService {
    @Autowired
    private SpaceRepository spaceRepository;
    
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    public Space createSpace(SpaceRequestDTO spaceRequest) {
        PermissionGroup permissionGroup = permissionGroupRepository.findByGroupName(spaceRequest.getPermissionGroupName());
        if (permissionGroup == null) {
            // Handle scenario where permission group doesn't exist
            // Throw an exception or handle accordingly
        }
        
        Space space = new Space();
        space.setName(spaceRequest.getName());
        space.setPermissionGroup(permissionGroup);

        return spaceRepository.save(space);
    }
    
    // Other methods for space-related operations (update, delete, etc.) can be added here
}