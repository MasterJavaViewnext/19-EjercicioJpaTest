package com.curso.principal;

import jakarta.persistence.Persistence;

public class Generar {
	public static void main(String[] args) {
		Persistence.generateSchema("noticias", null);
	}
}
