package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Permission;
import com.example.demo.models.PermissionGroup;
import com.example.demo.models.Space;
import com.example.demo.repositories.PermissionGroupRepository;
import com.example.demo.repositories.PermissionsRepository;
import com.example.demo.repositories.SpaceRepository;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
	@Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    public Space createSpaceWithPermissions() {
        // Check if the space already exists
        Space existingSpace = spaceRepository.findByName("stc-assessments");
        if (existingSpace != null) {
        	throw new Exception("Space already exist");
        }

        // Create a new space
        Space space = new Space();
        space.setName("stc-assessments");
        space = spaceRepository.save(space);

        // Create a permission group for admin
        PermissionGroup adminGroup = new PermissionGroup();
        adminGroup.setGroupName("admin");
        adminGroup = permissionGroupRepository.save(adminGroup);

        // Assign VIEW access to one user
        Permission viewPermissions = new Permission();
        viewPermissions.setUserEmail("user1@example.com");
        viewPermissions.setPermissionLevel("VIEW");
        viewPermissions.setGroup(adminGroup);
        permissionsRepository.save(viewPermissions);

        // Assign EDIT access to another user
        Permission editPermissions = new Permission();
        editPermissions.setUserEmail("user2@example.com");
        editPermissions.setPermissionLevel("EDIT");
        editPermissions.setGroup(adminGroup);
        permissionsRepository.save(editPermissions);

        return space;
    }
}
