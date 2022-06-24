package org.dsi.service;

import org.dsi.dao.Repo.AdminRepository;
import org.dsi.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


 
public class AdminDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AdminRepository adminRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Admin admin = adminRepository.getUserByUsername(username);
         
        if (admin == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyAdminDetails(admin);
    }
 
}