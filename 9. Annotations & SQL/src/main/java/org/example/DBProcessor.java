package org.example;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class DBProcessor {
    static Connection connection = null;
    static Statement statement = null;
    static PreparedStatement preparedStatement = null;

    public static void tableBiulder(Class IncomeClass) throws SQLException {
        connection.setAutoCommit(false);
        if (!IncomeClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("@Table missed.");
        }
        HashMap<Class, String> map = new HashMap<>();
        map.put(int.class, "INTEGER");
        map.put(String.class, "TEXT");
        map.put(boolean.class, "BOOLEAN");

        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(((Table) IncomeClass.getAnnotation(Table.class)).title().substring(0, 1).toUpperCase())
                .append(((Table) IncomeClass.getAnnotation(Table.class)).title().substring(1))
                .append(" (");
        Field[] fields = IncomeClass.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Column.class)) {
                stringBuilder.append((f.getName()).substring(0, 1).toUpperCase())
                        .append((f.getName()).substring(1))
                        .append(" ")
                        .append(map.get(f.getType()))
                        .append(", ");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(");");

        System.out.println(stringBuilder);

        statement.executeUpdate(String.valueOf(stringBuilder));
        connection.commit();
    }

    public static void tableFiller(TargetClass incomeTC) throws IllegalAccessException, SQLException {
        connection.setAutoCommit(false);
        if (!(incomeTC.getClass()).isAnnotationPresent(Table.class)) {
            throw new RuntimeException("@Table missed.");
        }
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        stringBuilder.append(incomeTC.getClass().getAnnotation(Table.class).title().substring(0, 1).toUpperCase())
                .append(incomeTC.getClass().getAnnotation(Table.class).title().substring(1))
                .append(" (");
        Field[] fields = incomeTC.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Column.class)) {
                stringBuilder.append(f.getName().substring(0, 1).toUpperCase())
                        .append(f.getName().substring(1))
                        .append(", ");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(") VALUES (");
        for (Field f : fields) {
            if (!f.getType().isPrimitive()) stringBuilder.append("'");
            if (f.isAnnotationPresent(Column.class)) {
                stringBuilder.append(f.get(incomeTC));
            }
            if (!f.getType().isPrimitive()) stringBuilder.append("'");
            stringBuilder.append(", ");
        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(");");

        statement.executeUpdate(String.valueOf(stringBuilder));

        System.out.println(stringBuilder);
        connection.commit();
    }

    public static void tableFiller2(TargetClass incomeTC) throws IllegalAccessException, SQLException {
        connection.setAutoCommit(false);
        if (!(incomeTC.getClass()).isAnnotationPresent(Table.class)) {
            throw new RuntimeException("@Table missed.");
        }
        Field[] fields = incomeTC.getClass().getDeclaredFields();
        StringBuilder stringBuilder0 = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Column.class)) {
                stringBuilder1.append(f.getName().substring(0, 1).toUpperCase())
                        .append(f.getName().substring(1)).append(", ");
            }
        }
        for (Field f : fields) {
            if (f.isAnnotationPresent(Column.class)) {
                stringBuilder2.append("?, ");
            }
        }
        stringBuilder1.setLength(stringBuilder1.length() - 2);
        stringBuilder2.setLength(stringBuilder2.length() - 2);
        preparedStatement = connection.prepareStatement("INSERT INTO "
                + (incomeTC.getClass().getAnnotation(Table.class).title().substring(0, 1).toUpperCase())
                + (incomeTC.getClass().getAnnotation(Table.class).title().substring(1)) + " ("
                + stringBuilder1 + ") VALUES (" + stringBuilder2 + ");");
        System.out.println(preparedStatement);

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Column.class)) {
                stringBuilder0.append(fields[i].get(incomeTC));
            }
            System.out.println(stringBuilder0);
            if (fields[i].getType() == int.class) {
                preparedStatement.setObject(i + 1, Integer.parseInt(stringBuilder0.toString()));
            } else if (fields[i].getType() == String.class) {
                preparedStatement.setObject(i + 1, stringBuilder0.toString());
            } else if (fields[i].getType() == boolean.class)
                preparedStatement.setObject(i + 1, Boolean.parseBoolean(stringBuilder0.toString()));
            stringBuilder0.setLength(0);
        }
        System.out.println(preparedStatement);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
        connection.commit();
    }
}