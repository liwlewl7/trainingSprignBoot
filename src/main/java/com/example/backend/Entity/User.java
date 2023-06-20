package com.example.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "e_user")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 120)
    private String email;


    @Column(nullable = false, length = 80)
    private String password;

    @Column(nullable = false, length = 80)
    private String name;
}
