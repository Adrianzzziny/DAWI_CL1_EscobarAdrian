package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {

	public static void main(String[] args) {
		//valores del nuevo usuario
		Usuario u = new Usuario(10, "Adrian", "Escobar", "adrian@gmail.com", "345", 
				"2003/02/06", 1, 1);
		
		//grabar en la tabla ---> JPA
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//empezo la transaccion
		em.getTransaction().begin();
		
		//proceso ---> grabar en la tabla
		em.persist(u);
		
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
		
	}
}
