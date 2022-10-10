package com.krugger.challenge.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="roles")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "name")
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique=true)
    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_permissions",
            joinColumns = {@JoinColumn(name = "rol_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    @Builder.Default
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "rol_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    @Builder.Default
    private Boolean active = true;

}
