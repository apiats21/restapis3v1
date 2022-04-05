package com.example.restapis3v1.repository;

import com.example.restapis3v1.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByName(String name);

    void deleteByName(String name);
}
