package com.example.backend.service;

import com.example.backend.Entity.Social;
import com.example.backend.Entity.User;
import com.example.backend.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository repository;


    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Social create(User user, String facebook, String line, String instragram, String tiktok) {
        //TODO: validate

        //create
        Social entity = new Social();

        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instragram);
        entity.setTiktok(tiktok);

        return repository.save(entity);
    }


}
