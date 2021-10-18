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

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import java.awt.Toolkit;  

public class CreateCompra extends JFrame {

	private JPanel contentPane;
	private JTable tableCompra;
	private JTextField textNombre;
	private JTextField textPvent;
	private JTextField textCmp;
	private JTextField textCnt;
	
	private JComboBox cmboxInventario;	
	private JComboBox cmboxProveedor;
	private JLabel lblTotal;
	
	Object[] header = new Object[]{"ID","Nombre","Precio_Compra","Precio_Venta","Cantidad","Total"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	
	private HashMap<Producto,Integer> listproducts = new HashMap<Producto,Integer>();  
	private ArrayList<Producto> newProducts = new ArrayList<Producto>();
	private ArrayList<Producto> oldProducts = new  ArrayList<Producto>();
	private JTextField textID;
	private JTextField textProvID;
	private JTextField textProvName;
	
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
		cmboxInventario.removeAllItems();		
		Inventario inventario = new Inventario();
		ArrayList<Producto> rs = null;
		rs = inventario.mostrarProductos();
		cmboxInventario.addItem("");		
		for(Producto prod : rs)
		{
			cmboxInventario.addItem(prod.getNombre());				
		}
	}
	
	
	
	private void popUpProveUpdate()
	{
		cmboxProveedor.removeAllItems();
		Agenda agenda = new Agenda();
		ArrayList<Proveedor> rs = null;
		rs = agenda.showProveedores();
		if(rs!=null)
		{
			cmboxProveedor.addItem("");
			for(Proveedor prv : rs)
			{
				cmboxProveedor.addItem(prv.getNombre());		
			}
		}
		
	}
	
	private void dataFilling(int idr)
	{			
		Inventario inventario = new Inventario();
		Producto prod = inventario.buscarProducto(idr);
		if(prod!=null)
		{
			textID.setText(""+prod.getID());
			textNombre.setText(prod.getNombre());
			textCmp.setText(""+prod.getPrecio_Compra());
			textPvent.setText(""+prod.getPrecio_Venta());					
		}
		else
		{
			textNombre.setText("");
			textCmp.setText("");
			textPvent.setText("");
			textCnt.setText("");
			
		}
	}
	
	private void dataFillingProvs(Proveedor prv)
	{		
		if(prv!=null)
		{
			textProvID.setText(""+prv.getNID());
			textProvName.setText(""+prv.getNombre());			
		}
		else
		{				
			textProvID.setText("");
			textProvName.setText("");
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
			sum += Integer.parseInt(model.getValueAt(i, 5).toString());
			i++;
		}
		
		lblTotal.setText(""+sum);
	}
	
	

