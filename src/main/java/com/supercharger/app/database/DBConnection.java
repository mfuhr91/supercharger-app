package com.supercharger.app.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBConnection {

    private static final String PERSISTENCE_UNIT_NAME = "persistence";
    private static EntityManager manager;

    public EntityManager getManager() {
        return manager;
    }

    private static final class InstanceHolder {
        private static final DBConnection instance = new DBConnection();
    }

    public static DBConnection getInstance() {
        return InstanceHolder.instance;
    }

    public static void start(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
    }

}
