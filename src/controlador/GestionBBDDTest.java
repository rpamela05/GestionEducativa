package controlador;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.Alumno;

public class GestionBBDDTest {

	@Test
	public void testInsertarAlumno() {
		Alumno alumno = new Alumno("125", "Juan", "Pérez", "juan.perez@email.com", "M");
		GestionBBDD gestor = new GestionBBDD();
		boolean resultado = gestor.InsertarAlumno(alumno);
		assertTrue(resultado);
	}

	@Test
	public void testActualizarAlumno() {
		Alumno alumno = new Alumno("125", "Carlos", "Gómez", "carlos.gomez@email.com", "M");
		GestionBBDD gestor = new GestionBBDD();
		boolean resultado = gestor.ActualizarAlumno(alumno);
		assertTrue("La actualización del alumno debería ser exitosa", resultado);
	}

}
