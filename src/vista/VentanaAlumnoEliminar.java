package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Alumno;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlumnoEliminar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;

	/**
	 * Create the panel.
	 */
	public VentanaAlumnoEliminar() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(77, 30, 57, 14);
		add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(186, 26, 65, 20);
		add(txtId);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(186, 59, 141, 20);
		add(txtNombre);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(77, 63, 65, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(77, 96, 65, 14);
		add(lblNewLabel_2);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(186, 92, 170, 20);
		add(txtApellido);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(186, 123, 198, 20);
		add(txtCorreo);
		
		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setBounds(77, 129, 65, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Género");
		lblNewLabel_4.setBounds(77, 162, 65, 14);
		add(lblNewLabel_4);
		
		JComboBox cmbGenero = new JComboBox();
		cmbGenero.setBounds(186, 157, 103, 22);
		add(cmbGenero);
		cmbGenero.addItem("Masculino");
		cmbGenero.addItem("Femenino");
		cmbGenero.addItem("Otro");
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean correcto = false;
				int valor = JOptionPane.showConfirmDialog(null, "¿Desea eliminar alumno?","Confirmar",JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.OK_OPTION) {
					GestionBBDD bd = new GestionBBDD();
					correcto = bd.EliminarAlumno(txtId.getText());
					if (correcto) {
						JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema... alumno no eliminado");
					}
				}
				txtId.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtCorreo.setText("");
				cmbGenero.setSelectedIndex(0);
				txtId.setEditable(true);
				btnNewButton_1.setEnabled(false);
				
			}
		});
		btnNewButton_1.setBounds(120, 212, 103, 23);
		add(btnNewButton_1);
		
		btnNewButton_1.setEnabled(false);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setEditable(false);
				String id = txtId.getText();

				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					txtId.setEditable(true);
				} else {
					GestionBBDD bd = new GestionBBDD();

					Alumno alm = bd.ConsultarAlumno(id);
					
					if(alm.getId().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontro el alumno", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
						txtId.setEditable(true);
					}else {
						txtNombre.setText(alm.getNombre());
						txtApellido.setText(alm.getApellido());
						txtCorreo.setText(alm.getCorreo());
						if (alm.getGenero().equals("M")) {
							cmbGenero.setSelectedIndex(0);
						}
						if (alm.getGenero().equals("F")) {
							cmbGenero.setSelectedIndex(1);
						}
						if (alm.getGenero().equals("Otro")) {
							cmbGenero.setSelectedIndex(2);
						}

						txtNombre.setEditable(false);
						txtApellido.setEditable(false);
						txtCorreo.setEditable(false);
						cmbGenero.setEnabled(false);
						btnNewButton_1.setEnabled(true);
					}
				}
			}
		});
		btnNewButton.setBounds(276, 29, 84, 18);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setEditable(true);
				txtId.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtCorreo.setText("");
				cmbGenero.setSelectedIndex(0);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(253, 212, 89, 23);
		add(btnNewButton_2);
		
		
		
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtCorreo.setEditable(false);
		cmbGenero.setEnabled(false);
		
	}
}
