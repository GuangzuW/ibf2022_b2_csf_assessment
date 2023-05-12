
	package ibf2022.batch2.csf.backend.controllers;

	import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestPart;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
	
	@RestController
	public class UploadController {
	
		@Autowired
		private ImageRepository imageRepository;

		@Autowired
		private ArchiveRepository archiveRepository;
	
		@PostMapping("/upload")
		public ResponseEntity<String> uploadFiles(@RequestPart(value = "file") MultipartFile file) {
			try {
				imageRepository.upload(file);
				List<String> urls = imageRepository.getImageUrls();
				
				return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
				
			} catch (IOException e) {
				return new ResponseEntity<>("Error Uploading File", HttpStatus.BAD_REQUEST);
			}

			
		}
	
	

	// TODO: Task 2, Task 3, Task 4

	

	// TODO: Task 5
	

	// TODO: Task 6


}