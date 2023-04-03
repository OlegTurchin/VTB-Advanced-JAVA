package org.example;

import lombok.Data;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class Main {

    static private final int THREADS_COUNT = 8;
    static Thread[] threads = new Thread[THREADS_COUNT];
    static CountDownLatch countDownLatchOuter = new CountDownLatch(THREADS_COUNT + 1);

    public static void main(String[] args) throws InterruptedException {
        initialPreparations();
        long startTime = System.currentTimeMillis();
//        optimisticRandomIncrementing();
        pessimisticRandomIncrementing();
        countDownLatchOuter.countDown();
        countDownLatchOuter.await();
        long finishTime = System.currentTimeMillis();
        System.out.println("Multithreading random incrementation take " + (finishTime - startTime) + "ms.");
        checkFinalResult();
    }

    private static void optimisticRandomIncrementing() {
        CountDownLatch countDownLatchInner = new CountDownLatch(THREADS_COUNT);
        try (SessionFactory sessionFactory = getSessionFactory()) {
            for (int i = 0; i < THREADS_COUNT; i++) {
                final int finalInt = i;
                threads[i] = new Thread(() -> {
                    System.out.println("Thread # " + finalInt + " started.");
                    for (int j = 0; j < 10_000; j++) {
                        boolean isUpdated = false;
                        int randomCelId = (int) (Math.random() * 40) + 1;
                        while (!isUpdated) {
                            Session session = sessionFactory.getCurrentSession();
                            session.beginTransaction();
                            Item item = session.get(Item.class, randomCelId, LockMode.OPTIMISTIC);
                            item.setValue(item.getValue() + 1);
                            uncheckedSleep(5);
                            try {
                                session.getTransaction().commit();
                                isUpdated = true;
                            } catch (OptimisticLockException optExc) {
                                session.getTransaction().rollback();
                            }
                            session.close();
                        }
                    }
                    countDownLatchInner.countDown();
                });
                threads[i].start();
                countDownLatchOuter.countDown();
            }
            countDownLatchInner.await();
        } catch (InterruptedException intExc) {
            intExc.printStackTrace();
            intExc.getMessage();
        }
    }

    private static void pessimisticRandomIncrementing() {
        CountDownLatch countDownLatchInner = new CountDownLatch(THREADS_COUNT);
        try (SessionFactory sessionFactory = getSessionFactory()) {
            for (int i = 0; i < THREADS_COUNT; i++) {
                final int finalInt = i;
                threads[i] = new Thread(() -> {
                    System.out.println("Thread # " + finalInt + " started.");
                    for (int j = 0; j < 10_000; j++) {
                        boolean isUpdated = false;
                        int randomCelId = (int) (Math.random() * 40) + 1;
                        while (!isUpdated) {
                            Session session = sessionFactory.getCurrentSession();
                            session.beginTransaction();
                            Item item = session.get(Item.class, randomCelId, LockMode.PESSIMISTIC_WRITE);
                            item.setValue(item.getValue() + 1);
                            uncheckedSleep(5);
                            try {
                                session.getTransaction().commit();
                                isUpdated = true;
                            } catch (OptimisticLockException optExc) {
                                session.getTransaction().rollback();
                            }
                            session.close();
                        }
                    }
                    countDownLatchInner.countDown();
                });
                threads[i].start();
                countDownLatchOuter.countDown();
            }
            countDownLatchInner.await();
        } catch (InterruptedException intExc) {
            intExc.printStackTrace();
            intExc.getMessage();
        }
    }

    private static SessionFactory getSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }

    static void uncheckedSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void initialPreparations() {
        SessionFactory sessionFactory = getSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            String sql = Files.lines(Paths.get("DBInit.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Something wrong during initializing the DB.");
            throw new RuntimeException();
        }
    }

    static void checkFinalResult() {
        SessionFactory sessionFactory = getSessionFactory();
        try (Session session = sessionFactory.getCurrentSession()) {
            String sql = "SELECT AVG (value) FROM items;";
            session.beginTransaction();
            Object avg = session.createNativeQuery(sql).getSingleResult();
            System.out.println(avg);
            session.getTransaction().commit();
        }
    }
}

@Data
@Entity
@Table(name = "items")
class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    private int value;

    @Version
    @Column(name = "version")
    private int version;
}