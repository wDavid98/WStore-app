import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateCompra extends JFrame {

	private JPanel contentPane;
	private JTable tableCompra;
	private JTextField textNombre;
	private JTextField textPvent;
	private JTextField textCmp;
	private JTextField textCnt;
	
	private JComboBox cmboxInventario;	
	
	Object[] header = new Object[]{"Nombre","Precio_Comra","Precio_Venta","Cantidad"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCompra frame = new CreateCompra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void popUpUpdate()
	{
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		ResultSet rs;
		String comn1 = "select nombre from Producto";	
		cmboxInventario.removeAllItems();				
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();
			cmboxInventario.addItem("");
			
			while(rs.next())
			{				
				cmboxInventario.addItem(rs.getString("nombre"));				
			}			
			
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error popUpUpdate(): "+e);
		}		
	}
	
	private void dataFilling(int id)
	{
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		ResultSet rs;
		String comn1 = "select * from Producto where ID='"+id+"';";			
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();			
			//-----
			if(id != 0)
			{
			textNombre.setText(rs.getString("nombre"));
			textCmp.setText(rs.getString("precio_compra"));
			textPvent.setText(rs.getString("precio_venta"));
			textCnt.setText(rs.getString("cantidad"));		
			}
			else
			{
				textNombre.setText("");
				textCmp.setText("");
				textPvent.setText("");
				textCnt.setText("");		
			}
			//-----
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error dataFilling(): "+e);
		}		
		
	}
	
	private int searchItemID(String name)
	{
		int id=0;
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;
		ResultSet rs;
		String comn1 = "select ID from Producto where nombre='"+name+"';";	
		try {
			if(name != "")
			{
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();				
			id = Integer.parseInt(rs.getString("ID"));			
			rs.close();
			pst.close();
			}
			else
			{id=0;}
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null,"Error searchItemID(): "+e);
		}		
		
		return id;		
	}
	
	/*Métodos
	private void newProductInsert()
	{
		
	}
	
	private void oldProductUpdate()
	{
		
	}	
	
	
	*/

	/**
	 * Create the frame.
	 */
	public CreateCompra() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JCheckBox ckboxNewProduct = new JCheckBox("Producto Nuevo");
		ckboxNewProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(ckboxNewProduct.isSelected() != false)
				{
					cmboxInventario.setEnabled(false);
					cmboxInventario.removeAllItems();															
					textNombre.setEditable(true);
					textCmp.setEditable(true);
					textPvent.setEditable(true);
					
				}
				else
				{
					
					cmboxInventario.setEnabled(true);
					popUpUpdate();
					textNombre.setEditable(false);
					textNombre.setText("");
					textCmp.setEditable(false);
					textCmp.setText("");
					textPvent.setEditable(false);
					textPvent.setText("");
					
				}
				
			}
		});
		
		JLabel lblTipo = new JLabel("Nombre");
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		
		JLabel lblPrecioCompra = new JLabel("Precio Compra");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setColumns(10);
		
		textPvent = new JTextField();
		textPvent.setEditable(false);
		textPvent.setColumns(10);
		
		textCmp = new JTextField();
		textCmp.setEditable(false);
		textCmp.setColumns(10);
		
		textCnt = new JTextField();
		textCnt.setColumns(10);
		
		JLabel lblCantidad_1 = new JLabel("TOTAL:");
		
		JLabel lblCantidad_2 = new JLabel("----");
		
		JLabel lblCantidad_1_1 = new JLabel("----");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnGuardar = new JButton("GUARDAR");
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				try
				{			
				String nbr = textNombre.getText();
				int pcom = Integer.parseInt(textCmp.getText());
				int pvent = Integer.parseInt(textPvent.getText());
				int cnt = Integer.parseInt(textCnt.getText());
				model.addRow(new Object[]{nbr,pcom,pvent,cnt});	
				tableCompra.setModel(model);	
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Los precios y la cantidad deben ser numéricos");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(85)
							.addComponent(ckboxNewProduct))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblCantidad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPrecioCompra, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTipo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPrecioVenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textCnt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(textCmp, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(textPvent, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(89)
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCantidad_1_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(69)
									.addComponent(lblCantidad_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCantidad_2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
							.addGap(138))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ckboxNewProduct)
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipo)
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecioVenta)
								.addComponent(textPvent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecioCompra)
								.addComponent(textCmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCantidad)
								.addComponent(textCnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnAgregar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCantidad_1_1)
								.addComponent(btnEliminar)
								.addComponent(lblCantidad_2)
								.addComponent(lblCantidad_1))))
					.addGap(16)
					.addComponent(btnGuardar)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		
		cmboxInventario = new JComboBox();
		cmboxInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmboxInventario.getSelectedItem() != "")
				{
					String itm = ""+cmboxInventario.getSelectedItem();
					int id = searchItemID(itm);
					dataFilling(id);
					//JOptionPane.showMessageDialog(null,itm+"--"+id);
				}						
			}
		});
		cmboxInventario.setMaximumRowCount(1000);
		scrollPane_1.setViewportView(cmboxInventario);
		
		popUpUpdate();
		
		tableCompra = new JTable();
		tableCompra.setModel(new DefaultTableModel(
				header,
			0
		));
		scrollPane.setViewportView(tableCompra);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
