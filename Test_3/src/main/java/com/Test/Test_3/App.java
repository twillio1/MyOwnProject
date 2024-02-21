package com.Test.Test_3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       SessionFactory fac=HibernateUtil.getSession();
       
       Session session = fac.openSession();
       
       
       Student st = session.get(Student.class,1);
       
       session.delete(st);
       
       Transaction tx = session.beginTransaction();
       tx.commit();
       System.out.println("Complete");
    
    }
}
