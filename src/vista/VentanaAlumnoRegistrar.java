package vista;

import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Alumno;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaAlumnoRegistrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;

	/**
	 * Create the panel.
	 */
	public VentanaAlumnoRegistrar() {
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

		JLabel lblNewLabel_4 = new JLabel("Género");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(99, 167, 65, 14);
		add(lblNewLabel_4);

		JComboBox<String> cmbGenero = new JComboBox();
		cmbGenero.setBounds(210, 163, 103, 22);
		add(cmbGenero);
		cmbGenero.addItem("Seleccione");
		cmbGenero.addItem("Masculino");
		cmbGenero.addItem("Femenino");
		cmbGenero.addItem("Otro");

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBackground(new Color(24, 127, 220));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && !txtCorreo.getText().isEmpty()
						&& !txtId.getText().isEmpty() && cmbGenero.getSelectedIndex() != 0) {
					Alumno alum = new Alumno();
					
					alum.setId(txtId.getText());
					alum.setNombre(txtNombre.getText());
					alum.setApellido(txtApellido.getText());
					alum.setCorreo(txtCorreo.getText());
					if(cmbGenero.getSelectedIndex()==1) {
						alum.setGenero("M");
					}if(cmbGenero.getSelectedIndex()==2) {
						alum.setGenero("F");
					}if(cmbGenero.getSelectedIndex()==3) {
						alum.setGenero("Otro");
					}
					
					boolean correcto = false;
					
					int valor=JOptionPane.showConfirmDialog(null, "¿Desea agregar un nuevo alumno?");
					if (valor==JOptionPane.OK_OPTION) {
						GestionBBDD bd= new GestionBBDD();
						correcto=bd.InsertarAlumno(alum);
						if(correcto) {
							JOptionPane.showMessageDialog(null, "Alumno agregado correctamente.");
						}else {
							JOptionPane.showMessageDialog(null, "Hubo un problema... alumno no agregado");
						}
						txtId.setText("");
						txtApellido.setText("");
						txtCorreo.setText("");
						txtNombre.setText("");
						cmbGenero.setSelectedIndex(0);
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
				cmbGenero.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(248, 216, 89, 23);
		add(btnNewButton_1);

	}
}
