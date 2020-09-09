package com.kira.employeespringboot.model;

import com.kira.employeespringboot.validation.PasswordConfirmed;
import com.kira.employeespringboot.validation.PasswordPolicy;
import com.kira.employeespringboot.validation.UniqueUserName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@PasswordConfirmed

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @UniqueUserName
    private String userName;
    @PasswordPolicy
    private String password;
    private String confirmedPassword;
    @Email
    private String email;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"),inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;
}
