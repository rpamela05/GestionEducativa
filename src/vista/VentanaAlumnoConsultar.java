package vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.GestionBBDD;
import modelo.Alumno;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class VentanaAlumnoConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableInfo;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	ArrayList<Alumno> arrAlum = new ArrayList<>();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JComboBox cmbGenero;
	private String ordenFiltro="asc";
	/**
	 * Create the panel.
	 */
	public VentanaAlumnoConsultar() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 414, 206);
		add(scrollPane);

		tableInfo = new JTable();
		scrollPane.setViewportView(tableInfo);

		modeloTabla.setColumnIdentifiers(new Object[] { "Código", "Nombres", "Apellidos", "Correo", "Género" });
		tableInfo.setModel(modeloTabla);

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtCodigo.setBounds(30, 30, 86, 20);
		add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(31, 10, 46, 14);
		add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(187, 10, 55, 14);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(186, 30, 136, 20);
		add(txtNombre);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(393, 10, 46, 14);
		add(lblGenero);

		cmbGenero = new JComboBox();
		cmbGenero.addItem("Todos");
		cmbGenero.addItem("Masculino");
		cmbGenero.addItem("Femenino");
		cmbGenero.addItem("Otro");

		cmbGenero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				aplicarFiltros();
			}
		});
		cmbGenero.setBounds(393, 29, 114, 22);
		add(cmbGenero);
		

		JRadioButton rbAsce = new JRadioButton("Ascendente");
		rbAsce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenFiltro="asc";
				aplicarFiltros();
			}
		});
		rbAsce.setBounds(430, 168, 112, 23);
		add(rbAsce);

		JRadioButton rbDesce = new JRadioButton("Descendente");
		rbDesce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenFiltro="desc";
				aplicarFiltros();
			}
		});
		rbDesce.setBounds(430, 194, 112, 23);
		add(rbDesce);

		ButtonGroup bgFiltro = new ButtonGroup();
		bgFiltro.add(rbAsce);
		bgFiltro.add(rbDesce);
		
		rbAsce.setSelected(true);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(9, 71, 520, 14);
		add(separator);

		GestionBBDD bd = new GestionBBDD();
		arrAlum = bd.ListarAlumno("", "", "", "ASC");

		cargarTabla(arrAlum);

	}

	public void cargarTabla(ArrayList<Alumno> arr) {

		modeloTabla.setRowCount(0);

		for (Alumno c : arr) {
			modeloTabla
					.addRow(new Object[] { c.getId(), c.getNombre(), c.getApellido(), c.getCorreo(), c.getGenero() });
		}
	}

	public void aplicarFiltros() {
		ArrayList<Alumno> aAlumno = new ArrayList<Alumno>();
		String genero = "";
		String nombre = txtNombre.getText().trim();
		String codigo = txtCodigo.getText().trim();
		if (cmbGenero.getSelectedIndex() == 1) {
			genero = "M";
		}
		if (cmbGenero.getSelectedIndex() == 2) {
			genero = "F";
		}
		if (cmbGenero.getSelectedIndex() == 3) {
			genero = "Otro";
		}if(cmbGenero.getSelectedIndex() == 0){
			genero="";
		}

		GestionBBDD bd = new GestionBBDD();
		aAlumno = bd.ListarAlumno(nombre, codigo, genero, ordenFiltro);
		cargarTabla(aAlumno);
	}
}
