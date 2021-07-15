import java.awt.BorderLayout;
import java.lang.Object.*;
import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import sun.misc.GC;

import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.sqlite.SQLiteConnection;

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
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
	
	
	
	private JComboBox comboBoxInventario;
	private JTextField textNID;
	private JTextField textPhone;
	private JTextField textDir;
	private JTextField TextNombre;
	//Otras variables auxiliares
	private String oldNid;
	private String nbr_cl_ti;
	private int pcom_cl_ti;
	private int pvnt_cl_ti;
	private int cnt_cl_ti;
	
	//Método para actualizar tabla inventario
	private void upDateTable() {
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select ID, nombre, precio_compra, precio_venta, cantidad from Producto";		
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();				
			
			//Llenado de tabla			
			tableInventario.setModel(DbUtils.resultSetToTableModel(rs));	
			
			
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
		}
	}
	
	//Método para actualizar tabla inventario segun un nombre
		private void searchNameinTable(String name) {
			Connection conne=sqliteconnection.dbconnector();
			PreparedStatement pst = null;	
			ResultSet rs = null;
			String comn1 = "select ID, nombre, precio_compra, precio_venta, cantidad from Producto where nombre="+"'"+name+"';";		
			try {		
				pst = conne.prepareStatement(comn1);
				rs = pst.executeQuery();			
				//Llenado de tabla
				tableInventario.setModel(DbUtils.resultSetToTableModel(rs));
				rs.close();
				pst.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
			}		
		}
	
	
	//Método para actualizar popUp menu
	private void upDatePopMenu()
	{		
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select nombre from Producto";	
		comboBoxInventario.removeAllItems();		
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();
			comboBoxInventario.addItem("");
			while(rs.next())
			{				
				comboBoxInventario.addItem(rs.getString("nombre"));
			}			
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
		}	
	}
	 
	
	private void updateDatosNegocio()
	{
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select * from DatosNegocio";		
		try {			
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();			
			TextNombre.setText(rs.getString("Nombre"));
			textDir.setText(rs.getString("Direccion"));
			textNID.setText(rs.getString("Nid"));
			textPhone.setText(rs.getString("Telefono"));
			oldNid = rs.getString("Nid");	
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla: "+e);
		}			
		
	}
	
	public String getNID()
	{
		return oldNid;
	}
	
	public String getNbr_byclick()
	{
		return nbr_cl_ti;
	}
	public void setNbr_byclick(String nn)
	{
		nbr_cl_ti = nn;	
	}
	
	public int getPcom_byclick()
	{
		return pcom_cl_ti;
	}
	public void setPcom_byclick(int nn)
	{
		pcom_cl_ti = nn;	
	}
	
	public int getPvent_byclick()
	{
		return pvnt_cl_ti;
	}
	public void setPvent_byclick(int nn)
	{
		pvnt_cl_ti = nn;	
	}
	
	public int getCnt_byclick()
	{
		return cnt_cl_ti;
	}
	public void setCnt_byclick(int nn)
	{
		cnt_cl_ti = nn;	
	}
	
	
	
	
	private void setNid(String st) {oldNid = st;}
	
	// Inicio del constructor	
	public Inicio() {
		
		//Conexión a SQLite					
					
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblInventario = new JLabel("INVENTARIO");
		
		JLabel lblInventario_2 = new JLabel("DIRECCI\u00D3N");
		
		JLabel lblInventario_3 = new JLabel("NID");
		
		JLabel lblInventario_4 = new JLabel("TEL\u00C9FONO");
		
		JLabel lblRowSelected = new JLabel("----");
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String rwiD = lblRowSelected.getText();
				if(rwiD!="")
				{						
					UpdateProduct pgUp_Prod = new UpdateProduct(Integer.parseInt(rwiD));	
					pgUp_Prod.setVisible(true);							
				}
				else
				{
					
				}
				upDateTable();
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnEditData = new JButton("Editar");		
		btnEditData.addMouseListener(new MouseAdapter() {			
			@Override			
			public void mouseClicked(MouseEvent e) {				
				if(btnEditData.getText() != "Editar")
				{
					Connection conne=sqliteconnection.dbconnector();
					PreparedStatement pst = null;						
					String Dtname = TextNombre.getText();
					String DtDir = textDir.getText();
					String Dtph = textPhone.getText();
					String Dtnid = textNID.getText();
					int rsi = 0;
					String comn1 = "update DatosNegocio set Nombre='"+Dtname+"',NID='"+Dtnid+"',Telefono='"+Dtph+"',Direccion='"+DtDir+"' where NID ='"+getNID()+"';";	
					try {						
						pst = conne.prepareStatement(comn1);
						rsi = pst.executeUpdate();						
						setNid(textNID.getText());
						rsi=0;
						pst.close();
						JOptionPane.showMessageDialog(null,"Actualizado con éxito:");
					}catch (SQLException e1) {
						
					}					
					btnEditData.setText("Editar");
					TextNombre.setEditable(false);
					textDir.setEditable(false);
					textNID.setEditable(false);
					textPhone.setEditable(false);
				}
				else
				{
					String oldNid = textNID.getText();					
					btnEditData.setText("Guardar");
					TextNombre.setEditable(true);
					textDir.setEditable(true);
					textNID.setEditable(true);
					textPhone.setEditable(true);
				}
			}
		});
		
		
		JButton btnVentas = new JButton("Ventas");
		btnVentas.setEnabled(false);
		
		JButton btnCompra_1 = new JButton("Compra");
		btnCompra_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateCompra pagCompra = new CreateCompra();
				pagCompra.setVisible(true);
			}
		});
		
		JButton btnVenta = new JButton("Venta");
		btnVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CreateVenta pagVnt = new CreateVenta();
				pagVnt.setVisible(true);
			}
		});
		
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
	            lblRowSelected.setText(""+ source.getModel().getValueAt(row, 0) );
	            setNbr_byclick(""+ source.getModel().getValueAt(row, 1));
	            setPcom_byclick((int)source.getModel().getValueAt(row, 2));
	            setPvent_byclick((int)source.getModel().getValueAt(row, 3));
	            setCnt_byclick((int)source.getModel().getValueAt(row, 4));
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
		
		textNID = new JTextField();
		textNID.setEditable(false);
		textNID.setText("------");
		textNID.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setEditable(false);
		textPhone.setToolTipText("");
		textPhone.setText("------");
		textPhone.setColumns(10);
		
		textDir = new JTextField();
		textDir.setText("------");
		textDir.setEditable(false);
		textDir.setToolTipText("");
		textDir.setColumns(10);
		
		TextNombre = new JTextField();
		TextNombre.setEditable(false);
		TextNombre.setText("Nombre_Negocio");
		TextNombre.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(130)
							.addComponent(TextNombre, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(261)
							.addComponent(lblInventario, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPaneCombo, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInventario_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textNID, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInventario_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnModificar_6, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnContabilidad, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnModificar_6_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnVentas, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnEditData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblInventario_2, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textDir, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
											.addGap(104)
											.addComponent(btnCompra_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
											.addGap(10)
											.addComponent(btnVenta, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))))
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
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(TextNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInventario)
						.addComponent(scrollPaneCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInventario_3)
								.addComponent(textNID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInventario_4)
								.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInventario_2)
								.addComponent(textDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(btnCompra_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(btnVenta))
								.addComponent(btnEditData))
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
		comboBoxInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxInventario.getSelectedItem() == "")
				{
					upDateTable();	
					comboBoxInventario.removeAllItems();
					upDatePopMenu();
				}
				else
				{
					searchNameinTable(""+comboBoxInventario.getSelectedItem());
					comboBoxInventario.removeAllItems();
					upDatePopMenu();
				}
				
			}
		});
		
		
		
	
		
		
		
		
		
	
				
		scrollPaneCombo.setViewportView(comboBoxInventario);
		
		contentPane.setLayout(gl_contentPane);		
		upDateTable();
		comboBoxInventario.removeAllItems();		
		upDatePopMenu();
		updateDatosNegocio();
		
		
		
		
			} // Fin del constructor
	
	
	
	public JComboBox getComboBoxInventario() {
		return comboBoxInventario;
	}
}
