package org.example;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "persons")
class Person {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Transaction> transactionSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions",
            joinColumns = @JoinColumn(name = "person"),
            inverseJoinColumns = @JoinColumn(name = "product"))
    private Set<Product> purchasedProductsSet;

    @Override
    public String toString() {
        return (name);
    }
}
