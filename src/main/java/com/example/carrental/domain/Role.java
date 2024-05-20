package com.example.carrental.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = Role.TABLE_NAME)
@SequenceGenerator(name = Role.SEQ_GEN, sequenceName = Role.SEQ_NAME, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    public static final String TABLE_NAME = "ROLES";
    public static final String SEQ_GEN = "ROLES_SEQ_GEN";
    public static final String SEQ_NAME = "ROLES_SEQ";

    public static final String COL_ID = "ROLE_ID";
    public static final String COL_NAME = "NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GEN)
    @Column(name = COL_ID)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_NAME, unique = true)
    private RoleEnum name;


    @Override
    public String getAuthority() {
        return name.toString();
    }
}
