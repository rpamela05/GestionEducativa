package modelo;

public class Alumno extends Persona{
	private String genero;
	
	public Alumno(String id, String nombre, String apellido, String correo, String genero) {
		super(id, nombre, apellido, correo);
		this.genero=genero;
		// TODO Auto-generated constructor stub
	}

	public Alumno(){
		super();
		this.genero="";
		// TODO Auto-generated constructor stub
	}
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
