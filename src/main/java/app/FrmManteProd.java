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
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	JComboBox cboTemas;
	private JTextField txtTitulo;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtOrigen;
	private JTable tablaLibros;
	private JScrollPane scrollPane;
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(23, 199, 144, 23);
		contentPane.add(btnNewButton);
		
		cboTemas = new JComboBox();
		cboTemas.setFont(new Font("Tahoma", Font.BOLD, 11));
		cboTemas.setBounds(122, 146, 144, 22);
		contentPane.add(cboTemas);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTema.setBounds(10, 150, 102, 14);
		contentPane.add(lblTema);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitulo.setBounds(10, 21, 102, 14);
		contentPane.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
			}
		});
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(122, 18, 144, 20);
		contentPane.add(txtTitulo);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidad.setBounds(10, 87, 102, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

			    boolean numeros = key >= 48 && key <= 57;
			        
			    if (!numeros)
			    {
			        e.consume();
			    }

			    if (txtCantidad.getText().trim().length() == 10) {
			        e.consume();
			    }
			}
		});
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(122, 84, 110, 20);
		contentPane.add(txtCantidad);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrecio.setBounds(10, 56, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

			    boolean numeros = key >= 48 && key <= 57;
			        
			    if (!numeros)
			    {
			        e.consume();
			    }

			    if (txtPrecio.getText().trim().length() == 10) {
			        e.consume();
			    }
			}
		});
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 53, 110, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrigen.setBounds(10, 118, 46, 14);
		contentPane.add(lblOrigen);
		
		txtOrigen = new JTextField();
		txtOrigen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

			    boolean mayusculas = key >= 65 && key <= 90;
			    boolean minusculas = key >= 97 && key <= 122;
			    boolean espacio = key == 32;
			            
			     if (!(minusculas || mayusculas || espacio))
			    {
			        e.consume();
			    }
			}
		});
		txtOrigen.setBounds(122, 115, 110, 20);
		contentPane.add(txtOrigen);
		txtOrigen.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 242, 414, 191);
		contentPane.add(scrollPane);
		
		tablaLibros = new JTable();
		tablaLibros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Titulo", "Precio", "Cantidad", "Origen", "Tema"
			}
		));
		scrollPane.setViewportView(tablaLibros);
		
		listado();
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
	
	public void listado() {
        EntityManagerFactory fabrica=Persistence.createEntityManagerFactory("mysql");
        EntityManager em = fabrica.createEntityManager();

        TypedQuery<Libro> consulta= em.createQuery("select pro from Libro pro", Libro.class);
        List<Libro> listadoProductos = consulta.getResultList();

         DefaultTableModel tablaRegista=(DefaultTableModel) tablaLibros.getModel();
         tablaRegista.setRowCount(0);
        for(Libro p: listadoProductos) {
            Object row[]= {p.getIdlibro(),p.getTituloLibro(),p.getPrecio(),p.getCantEjemplares(),p.getOrigen(),p.getIdTema()};
            tablaRegista.addRow(row);
        }
	}
	
}






















