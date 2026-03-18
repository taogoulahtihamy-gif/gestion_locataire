package com.location.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JpaUtil {

    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            Map<String, String> props = new HashMap<>();

            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            boolean renderConfigPresent =
                    dbHost != null && !dbHost.isBlank() &&
                            dbPort != null && !dbPort.isBlank() &&
                            dbName != null && !dbName.isBlank() &&
                            dbUser != null && !dbUser.isBlank() &&
                            dbPassword != null && !dbPassword.isBlank();

            if (renderConfigPresent) {
                props.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
                props.put("jakarta.persistence.jdbc.url",
                        "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName);
                props.put("jakarta.persistence.jdbc.user", dbUser);
                props.put("jakarta.persistence.jdbc.password", dbPassword);
            }

            return Persistence.createEntityManagerFactory("gestionLocationsPU", props);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'initialisation de JPA", e);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}