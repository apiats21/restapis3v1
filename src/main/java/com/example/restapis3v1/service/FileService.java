package com.example.restapis3v1.service;

import com.example.restapis3v1.model.File;

import java.util.List;

public interface FileService {

    File findByName(String name);

    File upload(File file);

    void download(Long id);

    List<File> getAll();

    void delete(Long id);



}
