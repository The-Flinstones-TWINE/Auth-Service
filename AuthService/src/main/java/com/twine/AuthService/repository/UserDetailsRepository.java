package com.twine.AuthService.repository;

import com.twine.AuthService.entities.UserDetails;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserDetailsRepository extends CassandraRepository<UserDetails, String> {
    @AllowFiltering
    UserDetails findByUseremail (String email);
//
//    @AllowFiltering
//    UserDetails findByUseremailPassword (String email, String password);
}
