package com.example.restapis3v1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity
//@Table(name = "users")
//@Data()
//public class User extends BaseEntity {
//
//    @Column(name = "username")
//    private String username;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "password")
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private Status status;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
//    @JsonManagedReference
//    private List<File> files;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
//    private List<Role> roles;
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
//}

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password")
    private String password;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='"+ '\'' +
                ", email='"+ '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles + " files " +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonManagedReference
    private List<File> files;
}