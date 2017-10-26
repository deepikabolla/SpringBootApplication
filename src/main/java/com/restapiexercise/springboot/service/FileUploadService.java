/**
 * 
 */
package com.restapiexercise.springboot.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author deepikabolla
 *
 */
public interface FileUploadService {

	void init();

    void store(MultipartFile file);

    void deleteAll();
}
