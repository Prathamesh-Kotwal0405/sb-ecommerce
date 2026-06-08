package com.ecom.app.service;

import com.ecom.app.dto.AddressDTO;
import com.ecom.app.dto.UserRequest;
import com.ecom.app.dto.UserResponse;
import com.ecom.app.model.Address;
import com.ecom.app.model.User;
import com.ecom.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserResponse> fetchAllUsers(){

        return  userRepository.findAll().stream().map(this::mapToUserResponse).collect(Collectors.toList());
    }

    public void addUser( UserRequest userRequest){
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress()!=null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setZipCode(userRequest.getAddress().getZipCode());
            user.setAddress(address);
        }

    }

    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    public boolean updateUser(Long id, UserRequest updatedUserRequest){
        return userRepository.findById(id)
                .map(existingUser -> {updateUserFromRequest(existingUser, updatedUserRequest);
                                            userRepository.save(existingUser);
                                            return true;}).orElse(false);
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setUserRole(user.getUserRole());
        if(user.getAddress()!=null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setZipCode(user.getAddress().getZipCode());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;
    }

}
