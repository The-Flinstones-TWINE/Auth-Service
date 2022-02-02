package com.twine.AuthService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;

    @Data
    @Table("google_user_credentials")
    @AllArgsConstructor
    @NoArgsConstructor
    public class GoogleUserDetails {

    @PrimaryKey
    private String userEmailId;
    @NotNull
    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @NotNull
    @Column("account_type")
    private String accounType;
}