package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Alumno;
import modelo.Curso;
import modelo.Matricula;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class VentanaMatriculaRegistrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	/**
	 * Create the panel.
	 */
	public VentanaMatriculaRegistrar() {
		setBackground(new Color(51, 204, 255));
		setToolTipText("");
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(98, 77, 46, 14);
		add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(220, 74, 65, 20);
		add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Alumno");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(98, 110, 65, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Curso");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(98, 143, 89, 14);
		add(lblNewLabel_2);
		
		txtCodigo.setEditable(false);
		
		JComboBox cmbAlumno = new JComboBox();
		cmbAlumno.setBounds(220, 105, 173, 22);
		add(cmbAlumno);
		cmbAlumno.addItem("Seleccione");
		cargarComboAlumno(cmbAlumno);
		
		JComboBox cmbCurso = new JComboBox();
		cmbCurso.setBounds(220, 139, 173, 22);
		add(cmbCurso);
		cmbCurso.addItem("Seleccione");
		cargarComboCurso(cmbCurso);
		
		txtCodigo.setText(cargarNumeroCodigo());
		
		
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBackground(new Color(24, 127, 220));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaActual = Date.valueOf(LocalDate.now());
				
				if (cmbCurso.getSelectedIndex() != 0 && cmbAlumno.getSelectedIndex() != 0) {
					Matricula mat = new Matricula();
					
					mat.setId(Integer.parseInt(txtCodigo.getText()));
					mat.setCodCurso(Integer.parseInt(cmbCurso.getSelectedItem().toString().split(" - ")[0]));
					mat.setNomCurso(cmbCurso.getSelectedItem().toString().split(" - ")[1]);
					mat.setCodAlumno(cmbAlumno.getSelectedItem().toString().split(" - ")[0]);
					mat.setNomAlumno(cmbAlumno.getSelectedItem().toString().split(" - ")[1]);
					mat.setFecha(fechaActual.toString());
					
					
					boolean correcto = false;
					boolean duplicado=false;
					int valor=JOptionPane.showConfirmDialog(null, "¿Desea agregar un nueva matricula?");
					if (valor==JOptionPane.OK_OPTION) {
						ArrayList<Matricula> arrMat=new ArrayList<Matricula>();
						GestionBBDD bd= new GestionBBDD();
						
						arrMat=bd.ListarMatricula("","","",true,"asc");
						
						for(Matricula m : arrMat) {
							if(m.getCodAlumno().equals(mat.getCodAlumno()) && m.getCodCurso()==mat.getCodCurso() && m.isEstado()){
								duplicado=true;
							}
						}
						
						if(duplicado) {
							JOptionPane.showMessageDialog(null, "Alumno ya registrado en el curso seleccionado.", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							correcto=bd.InsertarMatricula(mat);
							if(correcto) {
								JOptionPane.showMessageDialog(null, "Matricula agregada correctamente.");
							}else {
								JOptionPane.showMessageDialog(null, "Hubo un problema... matricula no agregada");
							}
							txtCodigo.setText(cargarNumeroCodigo());
							cmbCurso.setSelectedIndex(0);
							cmbAlumno.setSelectedIndex(0);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(128, 216, 89, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBackground(new Color(24, 127, 220));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbAlumno.setSelectedIndex(0);
				cmbCurso.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(248, 216, 89, 23);
		add(btnNewButton_1);
		
		
		
		

	}
	
	public void cargarComboCurso(JComboBox<String> cmb) {
		ArrayList<Curso> arrCur=new ArrayList<Curso>();
		GestionBBDD bd=new GestionBBDD();
		arrCur=bd.ListarCurso("","","","","asc");
		for (Curso c : arrCur) {
			cmb.addItem(c.getCodigo()+" - "+ c.getNombre());
		}
	}
	
	public void cargarComboAlumno(JComboBox<String> cmb) {
		ArrayList<Alumno> arrAlum=new ArrayList<Alumno>();
		GestionBBDD bd=new GestionBBDD();
		arrAlum=bd.ListarAlumno("","","","asc");
		for (Alumno a: arrAlum) {
			cmb.addItem(a.getId()+" - "+ a.getNombre()+" "+a.getApellido());
		}
	}
	
	//Llenar el codigo con el ultimo codigo ingresado mas 1
	public String cargarNumeroCodigo() {
		ArrayList<Matricula> arrMat=new ArrayList<Matricula>();
		GestionBBDD bd = new GestionBBDD();
		arrMat=bd.ListarMatricula("","","",true,"asc");
		int num;
		if(arrMat.size()==0) {
			num=1;
		}else {
			num=(bd.MaxCodigoMatricula())+1;
		}
		return num+"";
	}
}
