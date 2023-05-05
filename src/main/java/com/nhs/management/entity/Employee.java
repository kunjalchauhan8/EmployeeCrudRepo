package com.nhs.management.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EMPLOYEE_GEN")
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId", nullable = true)
    private List<Skill> skills;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
