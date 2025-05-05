package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Alumno");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoRegistrar var = new VentanaAlumnoRegistrar();
				cambiarPanel(var);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Consultar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoConsultar vac = new VentanaAlumnoConsultar();
				cambiarPanel(vac);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Actualizar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoActualizar vaa = new VentanaAlumnoActualizar();
				cambiarPanel(vaa);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Eliminar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAlumnoEliminar vae = new VentanaAlumnoEliminar();
				cambiarPanel(vae);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Profesor");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrar");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorRegistrar vpr = new VentanaProfesorRegistrar();
				cambiarPanel(vpr);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Consultar");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorConsultar vpc = new VentanaProfesorConsultar();
				cambiarPanel(vpc);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Actualizar");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorActualizar vpa = new VentanaProfesorActualizar();
				cambiarPanel(vpa);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Eliminar");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProfesorEliminar vpe = new VentanaProfesorEliminar();
				cambiarPanel(vpe);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);

		JMenu mnNewMenu_3 = new JMenu("Curso");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Registrar");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoRegistrar vcr = new VentanaCursoRegistrar();
				cambiarPanel(vcr);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_12);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Consultar");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoConsultar vcc = new VentanaCursoConsultar();
				cambiarPanel(vcc);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_13);

		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Actualizar");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoActualizar vca = new VentanaCursoActualizar();
				cambiarPanel(vca);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_14);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Eliminar");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCursoEliminar vce = new VentanaCursoEliminar();
				cambiarPanel(vce);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_15);

		JMenu mnNewMenu_2 = new JMenu("Matricula");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMatriculaRegistrar vmr = new VentanaMatriculaRegistrar();
				cambiarPanel(vmr);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Consultar");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMatriculaConsultar vmc = new VentanaMatriculaConsultar();
				cambiarPanel(vmc);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_9);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Actualizar | Anular");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMatriculaActualizarAnular vma = new VentanaMatriculaActualizarAnular();
				cambiarPanel(vma);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

	}

	public void cambiarPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();

	}


}
