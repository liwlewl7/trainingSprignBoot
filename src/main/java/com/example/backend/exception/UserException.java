package com.example.backend.exception;

public class UserException extends BaseException{
    public UserException(String code) {
        super("user "+code);
    }
    public static UserException requestNull(){
        return new UserException("register.request.null");
    }
    public static UserException emailNull(){
        return new UserException("register.email.null");
    }

    //CREATE
    public static UserException createEmailNull(){
        return new UserException("create.email.null");
    }
    public static UserException createEmailDuplicated(){
        return new UserException("create.email.dupelicated");
    }
    public static UserException createPasswordNull(){
        return new UserException("create.password.null");
    }
    public static UserException createNameNull(){
        return new UserException("create.name.null");
    }
    // Update
    public static UserException updateNameNotfound(){
        return new UserException("update.name.notfound");
    }

    //LOGIN
    public static UserException loginfailEmailNotFound(){
        return new UserException("login.fail");
    }
    public static UserException loginfailPasswordIncorrect(){
        return new UserException("login.fail");
    }


}
