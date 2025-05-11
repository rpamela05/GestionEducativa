package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Curso;
import modelo.Profesor;
import controlador.GestionBBDD;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaCursoActualizar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtHoras;
	private JTextField txtCodigo;
	private String codigoProfesor = "";
	/**
	 * Create the panel.
	 */
	public VentanaCursoActualizar() {
		setBackground(new Color(51, 204, 255));
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(79, 78, 65, 14);
		add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(187, 75, 141, 20);
		add(txtNombre);

		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		txtHoras.setBounds(187, 108, 65, 20);
		add(txtHoras);

		JLabel lblNewLabel_2 = new JLabel("N.º de horas");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(79, 111, 84, 14);
		add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(79, 45, 65, 14);
		add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(187, 42, 65, 20);
		add(txtCodigo);

		JComboBox cmbProfesor = new JComboBox();
		cmbProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codigoProfesor = cmbProfesor.getSelectedItem().toString().split(" - ")[0];
			}
		});
		cmbProfesor.setBounds(187, 139, 170, 22);
		add(cmbProfesor);


		

		JLabel lblNewLabel_3 = new JLabel("Profesor");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(79, 144, 65, 14);
		add(lblNewLabel_3);

		txtNombre.setEditable(false);
		txtHoras.setEditable(false);
		cmbProfesor.setEnabled(false);
		
		cargarComboProfesor(cmbProfesor);

		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.setBackground(new Color(24, 127, 220));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Curso cur = new Curso();
				cur.setCodigo(txtCodigo.getText());
				cur.setNombre(txtNombre.getText());
				cur.setHoras(Integer.parseInt(txtHoras.getText()));
				cur.setId_docente(codigoProfesor);

				boolean correcto = false;
				int valor = JOptionPane.showConfirmDialog(null, "¿Desea actualizar asignatura?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.OK_OPTION) {
					GestionBBDD bd = new GestionBBDD();
					correcto = bd.ActualizarCurso(cur);
					if (correcto) {
						JOptionPane.showMessageDialog(null, "Asignatura actualizada correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema... asignatura no actualizada");
					}
				}
				txtCodigo.setEditable(true);
				txtCodigo.setText("");
				txtNombre.setText("");
				txtHoras.setText("");
				cmbProfesor.setSelectedIndex(0);
				txtNombre.setEditable(false);
				txtHoras.setEditable(false);
				cmbProfesor.setEnabled(false);
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
				txtCodigo.setEditable(false);
				String id = txtCodigo.getText();
				Profesor p=new Profesor();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					txtCodigo.setEditable(true);
				} else {
					GestionBBDD bd = new GestionBBDD();

					Curso cur = bd.ConsultarCurso(id);
					
					if(cur.getCodigo().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontro la asignatura", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
						txtCodigo.setEditable(true);
					}else {
						txtNombre.setText(cur.getNombre());
						txtHoras.setText(cur.getHoras()+"");
						p=bd.ConsultarProfesor(cur.getId_docente());
						cmbProfesor.setSelectedItem(p.getId()+" - "+ p.getNombre()+" "+p.getApellido());
						txtNombre.setEditable(true);
						txtHoras.setEditable(true);
						cmbProfesor.setEnabled(true);
						btnNewButton_1.setEnabled(true);
					}
				}
			}
		});
		btnNewButton.setBounds(273, 43, 84, 18);
		add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.setBackground(new Color(24, 127, 220));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setEditable(true);
				txtCodigo.setText("");
				txtNombre.setText("");
				txtHoras.setText("");
				cmbProfesor.setSelectedIndex(0);
				txtNombre.setEditable(false);
				txtHoras.setEditable(false);
				cmbProfesor.setEnabled(false);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(253, 212, 89, 23);
		add(btnNewButton_2);

	}
	
	public void cargarComboProfesor(JComboBox<String> cmb) {
		ArrayList<Profesor> arrProf=new ArrayList<Profesor>();
		GestionBBDD bd=new GestionBBDD();
		arrProf=bd.ListarProfesor("","","","asc");
		for (Profesor p : arrProf) {
			cmb.addItem(p.getId()+" - "+ p.getNombre()+" "+p.getApellido());
		}
	}

}
