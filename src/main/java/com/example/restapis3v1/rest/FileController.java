package com.example.restapis3v1.rest;

import com.example.restapis3v1.model.File;
import com.example.restapis3v1.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/{filename}")
    public ResponseEntity<File> findByName(@PathVariable(name = "filename") String name) {
        File file = fileService.findByName(name);

        return new ResponseEntity<>(file, HttpStatus.OK);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<File>> getAll() {
        List<File> result = fileService.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        fileService.delete(id);
        return "File was deleted";
    }



}
