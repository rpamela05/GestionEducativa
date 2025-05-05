package modelo;

public class Profesor extends Persona{
	private String especialidad;

	public Profesor(String id, String nombre, String apellido, String correo, String especialidad) {
		super(id, nombre, apellido, correo);
		this.especialidad = especialidad;
	}
	
	public Profesor() {
		super();
		this.especialidad = "";
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	
}
