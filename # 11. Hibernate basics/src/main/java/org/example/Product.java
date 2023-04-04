package org.example;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
class Product {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Transaction> transactionSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions",
            joinColumns = @JoinColumn(name = "product"),
            inverseJoinColumns = @JoinColumn(name = "person"))
    private Set<Person> correspondingPersonsSet;

    @Override
    public String toString() {
        return (name + " of " + price);
    }
}
