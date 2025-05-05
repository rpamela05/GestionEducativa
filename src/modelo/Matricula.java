package modelo;

public class Matricula {
	private int id;
	private int codCurso;
	private String nomCurso;
	private String codAlumno;
	private String nomAlumno;
	private String fecha;
	private boolean estado;
	
	

	
	
	public Matricula(int id, int codCurso, String nomCurso, String codAlumno, String nomAlumno, String fecha,
			boolean estado) {
		this.id = id;
		this.codCurso = codCurso;
		this.nomCurso = nomCurso;
		this.codAlumno = codAlumno;
		this.nomAlumno = nomAlumno;
		this.fecha = fecha;
		this.estado = estado;
	}

	public Matricula() {
		this.id = 0;
		this.codCurso = 0;
		this.nomCurso = "";
		this.codAlumno = "";
		this.nomAlumno = "";
		this.fecha = "";
		this.estado = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public String getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNomCurso() {
		return nomCurso;
	}

	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}

	public String getNomAlumno() {
		return nomAlumno;
	}

	public void setNomAlumno(String nomAlumno) {
		this.nomAlumno = nomAlumno;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}
