package com.example.carrental.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME)
@SequenceGenerator(name = User.SEQ_GEN, sequenceName = User.SEQ_NAME, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    public static final String TABLE_NAME = "USERS";
    public static final String SEQ_GEN = "USERS_SEQ_GEN";
    public static final String SEQ_NAME = "USERS_SEQ";

    public static final String COL_ID = "USER_ID";
    public static final String COL_FIRSTNAME = "FIRSTNAME";
    public static final String COL_LASTNAME = "LASTNAME";
    public static final String COL_USERNAME = "USERNAME";
    public static final String COL_PASSWORD = "PASSWORD";

    public static final String JT_USER_ROLES = "Users_roles";
    public static final String JT_USERS_ROLES_USER_ID = "USER_ID";
    public static final String JT_USERS_ROLES_ROLE_ID = "ROLE_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GEN)
    @Column(name = COL_ID)
    private Long id;

    @Column(name = COL_FIRSTNAME)
    private String firstname;

    @Column(name = COL_LASTNAME)
    private String lastname;

    @Column(name = COL_USERNAME)
    private String username;

    @Column(name = COL_PASSWORD)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = JT_USER_ROLES, joinColumns = @JoinColumn(name = JT_USERS_ROLES_USER_ID), inverseJoinColumns = @JoinColumn(name = JT_USERS_ROLES_ROLE_ID))
    private Set<Role> roles;

    @Transient
    private final String fullName = firstname + " " + lastname;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
