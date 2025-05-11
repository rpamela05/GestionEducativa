package vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.GestionBBDD;
import modelo.Alumno;
import modelo.Curso;
import modelo.Profesor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class VentanaCursoConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableInfo;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	ArrayList<Curso> arrCur = new ArrayList<>();
	private JTextField txtCodigo;
	private JTextField txtCurso;
	private String horas="0";
	private String orden="asc";
	private String codigoProfesor = "";
	/**
	 * Create the panel.
	 */
	public VentanaCursoConsultar() {
		setBackground(new Color(51, 204, 255));
		setLayout(null);
		
		JLabel lblHoras = new JLabel("0");
		lblHoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoras.setBounds(268, 49, 21, 14);
		add(lblHoras);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 414, 206);
		add(scrollPane);
		
		tableInfo = new JTable();
		scrollPane.setViewportView(tableInfo);
		
		modeloTabla.setColumnIdentifiers(new Object[] { "Código", "Asignatura", "N.° Horas", "Profesor"});
		tableInfo.setModel(modeloTabla);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, 10, 46, 14);
		add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(39, 30, 40, 20);
		add(txtCodigo);
		
		txtCurso = new JTextField();
		txtCurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtCurso.setColumns(10);
		txtCurso.setBounds(96, 30, 112, 20);
		add(txtCurso);
		
		JLabel lblNombre = new JLabel("Curso");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(97, 10, 55, 14);
		add(lblNombre);
		
		JComboBox cmbProfesor = new JComboBox();
		cmbProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbProfesor.getSelectedIndex()==0) {
					codigoProfesor="";
				}else {
					codigoProfesor = cmbProfesor.getSelectedItem().toString().split(" - ")[0];
				}
				aplicarFiltros();
			}
		});
		cmbProfesor.setBounds(356, 29, 145, 22);
		add(cmbProfesor);
		
		JLabel lblGenero = new JLabel("Profesor");
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setBounds(356, 10, 46, 14);
		add(lblGenero);
		
		JRadioButton rbAsce = new JRadioButton("Ascendente");
		rbAsce.setForeground(Color.WHITE);
		rbAsce.setBackground(new Color(51, 204, 255));
		rbAsce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden="asc";
				aplicarFiltros();
			}
		});
		rbAsce.setSelected(true);
		rbAsce.setBounds(430, 168, 112, 23);
		add(rbAsce);
		
		JRadioButton rbDesce = new JRadioButton("Descendente");
		rbDesce.setForeground(Color.WHITE);
		rbDesce.setBackground(new Color(51, 204, 255));
		rbDesce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden="desc";
				aplicarFiltros();
			}
		});
		rbDesce.setBounds(430, 194, 112, 23);
		add(rbDesce);
		
		ButtonGroup bgOrden=new ButtonGroup();
		bgOrden.add(rbAsce);
		bgOrden.add(rbDesce);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 520, 14);
		add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("N.° Horas");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(224, 10, 91, 14);
		add(lblNewLabel_1);
		
		cmbProfesor.addItem("Todos");
		
		cargarComboProfesor(cmbProfesor);
		
		JSlider sldHoras = new JSlider();
		sldHoras.setBackground(new Color(51, 204, 255));
		sldHoras.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				horas= sldHoras.getValue()+"";
				lblHoras.setText(horas);
				aplicarFiltros();
			}
		});
		sldHoras.setValue(0);
		sldHoras.setMaximum(300);
		sldHoras.setBounds(218, 26, 130, 23);
		add(sldHoras);
		
		GestionBBDD bd= new GestionBBDD();
		arrCur=bd.ListarCurso("","","","","asc");
		cargarTabla(arrCur);

	}
	
	public void cargarTabla(ArrayList<Curso> aCur) {
		modeloTabla.setRowCount(0);

		for (Curso c : aCur) {
			modeloTabla.addRow(new Object[] { c.getCodigo(), c.getNombre(), c.getHoras(), c.getId_docente() });
		}
	}
	
	public void aplicarFiltros() {
		ArrayList<Curso> aCurso = new ArrayList<Curso>();
		String curso = txtCurso.getText().trim();
		String codigo = txtCodigo.getText().trim();

		GestionBBDD bd = new GestionBBDD();
		aCurso = bd.ListarCurso(codigo, curso, horas, codigoProfesor,orden);
		cargarTabla(aCurso);
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
