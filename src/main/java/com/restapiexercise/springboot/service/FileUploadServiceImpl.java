/**
 * 
 */
package com.restapiexercise.springboot.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author deepikabolla
 *
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Path rootLocation;
    

    @Autowired
    public FileUploadServiceImpl(FileUploadProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Long fileSize = file.getSize();
        String contentType = file.getContentType();
        BufferedWriter bw = null;
		FileWriter fw = null;
		FileOutputStream fos = null;
      
        try {
            if (file.isEmpty()) {
                throw new FileUploadException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new FileUploadException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try{
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            File normalFile = new File(file.getOriginalFilename());
            normalFile.createNewFile();
            String metadataInfo="File Name: " + filename +", "+"File Size: "+ fileSize + ", "+"Content Type: "+contentType;
            fw = new FileWriter(normalFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			fos = new FileOutputStream(normalFile);
			fos.write(file.getBytes());
			bw.write(metadataInfo);
            /*String metadataInfo = "File Name: " + filename +", "+"File Size: "+ fileSize + ", "+"Content Type: "+contentType;
			String FILELOCATION = "Metadatafile.txt";
			fw = new FileWriter(FILELOCATION, true);
			bw = new BufferedWriter(fw);
			bw.write(metadataInfo);*/
            }finally{
            	if(bw!=null)
            		bw.close();
            	if(fw!=null)
            		fw.close();
            	if(fos!=null)
            		fos.close();
            }
        }
        catch (IOException e) {
            throw new FileUploadException("Failed to store file " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new FileUploadException("Could not initialize storage", e);
        }
    }
}
