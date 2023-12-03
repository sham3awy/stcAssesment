package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FileRequestDTO;
import com.example.demo.models.File;
import com.example.demo.models.Permission;
import com.example.demo.repositories.FileRepository;
import com.example.demo.repositories.PermissionsRepository;
import com.example.demo.services.FileService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/spaces/{spaceId}/folders/{folderId}/files")
public class FileController {
	@Autowired
	private FileService fileService;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private PermissionsRepository permissionsRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public File viewFileMetadata(Long fileId, String userEmail) throws Exception {
		// Check if the user has access to the file
		Permission userPermissions = permissionsRepository.findByUserEmailAndGroup_File_Id(userEmail, fileId);
		if (userPermissions == null) {
			throw new Exception("Invalid Creditntails");
		}

		// Fetch file metadata using native SQL query
		String nativeQuery = "SELECT * FROM files WHERE id = :fileId";
		Query query = (Query) entityManager.createNativeQuery(nativeQuery, File.class);
		((jakarta.persistence.Query) query).setParameter("fileId", fileId);

		// Execute the query
		File file = (File) ((jakarta.persistence.Query) query).getSingleResult();
		return file;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createFile(@PathVariable Long folderId, @RequestBody FileRequestDTO fileRequest,
			@RequestParam("userEmail") String userEmail) {
		File createdFile = fileService.createFile(folderId, fileRequest, userEmail);
		return ResponseEntity.ok(createdFile);
	}

	public ResponseEntity<ByteArrayResource> downloadFile(Long fileId, String userEmail) throws Exception{
		// Check if the user has access to the file
		Permission userPermissions = permissionsRepository.findByUserEmailAndGroup_File_Id(userEmail, fileId);
		if (userPermissions == null) {
			throw new Exception("Invalid Creditntails");
		}

		// Fetch the file from the database
		Optional<File> optionalFile = fileRepository.findById(fileId);
		if (optionalFile.isEmpty()) {
			// Handle scenario where the file doesn't exist
			// Throw an exception or handle accordingly
		}

		File file = optionalFile.get();

		// Convert the file content to a ByteArrayResource (or use other
		// implementations)
		ByteArrayResource resource = new ByteArrayResource(file.getBinary()); // Assuming file.getData() returns byte[]

		// Create a ResponseEntity with the resource for file download
		return ResponseEntity.ok().contentLength((Long.parseLong(file.getBinary().length+"")))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(resource);
	}
}