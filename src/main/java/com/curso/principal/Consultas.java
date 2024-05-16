package com.curso.principal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.curso.model.Comentario;
import com.curso.model.Noticia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Consultas {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("noticias");
		EntityManager em = emf.createEntityManager();
		
		//Consultar los comentarios de la noticia 1
		TypedQuery<Comentario> consulta = em.createQuery("SELECT c FROM Comentario c WHERE c.noticia.id = 1", Comentario.class);
		List<Comentario> comentarios = consulta.getResultList();
		
		for (Comentario comentario : comentarios) {
			System.out.println(comentario);
		}
		
		
		//Añadir una noticia y 3 comentarios
		em.getTransaction().begin();
		Noticia noticia = new Noticia("titulo 2", "pepe", Date.valueOf("2024-05-15"));
		Comentario comentario1 = new Comentario("lorem ipsum texto del comentario", "Pedro", noticia);
		Comentario comentario2 = new Comentario("lorem ipsum texto del comentario", "Josito", noticia);
		Comentario comentario3 = new Comentario("lorem ipsum texto del comentario", "Pepito", noticia);
		
		List<Comentario> comentariosNoticia = new ArrayList<Comentario>();
		comentariosNoticia.add(comentario1);
		comentariosNoticia.add(comentario2);
		comentariosNoticia.add(comentario3);
		noticia.setComentarios(comentariosNoticia);
		
		em.persist(noticia);
		em.persist(comentario1);
		em.persist(comentario2);
		em.persist(comentario3);
		em.getTransaction().commit();
		
		
	}
}
