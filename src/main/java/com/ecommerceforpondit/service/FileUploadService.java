package com.ecommerceforpondit.ecommerceforpondit.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Component
public class FileUploadService {

    public void uploadFile(MultipartFile file, String path) throws IOException {
        File file1 = Files.createFile(Path.of(path)).toFile();
        FileOutputStream fos = new FileOutputStream(file1);
        fos.write(file.getBytes());
    }
}
