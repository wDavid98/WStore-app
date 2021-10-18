import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CreateVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfCode;
	private JTextField tfID;
	private JTextField tfNombre;
	private JTextField tfPrvent;
	private JTextField tfCantidad;
	private JTextField tfRowSelected;
	private JTextField textClientName;
	private JTextField textClienteCC;
	
	private JComboBox comboBoxItems;
	private JComboBox comboBoxClients;
	private JTextField tfStock;
	
	
	Object[] header = new Object[]{"ID","Nombre","Precio_Venta","Cantidad","Total"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	private JTextField tfTotalVenta;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateVenta frame = new CreateVenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private int getIDbyName(String name)
	{
		Integer idr = null;
		Inventario inventario = new Inventario();
		ArrayList<Producto> prods = inventario.mostrarProductos();
		for(Producto prd : prods)
		{
			if(prd.getNombre().equals(name))
			{
				idr = prd.getID();
			}
		}		
		return idr;
	}
	
	public void cerrarVentana() {		
		this.setVisible(false); this.dispose();
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
		
		tfTotalVenta.setText(""+sum);
	}
	
	private void fillDataWithCodeProduct(int code)
	{
		Inventario inventario = new Inventario();
		ArrayList<Producto> prods = inventario.mostrarProductos();
		for(Producto prd : prods)
		{
			if(prd.getID()==code)
			{
				tfID.setText(""+prd.getID());
				tfNombre.setText(""+prd.getNombre());
				tfPrvent.setText(""+prd.getPrecio_Venta());	
				tfStock.setText(""+prd.getStock());
			}
		}
	}
	
	private int getCurrentStock(int id)
	{
		int stk =0;
		Inventario inventario = new Inventario();
		ArrayList<Producto> prods = inventario.mostrarProductos();
		for(Producto prd : prods)
		{
			if(prd.getID()==id)
			{
				stk = prd.getStock();
			}
		}		
		return stk;
	}
	
	private void popUpUpdate2()
	{
		comboBoxItems.removeAllItems();		
		Inventario inventario = new Inventario();
		ArrayList<Producto> prods = inventario.mostrarProductos();
		comboBoxItems.addItem("");
		for(Producto prod : prods)
		{
			comboBoxItems.addItem(prod.getNombre());		
		}
	}
	
	private void popCliente()
	{
		comboBoxClients.removeAllItems();		
		Agenda agenda = new Agenda();
		ArrayList<Cliente> clnts = agenda.showClientes();
		comboBoxClients.addItem("");
		for(Cliente cln : clnts)
		{
			comboBoxClients.addItem(cln.getCC());		
		}
	}

	/**
	 * Create the frame.
	 */
	public CreateVenta() {
		setForeground(Color.WHITE);
		setTitle("Crear Venta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 823, 417);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblLista = new JLabel("Lista");
		lblLista.setHorizontalAlignment(SwingConstants.CENTER);
		lblLista.setFont(new Font("Sylfaen", Font.BOLD, 14));
		
		JLabel lblInserteProducto = new JLabel("Inserte Producto");
		lblInserteProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserteProducto.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		
		JLabel lblPorCdigo = new JLabel("Por C\u00F3digo:");
		lblPorCdigo.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		tfCode = new JTextField();
		tfCode.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				fillDataWithCodeProduct(Integer.parseInt(tfCode.getText()));
			}
		});
		
		tfCode.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		comboBoxItems = new JComboBox();
		comboBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxItems.getSelectedItem() != "")
				{
					int id = getIDbyName(comboBoxItems.getSelectedItem().toString());
					fillDataWithCodeProduct(id);					
				}		
			}
		});
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta Unidad");
		lblPrecioVenta.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setColumns(10);
		
		tfPrvent = new JTextField();
		tfPrvent.setEditable(false);
		tfPrvent.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {									
				try 
					{
						if( Integer.parseInt(tfCantidad.getText()) < Integer.parseInt(tfStock.getText()) && Integer.parseInt(tfCantidad.getText())!=0)
						{
							String nbr = tfNombre.getText();
							Inventario inventario = new Inventario();						
							int cStk;		
							int nStk;
							int idp = Integer.parseInt(tfID.getText());
							int pvent = Integer.parseInt(tfPrvent.getText());					
							int cnt1 = Integer.parseInt(tfCantidad.getText());						
							int tot = pvent*cnt1;
							model.addRow(new Object[]{idp,nbr,pvent,cnt1,tot});								
							cStk = inventario.buscarProducto(idp).getStock();
							nStk = cStk - cnt1;				
							tfStock.setText(""+nStk);
							//fillDataWithCodeProduct(idp);	
							sumaTotal();							
						}
						else {JOptionPane.showMessageDialog(null,"Cantidad nula o erronea");}
						
					}catch(Exception e){JOptionPane.showMessageDialog(null,"Error de formato");}					
			}
		});
		
		tfRowSelected = new JTextField();
		tfRowSelected.setBackground(Color.WHITE);
		tfRowSelected.setEnabled(false);
		tfRowSelected.setEditable(false);
		tfRowSelected.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tfRowSelected.getText().isEmpty() == false)
				{
					int cnt = Integer.parseInt(""+model.getValueAt(Integer.parseInt(tfRowSelected.getText()), 3));
					int idt = Integer.parseInt(""+model.getValueAt(Integer.parseInt(tfRowSelected.getText()), 0));
					int cStk = getCurrentStock(idt);
					int nStk = cStk + cnt;
					/*upDateStock(idt, nStk);*/
					fillDataWithCodeProduct(idt);	
					model.removeRow(Integer.parseInt(tfRowSelected.getText()));		
					table.setModel(model);
					sumaTotal();						
				}				
			}
		});
		
		JButton btnVender = new JButton("VENDER");
		btnVender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Inventario inventario = new Inventario();
				//Recorrer tabla y añadir productos nuevos y productos viejos al array correspondiente
				HashMap<Producto,Integer> listprods = new HashMap<Producto,Integer>();
				ArrayList<Producto> oldProducts = new ArrayList<Producto>();
				int i = 0;
				
				while(i<model.getRowCount())
				{
					//Primero agregar los productos
					int idpr = Integer.parseInt(model.getValueAt(i, 0).toString());
					int cnt = Integer.parseInt(model.getValueAt(i, 3).toString());
					boolean ext = inventario.verificarExistencia(idpr,cnt);
                    if(ext) // Si hay suficiente para vender
                    {
                        Producto prod = inventario.productoAVender(idpr,cnt);
                        listprods.put(prod,cnt);
                        inventario.ModificarProducto(prod.getID(), prod.getNombre(), prod.getPrecio_Compra(), prod.getPrecio_Venta(), prod.getStock());
                        System.out.println("Producto añadido");
                    }
                    else //Si no hay suficiente para vender
                    {
                        JOptionPane.showMessageDialog(null,"No hay suficiente Stock disponible para el producto: "+model.getValueAt(i, 1).toString());
                    }                    
                    i++;	
                                      
				}
				
				Balance historial = new Balance();		
				Agenda agenda = new Agenda();
				Cliente cln = null;
                //Crear la venta a partir del diccionario producto-cantidad
                Venta venta = historial.crearVenta(listprods); 
                //En este caso, asignar un cliente a la venta
                venta.setCliente(cln);
                if(textClienteCC.getText().equals(""))
				{
					
				}
				else
				{
					cln = agenda.buscarCliente(Integer.parseInt(textClienteCC.getText()));
				}		
                venta.setCliente(cln);
                //Guardar Venta en el historial.
                historial.agregarVenta(venta); // --> Guardar en la BD
                System.out.println("Se registro la venta");
                
                model.setRowCount(0);
				table.setModel(model);
				
                tfCantidad.setText("");
                tfCode.setText("");
                tfID.setText("");
                tfNombre.setText("");
                tfPrvent.setText("");
                tfRowSelected.setText("");
                tfStock.setText("");
                tfTotalVenta.setText("");
                comboBoxClients.setSelectedIndex(0);
                comboBoxItems.setSelectedIndex(0);
                model.setRowCount(0);
				table.setModel(model);
				JOptionPane.showMessageDialog(null, "Venta Guardada");
				
				
			}
		});
		
		textClientName = new JTextField();
		textClientName.setEditable(false);
		textClientName.setColumns(10);
		
		textClienteCC = new JTextField();
		textClienteCC.setEditable(false);
		textClienteCC.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		
		JLabel lblCc = new JLabel("C.C ");
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fillDataWithCodeProduct(Integer.parseInt(tfCode.getText().toString()));
			}
		});
		
		JLabel lblStockDisponible = new JLabel("Stock Disponible");
		lblStockDisponible.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		
		tfStock = new JTextField();
		tfStock.setEditable(false);
		tfStock.setColumns(10);
		
		JLabel lbl_random = new JLabel("Total:");
		
		tfTotalVenta = new JTextField();
		tfTotalVenta.setEditable(false);
		tfTotalVenta.setColumns(10);
		
		comboBoxClients = new JComboBox();
		comboBoxClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxClients.getSelectedItem() != "")
				{
					Agenda agenda = new Agenda();
					Cliente client = null;
					ArrayList<Cliente> clns = agenda.showClientes();
					int id= Integer.parseInt(comboBoxClients.getSelectedItem().toString());
					for(Cliente cln : clns)
					{
						if(cln.getCC()==id)
						{
							client = cln;
						}
					}
					textClientName.setText(client.getNombre());
					textClienteCC.setText(""+client.getCC());
				}
				else
				{
					textClientName.setText("");
					textClienteCC.setText("");
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
															.addComponent(lblId, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(lblNombre_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(lblPrecioVenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
															.addComponent(tfID)
															.addComponent(tfNombre)
															.addComponent(tfPrvent, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblPorCdigo, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(tfCode, 147, 147, 147))))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addGap(37)
												.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(79)
												.addComponent(lblInserteProducto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblStockDisponible, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(tfCantidad, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
											.addComponent(tfStock, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))))
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addGap(34)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(72)))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLista, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(tfRowSelected, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbl_random, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfTotalVenta, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
										.addGap(29))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
												.addComponent(lblCc, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textClienteCC))
											.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
												.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textClientName))
											.addComponent(comboBoxClients, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
										.addComponent(btnVender, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
										.addGap(45))))))
					.addGap(17))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInserteProducto)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPorCdigo)
										.addComponent(tfCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(28)
											.addComponent(lblNombre))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnBuscar)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblId)
										.addComponent(tfID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNombre_1)
										.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrecioVenta)
										.addComponent(tfPrvent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblStockDisponible)
										.addComponent(tfStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCantidad)
										.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(btnAgregar)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblLista)
									.addGap(7)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfRowSelected, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_random)
										.addComponent(tfTotalVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(comboBoxClients, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblCliente)
												.addComponent(textClientName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(20)
											.addComponent(btnVender)))))
							.addGap(64))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCc)
								.addComponent(textClienteCC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(75))))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JTable source = (JTable)evt.getSource();
	            int row = source.rowAtPoint( evt.getPoint() );	            
	            tfRowSelected.setText(""+ row );
			}
		});
		table.setModel(model);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		popUpUpdate2();
		popCliente();
		
		
		
	}
}
