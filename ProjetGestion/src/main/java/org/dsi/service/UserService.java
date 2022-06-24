package org.dsi.service;


import java.util.List;

import org.dsi.dao.Repo.UserRepository;

import org.dsi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	@Autowired
    private UserRepository userRepo;
 
 /*
  * TODO: Get the List of Shops
  */
 public List<User> getAllUsers(){
	 List<User> list =  (List<User>)userRepo.findAll();
  return list;
 }
 
 /*
  * TODO: Get Shop By keyword
  */
 public List<User> getByKeyword(String keyword){
  return userRepo.findByKeyword(keyword);
 }
}