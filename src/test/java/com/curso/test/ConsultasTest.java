package com.curso.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.model.Comentario;
import com.curso.model.Noticia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ConsultasTest {
	EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeEach
	void iniciar() {
		emf = Persistence.createEntityManagerFactory("noticias");
		em = emf.createEntityManager();
	}
	
	@Test
	public void entityManagerFactoryTest() {
		assertNotNull(emf);
	}
	
	@Test
	public void entityManagerTest() {
		assertNotNull(em);
	}
	
	@Test
	public void seleccionarNoticiaTest() {
		Noticia noticia = em.find(Noticia.class, "1");
		assertEquals(noticia.getAutor(), "raul");
	}
	
	/**
	 * Inserta una noticia, busca esa noticia y compara para saber si ha insertado bien
	 */
	@Test
	public void insertarNoticiaTest() {
		em.getTransaction().begin();
		Noticia noticia = new Noticia("titulo test", "pepiñoTest", Date.valueOf("2024-05-11"));
		em.persist(noticia);
		em.getTransaction().commit();

		TypedQuery<Noticia> consulta = em.createQuery("SELECT n FROM Noticia n WHERE n.autor = :autor", Noticia.class);
		consulta.setParameter("autor", "pepiñoTest");
		Noticia insertada =  consulta.getSingleResult();
				
		assertEquals(noticia, insertada);
	}
	
	public void updateComentarioTest() {
		//NO me dio tiempo
	}
	
	
	/**
	 * Borra una noticia existente y comprueba que está eliminada
	 */
	@Test 
	public void borrarNoticiaTest() {
		Noticia noticia = em.find(Noticia.class, "6");
		
		em.getTransaction().begin();
		em.remove(noticia);
		em.getTransaction().commit();
		
		Noticia noNoticia = em.find(Noticia.class, "6");
		assertNull(noNoticia);
		
	}
}
