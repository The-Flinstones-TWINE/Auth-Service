package com.twine.AuthService.repository;


import com.twine.AuthService.entities.GoogleUserDetails;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleUserRepository extends CassandraRepository<GoogleUserDetails, String> {

}

