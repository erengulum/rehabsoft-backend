package com.hacettepe.rehabsoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uname", length = 100, unique = true)
    private String username;

    @Column(name = "pwd", length = 200)
    private String password;

    @Column(name = "firstname", length = 200)
    private String firstName;

    @Column(name = "surname", length = 200)
    private String surname;

    @Column(name = "email", length = 100,unique=true)
    private String email;

    @ManyToOne
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Role role;

    public User(Long id){
        this.id = id;
    }



}
