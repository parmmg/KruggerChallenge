package com.krugger.challenge.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@NoArgsConstructor
@Table(name ="vaccines")
@Builder
@AllArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    private String description;

    @OneToMany(mappedBy = "vaccine", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<EmployeeVaccine> employeeVaccines = new HashSet<>();

    @Builder.Default
    private Boolean active = true;

}
