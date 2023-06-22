package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "e_address")
public class Address extends BaseEntity{

    @Column(nullable = false, length = 120)
    private String line1;

    @Column(nullable = false, length = 120)
    private String line2;

    @Column(nullable = false, length = 120)
    private String zipcode;


    @ManyToOne
    @JoinColumn(name = "e_user_id",nullable = false)
    private User user;
}
