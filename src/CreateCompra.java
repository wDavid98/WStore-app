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
	private JLabel lblTotal;
	
	Object[] header = new Object[]{"Nombre","Precio_Compra","Precio_Venta","Cantidad","Total"};
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
	
	public void cerrarVentana() {		
		this.setVisible(false); this.dispose();
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
	
	
	private void sumaTotal()
	{
		//Genera la suma de la columna total, para generar el valor de la compra.
		int sum = 0;
		int i = 0;
		while(i<model.getRowCount())
		{
			sum += Integer.parseInt(model.getValueAt(i, 4).toString());
			i++;
		}
		
		lblTotal.setText(""+sum);
	}
	
	private void BuynewProduct(String name, int pcom, int pvent, int cant)
	{
		//Añade un producto nuevo a la base de datos, tabla Producto
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;
		int rs;
		String comn1 = "Insert into Producto(nombre,precio_compra,precio_venta,cantidad) values('"+name+"','"+pcom+"','"+pvent+"','"+cant+"');";	
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeUpdate();	
			rs=0;
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error BuynewProduct(): "+e);
		}		
	}
	
	
	private void BuyoldProduct(String name, int cant)
	{
		/*Si el nombre ya existe en la base de datos, actualiza su cantidad añadienola a la que ya había
		No permite modificaciones de otros atributos, por lo que o se agrega el mismo producto con otro nombre, o se modifica directamente
		desde la pastalla principal*/
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;
		int rs;
		String comn1 = "update Producto set cantidad='"+cant+"' where nombre='"+name+"';";
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeUpdate();					
			rs=0;
			pst.close();
		} catch (SQLException e) {JOptionPane.showMessageDialog(null,"Error BuyoldProduct(): "+e);}
		
	}
	
	private int getCurrentCuantity(String name)
	{
		int cnt = 0;
		String comn1 = "select cantidad from Producto where nombre='"+name+"';";			
		try {	
			Connection conne=sqliteconnection.dbconnector();
			PreparedStatement pst;
			ResultSet rs;
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();			
			//-----			
			cnt = Integer.parseInt(rs.getString("cantidad").toString());
			//-----			
			rs.close();
			pst.close();			
		}catch(Exception e) {}
		return cnt;
	}
	
	private boolean searchProduct(String name)
	{
		//Revisa si el producto existe en la tabla Producto, retornando true si lo encuentra, false si no.
		boolean fnd = false;
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;
		ResultSet rs;
		String comn1 = "select * from Producto where nombre='"+name+"';";	
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();				
			if(rs.next())
			{
				fnd=true;
			}			
			rs.close();
			pst.close();
		} catch (SQLException e) {}
		
		return fnd;
	}

	/**
	 * Create the frame.
	 */
	public CreateCompra() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		lblTotal = new JLabel("----");
		
		JLabel lblSelRow = new JLabel("----");
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.removeRow(Integer.parseInt(lblSelRow.getText()));		
				tableCompra.setModel(model);
				sumaTotal();
			}
		});
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int nrow = model.getRowCount();
				int i = 0;
				int cnnew = 0;
				int cnold = 0;
				try {
					while(i<model.getRowCount())
					{
						String name = model.getValueAt(i, 0).toString();
						int pcom = Integer.parseInt(model.getValueAt(i, 1).toString());
						int pven = Integer.parseInt(model.getValueAt(i, 2).toString());
						int cnt = Integer.parseInt(model.getValueAt(i, 3).toString());						
						boolean fnd = searchProduct(name);						
						if(!fnd)
						{
							BuynewProduct(name, pcom, pven, cnt);
							cnnew++;
						}
						else
						{
							int ncn = getCurrentCuantity(name)+cnt;
							BuyoldProduct(name, ncn);
							cnold++;
						}
						i++;
					}
					JOptionPane.showMessageDialog(null, "Productos nuevos: "+cnnew+", Producto actualizados: "+cnold);
					cerrarVentana();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Tabla vacia - "+e);
				}
				
			}
		});
		
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
				int tot = pcom*cnt;
				model.addRow(new Object[]{nbr,pcom,pvent,cnt,tot});	
				tableCompra.setModel(model);	
				
				sumaTotal();
				
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
									.addComponent(lblSelRow, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(69)
									.addComponent(lblCantidad_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(lblSelRow)
								.addComponent(btnEliminar)
								.addComponent(lblTotal)
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
		tableCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JTable source = (JTable)evt.getSource();
	            int row = source.rowAtPoint( evt.getPoint() );	            
	            lblSelRow.setText(""+ row );
			}
		});
		tableCompra.setModel(model);
		scrollPane.setViewportView(tableCompra);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
