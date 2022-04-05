package com.example.restapis3v1.rest;

import com.example.restapis3v1.model.File;
import com.example.restapis3v1.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        fileService.upload(file);

        return "File uploaded";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        byte[] bytes = fileService.download(filename);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);
    }


    @GetMapping(value = "/findbyname/{filename}")
    public ResponseEntity<File> findByName(@PathVariable(name = "filename") String name) {
        File file = fileService.findByName(name);

        return new ResponseEntity<>(file, HttpStatus.OK);
    }


    @GetMapping(value = "/getall")
    public ResponseEntity<List<File>> getAll() {
        List<File> result = fileService.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{name}")
    public String delete(@PathVariable(name = "name") String name) {

        fileService.deleteByName(name);

        return "File was deleted";
    }
}