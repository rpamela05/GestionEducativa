package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import modelo.Alumno;
import modelo.Curso;
import modelo.Matricula;
import modelo.Profesor;

public class GestionBBDD {
	
	 /**
	 * Inserta un nuevo alumno en la base de datos.
     * @param a Objeto Alumno a insertar.
     * @return true si la inserción fue exitosa, false si ocurrió un error.
     */
	public boolean InsertarAlumno(Alumno a) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into persona (id, nombres, apellidos, correo) " + "values ('" + a.getId()
					+ "','" + a.getNombre() + "','" + a.getApellido() + "','" + a.getCorreo() + "'); ");
			consulta.executeUpdate(
					"insert into alumno (id, genero) " + "values ('" + a.getId() + "','" + a.getGenero() + "'); ");
			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Lista los alumnos que coinciden con los filtros especificados.
     * @param nombre Filtro por nombre del alumno.
     * @param codigo Filtro por ID del alumno.
     * @param genero Filtro por género.
     * @param orden Orden de los resultados (ASC o DESC).
     * @return Lista de alumnos que cumplen con los criterios.
     */
	public ArrayList<Alumno> ListarAlumno(String nombre, String codigo, String genero, String orden) {
		ArrayList<Alumno> arrAlum = new ArrayList<>();
		Alumno a = null;
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			
			PreparedStatement ps = conexion
					.prepareStatement("select p.*,a.genero from alumno a " + "inner join persona p " + "on a.id =p.id "
							+ "where a.id like ? and concat(p.nombres,' ',p.apellidos) like ? " + "and a.genero like ? "
							+ "order by a.id " + orden);

			ps.setString(1, "%" + codigo + "%");
			ps.setString(2, "%" + nombre + "%");
			ps.setString(3, "%" + genero + "%");

			ResultSet registro = ps.executeQuery();

			while (registro.next()) {
				a = new Alumno();

				a.setId(registro.getString(1));
				a.setNombre(registro.getNString(2));
				a.setApellido(registro.getString(3));
				a.setCorreo(registro.getString(4));
				a.setGenero(registro.getString(5));

				arrAlum.add(a);
			}
			conexion.close();
			return arrAlum;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
     * Consulta los datos de un alumno específico por su ID.
     * @param id ID del alumno a consultar.
     * @return Objeto Alumno si se encuentra, null si no existe.
     */
	public Alumno ConsultarAlumno(String id) {
		Alumno alm = null;
		try {
			alm = new Alumno();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement(
					"select p.*,a.genero from alumno a " + "inner join persona p " + "on a.id =p.id " + "where a.id=?");

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				alm.setId(rs.getString(1));
				alm.setNombre(rs.getNString(2));
				alm.setApellido(rs.getString(3));
				alm.setCorreo(rs.getString(4));
				alm.setGenero(rs.getString(5));
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alm;
	}
	
	/**
     * Actualiza los datos de un alumno en la base de datos.
     * @param a Objeto Alumno con los datos actualizados.
     * @return true si la actualización fue exitosa, false si ocurrió un error.
     */
	public boolean ActualizarAlumno(Alumno a) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion
					.prepareStatement("UPDATE persona " + " SET nombres=?, apellidos=?, correo=? " + " WHERE id=?");

			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellido());
			ps.setString(3, a.getCorreo());
			ps.setString(4, a.getId());

			ps.executeUpdate();

			PreparedStatement ps2 = conexion.prepareStatement("UPDATE alumno " + "SET genero=? " + "WHERE id=?");

			ps2.setString(1, a.getGenero());
			ps2.setString(2, a.getId());

			ps2.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Elimina un alumno de la base de datos.
     * @param Id ID del alumno a eliminar.
     * @return true si la eliminación fue exitosa, false si ocurrió un error.
     */
	public boolean EliminarAlumno(String Id) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM alumno " + "WHERE id=?");

			ps.setString(1, Id);

			ps.executeUpdate();

			PreparedStatement ps2 = conexion.prepareStatement("DELETE FROM persona " + "WHERE id=?");

			ps2.setString(1, Id);

			ps2.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Inserta un nuevo profesor en las tablas 'persona' y 'docente'.
     * @param Objeto Profesor que contiene los datos del profesor a insertar.
     * @return true si la inserción fue exitosa, false si ocurrió un error.
     */
	public boolean InsertarProfesor(Profesor p) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into persona (id, nombres, apellidos, correo) " + "values ('" + p.getId()
					+ "','" + p.getNombre() + "','" + p.getApellido() + "','" + p.getCorreo() + "')");
			consulta.executeUpdate("insert into docente (id, especialidad) " + "values ('" + p.getId() + "','"
					+ p.getEspecialidad() + "') ");
			conexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     *Lista profesores de la base de datos según los filtros de búsqueda y orden.
     * @param codigo Filtro para el código (ID) del profesor.
     * @param nombre Filtro para el nombre completo del profesor.
     * @param especialidad Filtro para la especialidad del profesor.
     * @param orden Criterio de ordenamiento (por ID ascendente o descendente).
     * @return ArrayList de tipo profesor con los profesores que cumplen los criterios de búsqueda, o null si ocurre un error.
     */
	public ArrayList<Profesor> ListarProfesor(String codigo, String nombre, String especialidad, String orden) {
		ArrayList<Profesor> arrPro = new ArrayList<>();
		Profesor p = null;
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement(
					"select p.*,pf.especialidad from docente pf " + "inner join persona p " + "on pf.id =p.id "
							+ "where pf.id like ? and concat(p.nombres,' ',p.apellidos) like ? "
							+ "and pf.especialidad like ? order by p.id "+orden);
			ps.setString(1,"%"+ codigo+"%");
			ps.setString(2,"%"+ nombre+"%");
			ps.setString(3,"%"+ especialidad+"%");
			
			ResultSet registro = ps.executeQuery();
			while (registro.next()) {
				p = new Profesor();

				p.setId(registro.getString(1));
				p.setNombre(registro.getNString(2));
				p.setApellido(registro.getString(3));
				p.setCorreo(registro.getString(4));
				p.setEspecialidad(registro.getString(5));

				arrPro.add(p);
			}
			conexion.close();
			return arrPro;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
     *Consulta los datos de un profesor específico por su ID.
     * @param Identificador del profesor a consultar.
     * @return Profesor con los datos encontrados, o null si no se encuentra o hay error.
     */
	public Profesor ConsultarProfesor(String id) {
		Profesor prof = null;
		try {
			prof = new Profesor();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("select p.*,pf.especialidad from docente pf "
					+ "inner join persona p " + "on pf.id =p.id " + "where pf.id=?");

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				prof.setId(rs.getString(1));
				prof.setNombre(rs.getNString(2));
				prof.setApellido(rs.getString(3));
				prof.setCorreo(rs.getString(4));
				prof.setEspecialidad(rs.getString(5));
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prof;
	}
	
	 /**
     * Actualiza la información de un profesor en las tablas 'persona' y 'docente'.
     * @param Objeto Profesor con los nuevos datos a actualizar.
     * @return true si la actualización fue exitosa, false en caso de error.
     */
	public boolean ActualizarProfesor(Profesor p) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion
					.prepareStatement("UPDATE persona " + " SET nombres=?, apellidos=?, correo=? " + " WHERE id=?");

			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido());
			ps.setString(3, p.getCorreo());
			ps.setString(4, p.getId());

			ps.executeUpdate();

			PreparedStatement ps2 = conexion.prepareStatement("UPDATE docente " + "SET especialidad=? " + "WHERE id=?");

			ps2.setString(1, p.getEspecialidad());
			ps2.setString(2, p.getId());

			ps2.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Elimina un profesor de las tablas 'docente' y 'persona' por su ID.
     * @param Id Identificador del profesor que se desea eliminar.
     * @return true si la eliminación fue exitosa, false si ocurrió un error.
     */
	public boolean EliminarProfesor(String Id) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM docente " + "WHERE id=?");

			ps.setString(1, Id);

			ps.executeUpdate();

			PreparedStatement ps2 = conexion.prepareStatement("DELETE FROM persona " + "WHERE id=?");

			ps2.setString(1, Id);

			ps2.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	 /**
     * Inserta un nuevo curso en la base de datos.
     * @param Objeto Curso con los datos a insertar.
     * @return true si la operación fue exitosa, false si ocurrió un error.
     */
	public boolean InsertarCurso(Curso c) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into curso (nombre, horas, id_docente) " + "values ('" + c.getNombre() + "',"
					+ c.getHoras() + ",'" + c.getId_docente() + "')");
			conexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Lista cursos según filtros de búsqueda y ordenamiento.
     * @param codigo Filtro para el código del curso.
     * @param curso Filtro para el nombre del curso.
     * @param horas Filtro mínimo de horas del curso.
     * @param profesor Filtro para el ID del profesor.
     * @param orden Orden de los resultados (ascendente o descendente).
     * @return Lista de cursos encontrados o null si ocurre un error.
     */
	public ArrayList<Curso> ListarCurso(String codigo,String curso,String horas, String profesor,String orden) {
		ArrayList<Curso> arrCur = new ArrayList<>();
		Curso c = null;
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			

			PreparedStatement ps = conexion.prepareStatement("select c.codigo,c.nombre,c.horas,CONCAT(p.nombres,' ',p.apellidos) from  curso c "
							+ "inner join persona p " + "on c.id_docente =p.id "
									+ "where c.codigo like ? and c.nombre like ? "
									+ "and c.horas>=? and c.id_docente like ?"
									+ " order by c.codigo "+orden);
			
			ps.setString(1,"%"+codigo+"%");
			ps.setString(2,"%"+curso+"%");
			ps.setString(3,horas);
			ps.setString(4,"%"+profesor+"%");
			
			
			ResultSet registro=ps.executeQuery();
			
			while (registro.next()) {
				c = new Curso();

				c.setCodigo(registro.getString(1));
				c.setNombre(registro.getString(2));
				c.setHoras(registro.getInt(3));
				c.setId_docente(registro.getString(4));

				arrCur.add(c);
			}
			conexion.close();
			return arrCur;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
     * Consulta los datos de un curso específico por su código.
     * @param id Código del curso.
     * @return Objeto Curso con los datos encontrados o null si no se encuentra.
     */
	public Curso ConsultarCurso(String id) {
		Curso cur = null;
		try {
			cur = new Curso();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("select * from curso " + "where codigo=?");

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				cur.setCodigo(rs.getString(1));
				cur.setNombre(rs.getString(2));
				cur.setHoras(rs.getInt(3));
				cur.setId_docente(rs.getString(4));
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cur;
	}
	
	 /**
     * Actualiza la información de un curso en la base de datos.
     * @param c Objeto Curso con los datos actualizados.
     * @return true si la operación fue exitosa, false si ocurrió un error.
     */
	public boolean ActualizarCurso(Curso c) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion
					.prepareStatement("UPDATE curso " + "SET nombre=?, horas=?, id_docente=? " + "WHERE codigo=?");

			ps.setString(1, c.getNombre());
			ps.setInt(2, c.getHoras());
			ps.setString(3, c.getId_docente());
			ps.setString(4, c.getCodigo());

			ps.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Elimina un curso de la base de datos por su código.
     * @param Id Código del curso a eliminar.
     * @return true si la eliminación fue exitosa, false si ocurrió un error.
     */
	public boolean EliminarCurso(String Id) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("DELETE FROM curso " + "WHERE codigo=?");

			ps.setString(1, Id);

			ps.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
     * Obtiene el valor máximo actual del campo 'codigo' de la tabla curso.
     * @return Valor entero máximo del código o 0 si no hay cursos o ocurrió un error.
     */
	public int MaxCodigoCurso() {
		int num = 0;
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("select max(codigo) from  curso");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	/**
     * Inserta una nueva matrícula en la base de datos.
     * @param m Objeto Matricula con los datos a insertar.
     * @return true si la inserción fue exitosa, false si ocurrió un error.
     */
	public boolean InsertarMatricula(Matricula m) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate("insert into matricula (codigo_curso, codigo_alumno, fecha) " + "values ("
					+ m.getCodCurso() + ",'" + m.getCodAlumno() + "','" + m.getFecha() + "')");
			conexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Lista las matrículas filtradas por código, alumno, curso, estado y orden.
     * @param codigo Filtro por ID de matrícula.
     * @param alumno Filtro por nombre completo del alumno.
     * @param curso Filtro por nombre del curso.
     * @param estado Estado booleano de la matrícula.
     * @param orden Ordenamiento de resultados (ascendente o descendente).
     * @return Lista de matrículas encontradas o null si ocurre un error.
     */
	public ArrayList<Matricula> ListarMatricula(String codigo, String alumno, String curso, boolean estado, String orden) {
		ArrayList<Matricula> arrMat = new ArrayList<>();
		Matricula m = null;
		Connection conexion;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			

			PreparedStatement ps = conexion.prepareStatement(
					"select m.id, c.codigo, c.nombre, a.id, CONCAT(a.nombres,' ',a.apellidos),m.fecha,m.estado from matricula m "
							+ "inner join curso c  " + "inner join persona a "
							+ "on m.codigo_curso =c.codigo and a.id =m.codigo_alumno "
							+ "where m.id like ? and CONCAT(a.nombres,' ',a.apellidos) like ? "
							+ "and c.nombre like ?"
							+ "and m.estado = ? "
							+ "order by m.id "+orden);
			
			ps.setString(1, "%"+codigo+"%");
			ps.setString(2, "%"+alumno+"%");
			ps.setString(3, "%"+curso+"%");
			ps.setBoolean(4, estado);
			
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				m = new Matricula();

				m.setId(rs.getInt(1));
				m.setCodCurso(rs.getInt(2));
				m.setNomCurso(rs.getString(3));
				m.setCodAlumno(rs.getString(4));
				m.setNomAlumno(rs.getString(5));
				m.setFecha(rs.getString(6));
				m.setEstado(rs.getBoolean(7));
				arrMat.add(m);
			}
			conexion.close();
			return arrMat;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
     * Consulta los datos de una matrícula específica por su ID.
     * @param id Identificador de la matrícula.
     * @return Objeto Matricula con los datos encontrados o null si no se encuentra.
     */
	public Matricula ConsultarMatricula(String id) {
		Matricula mat = null;
		try {
			mat = new Matricula();
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement(
					"select m.id, c.nombre, CONCAT(a.nombres,' ',a.apellidos),m.fecha,m.estado from matricula m "
							+ "inner join curso c " + "inner join persona a "
							+ "on m.codigo_curso =c.codigo and a.id =m.codigo_alumno " + "where m.id=?");

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				mat.setId(rs.getInt(1));
				mat.setNomCurso(rs.getString(2));
				mat.setNomAlumno(rs.getString(3));
				mat.setFecha(rs.getString(4));
				mat.setEstado(rs.getBoolean(5));
			}

			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mat;
	}
	
	/**
     * Actualiza los datos de una matrícula existente.
     * @param m Objeto Matricula con los datos actualizados.
     * @return true si la actualización fue exitosa, false si ocurrió un error.
     */
	public boolean ActualizarMatricula(Matricula m) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("UPDATE matricula SET fecha=?, estado=? WHERE id=?");

			ps.setString(1, m.getFecha());
			ps.setBoolean(2, m.isEstado());
			ps.setInt(3, m.getId());

			ps.executeUpdate();

			conexion.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * Obtiene el valor máximo actual del campo 'id' en la tabla matrícula.
     * @return Valor entero máximo del ID o 0 si no hay registros o ocurrió un error.
     */
	public int MaxCodigoMatricula() {
		int num = 0;
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_educativa", "root", "");
			PreparedStatement ps = conexion.prepareStatement("select max(id) from  matricula");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
}
