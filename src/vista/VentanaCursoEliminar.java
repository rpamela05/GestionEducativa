package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Curso;
import modelo.Profesor;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaCursoEliminar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtHoras;

	/**
	 * Create the panel.
	 */
	public VentanaCursoEliminar() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(78, 52, 57, 14);
		add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(187, 48, 65, 20);
		add(txtCodigo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(187, 81, 141, 20);
		add(txtNombre);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(78, 85, 65, 14);
		add(lblNewLabel_1);
		
		JLabel Horas = new JLabel("Horas");
		Horas.setBounds(78, 118, 65, 14);
		add(Horas);
		
		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		txtHoras.setBounds(187, 114, 65, 20);
		add(txtHoras);
		
		JLabel lblNewLabel_3 = new JLabel("Profesor");
		lblNewLabel_3.setBounds(78, 151, 65, 14);
		add(lblNewLabel_3);
		
		JComboBox cmbProfesor = new JComboBox();
		cmbProfesor.setBounds(187, 145, 161, 22);
		add(cmbProfesor);
		
		txtNombre.setEditable(false);
		txtHoras.setEditable(false);
		cmbProfesor.setEnabled(false);
		cargarComboProfesor(cmbProfesor);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean correcto = false;
				int valor = JOptionPane.showConfirmDialog(null, "¿Desea eliminar curso?","Confirmar",JOptionPane.YES_NO_OPTION);
				if (valor == JOptionPane.OK_OPTION) {
					GestionBBDD bd = new GestionBBDD();
					correcto = bd.EliminarCurso(txtCodigo.getText());
					if (correcto) {
						JOptionPane.showMessageDialog(null, "Curso eliminado correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un problema... curso no eliminado");
					}
				}
				txtCodigo.setText("");
				txtNombre.setText("");
				txtHoras.setText("");
				cmbProfesor.setSelectedIndex(0);
				txtCodigo.setEditable(true);
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
				Profesor p=new Profesor();
				if (id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					txtCodigo.setEditable(true);
				} else {
					GestionBBDD bd = new GestionBBDD();

					Curso cur = bd.ConsultarCurso(id);
					
					if(cur.getCodigo().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontro el curso", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
						txtCodigo.setEditable(true);
					}else {
						txtNombre.setText(cur.getNombre());
						txtHoras.setText(cur.getHoras()+"");
						p=bd.ConsultarProfesor(cur.getId_docente());
						cmbProfesor.setSelectedItem(p.getId()+" - "+ p.getNombre()+" "+p.getApellido());
						txtNombre.setEditable(false);
						txtHoras.setEditable(false);
						cmbProfesor.setEnabled(false);
						btnNewButton_1.setEnabled(true);
					}
				}
			}
		});
		btnNewButton.setBounds(277, 51, 84, 18);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setEditable(true);
				txtCodigo.setText("");
				txtNombre.setText("");
				txtHoras.setText("");
				cmbProfesor.setSelectedIndex(0);
				btnNewButton_1.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(253, 212, 89, 23);
		add(btnNewButton_2);
		
		
		
		txtNombre.setEditable(false);
		txtHoras.setEditable(false);
		cmbProfesor.setEnabled(false);
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
