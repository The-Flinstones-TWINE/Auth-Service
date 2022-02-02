package com.twine.AuthService.service;

import com.twine.AuthService.entities.UserDetails;
import com.twine.AuthService.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsService {
    final UserDetailsRepository userDetailsRepo;
    private UserDetails userDetails= new UserDetails();

    //----------------------------Authenticate by Email-----------------------------------------------
    public Boolean authenticatedUserByEmail(String email, String password){

        Boolean aunthenticated = false;
        userDetails = userDetailsRepo.findByUseremail(email);
        if(userDetails.getUseremail().equals(email) && userDetails.getPassword().equals(password)){
            aunthenticated = true;
        }
        else aunthenticated = false;

        return aunthenticated;
    }

    //----------------------------Create user-----------------------------------------------------
    public UserDetails createUser(UserDetails user){

        return userDetailsRepo.save(user);
    }

    //----------------------------Create users------------------------------------------------
    public List<UserDetails> createUsers(List<UserDetails> users){
        return userDetailsRepo.saveAll(users);
    }

    //----------------------------Get by Email--------------------------------------------------------
    public UserDetails getUserByEmail(String email){

        return userDetailsRepo.findByUseremail(email);
    }

    //----------------------------Get all users--------------------------------------------------------
    public List<UserDetails> getAllUsers(){

        return userDetailsRepo.findAll();
    }

    //------------------------------Update User By Email ------------------------------------------------
    public UserDetails updateUserByEmail(String email, UserDetails user) {

        /*Optional<UserDetailsEntity> optionalUser = userDetailsRepo.findById(id);
        if (!optionalUserDetailsEntity.isPresent())
            throw new UserDetailsEntityNotFoundException("UserDetailsEntity Record with id " + id + " is not available");
        player.setPlayerId(id);*/
        user.setUseremail(email);
        return userDetailsRepo.save(user);
    }

    //-------------------------------Delete User By Email----------------------------------------------
    public void deleteUserByEmail(String email) {

        /*Optional<UserDetailsEntity> optionalUser = userDetailsRepo.findById(id);
        if (!optionalUser.isPresent())
            throw new UserNotFoundException("User Record with id " + id + " is not available");*/
        userDetailsRepo.deleteById(email);
    }
}
