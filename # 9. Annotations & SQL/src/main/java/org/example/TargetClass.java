package org.example;

import lombok.*;

@Table(title = "TargetClass")
@AllArgsConstructor @Getter
public class TargetClass {
    @Column
    String name;

    @Column
    int age;

    @Column
    int salary;

    @Column
    boolean isCollegeGraduated;

}
