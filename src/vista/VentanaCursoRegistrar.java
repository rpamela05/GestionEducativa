package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.GestionBBDD;
import modelo.Curso;
import modelo.Profesor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaCursoRegistrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtHoras;
	private String codigoProfesor = "";
	/**
	 * Create the panel.
	 */
	public VentanaCursoRegistrar() {
		setToolTipText("");
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(99, 58, 46, 14);
		add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(221, 55, 65, 20);
		add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(99, 91, 65, 14);
		add(lblNewLabel_1);

		txtNombre = new JTextField();
		txtNombre.setBounds(221, 88, 118, 20);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("N.º de horas");
		lblNewLabel_2.setBounds(99, 124, 89, 14);
		add(lblNewLabel_2);

		txtHoras = new JTextField();
		txtHoras.setBounds(221, 121, 51, 20);
		add(txtHoras);
		txtHoras.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Profesor");
		lblNewLabel_3.setBounds(99, 157, 65, 14);
		add(lblNewLabel_3);
		
		txtCodigo.setEditable(false);
		
		
		
		
		
		JComboBox cmbProf = new JComboBox();
		cmbProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionado= cmbProf.getSelectedItem().toString();
				codigoProfesor = seleccionado.split(" - ")[0];
			}
		});
		cmbProf.setBounds(221, 152, 173, 22);
		add(cmbProf);
		cmbProf.addItem("Seleccione");
		cargarComboProfesor(cmbProf);
		
		txtCodigo.setText(cargarNumeroCodigo());
		
		
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNombre.getText().isEmpty() && !txtHoras.getText().isEmpty() 
						&& !txtCodigo.getText().isEmpty() && cmbProf.getSelectedIndex() != 0) {
					Curso cur = new Curso();
					
					cur.setCodigo(txtCodigo.getText());
					cur.setNombre(txtNombre.getText());
					cur.setHoras(Integer.parseInt(txtHoras.getText()));
					cur.setId_docente(codigoProfesor);
					
					
					boolean correcto = false;
					
					int valor=JOptionPane.showConfirmDialog(null, "¿Desea agregar un nuevo curso?");
					if (valor==JOptionPane.OK_OPTION) {
						GestionBBDD bd= new GestionBBDD();
						correcto=bd.InsertarCurso(cur);
						if(correcto) {
							JOptionPane.showMessageDialog(null, "Curso agregado correctamente.");
						}else {
							JOptionPane.showMessageDialog(null, "Hubo un problema... curso no agregado");
						}
						txtCodigo.setText(cargarNumeroCodigo());
						txtHoras.setText("");
						txtNombre.setText("");
						cmbProf.setSelectedIndex(0);
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
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtHoras.setText("");
				txtNombre.setText("");
				cmbProf.setSelectedIndex(0);
			}
		});
		btnNewButton_1.setBounds(248, 216, 89, 23);
		add(btnNewButton_1);
		
		

	}
	
	public void cargarComboProfesor(JComboBox<String> cmb) {
		ArrayList<Profesor> arrProf=new ArrayList<Profesor>();
		GestionBBDD bd=new GestionBBDD();
		arrProf=bd.ListarProfesor("","","","asc");
		for (Profesor c : arrProf) {
			cmb.addItem(c.getId()+" - "+ c.getNombre()+" "+c.getApellido());
		}
	}
	
	//Llenar el codigo con el ultimo codigo ingresado mas 1
	public String cargarNumeroCodigo() {
		ArrayList<Curso> arrCurso=new ArrayList<Curso>();
		GestionBBDD bd = new GestionBBDD();
		arrCurso=bd.ListarCurso("","","","","asc");
		int num;
		if(arrCurso.size()==0) {
			num=1;
		}else {
			num=(bd.MaxCodigoCurso())+1;
		}
		return num+"";
	}
}
