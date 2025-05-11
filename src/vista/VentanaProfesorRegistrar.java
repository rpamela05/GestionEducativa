package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Profesor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaProfesorRegistrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextField txtEspecialidad;

	/**
	 * Create the panel.
	 */
	public VentanaProfesorRegistrar() {
		setBackground(new Color(51, 204, 255));
		setToolTipText("");
		setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(99, 35, 46, 14);
		add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(210, 32, 65, 20);
		add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(99, 68, 65, 14);
		add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(210, 65, 141, 20);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(99, 101, 65, 14);
		add(lblNewLabel_2);

		txtApellido = new JTextField();
		txtApellido.setBounds(210, 98, 141, 20);
		add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(99, 134, 65, 14);
		add(lblNewLabel_3);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(210, 129, 170, 20);
		add(txtCorreo);
		txtCorreo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Especialidad");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(99, 167, 83, 14);
		add(lblNewLabel_4);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBackground(new Color(24, 127, 220));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && !txtCorreo.getText().isEmpty()
						&& !txtId.getText().isEmpty() && !txtEspecialidad.getText().isEmpty()) {
					Profesor prof = new Profesor();
					
					prof.setId(txtId.getText());
					prof.setNombre(txtNombre.getText());
					prof.setApellido(txtApellido.getText());
					prof.setCorreo(txtCorreo.getText());
					prof.setEspecialidad(txtEspecialidad.getText());
					
					
					boolean correcto = false;
					
					int valor=JOptionPane.showConfirmDialog(null, "¿Desea agregar un nuevo profesor?");
					if (valor==JOptionPane.OK_OPTION) {
						GestionBBDD bd= new GestionBBDD();
						correcto=bd.InsertarProfesor(prof);
						if(correcto) {
							JOptionPane.showMessageDialog(null, "Profesor agregado correctamente.");
						}else {
							JOptionPane.showMessageDialog(null, "Hubo un problema... profesor no agregado");
						}
						txtId.setText("");
						txtApellido.setText("");
						txtCorreo.setText("");
						txtNombre.setText("");
						txtEspecialidad.setText("");
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
				txtId.setText("");
				txtApellido.setText("");
				txtCorreo.setText("");
				txtNombre.setText("");
				txtEspecialidad.setText("");
			}
		});
		btnNewButton_1.setBounds(248, 216, 89, 23);
		add(btnNewButton_1);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(210, 160, 141, 20);
		add(txtEspecialidad);

	}
}
