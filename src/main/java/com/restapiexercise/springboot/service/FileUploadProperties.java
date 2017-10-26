/**
 * 
 */
package com.restapiexercise.springboot.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author deepikabolla
 *
 */
@ConfigurationProperties("storage")
public class FileUploadProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
