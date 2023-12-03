package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.FileRequestDTO;
import com.example.demo.models.File;
import com.example.demo.models.Folder;
import com.example.demo.models.Permission;
import com.example.demo.repositories.FileRepository;
import com.example.demo.repositories.FolderRepository;
import com.example.demo.repositories.PermissionsRepository;
@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    public File createFile(Long folderId, FileRequestDTO fileRequest, String userEmail) {
        // Fetch folder by ID from repository
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);
        if (optionalFolder.isEmpty()) {
            // Handle scenario where the folder doesn't exist
            // Throw an exception or handle accordingly
        }

        Folder folder = optionalFolder.get();

        // Check if the user has EDIT access to the folder
        Permission userPermissions = permissionsRepository.findByUserEmailAndGroup_Folder_Id(userEmail, folderId);
        if (userPermissions == null || !userPermissions.getPermissionLevel().equals("EDIT")) {
            // Handle scenario where the user doesn't have EDIT access to the folder
            // Throw an exception or handle accordingly
        }

        // Create a file under the folder
        File file = new File();
        file.setName(fileRequest.getName());
        // Set folder for the file
        file.setFolder(folder);
        // Save the file using fileRepository.save(file)
        return file;
    }

    // Other file-related methods can be added here
}
