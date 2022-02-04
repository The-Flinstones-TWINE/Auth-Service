package com.twine.AuthService.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table("user_details")
@Data
public class UserDetails {

    @PrimaryKey
    private String useremail;

    @NotNull
    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @NotNull
    @Column("account_type")
    private String accountType;

    @NotNull
    private String password;

}