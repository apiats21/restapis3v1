package com.example.restapis3v1.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.restapis3v1.model.File;
import com.example.restapis3v1.model.Status;
import com.example.restapis3v1.model.User;
import com.example.restapis3v1.repository.FileRepository;
import com.example.restapis3v1.repository.UserRepository;
import com.example.restapis3v1.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final AmazonS3 s3;

    @Value("${bucketName}")
    private String bucketName;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, UserRepository userRepository, AmazonS3 s3) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.s3 = s3;
    }

    @Override
    public String upload(MultipartFile file) {

        saveFileToDb(file);

        String originalFileName = file.getOriginalFilename();

        try {
            java.io.File fileToS3 = convertMultipartToFile(file);
            PutObjectResult putObjectResult = s3.putObject(bucketName, originalFileName, fileToS3);
            return putObjectResult.getContentMd5();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveFileToDb(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        User user = getUser();

        File fileDb = new File();
        fileDb.setCreated(new Date());
        fileDb.setStatus(Status.ACTIVE);

        fileDb.setName(originalFileName);
        fileDb.setLocation(bucketName);
        fileDb.setUsers(user);
        fileRepository.save(fileDb);
    }

    @Override
    public byte[] download(String filename) {
        S3Object object = s3.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();

        try {
            return IOUtils.toByteArray(objectContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<File> getAll() {
        List<File> result = fileRepository.findAll();
        log.info("IN getAll - {} files found", result.size());
        return result;
    }

    @Override
    public void deleteByName(String filename) {

        fileRepository.deleteByName(filename);
        s3.deleteObject(bucketName, filename);

            log.info("IN delete - file with name {} was deleted", filename);

    }

    @Override
    public File findByName(String name) {
        File file = fileRepository.findByName(name);

        log.info("IN findByName - file: {} found by name: {}", file, name);
        return file;
    }

    private User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();

        return userRepository.findByUsername(username);
    }

    private java.io.File convertMultipartToFile(MultipartFile file) throws IOException {
        java.io.File convFile = new java.io.File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
