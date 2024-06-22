package com.example.foundationMail.Service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.foundationMail.Model.UserInfo;
import com.example.foundationMail.Model.UserInfoDetails;
import com.example.foundationMail.Model.Repository.UserInfoRepository; 
@Service
public class UserInfoService implements UserDetailsService { 
 private final UserInfoRepository repository; 
    private final PasswordEncoder encoder;
    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
  Optional<UserInfo> userDetail = repository.findByName(username); 
  // Converting userDetail to UserDetails 
  return userDetail.map(UserInfoDetails::new) 
    .orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
 } 
 public String addUser(UserInfo userInfo) { 
  userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
  repository.save(userInfo); 
  return "User Added Successfully"; 
 } 

}

