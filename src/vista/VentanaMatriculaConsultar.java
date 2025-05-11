package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controlador.GestionBBDD;
import modelo.Alumno;
import modelo.Matricula;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class VentanaMatriculaConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableInfo;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	ArrayList<Matricula> arrMat = new ArrayList<Matricula>();
	private JTextField txtAlumno;
	private JTextField txtCodigo;
	private JTextField txtCurso;
	private String orden="asc";
	private boolean estado=true;
	/**
	 * Create the panel.
	 */
	public VentanaMatriculaConsultar() {
		setBackground(new Color(51, 204, 255));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 414, 206);
		add(scrollPane);
		
		tableInfo = new JTable();
		scrollPane.setViewportView(tableInfo);
		
		modeloTabla.setColumnIdentifiers(new Object[] { "Código", "Alumno", "Curso", "Fecha", "¿Activo?"});
		tableInfo.setModel(modeloTabla);
		
		JRadioButton rbDesce = new JRadioButton("Descendente");
		rbDesce.setBackground(new Color(51, 204, 255));
		rbDesce.setForeground(Color.WHITE);
		rbDesce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden="desc";
				aplicarFiltros();
			}
		});
		rbDesce.setBounds(430, 248, 112, 23);
		add(rbDesce);
		
		JRadioButton rbAsce = new JRadioButton("Ascendente");
		rbAsce.setBackground(new Color(51, 204, 255));
		rbAsce.setForeground(Color.WHITE);
		rbAsce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orden="asc";
				aplicarFiltros();
			}
		});
		rbAsce.setSelected(true);
		rbAsce.setBounds(430, 222, 112, 23);
		add(rbAsce);
		
		txtAlumno = new JTextField();
		txtAlumno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtAlumno.setColumns(10);
		txtAlumno.setBounds(173, 30, 129, 20);
		add(txtAlumno);
		
		JLabel lblNombre = new JLabel("Alumno");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(174, 10, 55, 14);
		add(lblNombre);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(39, 30, 76, 20);
		add(txtCodigo);
		
		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, 10, 55, 14);
		add(lblNewLabel);
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(11, 71, 520, 14);
		add(separator);
		
		JRadioButton rbSi = new JRadioButton("Si");
		rbSi.setBackground(new Color(51, 204, 255));
		rbSi.setForeground(Color.WHITE);
		rbSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estado=true;
				aplicarFiltros();
			}
		});
		rbSi.setSelected(true);
		rbSi.setBounds(430, 127, 109, 23);
		add(rbSi);
		
		JRadioButton rbNo = new JRadioButton("No");
		rbNo.setBackground(new Color(51, 204, 255));
		rbNo.setForeground(Color.WHITE);
		rbNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estado=false;
				aplicarFiltros();
			}
		});
		rbNo.setBounds(430, 153, 109, 23);
		add(rbNo);
		
		ButtonGroup bgActivo=new ButtonGroup();
		bgActivo.add(rbSi);
		bgActivo.add(rbNo);
		
		ButtonGroup bgOrden=new ButtonGroup();
		bgOrden.add(rbAsce);
		bgOrden.add(rbDesce);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(434, 191, 112, 8);
		add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("¿Activo?");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(454, 104, 69, 14);
		add(lblNewLabel_1);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setForeground(Color.WHITE);
		lblCurso.setBounds(354, 10, 55, 14);
		add(lblCurso);
		
		txtCurso = new JTextField();
		txtCurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		txtCurso.setColumns(10);
		txtCurso.setBounds(354, 30, 137, 20);
		add(txtCurso);

		Calendar calDesde = Calendar.getInstance();
		calDesde.set(2025, Calendar.JANUARY, 1);

		
		GestionBBDD bd= new GestionBBDD();
		arrMat=bd.ListarMatricula("","","",true,"asc");
		cargarTabla(arrMat);

	}
	
	public void cargarTabla(ArrayList<Matricula> aMat) {
		modeloTabla.setRowCount(0);
		
		for (Matricula m : aMat) {
			String estado="";
			if(m.isEstado()) {
				estado="Si";
			}else {
				estado="No";
			}
			LocalDate fecha = LocalDate.parse(m.getFecha(), DateTimeFormatter.ofPattern("yyy-MM-dd"));
			modeloTabla.addRow(new Object[] { m.getId(), m.getNomAlumno(), m.getNomCurso(), fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					estado });
		}
	}
	
	public void aplicarFiltros() {
		ArrayList<Matricula> aMatriculas = new ArrayList<Matricula>();
		String codigo = txtCodigo.getText().trim();
		String alumno = txtAlumno.getText().trim();
		String curso = txtCurso.getText().trim();
		
		

		GestionBBDD bd = new GestionBBDD();
		aMatriculas = bd.ListarMatricula(codigo, alumno, curso, estado,orden);
		cargarTabla(aMatriculas);
	}
}
