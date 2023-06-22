package com.example.backend.mapper;

import com.example.backend.Entity.User;
import com.example.backend.model.MRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-22T16:19:53+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public MRegisterResponse toRegisterResponse(User user) {
        if ( user == null ) {
            return null;
        }

        MRegisterResponse mRegisterResponse = new MRegisterResponse();

        mRegisterResponse.setEmail( user.getEmail() );
        mRegisterResponse.setName( user.getName() );

        return mRegisterResponse;
    }
}
