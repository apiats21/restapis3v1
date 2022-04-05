package com.example.restapis3v1.service;

import com.example.restapis3v1.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    File findByName(String name);

    String upload(MultipartFile file);

    byte[] download(String filename);

    List<File> getAll();

    void deleteByName(String name);




}
