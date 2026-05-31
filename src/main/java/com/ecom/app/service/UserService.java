package com.ecom.app.service;

import com.ecom.app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();
    int count = 0;

    public List<User> fetchAllUsers(){
        return userList;
    }

    public void addUser( User user){
        count+=1;
        user.setId(count);
        userList.add(user);
    }

    //continue from 0.40
}
