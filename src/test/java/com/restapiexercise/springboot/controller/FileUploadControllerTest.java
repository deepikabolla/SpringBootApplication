/**
 * 
 */
package com.restapiexercise.springboot.controller;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.restapiexercise.springboot.service.FileUploadServiceImpl;

/**
 * @author deepikabolla
 *
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadControllerTest {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	private FileUploadServiceImpl fileUploadService;
	
	@Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "TestFile.txt",
                "text/plain", "Test File".getBytes());
        this.mvc.perform(fileUpload("/api/file/upload").file(multipartFile))
                .andExpect(status().isOk());

        then(this.fileUploadService).should().store(multipartFile);
    }

}
