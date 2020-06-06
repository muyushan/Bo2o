package com.sane.so2o.entity.ud;

import com.sane.so2o.entity.User;
import lombok.Data;

@Data
public class UserUD extends User {
    private String userPwdVerify;
    private String emailVerifyCode;

    public String getKey(){
        return "user::"+getUserEmail();
    }



}
