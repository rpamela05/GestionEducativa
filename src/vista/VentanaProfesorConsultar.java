package vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.GestionBBDD;
import modelo.Alumno;
import modelo.Profesor;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class VentanaProfesorConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableInfo;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	ArrayList<Profesor> arrProf = new ArrayList<>();
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JTextField txtEspecialidad;
	private String ordenFiltro="asc";
	/**
	 * Create the panel.
	 */
	public VentanaProfesorConsultar() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 414, 206);
		add(scrollPane);
		
		tableInfo = new JTable();
		scrollPane.setViewportView(tableInfo);
		
		modeloTabla.setColumnIdentifiers(new Object[] { "Código", "Nombres", "Apellidos", "Correo", "Especialidad"});
		tableInfo.setModel(modeloTabla);
		
		JRadioButton rbAsce = new JRadioButton("Ascendente");
		rbAsce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenFiltro="asc";
				aplicarFiltros();
			}
		});
		rbAsce.setBounds(436, 168, 112, 23);
		add(rbAsce);
		
		JRadioButton rbDesce = new JRadioButton("Descendente");
		rbDesce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenFiltro="desc";
				aplicarFiltros();
			}
		});
		rbDesce.setBounds(436, 194, 112, 23);
		add(rbDesce);
		
		ButtonGroup bgOrden= new ButtonGroup();
		bgOrden.add(rbAsce);
		bgOrden.add(rbDesce);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(195, 30, 136, 20);
		add(txtNombre);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(39, 30, 86, 20);
		add(txtCodigo);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(40, 10, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(196, 10, 55, 14);
		add(lblNombre);
		
		JLabel lblGenero = new JLabel("Especialidad");
		lblGenero.setBounds(394, 10, 86, 14);
		add(lblGenero);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(394, 30, 136, 20);
		add(txtEspecialidad);
		rbAsce.setSelected(true);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 520, 14);
		add(separator);
		
		GestionBBDD bd= new GestionBBDD();
		arrProf=bd.ListarProfesor("","","","asc");
		cargarTabla(arrProf);

	}
	
	public void cargarTabla(ArrayList<Profesor> arrP) {
		modeloTabla.setRowCount(0);

		for (Profesor c : arrP) {
			modeloTabla.addRow(new Object[] { c.getId(), c.getNombre(), c.getApellido(), c.getCorreo(),
					c.getEspecialidad() });
		}
	}
	public void aplicarFiltros() {
		ArrayList<Profesor> aProfesor = new ArrayList<Profesor>();
		String especialidad = txtEspecialidad.getText().trim();
		String nombre = txtNombre.getText().trim();
		String codigo = txtCodigo.getText().trim();

		GestionBBDD bd = new GestionBBDD();
		aProfesor = bd.ListarProfesor(codigo, nombre, especialidad,ordenFiltro);
		cargarTabla(aProfesor);
	}
}
