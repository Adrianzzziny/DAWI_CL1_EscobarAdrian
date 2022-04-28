package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	
	public static void main(String[] args) {
		//ACTUALIZAR DATOS DE UN USUARIO
		Usuario u = new Usuario(33, "Carla", "Toro", "U0022@gmail.com", "10002", 
				"2022/04/18", 2, 1);
		
		//grabar en la tabla ---> JPA
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//empezo la transaccion
		em.getTransaction().begin();
		
		//proceso ---> actualizar en la tabla MERGE
		em.merge(u); // actualizará si existe codigo, si no, lo inserta como nuevo registro
		
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
	}
}
