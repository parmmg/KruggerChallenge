package com.krugger.challenge.entity;

import com.krugger.challenge.enumerator.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "employees")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "dni")
@Builder
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Required DNI")
    @Size(min=10, max=10, message = "DNI Not Valid. Must have at least 10 digits")
    @Pattern(regexp = "^[0-9]+$", message = "DNI Not Valid. Must contain only numeric digits")
    @Column(unique = true)
    private String dni;

    @NotNull(message = "Required Name")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First Name must NOT contain numeric digits or special characters")
    private String firstName;

    @NotNull(message = "Apellido Requerido")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Last Name must NOT contain numeric digits or special characters")
    private String lastName;

    @Email(regexp = "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?",
            message = "Email Not Valid")
    private String mail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDate;

    private String address;

    private String phone;

    @Builder.Default
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<EmployeeVaccine> employeeVaccines = new HashSet<>();

}
