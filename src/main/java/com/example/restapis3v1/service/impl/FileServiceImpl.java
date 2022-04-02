package com.example.restapis3v1.service.impl;

import com.example.restapis3v1.model.File;
import com.example.restapis3v1.repository.FileRepository;
import com.example.restapis3v1.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File findByName(String name) {
        File file = fileRepository.findByName(name);

        log.info("IN findByName - file: {} found by name: {}", file, name);
        return file;
    }
}
