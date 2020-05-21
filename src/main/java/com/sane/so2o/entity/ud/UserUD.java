package com.sane.so2o.entity.ud;

import com.sane.so2o.entity.User;
import lombok.Data;

@Data
public class UserUD extends User {
    private String user_pwd_verify;
    private String email_verify_code;

    public String getKey(){
        return "user::"+getUser_email();
    }



}
