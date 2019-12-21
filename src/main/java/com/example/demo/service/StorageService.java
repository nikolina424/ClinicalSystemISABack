package com.example.demo.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    private final Path rootLocation = Paths.get("C:\\Users\\Stefan\\project_clinic\\ClinicalSystemISAFront\\public\\images");
    private String randomName;

    public String store(MultipartFile file) {
        try {
            randomName = RandomString.make(16);
            Files.copy(file.getInputStream(), this.rootLocation.resolve(randomName + file.getOriginalFilename()));
            return randomName + file.getOriginalFilename();
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
