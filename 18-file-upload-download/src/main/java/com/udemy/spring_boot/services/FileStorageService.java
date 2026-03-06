package com.udemy.spring_boot.services;

import com.udemy.spring_boot.config.FileStorageConfig;
import com.udemy.spring_boot.exception.FileNotFoundException;
import com.udemy.spring_boot.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            logger.info("Creating directories");
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where files will be stored!", e);
        }
    }

    public String storeFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            logger.error("Unable to save a null or empty file");
            throw new FileStorageException("Unable to save a null or empty file");
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                logger.error("File name contains an invalid path sequence {}", fileName);
                throw new FileStorageException("File name contains an invalid path sequence " + fileName);
            }

            logger.info("Saving file in disk");
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            logger.error("Could not store file {}", fileName);
            throw new FileStorageException("Could not store file " + fileName + ", please try again", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            }
            else {
                logger.error("File resource not found: {}", fileName);
                throw new FileNotFoundException("File not found: " + fileName);
            }
        } catch (Exception e) {
            logger.error("File not found: {}", fileName);
            throw new FileNotFoundException("File not found: " + fileName, e);
        }
    }
}
