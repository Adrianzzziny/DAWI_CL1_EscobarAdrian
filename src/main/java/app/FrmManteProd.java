package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Libro;
import model.Tema;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	JComboBox cboTemas;
	private JTextField txtTitulo;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtOrigen;
	private JTable tablaRegistro;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(23, 172, 89, 23);
		contentPane.add(btnNewButton);
		
		cboTemas = new JComboBox();
		cboTemas.setBounds(122, 129, 110, 22);
		contentPane.add(cboTemas);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setBounds(10, 133, 102, 14);
		contentPane.add(lblTema);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(10, 11, 102, 14);
		contentPane.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(122, 8, 144, 20);
		contentPane.add(txtTitulo);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 70, 102, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(122, 67, 110, 20);
		contentPane.add(txtCantidad);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 42, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 39, 110, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(10, 97, 46, 14);
		contentPane.add(lblOrigen);
		
		txtOrigen = new JTextField();
		txtOrigen.setBounds(122, 98, 110, 20);
		contentPane.add(txtOrigen);
		txtOrigen.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 414, 144);
		contentPane.add(scrollPane);
		
		tablaRegistro = new JTable();
		tablaRegistro.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Titulo", "Precio", "Cantidad", "Origen", "Tema"
			}
		));
		scrollPane.setViewportView(tablaRegistro);
		
		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		List<Tema> lstTemas = em.createQuery("select a from Tema a", Tema.class).getResultList();
		
		cboTemas.addItem("Seleccione");
		for (Tema t : lstTemas) {
			System.out.println(t);
			cboTemas.addItem(t.getNombre());
		}
		em.close();
	}
	
	
	void registrar() {
		String titulo = txtTitulo.getText();
		double precio = Double.parseDouble(txtPrecio.getText());
		int cantidad = Integer.parseInt(txtCantidad.getText());
		String origen = txtOrigen.getText();
		int tema = cboTemas.getSelectedIndex();
		
		Libro l = new Libro();
		l.setTituloLibro(titulo);
		l.setPrecio(precio);
		l.setCantEjemplares(cantidad);
		l.setOrigen(origen);
		l.setIdTema(tema);
		
		//grabar en la tabla ---> JPA
		EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(l);
		
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
		
		JOptionPane.showMessageDialog(this, "Producto Registrado");
		
		}
}





















