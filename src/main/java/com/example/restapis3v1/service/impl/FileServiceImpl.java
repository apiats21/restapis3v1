package com.example.restapis3v1.service.impl;

import com.example.restapis3v1.model.File;
import com.example.restapis3v1.repository.FileRepository;
import com.example.restapis3v1.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File upload(File file) {
        return null;
    }

    @Override
    public void download(Long id) {

    }

    @Override
    public List<File> getAll() {
        List<File> result = fileRepository.findAll();
        log.info("IN getAll - {} files found", result.size());
        return result;
    }

    @Override
    public void delete(Long id) {
        fileRepository.deleteById(id);
        log.info("IN delete - file with id {} was deleted", id);

    }

    @Override
    public File findByName(String name) {
        File file = fileRepository.findByName(name);

        log.info("IN findByName - file: {} found by name: {}", file, name);
        return file;
    }
}
