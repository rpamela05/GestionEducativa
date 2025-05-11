package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnAlumno;
	private JMenu mnProfesor;
	private JMenu mnAsignatura;
	private JMenu mnMatricula;
	private JMenuItem mniAgregarA;
	private JMenuItem mniConsultarA;
	private JMenuItem mniEditarA;
	private JMenuItem mniEliminarA;
	private JMenuItem mniAgregarP;
	private JMenuItem mniConsultarP;
	private JMenuItem mniEditarP;
	private JMenuItem mniEliminarP;
	private JMenuItem mniAgregarAs;
	private JMenuItem mniConsultarAs;
	private JMenuItem mniEditarAs;
	private JMenuItem mniEliminarAs;
	private JMenuItem mniAgregarM;
	private JMenuItem mniConsultarM;
	private JMenuItem mniEditarM;
	
	private String logo_alumno="/icons/estudiante.png";
	private String logo_profesor="/icons/profesor.png";
	private String logo_asignatura="/icons/cursos.png";
	private String logo_matricula="/icons/matricula.png";
	private String logo_agregar="/icons/agregar.png";
	private String logo_editar="/icons/editar.png";
	private String logo_listar="/icons/consulta.png";
	private String logo_eliminar="/icons/eliminar.png";
	
	
	Color mColorFondo = new Color(24, 127, 220);
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanPrincipal frame = new VentanPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanPrincipal() {
		setTitle("Gestion Educativa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 391);

		Image icono = Toolkit.getDefaultToolkit().getImage("img/icono.png");
		setIconImage(icono);

		JMenuBar mbMenu = new JMenuBar();
		setJMenuBar(mbMenu);
		mbMenu.setBackground(mColorFondo);
		mbMenu.setForeground(Color.WHITE); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rpame\\eclipse-workspace\\Gestion_Educativa\\img\\fondo.png"));
		contentPane.add(lblNewLabel, "name_79588369914000");
		
		mnAlumno=new JMenu(" Alumno ");
		mnAlumno.setIcon(getIcono(logo_alumno));
		mnProfesor=new JMenu(" Profesor ");
		mnProfesor.setIcon(getIcono(logo_profesor));
		mnAsignatura=new JMenu(" Asignatura ");
		mnAsignatura.setIcon(getIcono(logo_asignatura));
		mnMatricula= new JMenu(" Matrícula ");
		mnMatricula.setIcon(getIcono(logo_matricula));
		
		
		mniAgregarA=new JMenuItem(" Agregar ",getIcono(logo_agregar));
		mniAgregarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoRegistrar var= new VentanaAlumnoRegistrar();
				cambiarPanel(var);
			}
		});
		mniConsultarA=new JMenuItem(" Consultar ",getIcono(logo_listar));
		mniConsultarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoConsultar vac= new VentanaAlumnoConsultar();
				cambiarPanel(vac);
			}
		});
		mniEditarA = new JMenuItem(" Editar ",getIcono(logo_editar));
		mniEditarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoActualizar vaa= new VentanaAlumnoActualizar();
				cambiarPanel(vaa);
			}
		});
		mniEliminarA= new JMenuItem(" Eliminar ",getIcono(logo_eliminar));
		mniEliminarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoEliminar vae= new VentanaAlumnoEliminar();
				cambiarPanel(vae);
			}
		});
		mniAgregarP=new JMenuItem(" Agregar ",getIcono(logo_agregar));
		mniAgregarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorRegistrar vpr= new VentanaProfesorRegistrar();
				cambiarPanel(vpr);
			}
		});
		mniConsultarP=new JMenuItem(" Consultar ",getIcono(logo_listar));
		mniConsultarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorConsultar vpc= new VentanaProfesorConsultar();
				cambiarPanel(vpc);
			}
		});
		mniEditarP = new JMenuItem(" Editar ",getIcono(logo_editar));
		mniEditarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorActualizar vpa= new VentanaProfesorActualizar();
				cambiarPanel(vpa);
			}
		});
		mniEliminarP= new JMenuItem(" Eliminar ",getIcono(logo_eliminar));
		mniEliminarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorEliminar vpe= new VentanaProfesorEliminar();
				cambiarPanel(vpe);
			}
		});
		mniAgregarAs=new JMenuItem(" Agregar ",getIcono(logo_agregar));
		mniAgregarAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoRegistrar vcr= new VentanaCursoRegistrar();
				cambiarPanel(vcr);
			}
		});
		mniConsultarAs=new JMenuItem(" Consultar ",getIcono(logo_listar));
		mniConsultarAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoConsultar vcc= new VentanaCursoConsultar();
				cambiarPanel(vcc);
			}
		});
		mniEditarAs = new JMenuItem(" Editar ",getIcono(logo_editar));
		mniEditarAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoActualizar vca= new VentanaCursoActualizar();
				cambiarPanel(vca);
			}
		});
		mniEliminarAs= new JMenuItem(" Eliminar ",getIcono(logo_eliminar));
		mniEliminarAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoEliminar vce= new VentanaCursoEliminar();
				cambiarPanel(vce);
			}
		});
		mniAgregarM=new JMenuItem(" Agregar ",getIcono(logo_agregar));
		mniAgregarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMatriculaRegistrar vmr= new VentanaMatriculaRegistrar();
				cambiarPanel(vmr);
			}
		});
		mniConsultarM=new JMenuItem(" Consultar ",getIcono(logo_listar));
		mniConsultarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMatriculaConsultar vmc= new VentanaMatriculaConsultar();
				cambiarPanel(vmc);
			}
		});
		mniEditarM = new JMenuItem(" Editar ",getIcono(logo_editar));
		mniEditarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMatriculaActualizarAnular vmaa= new VentanaMatriculaActualizarAnular();
				cambiarPanel(vmaa);
			}
		});
		
		mnAlumno.setOpaque(true);
		mnAlumno.setBackground(mColorFondo);
		mnAlumno.setForeground(Color.white);
		mnProfesor.setOpaque(true);
		mnProfesor.setBackground(mColorFondo);
		mnProfesor.setForeground(Color.white);
		mnAsignatura.setOpaque(true);
		mnAsignatura.setBackground(mColorFondo);
		mnAsignatura.setForeground(Color.white);
		mnMatricula.setOpaque(true);
		mnMatricula.setBackground(mColorFondo);
		mnMatricula.setForeground(Color.white);
		
		mniAgregarA.setOpaque(true);
		mniAgregarA.setBackground(mColorFondo);
		mniAgregarA.setForeground(Color.white);
		mniAgregarAs.setOpaque(true);
		mniAgregarAs.setBackground(mColorFondo);
		mniAgregarAs.setForeground(Color.white);
		mniAgregarP.setOpaque(true);
		mniAgregarP.setBackground(mColorFondo);
		mniAgregarP.setForeground(Color.white);
		mniAgregarM.setOpaque(true);
		mniAgregarM.setBackground(mColorFondo);
		mniAgregarM.setForeground(Color.white);
		mniEditarA.setOpaque(true);
		mniEditarA.setBackground(mColorFondo);
		mniEditarA.setForeground(Color.white);
		mniEditarAs.setOpaque(true);
		mniEditarAs.setBackground(mColorFondo);
		mniEditarAs.setForeground(Color.white);
		mniEditarP.setOpaque(true);
		mniEditarP.setBackground(mColorFondo);
		mniEditarP.setForeground(Color.white);
		mniEditarM.setOpaque(true);
		mniEditarM.setBackground(mColorFondo);
		mniEditarM.setForeground(Color.white);
		mniConsultarA.setOpaque(true);
		mniConsultarA.setBackground(mColorFondo);
		mniConsultarA.setForeground(Color.white);
		mniConsultarAs.setOpaque(true);
		mniConsultarAs.setBackground(mColorFondo);
		mniConsultarAs.setForeground(Color.white);
		mniConsultarP.setOpaque(true);
		mniConsultarP.setBackground(mColorFondo);
		mniConsultarP.setForeground(Color.white);
		mniConsultarM.setOpaque(true);
		mniConsultarM.setBackground(mColorFondo);
		mniConsultarM.setForeground(Color.white);
		mniEliminarA.setOpaque(true);
		mniEliminarA.setBackground(mColorFondo);
		mniEliminarA.setForeground(Color.white);
		mniEliminarAs.setOpaque(true);
		mniEliminarAs.setBackground(mColorFondo);
		mniEliminarAs.setForeground(Color.white);
		mniEliminarP.setOpaque(true);
		mniEliminarP.setBackground(mColorFondo);
		mniEliminarP.setForeground(Color.white);
		
		
		mbMenu.add(mnAlumno);
		mbMenu.add(mnProfesor);
		mbMenu.add(mnAsignatura);
		mbMenu.add(mnMatricula);
		
		mnAlumno.add(mniAgregarA);
		mnAlumno.add(mniConsultarA);
		mnAlumno.add(mniEditarA);
		mnAlumno.add(mniEliminarA);
		
		mnProfesor.add(mniAgregarP);
		mnProfesor.add(mniConsultarP);
		mnProfesor.add(mniEditarP);
		mnProfesor.add(mniEliminarP);
		
		mnAsignatura.add(mniAgregarAs);
		mnAsignatura.add(mniConsultarAs);
		mnAsignatura.add(mniEditarAs);
		mnAsignatura.add(mniEliminarAs);
		
		mnMatricula.add(mniAgregarM);
		mnMatricula.add(mniConsultarM);
		mnMatricula.add(mniEditarM);
	}
	private Icon getIcono(String ruta){
        return new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage().getScaledInstance(30, 30, 0));
    }

	public void cambiarPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();

	}


}
