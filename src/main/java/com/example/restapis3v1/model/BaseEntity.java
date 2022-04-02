package com.example.restapis3v1.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