	/**
	 * Create the frame.
	 */
	public CreateCompra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Mis Documentos\\Documentos\\GitHub\\WStore-app\\templates\\pngwing.com.png"));
		setTitle("Crear Compra");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 414);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JCheckBox ckboxNewProduct = new JCheckBox("Producto Nuevo");
		ckboxNewProduct.setBackground(Color.WHITE);
		ckboxNewProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(ckboxNewProduct.isSelected() != true)
				{
					textID.setText("");
					cmboxInventario.setEnabled(true);
					popUpUpdate();
					textNombre.setEditable(false);
					textNombre.setText("");
					textCmp.setEditable(false);
					textCmp.setText("");
					textPvent.setEditable(false);
					textPvent.setText("");
									
				}
				else
				{
					textID.setText("Null");
					cmboxInventario.setEnabled(false);
					cmboxInventario.removeAllItems();															
					textNombre.setEditable(true);
					textCmp.setEditable(true);
					textPvent.setEditable(true);						
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
				Inventario inventario = new Inventario();
				listproducts.clear();
				newProducts.clear();
				oldProducts.clear();
				//Recorrer tabla y añadir productos nuevos y productos viejos al array correspondiente
				int i = 0;
				
				while(i<model.getRowCount())
				{
					if(model.getValueAt(i, 0).toString().equals("Null"))
					{
						//Si no tienen ID indican que son productos nuevos
						String nmbre = model.getValueAt(i, 1).toString();
						int Pcom = Integer.parseInt(model.getValueAt(i, 2).toString());
						int Pvent = Integer.parseInt(model.getValueAt(i, 3).toString());
						int stk = Integer.parseInt(model.getValueAt(i, 4).toString());
						//String Date= model.getValueAt(i, 5).toString()					
		                Producto prod = inventario.ProductoNuevo(nmbre,Pcom,Pvent,stk); 
		                listproducts.put(prod, prod.getStock());		                                
						newProducts.add(prod);		
						
						i++;														
					}
					else
					{
						//Si tienen ID son productos ya registrados
						int id = Integer.parseInt(model.getValueAt(i, 0).toString());
						int cnt = Integer.parseInt(model.getValueAt(i, 4).toString());
						//String Date= model.getValueAt(i, 5).toString()
						Producto prod = inventario.productoAntiguo(id,cnt);
                        //Se guarda producto en el diccionario prod-cantidad
                        listproducts.put(prod,cnt);    
                        //se guarda en la lista de antiguos para modificación de inventario
                        oldProducts.add(prod);                          
                        
                        i++;
					}
				}
			
				Balance historial = new Balance();			
				Agenda agenda = new Agenda();
				Proveedor prov = null;
				if(textProvID.getText().equals(""))
				{
					
				}
				else
				{
					prov = agenda.buscarProveedor(Integer.parseInt(textProvID.getText()));
				}				
				//Para guardar la compra con proveedor se sigue:
                //se crea la con el diccionario prod-cant
                Compra compra = historial.crearCompra(listproducts);
                //Se le asigna el proveedor a la compra
                compra.setProveedor(prov);
                // se agrega la compra al historial
                historial.agregarCompra(compra); 
                //se agregan al inventario los nuevos productos
                inventario.addNewProducts(newProducts); 
                //se modifican en el inventario los productos antiguos
                inventario.addOldProducts(oldProducts); 
                
                model.setRowCount(0);
				tableCompra.setModel(model);
				textCmp.setText("");
				textCnt.setText("");
				textID.setText("");
				textNombre.setText("");
				textProvID.setText("");
				textProvName.setText("");
				textPvent.setText("");
				lblTotal.setText("");
				cmboxInventario.setSelectedIndex(0);
				cmboxProveedor.setSelectedIndex(0);
                
                model.setRowCount(0);
				tableCompra.setModel(model);
				JOptionPane.showMessageDialog(null, "Compra Guardada");
								
			}
		});
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {					
				try
				{
					String id = textID.getText();
					String nbr = textNombre.getText();
					int pcom = Integer.parseInt(textCmp.getText());
					int pvent = Integer.parseInt(textPvent.getText());
					int cnt = Integer.parseInt(textCnt.getText());
					int tot = pcom*cnt;
					model.addRow(new Object[]{id,nbr,pcom,pvent,cnt,tot});	
					tableCompra.setModel(model);	
					sumaTotal();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Los precios y la cantidad deben ser numéricos");
				}
			}
		});
		
		JLabel lblId = new JLabel("ID:");
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setColumns(10);
		
		cmboxProveedor = new JComboBox();
		cmboxProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmboxProveedor.getSelectedItem() != "")
				{
					Agenda agenda = new Agenda();
					String itm = ""+cmboxProveedor.getSelectedItem();
					Proveedor prv = agenda.buscarProveedorbyName(itm);
					dataFillingProvs(prv);				
				}
				else
				{
					textProvID.setText("");
					textProvName.setText("");
				}
			}
		});
		cmboxProveedor.setMaximumRowCount(1000);
		
		textProvID = new JTextField();
		textProvID.setEditable(false);
		textProvID.setColumns(10);
		
		textProvName = new JTextField();
		textProvName.setEditable(false);
		textProvName.setColumns(10);
		
		JLabel lblId_1 = new JLabel("NIT");
		
		JLabel lblTipo_1 = new JLabel("Nombre");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(35, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblPrecioVenta, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblTipo, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
											.addComponent(lblPrecioCompra, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(textCnt, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addComponent(textPvent, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addComponent(textID, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addComponent(textCmp, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
									.addComponent(cmboxProveedor, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(ckboxNewProduct)
								.addGap(101)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(99)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSelRow, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblTipo_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblId_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(textProvID, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addComponent(textProvName, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
							.addGap(55)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCantidad_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ckboxNewProduct)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipo))
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textPvent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrecioVenta))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textCmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrecioCompra))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textCnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCantidad)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelRow)
								.addComponent(btnEliminar)
								.addComponent(lblTotal)
								.addComponent(lblCantidad_1))))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmboxProveedor, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(textProvID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblId_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textProvName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTipo_1)))
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(258, Short.MAX_VALUE)
					.addComponent(btnAgregar)
					.addGap(104))
		);
		
		cmboxInventario = new JComboBox();
		cmboxInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmboxInventario.getSelectedItem() != "")
				{
					String itm = ""+cmboxInventario.getSelectedItem();
					int id = searchItemID(itm);
					dataFilling(id);					
				}	
			}
		});
		cmboxInventario.setMaximumRowCount(1000);
		scrollPane_1.setViewportView(cmboxInventario);
		
		popUpUpdate();
		popUpProveUpdate();
		
		tableCompra = new JTable();
		tableCompra.setBackground(Color.WHITE);
		tableCompra.setForeground(Color.DARK_GRAY);
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
