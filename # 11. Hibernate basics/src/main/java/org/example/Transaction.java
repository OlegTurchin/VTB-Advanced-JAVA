package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Data
    @Embeddable
    public static class Transaction_Key implements Serializable {

        @Column(name = "person")
        private int personId;

        @Column(name = "product")
        private int productId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Transaction_Key that = (Transaction_Key) o;
            return Objects.equals(this.getPersonId(), that.getPersonId()) && Objects.equals(this.getProductId(), that.getProductId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPersonId(), getProductId());
        }
    }

    @EmbeddedId
    Transaction_Key transaction_key;

    @Column(name = "price")
    private double currentPrice;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn (name = "person", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn (name = "product", insertable = false, updatable = false)
    private Product product;

    @Override
    public String toString(){
        return transaction_key + " of " + currentPrice + "$";
//        return (product.getName() + "has been purchased by " + person.getName() + " for the price of " + currentPrice + " $");
    }

}
