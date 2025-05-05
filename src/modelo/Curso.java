package modelo;

public class Curso {
	private String codigo;
	private String nombre;
	private int horas;
	private String id_docente;
	
	public Curso(String codigo, String nombre, int horas, String id_docente) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.horas = horas;
		this.id_docente = id_docente;
	}
	
	public Curso() {
		this.codigo = "";
		this.nombre = "";
		this.horas = 0;
		this.id_docente = "";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getId_docente() {
		return id_docente;
	}

	public void setId_docente(String id_docente) {
		this.id_docente = id_docente;
	}
	
	
	
	
}
