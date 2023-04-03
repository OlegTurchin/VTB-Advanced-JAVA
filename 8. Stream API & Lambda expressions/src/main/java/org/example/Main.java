package org.example;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {
        String[] words = new String[]{"infinite", "likely", "food", "lock", "soak", "recover", "sand", "side", "heaven",
                "wake", "precision", "school", "explicit", "precision", "lock", "cultural", "rotation", "cover", "lock",
                "precision", "my", "walk", "church", "precision", "guest"};
        Arrays.stream(words).collect(groupingBy(x -> x, counting()))
                .entrySet().stream()
                .sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue()))
                .limit(1)
                .forEach(System.out::println);

        Employee[] employees = new Employee[]{new Employee("Albert", 25, 54_000), new Employee("Stevie", 45, 138_000),
                new Employee("Neil", 56, 265_000), new Employee("Stevie", 34, 84_000),
                new Employee("Barry", 22, 40_000), new Employee("Philip", 37, 94_000)};

        Arrays.stream(employees)
                .sorted((o1, o2) -> o2.salary - o1.salary)
                .limit(1)
                .forEach(System.out::println);
        OptionalDouble avgSalary = Arrays.stream(employees)
                .mapToInt(Employee::getSalary)
                .average();
        System.out.println("Average salary at the facility is " + (int)(avgSalary.getAsDouble()) + " $");

        System.out.println(Arrays.stream(employees)
                .sorted((o1, o2) -> o2.age - o1.age)
                .limit(2).
                map(Employee::getName)
                .collect(Collectors.joining(" and ", "Two oldest employees' names are ", ".")));
    }
}

class Employee {
    String name;
    int age;
    int salary;

    int getSalary() {
        return salary;
    }

    String getName() {
        return name;
    }

    Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString(){
        return name + "'s age is " + age + " and salary is " + salary + " $";
    }
}
