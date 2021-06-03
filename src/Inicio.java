import java.awt.BorderLayout;
import java.lang.Object.*;
import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.List;
import java.awt.ScrollPane;
import java.awt.Choice;
import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
public class Inicio extends JFrame {

	private JPanel contentPane;
	private JTable tableInventario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	
	// Variables propias de SQL
	Connection conne = null;
	PreparedStatement pst = null;	
	ResultSet rs = null;
	private JComboBox comboBoxInventario;
	
	//Método para actualizar tabla inventario
	private void upDateTable() {
		String comn1 = "select ID, nombre, precio_compra, precio_venta, cantidad from Producto";		
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();			
			//Llenado de tabla
			tableInventario.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
		}		
	}
	
	//Método para actualizar tabla inventario segun un nombre
		private void searchNameinTable(String name) {
			String comn1 = "select ID, nombre, precio_compra, precio_venta, cantidad from Producto where nombre="+"'"+name+"';";		
			try {
				pst = conne.prepareStatement(comn1);
				rs = pst.executeQuery();			
				//Llenado de tabla
				tableInventario.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
			}		
		}
	
	
	//Método para actualizar popUp menu
	private void upDatePopMenu()
	{
		String comn1 = "select nombre from Producto";
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();			
			while(rs.next())
			{
				comboBoxInventario.addItem(rs.getString("nombre"));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
		}	
	}
	 
	
	
	// Inicio del constructor	
	public Inicio() {
		
		//Conección a SQLite
		conne=sqliteconnection.dbconnector();		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblInventario = new JLabel("INVENTARIO");
		
		JLabel lblInventario_1 = new JLabel("INVENTARIO");
		
		JLabel lblInventario_2 = new JLabel("DIRECCI\u00D3N");
		
		JLabel lblInventario_3 = new JLabel("NID");
		
		JLabel lblInventario_4 = new JLabel("TEL\u00C9FONO");
		
		JLabel lblInventario_5 = new JLabel("----");
		
		JLabel lblInventario_6 = new JLabel("-----");
		
		JLabel lblInventario_7 = new JLabel("----");
		
		JLabel lblRowSelected = new JLabel("----");
		
		JButton btnModificar = new JButton("Modificar");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnCompra = new JButton("Editar");
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.setEnabled(false);
		
		JButton btnCompra_1 = new JButton("Compra");
		
		JButton btnVenta = new JButton("Venta");
		
		JButton btnContabilidad = new JButton("Contabilidad");
		btnContabilidad.setEnabled(false);
		
		JButton btnModificar_6 = new JButton("Clientes");
		btnModificar_6.setEnabled(false);
		
		JButton btnModificar_6_1 = new JButton("Proveedores");
		btnModificar_6_1.setEnabled(false);
		
		JScrollPane scrollPaneCombo = new JScrollPane();
		
		tableInventario = new JTable();
		tableInventario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JTable source = (JTable)evt.getSource();
	            int row = source.rowAtPoint( evt.getPoint() );
	            int column = source.columnAtPoint( evt.getPoint() );
	            //String s=source.getModel().getValueAt(row, column)+"";
	            //JOptionPane.showMessageDialog(null, s);
	            lblRowSelected.setText(""+ source.getModel().getValueAt(row, 0) );
			}
		});
		
		tableInventario.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), "Escoba", new Integer(800), new Integer(1200), new Integer(5)},
				{new Integer(2), "Trapero", new Integer(800), new Integer(1500), new Integer(10)},
				{new Integer(3), "Jabon de loza", new Integer(500), new Integer(2000), new Integer(22)},
				{new Integer(4), "Cuaderno", new Integer(900), new Integer(1100), new Integer(1)},
				{new Integer(5), "Trapero", new Integer(800), new Integer(1500), new Integer(125)},
				{new Integer(6), "Jabon de loza", new Integer(500), new Integer(2000), new Integer(545)},
			},
			new String[] {
				"ID", "nombre", "precio_compra", "precio_venta", "Cantidad"
			}
		));
		tableInventario.getColumnModel().getColumn(0).setPreferredWidth(55);
		tableInventario.getColumnModel().getColumn(1).setPreferredWidth(92);
		tableInventario.getColumnModel().getColumn(2).setPreferredWidth(93);
		tableInventario.getColumnModel().getColumn(3).setPreferredWidth(88);
		scrollPane.setViewportView(tableInventario);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(155)
							.addComponent(lblInventario_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(172)
							.addComponent(lblInventario, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
							.addComponent(scrollPaneCombo, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInventario_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(lblInventario_5, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInventario_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(lblInventario_6, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInventario_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(lblInventario_7, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnCompra, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(114)
									.addComponent(btnCompra_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(btnVenta, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnModificar_6, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnContabilidad, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnModificar_6_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnVentas, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(25)
									.addComponent(lblRowSelected, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInventario_1)
								.addComponent(lblInventario)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneCombo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInventario_3)
								.addComponent(lblInventario_5))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInventario_4)
								.addComponent(lblInventario_6))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInventario_2)
								.addComponent(lblInventario_7))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCompra)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(btnCompra_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(btnVenta)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnModificar_6)
								.addComponent(btnContabilidad))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnModificar_6_1)
								.addComponent(btnVentas)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnModificar)
									.addComponent(lblRowSelected))
								.addComponent(btnEliminar))))
					.addGap(152))
		);
		
		comboBoxInventario = new JComboBox();				
		scrollPaneCombo.setViewportView(comboBoxInventario);
		contentPane.setLayout(gl_contentPane);		
		upDateTable();
		upDatePopMenu();
		
		
		
	} // Fin del constructor
	
	
	
	public JComboBox getComboBoxInventario() {
		return comboBoxInventario;
	}
}
