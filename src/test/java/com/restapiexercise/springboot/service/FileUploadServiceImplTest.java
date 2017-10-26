/**
 * 
 */
package com.restapiexercise.springboot.service;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.restapiexercise.springboot.service.FileUploadException;
import com.restapiexercise.springboot.service.FileUploadProperties;
import com.restapiexercise.springboot.service.FileUploadServiceImpl;

/**
 * @author deepikabolla
 *
 */
public class FileUploadServiceImplTest {

	 private FileUploadProperties properties = new FileUploadProperties();
	 private FileUploadServiceImpl fileUploadService;
	 
	 @Before
	    public void init() {
	        properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
	        fileUploadService = new FileUploadServiceImpl(properties);
	        fileUploadService.init();
	    }

	 @Test
	    public void upload() {
	        fileUploadService.store(new MockMultipartFile("TestFile", "TestFile.txt", MediaType.TEXT_PLAIN_VALUE,
	                "This is a test file".getBytes()));
	    }

	    @Test(expected = FileUploadException.class)
	    public void uploadNotAllowed() {
	        fileUploadService.store(new MockMultipartFile("TestFile", "../TestFile.txt",
	                MediaType.TEXT_PLAIN_VALUE, "This is a test file".getBytes()));
	    }

	    @Test
	    public void uploadAllowed() {
	        fileUploadService.store(new MockMultipartFile("TestFile", "upload/../TestFile.txt",
	                MediaType.TEXT_PLAIN_VALUE, "This is a test file".getBytes()));
	    }

}
