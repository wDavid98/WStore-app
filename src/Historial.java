import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import com.toedter.calendar.JCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.beans.PropertyChangeEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Historial extends JFrame {

	private JPanel contentPane;
	private JTable tablehist;
	private JTextField texttotal;
	
	
	private String state = "Compra";
	
	Balance balance = new Balance();
	
	Object[] header = new Object[]{"ID","Fecha","Cliente/Proveedor","Total"};
	DefaultTableModel model = new DefaultTableModel(header, 0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historial frame = new Historial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void sumaTotal()
	{
		//Genera la suma de la columna total, para generar el valor de la compra.
		int sum = 0;
		int i = 0;
		while(i<model.getRowCount())
		{
			sum += Integer.parseInt(model.getValueAt(i,3).toString());
			i++;
		}
		
		texttotal.setText(""+sum);
	}
	
	
	public void fillmodelwith(String value)
	{	
		model.setRowCount(0);
		if(value.equals("Compra"))
		{							
			ArrayList<Compra> rs = null;
			rs = balance.mostrarCompras();
			for(Compra cpr : rs)
			{	
				String a ="Null";
				if(cpr.getProveedor()!=null)
				{
					a = ""+cpr.getProveedorID();
				}
				model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
				tablehist.setModel(model);
			}
		}
		else if(value.equals("Venta"))
		{			
			ArrayList<Venta> rs = null;
			rs = balance.mostrarVentas();
			for(Venta cpr : rs)
			{
				System.out.println("Entrax2");
				model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),cpr.getClienteID(),cpr.getTotal()});	
				tablehist.setModel(model);
			}
		}
		
	}
	
	public Date StringtoDate(String str)
	{
		Date date = null;
    	SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);  
    	try {
			date=formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return date;
	}
	
	public void fillmodelwithDate(String value,Date date,String stage)
	{	
		model.setRowCount(0);
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		int day = localDate.getDayOfMonth();
		if(value.equals("Compra"))
		{							
			ArrayList<Compra> rs = null;
			rs = balance.mostrarCompras();
			for(Compra cpr : rs)
			{	
				LocalDate localDate_2 = cpr.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int dia = localDate_2.getDayOfMonth();
				int mes = localDate_2.getMonthValue();
				int anio = localDate_2.getYear();
				String a ="Null";							
				if(cpr.getProveedor()!=null)
				{
					a = ""+cpr.getProveedorID();
				}
				if(stage == "day")
				{					
					if(dia==day && mes==month && anio==year)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
				}
				else if(stage == "month")
				{
					if(mes==month && anio==year)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
				}
				else if(stage == "year")
				{
					if(anio==year)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
				}	
				else if(stage == "today")
				{
					Date today = new Date();					
					LocalDate localDate_3 = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int dia_t = localDate_3.getDayOfMonth();
					int mes_t = localDate_3.getMonthValue();
					int anio_t = localDate_3.getYear();
					if(dia_t==dia && mes_t==mes && anio_t==anio)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
					
				}
				
			}
		}
		else if(value.equals("Venta"))
		{			
			ArrayList<Venta> rs = null;
			rs = balance.mostrarVentas();
			for(Venta cpr : rs)
			{	
				LocalDate localDate_2 = cpr.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int dia = localDate_2.getDayOfMonth();
				int mes = localDate_2.getMonthValue();
				int anio = localDate_2.getYear();
				String a ="Null";							
				if(cpr.getCliente()!=null)
				{
					a = ""+cpr.getClienteID();
				}
				if(stage == "day")
				{					
					if(dia==day && mes==month && anio==year)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
				}
				else if(stage == "month")
				{
					if(mes==month && anio==year)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
				}
				else if(stage == "year")
				{
					if(anio==year)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
				}	
				else if(stage == "today")
				{
					Date today = new Date();					
					LocalDate localDate_3 = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int dia_t = localDate_3.getDayOfMonth();
					int mes_t = localDate_3.getMonthValue();
					int anio_t = localDate_3.getYear();
					if(dia_t==dia && mes_t==mes && anio_t==anio)
					{
						model.addRow(new Object[]{cpr.getID(),cpr.getFecha(),a,cpr.getTotal()});	
						tablehist.setModel(model);				
					}
					
				}
				
			}
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Historial() {
		setResizable(false);
		setTitle("Compras y Ventas");
		JLabel lblRowSelected = new JLabel("----");
		lblRowSelected.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
		tablehist = new JTable();		
		tablehist.setBackground(Color.WHITE);
		tablehist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				JTable source = (JTable)evt.getSource();
	            int row = source.rowAtPoint( evt.getPoint() );
	            int column = source.columnAtPoint( evt.getPoint() );
	            lblRowSelected.setText(""+ source.getModel().getValueAt(row, 0) );	 
	            
			}
		});
		JLabel lbldate = new JLabel("----");
		lbldate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
		lbldate.setHorizontalAlignment(SwingConstants.CENTER);
		fillmodelwith("Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 396);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ButtonGroup grupo1 = new ButtonGroup();
		
		JLabel lblVer = new JLabel("Ver");
		lblVer.setHorizontalAlignment(SwingConstants.CENTER);
		lblVer.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
		
		JComboBox comboBoxItems = new JComboBox();
		comboBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(comboBoxItems.getSelectedItem().toString());
				if(comboBoxItems.getSelectedItem().toString().equals("Compras"))
				{
					fillmodelwith("Compra");
					state = "Compra";
					sumaTotal();
				}
				else if(comboBoxItems.getSelectedItem().toString().equals("Ventas"))
				{
					state = "Venta";
					fillmodelwith("Venta");
					sumaTotal();
				}
				
			}
		});
		comboBoxItems.setModel(new DefaultComboBoxModel(new String[] {"", "Compras", "Ventas"}));
		
		JLabel lblHistorial = new JLabel("Historial");
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setFont(new Font("Swis721 BT", Font.BOLD, 18));
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().setBackground(Color.WHITE);
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				int day = calendar.getDayChooser().getDay();
				int month = calendar.getMonthChooser().getMonth()+1;
				int year = calendar.getYearChooser().getYear();
				lbldate.setText(""+day+"/"+month+"/"+year);
				
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		scrollPane.setViewportView(tablehist);
		tablehist.setSurrendersFocusOnKeystroke(true);
		tablehist.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID","Fecha","Cliente/Proveedor","Total"
			}
		));
		
		JRadioButton rb1 = new JRadioButton("Completo");
		rb1.setBackground(Color.WHITE);
		rb1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rb1.setHorizontalAlignment(SwingConstants.CENTER);
		rb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.setRowCount(0);
				fillmodelwith(state);
				sumaTotal();
			}
		});
		rb1.setSelected(true);
		
		JRadioButton rb3 = new JRadioButton("Hoy");
		rb3.setBackground(Color.WHITE);
		rb3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rb3.setHorizontalAlignment(SwingConstants.CENTER);
		rb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);				
				fillmodelwithDate(state, new Date(), "today");
				sumaTotal();
				
			}
		});
		
		JRadioButton rb2 = new JRadioButton("D\u00EDa");
		rb2.setBackground(Color.WHITE);
		rb2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rb2.setHorizontalAlignment(SwingConstants.CENTER);
		rb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!lbldate.getText().equals("----"))
				{
					model.setRowCount(0);
					Date date = StringtoDate(lbldate.getText().toString());
					fillmodelwithDate(state, date, "day");
					sumaTotal();
				}
			}
		});
		
		JRadioButton rb5 = new JRadioButton("Mes");
		rb5.setBackground(Color.WHITE);
		rb5.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rb5.setHorizontalAlignment(SwingConstants.CENTER);
		rb5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!lbldate.getText().equals("----"))
				{
					model.setRowCount(0);
					Date date = StringtoDate(lbldate.getText().toString());
					fillmodelwithDate(state, date, "month");
					sumaTotal();
				}
			}
		});
		
		JRadioButton rb4 = new JRadioButton("A\u00F1o");
		rb4.setBackground(Color.WHITE);
		rb4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rb4.setHorizontalAlignment(SwingConstants.CENTER);
		rb4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!lbldate.getText().equals("----"))
				{
					model.setRowCount(0);
					Date date = StringtoDate(lbldate.getText().toString());
					fillmodelwithDate(state, date, "year");
					sumaTotal();
				}
			}
		});
		
		grupo1.add(rb1);
		grupo1.add(rb2);
		grupo1.add(rb3);
		grupo1.add(rb4);
		grupo1.add(rb5);
		
		JLabel lbl_random = new JLabel("Total:");
		lbl_random.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
		
		texttotal = new JTextField();
		texttotal.setEditable(false);
		texttotal.setColumns(10);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!lblRowSelected.getText().equals("----"))
				{
					int id = Integer.parseInt(lblRowSelected.getText());
					Detalles recibo = new Detalles(state,id);
					recibo.setVisible(true);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la tabla");
				}
				
			}
		});
		
		JButton btnEliminar_1_1 = new JButton("Eliminar");
		btnEliminar_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lblRowSelected.getText().equals("----"))
				{
					JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para eliminar");
				}
				else
				{
					int opt = JOptionPane.showConfirmDialog(null, "¿Seguro desea eliminar este registro?");
					if(opt==0)
					{
						int id = Integer.parseInt(lblRowSelected.getText());
						Balance balance = new Balance();
						if(state=="Compra")
						{
							
							balance.eliminarCompra(id);
							model.setRowCount(0);
							fillmodelwith(state);
							
						}
						else if(state=="Venta")
						{
							balance.eliminarVenta(id);
							model.setRowCount(0);
							fillmodelwith(state);
						}
						
					}
					
						
				}
						
			}
		});
		
		JLabel lbl_x = new JLabel("ID:");
		lbl_x.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 11));
		
		
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblVer, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rb3)
							.addGap(22)
							.addComponent(rb4)
							.addGap(10)
							.addComponent(rb2)
							.addGap(10)
							.addComponent(rb5))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(rb1)
								.addGap(18)
								.addComponent(lbldate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(26)
								.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnVer, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnEliminar_1_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbl_x, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblRowSelected, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lbl_random, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(texttotal, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(403, Short.MAX_VALUE)
					.addComponent(lblHistorial, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(189))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblHistorial, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblVer)
										.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
									.addGap(22)
									.addComponent(calendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(rb1)
										.addComponent(lbldate)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(197)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbl_x)
										.addComponent(lblRowSelected))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnVer)
										.addComponent(btnEliminar_1_1))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rb3)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(rb4)
									.addComponent(rb5)
									.addComponent(rb2)))
							.addGap(54))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(texttotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_random))
							.addContainerGap(78, Short.MAX_VALUE))))
		);
		
		contentPane.setLayout(gl_contentPane);
		
		
		
		
	}
}
