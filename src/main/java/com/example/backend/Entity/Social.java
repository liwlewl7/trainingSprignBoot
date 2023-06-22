package com.example.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "e_social")
public class Social extends BaseEntity{
    @Column(nullable = false, length = 120)
    private String facebook;

    @Column(nullable = false, length = 120)
    private String line;

    @Column(nullable = false, length = 120)
    private String instagram;

    @Column(nullable = false, length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "e_user_id")
    private User user;
}
