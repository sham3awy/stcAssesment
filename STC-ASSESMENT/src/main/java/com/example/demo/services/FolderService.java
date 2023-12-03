package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.FolderRequestDTO;
import com.example.demo.models.Folder;
import com.example.demo.models.Permission;
import com.example.demo.models.Space;
import com.example.demo.repositories.FolderRepository;
import com.example.demo.repositories.PermissionsRepository;
import com.example.demo.repositories.SpaceRepository;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    public Folder createFolder(Long spaceId, FolderRequestDTO folderRequest, String userEmail) {
        // Fetch space by ID from repository
        Optional<Space> optionalSpace = spaceRepository.findById(spaceId);
        if (optionalSpace.isEmpty()) {
            // Handle scenario where space doesn't exist
            // Throw an exception or handle accordingly
        }

        Space space = optionalSpace.get();

        // Check if the user has EDIT access to the space
        Permission userPermissions = permissionsRepository.findByUserEmailAndGroup_Space_Id(userEmail, spaceId);
        if (userPermissions == null || !userPermissions.getPermissionLevel().equals("EDIT")) {
            // Handle scenario where the user doesn't have EDIT access to the space
            // Throw an exception or handle accordingly
        }

        // Create a folder under the space
        Folder folder = new Folder();
        folder.setName(folderRequest.getName());
        // Set space for the folder
        folder.setSpace(space);
        // Save the folder using folderRepository.save(folder)
        return folder;
    }

    // Other folder-related methods can be added here
}