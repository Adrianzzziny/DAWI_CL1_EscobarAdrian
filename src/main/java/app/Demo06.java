package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo06 {
	public static void main(String[] args) {
		//CREAR LISTADO DE USUARIOS
		
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		// select * from tb_usuarios ----(FORMA NORMAL) 
		TypedQuery<Usuario> consulta = em.createQuery("select a from Usuario a", Usuario.class);
		List<Usuario> lstUsuarios = consulta.getResultList();
		
		//---FORMA RESUMIDA
		//List<Usuario> lstUsuarios = em.createQuery("select a from Usuario a", Usuario.class).getResultList()
		
		for (Usuario u : lstUsuarios) {
			System.out.println(u);
		}
		
		em.close();
	}
}
