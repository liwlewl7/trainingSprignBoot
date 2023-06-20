package com.example.backend.mapper;

import com.example.backend.Entity.User;
import com.example.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}
