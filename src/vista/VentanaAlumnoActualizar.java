package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Alumno;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaAlumnoActualizar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtId;
	private JTextField txtCorreo;

	/**
	 * Create the panel.
	 */
	public VentanaAlumnoActualizar() {
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

		JComboBox cmbGenero = new JComboBox();
		cmbGenero.setBounds(188, 158, 103, 22);
		add(cmbGenero);
		cmbGenero.addItem("Masculino");
		cmbGenero.addItem("Femenino");
		cmbGenero.addItem("Otro");

		JLabel lblNewLabel_4 = new JLabel("Género");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(80, 162, 65, 14);
		add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(80, 129, 65, 14);
		add(lblNewLabel_3);

		txtNombre.setEditable(false);
		txtApellidos.setEditable(false);
		txtCorreo.setEditable(false);
		cmbGenero.setEnabled(false);
		


		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(24, 127, 220));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alumno alm = new Alumno();
				alm.setId(txtId.getText());
				alm.setNombre(txtNombre.getText());
				alm.setApellido(txtApellidos.getText());
				alm.setCorreo(txtCorreo.getText());
				if (cmbGenero.getSelectedIndex() == 0) {
					alm.setGenero("M");
				}
				if (cmbGenero.getSelectedIndex() == 1) {
					alm.setGenero("F");
				}
				if (cmbGenero.getSelectedIndex() == 2) {
					alm.setGenero("Otro");
				}

				boolean correcto = false;
				int valor = JOptionPane.showConfirmDialog(null, "¿Desea actualizar alumno?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.OK_OPTION) {
					GestionBBDD bd = new GestionBBDD();
					correcto = bd.ActualizarAlumno(alm);
					if (correcto) {
						JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema... alumno no actualizado");
					}
				}
				txtId.setEditable(true);
				txtId.setText("");
				txtNombre.setText("");
				txtApellidos.setText("");
				txtCorreo.setText("");
				cmbGenero.setSelectedIndex(0);
				txtNombre.setEditable(false);
				txtApellidos.setEditable(false);
				txtCorreo.setEditable(false);
				cmbGenero.setEnabled(false);
				btnNewButton_1.setEnabled(false);

			}
		});
		btnNewButton_1.setBounds(120, 212, 103, 23);
		add(btnNewButton_1);
		
		btnNewButton_1.setEnabled(false);
		

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(24, 127, 220));
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
						txtApellidos.setText(alm.getApellido());
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

						txtNombre.setEditable(true);
						txtApellidos.setEditable(true);
						txtCorreo.setEditable(true);
						cmbGenero.setEnabled(true);
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
				cmbGenero.setSelectedIndex(0);
				txtNombre.setEditable(false);
				txtApellidos.setEditable(false);
				txtCorreo.setEditable(false);
				cmbGenero.setEnabled(false);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(253, 212, 89, 23);
		add(btnNewButton_2);

	}

}
