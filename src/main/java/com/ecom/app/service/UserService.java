package com.ecom.app.service;

import com.ecom.app.model.User;
import com.ecom.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void addUser( User user){
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User updatedUser){
        return userRepository.findById(id)
                .map(existingUser -> {existingUser.setFirstName(updatedUser.getFirstName());
                                            existingUser.setLastName(updatedUser.getLastName());
                                            userRepository.save(existingUser);
                                            return true;}).orElse(false);
    }

    //continue from 1.26
}
