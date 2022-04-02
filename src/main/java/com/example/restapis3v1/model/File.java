package com.example.restapis3v1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
public class File extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User users;



}