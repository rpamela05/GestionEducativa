package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Alumno;
import modelo.Profesor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.color.ProfileDataException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaProfesorActualizar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtId;
	private JTextField txtCorreo;
	private JTextField txtEspecialidad;

	/**
	 * Create the panel.
	 */
	public VentanaProfesorActualizar() {
		setBackground(new Color(51, 204, 255));
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(80, 63, 65, 14);
		add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(188, 60, 141, 20);
		add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(188, 93, 170, 20);
		add(txtApellidos);

		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(80, 96, 65, 14);
		add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(80, 30, 65, 14);
		add(lblNewLabel);

		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(188, 27, 65, 20);
		add(txtId);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(188, 124, 198, 20);
		add(txtCorreo);

		JLabel lblNewLabel_4 = new JLabel("Especialidad");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(80, 162, 84, 14);
		add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(80, 129, 65, 14);
		add(lblNewLabel_3);

		txtNombre.setEditable(false);
		txtApellidos.setEditable(false);
		txtCorreo.setEditable(false);
		


		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.setBackground(new Color(24, 127, 220));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profesor prof= new Profesor();
				prof.setId(txtId.getText());
				prof.setNombre(txtNombre.getText());
				prof.setApellido(txtApellidos.getText());
				prof.setCorreo(txtCorreo.getText());
				prof.setEspecialidad(txtEspecialidad.getText());

				boolean correcto = false;
				int valor = JOptionPane.showConfirmDialog(null, "¿Desea actualizar profesor?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.OK_OPTION) {
					GestionBBDD bd = new GestionBBDD();
					correcto = bd.ActualizarProfesor(prof);
					if (correcto) {
						JOptionPane.showMessageDialog(null, "Profesor actualizado correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema... profesor no actualizado");
					}
				}
				txtId.setEditable(true);
				txtId.setText("");
				txtNombre.setText("");
				txtApellidos.setText("");
				txtCorreo.setText("");
				txtEspecialidad.setText("");
				txtNombre.setEditable(false);
				txtApellidos.setEditable(false);
				txtCorreo.setEditable(false);
				txtEspecialidad.setEditable(false);
				btnNewButton_1.setEnabled(false);

			}
		});
		btnNewButton_1.setBounds(120, 212, 103, 23);
		add(btnNewButton_1);
		
		btnNewButton_1.setEnabled(false);
		

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBackground(new Color(24, 127, 220));
		btnNewButton.setForeground(Color.WHITE);
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

					Profesor prof= bd.ConsultarProfesor(id);
					
					if(prof.getId().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontro el profesor", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
						txtId.setEditable(true);
					}else {
						txtNombre.setText(prof.getNombre());
						txtApellidos.setText(prof.getApellido());
						txtCorreo.setText(prof.getCorreo());
						txtEspecialidad.setText(prof.getEspecialidad());

						txtNombre.setEditable(true);
						txtApellidos.setEditable(true);
						txtCorreo.setEditable(true);
						txtEspecialidad.setEditable(true);
						btnNewButton_1.setEnabled(true);
					}
				}
			}
		});
		btnNewButton.setBounds(274, 28, 84, 18);
		add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setBackground(new Color(24, 127, 220));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setEditable(true);
				txtId.setText("");
				txtNombre.setText("");
				txtApellidos.setText("");
				txtCorreo.setText("");
				txtEspecialidad.setText("");
				txtNombre.setEditable(false);
				txtApellidos.setEditable(false);
				txtCorreo.setEditable(false);
				txtEspecialidad.setEditable(false);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(253, 212, 89, 23);
		add(btnNewButton_2);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setEditable(false);
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(188, 155, 170, 20);
		add(txtEspecialidad);

	}

}
