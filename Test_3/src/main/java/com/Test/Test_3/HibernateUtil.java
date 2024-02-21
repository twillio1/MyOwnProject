package com.Test.Test_3;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

private static SessionFactory sessionFactory;
	
	public static SessionFactory getSession()
	{
		
		if(sessionFactory==null)
		{
			Configuration config=new Configuration();
			
			Properties prop=new Properties();
			
			//prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			prop.put(Environment.URL, "jdbc:mysql://localhost:3306/student_db_1");
			prop.put(Environment.USER, "root");
			prop.put(Environment.PASS, "258025");
			prop.put(Environment.HBM2DDL_AUTO, "update");
			prop.put(Environment.SHOW_SQL, true);
			prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			
			
			config.setProperties(prop);
			config.addAnnotatedClass(Student.class);
			
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		}
		
		return sessionFactory;
	}

}
