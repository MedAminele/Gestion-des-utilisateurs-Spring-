package org.dsi.service;

import java.util.Arrays;
import java.util.Collection;

import org.dsi.entities.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

 
public class MyAdminDetails implements UserDetails {
 
    private Admin admin;
     
    public MyAdminDetails(Admin user) {
        this.admin = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(admin.getRole());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return admin.getPassword();
    }
 
    @Override
    public String getUsername() {
        return admin.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
}
