package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.File;

public class ClientDemo {
    public static void main(String[] args) {
        try {
            // Create Hibernate configuration
            Configuration cfg = new Configuration();
            
            // Get the configuration file path
            String configPath = "src/main/resources/hibernate.cfg.xml";
            File configFile = new File(configPath);
            
            // Check if config file exists
            if (configFile.exists()) {
                // Load configuration from specific file path
                cfg.configure(configFile);
            } else {
                // Fallback to classpath configuration
                cfg.configure();
            }
            
            // Explicitly add annotated class
            cfg.addAnnotatedClass(Department.class);
            
            // Build SessionFactory
            SessionFactory factory = cfg.buildSessionFactory();
            
            // Open a Hibernate session
            Session session = factory.openSession();
            Transaction transaction = null;
            
            try {
                // Begin transaction
                transaction = session.beginTransaction();
                
                // I. Insert records using Persistent Object
                insertDepartments(session);
                
                // II. Delete records using HQL with Positional Parameters
                deleteDepartmentById(session, 2);
                
                // Commit transaction
                transaction.commit();
            } catch (Exception e) {
                // Rollback transaction if error occurs
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                // Close session and factory
                session.close();
                factory.close();
            }
        } catch (Exception e) {
            System.err.println("Configuration Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void insertDepartments(Session session) {
        Department dept1 = new Department("Computer Science", "Block A", "Dr. Smith");
        Department dept2 = new Department("Electrical Engineering", "Block B", "Dr. Johnson");
        Department dept3 = new Department("Mechanical Engineering", "Block C", "Dr. Williams");
        
        session.save(dept1);
        session.save(dept2);
        session.save(dept3);
        
        System.out.println("Departments inserted successfully.");
    }
    
    private static void deleteDepartmentById(Session session, int departmentId) {
        String hql = "DELETE FROM Department WHERE departmentId = ?1";
        Query<Integer> query = session.createQuery(hql);
        query.setParameter(1, departmentId);
        
        int deletedCount = query.executeUpdate();
        System.out.println("Number of departments deleted: " + deletedCount);
    }
}