package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FolderRequestDTO;
import com.example.demo.models.Folder;
import com.example.demo.services.FolderService;

@RestController
@RequestMapping("/spaces/{spaceId}/folders")
public class FolderController {
    @Autowired
    private FolderService folderService;

    @PostMapping("/create")
    public ResponseEntity<?> createFolder(
        @PathVariable Long spaceId,
        @RequestBody FolderRequestDTO folderRequest,
        @RequestParam("userEmail") String userEmail
    ) {
        Folder createdFolder = folderService.createFolder(spaceId, folderRequest, userEmail);
        return ResponseEntity.ok(createdFolder);
    }
}