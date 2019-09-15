package br.com.teste.utils;

import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import tools.devnull.trugger.scan.ClassScan;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			
			configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
			configuration.setProperty("hibernate.connection.url", ConexaoUtils.url());
			configuration.setProperty("hibernate.connection.username", ConexaoUtils.USUARIO);
			configuration.setProperty("hibernate.connection.password", ConexaoUtils.SENHA);
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			configuration.setProperty("hibernate.current_session_context_class", "thread");

			configuration.setProperty("hibernate.format_sql", "false");
			configuration.setProperty("hibernate.show_sql", "true");
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			configuration.setProperty("hibernate.transaction.auto_close_session", "false");
			configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

			configuration = registraBean(configuration);

			StandardServiceRegistryBuilder registroServico = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			StandardServiceRegistry servico = registroServico.build();

			return configuration.buildSessionFactory(servico);
		} catch (Exception e) {
			System.out.println("Criação inicial do objeto SessionFactory falhou. Erro: " + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Configuration registraBean(Configuration configuration) {
		Configuration cfg = configuration;
		Set<Class> listClass = ClassScan.findAll().in("br.com.teste.entidades");

		for (Class clazz : listClass) {
			if (clazz.getAnnotation(Entity.class) != null) {
				cfg.addAnnotatedClass(clazz);
			}
		}

		return cfg;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
