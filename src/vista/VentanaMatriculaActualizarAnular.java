package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Matricula;
import controlador.GestionBBDD;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JCheckBox;

public class VentanaMatriculaActualizarAnular extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtAlumno;
	private JTextField txtCurso;
	private JTextField txtCodigo;
	/**
	 * Create the panel.
	 */
	public VentanaMatriculaActualizarAnular() {
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Alumno");
		lblNewLabel_1.setBounds(79, 78, 65, 14);
		add(lblNewLabel_1);

		txtAlumno = new JTextField();
		txtAlumno.setColumns(10);
		txtAlumno.setBounds(187, 75, 141, 20);
		add(txtAlumno);

		txtCurso = new JTextField();
		txtCurso.setColumns(10);
		txtCurso.setBounds(187, 108, 141, 20);
		add(txtCurso);

		JLabel lblNewLabel_2 = new JLabel("Curso");
		lblNewLabel_2.setBounds(79, 111, 84, 14);
		add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(79, 45, 65, 14);
		add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(187, 42, 65, 20);
		add(txtCodigo);
		
		JDateChooser dFecha = new JDateChooser();
		dFecha.setBounds(187, 142, 103, 20);
		add(dFecha);

		JCheckBox jcbActivo = new JCheckBox("¿Activo?");
		jcbActivo.setBounds(79, 172, 97, 23);
		add(jcbActivo);

		JLabel lblNewLabel_3 = new JLabel("Fecha");
		lblNewLabel_3.setBounds(79, 144, 65, 14);
		add(lblNewLabel_3);

		txtAlumno.setEditable(false);
		txtCurso.setEditable(false);
		dFecha.setEnabled(false);
		jcbActivo.setEnabled(false);

		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean seleccionar;
				if(jcbActivo.isSelected()) {
					seleccionar=true;
				}else {
					seleccionar=false;
				}
				Date fechaSeleccionada = dFecha.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaFormateada = sdf.format(fechaSeleccionada);
				
				Matricula mat = new Matricula();
				mat.setId(Integer.parseInt(txtCodigo.getText()));
				mat.setFecha(fechaFormateada);
				mat.setEstado(seleccionar);

				boolean correcto = false;
				int valor = JOptionPane.showConfirmDialog(null, "¿Desea modificar la matricula?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.OK_OPTION) {
					GestionBBDD bd = new GestionBBDD();
					correcto = bd.ActualizarMatricula(mat);
					if (correcto) {
						JOptionPane.showMessageDialog(null, "Matricula actualizada correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema... matricula no actualizada");
					}
				}
				txtCodigo.setEditable(true);
				txtCodigo.setText("");
				txtAlumno.setText("");
				txtCurso.setText("");
				dFecha.setDate(null);
				jcbActivo.setSelected(false);
				txtAlumno.setEditable(false);
				txtCurso.setEditable(false);
				dFecha.setEnabled(false);
				jcbActivo.setEnabled(false);
				btnNewButton_1.setEnabled(false);

			}
		});
		btnNewButton_1.setBounds(120, 212, 103, 23);
		add(btnNewButton_1);
		
		btnNewButton_1.setEnabled(false);
		

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setEditable(false);
				String id = txtCodigo.getText();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					txtCodigo.setEditable(true);
				} else {
					GestionBBDD bd = new GestionBBDD();

					Matricula mat = bd.ConsultarMatricula(id);
					
					if(mat.getId()==0) {
						JOptionPane.showMessageDialog(null, "No se encontro la matricula", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
						txtCodigo.setEditable(true);
					}else {
						dFecha.setDateFormatString("dd/MM/yyyy");
						Date fecha=null;
						try {
							fecha = new SimpleDateFormat("yyyy-MM-dd").parse(mat.getFecha());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtAlumno.setText(mat.getNomAlumno());
						txtCurso.setText(mat.getNomCurso()+"");
						if(mat.isEstado()) {
							jcbActivo.setSelected(true);
						}else {
							jcbActivo.setSelected(false);
						}
						dFecha.setDate(fecha);
						jcbActivo.setEnabled(true);
						dFecha.setEnabled(true);
						btnNewButton_1.setEnabled(true);
					}
				}
			}
		});
		btnNewButton.setBounds(273, 43, 84, 18);
		add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setEditable(true);
				txtCodigo.setText("");
				txtAlumno.setText("");
				txtCurso.setText("");
				dFecha.setDate(null);
				jcbActivo.setSelected(false);
				txtAlumno.setEditable(false);
				txtCurso.setEditable(false);
				dFecha.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(253, 212, 89, 23);
		add(btnNewButton_2);
		
	}
	
}
