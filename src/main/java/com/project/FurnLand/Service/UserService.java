package com.project.FurnLand.Service;

import com.project.FurnLand.Entity.User;
import com.project.FurnLand.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> getAllUserByRoleId(Long id){
        List<User> users = userRepository.getUserByRole(id);
        ResponseEntity response = ResponseEntity.ok(users);
        return response;
    }
}
