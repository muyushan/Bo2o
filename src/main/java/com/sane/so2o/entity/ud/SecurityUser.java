package com.sane.so2o.entity.ud;

import com.sane.so2o.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String power=user.getUserPower();
        if(StringUtils.isNotEmpty(power)){
            List<String> powers= Arrays.asList(power.split(";"));
            powers.forEach(p->{
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(power);
                grantedAuthorityList.add(grantedAuthority);
            });
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getUserPwd();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getUserLock().intValue()==0;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getUserFreeze().intValue()==0;
    }

    public User getDetail(){
        return user;
    }
}
