package com.krugger.challenge.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "userName")
@ToString(of = "userName")
@Entity
@NoArgsConstructor
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User {

    @Id
    private UUID employeeId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Employee employee;

    @NotNull
    @Column(unique = true)
    private String userName;

    @NotNull
    @Size(min=8, message = "Password must contains at less 8 digits")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "rol_id")})
    private Set<Role> roles = new HashSet<>();

}
