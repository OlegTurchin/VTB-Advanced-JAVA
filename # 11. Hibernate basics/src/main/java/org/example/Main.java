package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        String initialMessage = """
                    PLEASE, INSERT one of the commands below:\s
                1. /transaction                   -- and then 'FIRST OR LAST NAME OF PERSON' and 'NAME OF PRODUCT'
                2. /findPersonsByProductTitle     -- and then 'NAME OF PRODUCT'
                3. /showProductsByPerson          -- and then 'FIRST OR LAST NAME OF PERSON'
                4. /removePerson                  -- and then 'FIRST OR LAST NAME OF PERSON'
                5. /removeProduct                 -- and then 'NAME OF PRODUCT'
                6. /exit
                """;

        System.out.println(initialMessage);
        SessionFactory sessionFactory = getSessionFactory();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] separatedCommands = command.split(" ");
        Session session;
        Product product;
        Person person;

        switch (separatedCommands[0]) {
            case "/transaction" -> {
                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                person = (Person) session
                        .createQuery("FROM Person p WHERE p.name = :name")
                        .setParameter("name", separatedCommands[1])
                        .getSingleResult();
                product = (Product) session
                        .createQuery("FROM Product p WHERE p.name = :name")
                        .setParameter("name", separatedCommands[2])
                        .getSingleResult();
                Transaction.Transaction_Key transaction_key = new Transaction.Transaction_Key();
                transaction_key.setPersonId(person.getId());
                transaction_key.setProductId(product.getId());
                Transaction transaction = new Transaction();
                transaction.setTransaction_key(transaction_key);
                transaction.setCurrentPrice(product.getPrice());
                session.save(transaction);
                session.getTransaction().commit();
                System.out.println("The following transaction has been saved: " + transaction);
                System.out.println("Enter new command");
            }
            case "/findPersonsByProductTitle" -> {
                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                product = (Product) session
                        .createQuery("FROM Product p WHERE p.name = :name")
                        .setParameter("name", separatedCommands[1])
                        .getSingleResult();
                Set<Person> personSet = product.getCorrespondingPersonsSet();
                personSet.forEach(System.out::println);
                session.getTransaction().commit();
                System.out.println("Enter new command");
            }
            case "/showProductsByPerson" -> {
                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                person = (Person) session
                        .createQuery("FROM Person p WHERE p.name = :name")
                        .setParameter("name", separatedCommands[1])
                        .getSingleResult();
                Set<Product> productSet = person.getPurchasedProductsSet();
                productSet.forEach(System.out::println);
                session.getTransaction().commit();
                System.out.println("Enter new command");
            }
            case "/removePerson" -> {
                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                person = (Person) session
                        .createQuery("FROM Person p WHERE p.name = :name")
                        .setParameter("name", separatedCommands[1])
                        .getSingleResult();
                session.delete(person);
                System.out.println("Product " + person + " deleted.");
                session.getTransaction().commit();
                System.out.println("Enter new command");
            }
            case "/removeProduct" -> {
                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                product = (Product) session
                        .createQuery("FROM Product p WHERE p.name = :name")
                        .setParameter("name", separatedCommands[1])
                        .getSingleResult();
                session.delete(product);
                System.out.println("Product " + product + " deleted.");
                session.getTransaction().commit();
                System.out.println("Enter new command");
            }
            case "/exit" -> {
            }
        }
    }

    private static SessionFactory getSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Transaction.class)
                .buildSessionFactory();
    }
}
