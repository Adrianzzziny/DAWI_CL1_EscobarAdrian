package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
	public static void main(String[] args) {
		//ELIMINAR USUARIO
		
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//empezo la transaccion
		em.getTransaction().begin();
		
		//proceso ---> ELIMINAR UN USUARIO
		//FORMA 1 --> BORRADO LOGICO --> No lo borras, solo cambias algun estado, flag
		//MERGE
		//FORMA 2 --> BORRADO FISICO --> BORRAR DEFINITIVO
		Usuario u = new Usuario(33, "Carla", "Toro", "U0022@gmail.com", "10002", 
				"2022/04/18", 2, 1);
		em.remove(u);
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
	}
}
