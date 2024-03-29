package com.twine.AuthService.service;

import com.twine.AuthService.entities.UserDetails;
import com.twine.AuthService.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserDetailsService {
    final UserDetailsRepository userDetailsRepo;
    private UserDetails userDetails= new UserDetails();

    //----------------------------Authenticate by Email-----------------------------------------------
    public UserDetails authenticatedUserByEmail(String email, String password){

        userDetails = userDetailsRepo.findByUseremail(email);
        if(userDetails.getUseremail().equals(email) && userDetails.getPassword().equals(password)){
            return userDetails;
        }
        throw new RuntimeException();

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

    //bookmarkBusiness
    public UserDetails bookmarkBusiness(String email, String businessId){
        UserDetails userDetail = getUserByEmail(email);
        Set<String> bookmarks ;
        bookmarks = userDetail.getBookmarkedBusinesses();
        if(bookmarks==null) bookmarks = new HashSet<>() ;
        bookmarks.add(businessId);
        userDetail.setBookmarkedBusinesses(bookmarks);
        return userDetailsRepo.save(userDetail);
    }

    //bookmarkItem
    public UserDetails bookmarkProducts(String email, String id){
        UserDetails userDetail = getUserByEmail(email);
        Set<String> bookmarks ;
        bookmarks = userDetail.getBookmarkedItems();
        if(bookmarks==null) bookmarks = new HashSet<>() ;
        bookmarks.add(id);
        userDetail.setBookmarkedItems(bookmarks);
        return userDetailsRepo.save(userDetail);
    }

    //isBookmarked
    public Boolean isBusinessBookmarked(String email, String id){
        UserDetails userDetail = getUserByEmail(email);
        if(!userDetail.getBookmarkedBusinesses().isEmpty() && userDetail.getBookmarkedBusinesses().contains(id) ) return true;
        return false;
    }
    //isBookmarked
    public Boolean isProductBookmarked(String email, String id){
        UserDetails userDetail = getUserByEmail(email);
        if(!userDetail.getBookmarkedItems().isEmpty() && userDetail.getBookmarkedItems().contains(id) ) return true;
        return false;
    }

    //Get all businesses bookmarked by email id
    public Set<String> getAllBookmarkedBusiness(String email){
        Set<String> bookmarkedBusiness;
        UserDetails userDetail = getUserByEmail(email);

        bookmarkedBusiness = userDetail.getBookmarkedBusinesses();

        return bookmarkedBusiness;
    }
}
