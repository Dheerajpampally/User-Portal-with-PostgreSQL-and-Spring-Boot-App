package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data; // Import Lombok's @Data annotation

@Entity
@Table(name = "user_registration")
@Data // Use Lombok's @Data annotation to generate getters and setters
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phonenumber;
    private Date dateofbirth;
}
