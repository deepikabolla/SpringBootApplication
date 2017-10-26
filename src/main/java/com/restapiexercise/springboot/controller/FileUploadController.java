/**
 * 
 */
package com.restapiexercise.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restapiexercise.springboot.service.FileUploadService;


/**
 * @author deepikabolla
 *
 */
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

	public static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam ("file") MultipartFile file) {
		
		fileUploadService.store(file);
        return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
