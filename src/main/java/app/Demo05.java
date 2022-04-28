package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	public static void main(String[] args) {
		//ELIMINAR Version 2.0 (USANDO BUSQUEDA)
		
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//empezo la transaccion
		em.getTransaction().begin();
		
		//proceso ---> ELIMINAR UN USUARIO
		//FORMA 2 --> BORRADO FISICO --> BORRAR DEFINITIVO
		Usuario u = em.find(Usuario.class, 33);
		if (u == null) 
			System.err.println("Codigo no existe");
		else {
		em.remove(u);
		System.out.println("Usuario ELIMINADO");
		}
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
	}
}
