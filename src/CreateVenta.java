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
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfCode;
	private JTextField tfID;
	private JTextField tfNombre;
	private JTextField tfPrvent;
	private JTextField tfCantidad;
	private JTextField tfRowSelected;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	
	private JComboBox comboBoxItems;
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
		int idr = 0;
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		ResultSet rs;
		String comn1 = "select ID from Producto where nombre = '"+name+"';";	
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();				
			idr = Integer.parseInt(rs.getString("ID"));			
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"No encontrado");
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
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		ResultSet rs;
		String comn1 = "select * from Producto where ID = '"+code+"';";	
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();				
			tfID.setText(""+rs.getString("ID"));
			tfNombre.setText(""+rs.getString("nombre"));
			tfPrvent.setText(""+rs.getString("precio_venta"));	
			tfStock.setText(""+rs.getString("Cantidad"));
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"No encontrado");
		}		
	}
	
	private int getCurrentStock(int id)
	{
		int stk =0;
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		ResultSet rs;
		String comn1 = "select Cantidad from Producto where ID = '"+id+"';";	
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();				
			stk = Integer.parseInt(rs.getString("Cantidad"));			
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"No encontrado");
		}		
		return stk;
	}
	
	private void upDateStock(int id, int act)
	{
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		int rs;
		String comn1 = "Update Producto set Cantidad ='"+act+"'where ID = '"+id+"';";	
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeUpdate();
			rs = 0;
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"No encontrado");
		}		
	}
	
	
	private void popUpUpdate2()
	{
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;	
		ResultSet rs;
		String comn1 = "select nombre from Producto";	
		comboBoxItems.removeAllItems();				
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();
			comboBoxItems.addItem("");
			
			while(rs.next())
			{				
				comboBoxItems.addItem(rs.getString("nombre"));				
			}			
			
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error popUpUpdate(): "+e);
		}		
	}

	/**
	 * Create the frame.
	 */
	public CreateVenta() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 823, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblLista = new JLabel("Lista");
		
		JLabel lblInserteProducto = new JLabel("Inserte Producto");
		
		JLabel lblPorCdigo = new JLabel("Por C\u00F3digo:");
		
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
		
		comboBoxItems = new JComboBox();
		comboBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxItems.getSelectedItem() != "")
				{
					int id = getIDbyName(comboBoxItems.getSelectedItem().toString());
					fillDataWithCodeProduct(id);
					//JOptionPane.showMessageDialog(null,itm+"--"+id);
				}		
			}
		});
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta Unidad");
		
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
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				try 
					{
						if( Integer.parseInt(tfCantidad.getText()) < Integer.parseInt(tfStock.getText()) && Integer.parseInt(tfCantidad.getText())!=0)
						{
							String nbr = tfNombre.getText();
							int cStk;		
							int nStk;
							int idp = Integer.parseInt(tfID.getText());
							int pvent = Integer.parseInt(tfPrvent.getText());					
							int cnt1 = Integer.parseInt(tfCantidad.getText());						
							int tot = pvent*cnt1;
							model.addRow(new Object[]{idp,nbr,pvent,cnt1,tot});	
							
							cStk = getCurrentStock(idp);
							nStk = cStk - cnt1;
							upDateStock(idp,nStk);
							fillDataWithCodeProduct(idp);	
							sumaTotal();
							
						}
						else {JOptionPane.showMessageDialog(null,"Cantidad nula o erronea");}
						
					}catch(Exception e){JOptionPane.showMessageDialog(null,"Error de formato");}							
			}
		});
		
		tfRowSelected = new JTextField();
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
					upDateStock(idt, nStk);
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
				cerrarVentana();
			}
		});
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setEnabled(false);
		
		JLabel lblCc = new JLabel("C.C ");
		lblCc.setEnabled(false);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setEnabled(false);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fillDataWithCodeProduct(Integer.parseInt(tfCode.getText().toString()));
			}
		});
		
		JLabel lblStockDisponible = new JLabel("Stock Disponible");
		
		tfStock = new JTextField();
		tfStock.setEditable(false);
		tfStock.setColumns(10);
		
		JLabel lbl_random = new JLabel("Total:");
		
		tfTotalVenta = new JTextField();
		tfTotalVenta.setEditable(false);
		tfTotalVenta.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
															.addComponent(lblId, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(lblNombre_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(lblPrecioVenta, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
															.addComponent(tfID)
															.addComponent(tfNombre)
															.addComponent(tfPrvent, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(lblPorCdigo, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(tfCode, 147, 147, 147))))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(79)
												.addComponent(lblInserteProducto))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addGap(37)
												.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblStockDisponible, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
										.addGap(28)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(tfCantidad, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
											.addComponent(tfStock, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))))
								.addGap(3))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(81)
								.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
							.addGap(34)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVender, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblLista, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(10)
											.addComponent(tfRowSelected, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lbl_random, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(tfTotalVenta, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
										.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(44)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblTelfono, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCc, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
								.addGap(40))))
					.addGap(170))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(27, Short.MAX_VALUE)
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCantidad)
								.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnAgregar)
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLista)
							.addGap(7)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfRowSelected, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfTotalVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_random))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCc)
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelfono)
								.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVender)))
					.addGap(25))
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
		
		
		
	}
}
