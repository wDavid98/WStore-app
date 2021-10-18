import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Detalles extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	
	private String state;
	private int id_tipo;
	
	Object[] header = new Object[]{"ID","Producto","Cantidad","Total"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	private JTextField text_type;
	private JTextField text_ID;
	private JTextField text_kind;
	private JTextField text_Date;
	private JTextField text_total;
	
	private JLabel lbl_kind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Detalles frame = new Detalles("",0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void filltable(int id)
	{
		Balance balance = new Balance();
		if(state=="Compra")
		{
			model.setRowCount(0);
			Compra cmp = balance.buscarCompra(id);
			lbl_kind.setText("Proveedor: ");
			text_Date.setText(""+cmp.getFecha());
			text_ID.setText(""+cmp.getID());
			if(cmp.getProveedor()!=null)
			{
				text_kind.setText(cmp.getProveedor().getNombre());
			}
			else
			{
				text_kind.setText("Null");
			}			
			text_total.setText(""+cmp.getTotal());
			text_type.setText("Compra");
			HashMap<Producto,Integer> prods = cmp.getProductos();
			for(Map.Entry<Producto,Integer> data : prods.entrySet())
	        {   
				int idt = data.getKey().getID();
				String nmb = data.getKey().getNombre();
				int cant = data.getValue();
				int total = cant*data.getKey().getPrecio_Compra();
				model.addRow(new Object[]{idt,nmb,cant,total});	
				table.setModel(model);
	        }	
		}
		else if (state=="Venta")
		{
			Venta cmp = balance.buscarVenta(id);
			model.setRowCount(0);			
			lbl_kind.setText("Cliente: ");
			text_Date.setText(""+cmp.getFecha());
			text_ID.setText(""+cmp.getID());
			if(cmp.getCliente()!=null)
			{
				text_kind.setText(cmp.getCliente().getNombre());
			}
			else
			{
				text_kind.setText("Null");
			}			
			text_total.setText(""+cmp.getTotal());
			text_total.setText(""+cmp.getTotal());
			text_type.setText("Venta");
			HashMap<Producto,Integer> prods = cmp.getProductos();
			for(Map.Entry<Producto,Integer> data : prods.entrySet())
	        {   
				int idt = data.getKey().getID();
				String nmb = data.getKey().getNombre();
				int cant = data.getValue();
				int total = cant*data.getKey().getPrecio_Venta();
				model.addRow(new Object[]{idt,nmb,cant,total});	
				table.setModel(model);
	        }	
			
		}
	}

	/**
	 * Create the frame.
	 */
	public Detalles(String type, int id_detalle) {
		setResizable(false);
		setTitle("Recibo");	
		state = type;
		id_tipo = id_detalle;
		if(state=="Venta")
		{
			
		}
		else if(state=="Compra")
		{
			
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 263);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDetalles = new JLabel("Detalles");
		lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalles.setFont(new Font("Swis721 Lt BT", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lbl_x = new JLabel("Tipo:");
		lbl_x.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lbl_x.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lbl_x1 = new JLabel("ID:");
		lbl_x1.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lbl_x1.setHorizontalAlignment(SwingConstants.LEFT);
		
		lbl_kind = new JLabel("Tercero:");
		lbl_kind.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lbl_kind.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lbl_x_2_1 = new JLabel("Fecha:");
		lbl_x_2_1.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lbl_x_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lbl_x_2_1_1 = new JLabel("Total:");
		lbl_x_2_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lbl_x_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		text_type = new JTextField();
		text_type.setEditable(false);
		text_type.setColumns(10);
		
		text_ID = new JTextField();
		text_ID.setEditable(false);
		text_ID.setColumns(10);
		
		text_kind = new JTextField();
		text_kind.setEditable(false);
		text_kind.setColumns(10);
		
		text_Date = new JTextField();
		text_Date.setEditable(false);
		text_Date.setColumns(10);
		
		text_total = new JTextField();
		text_total.setEditable(false);
		text_total.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lbl_kind, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbl_x1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lbl_x, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(text_ID)
										.addComponent(text_kind)
										.addComponent(text_type, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbl_x_2_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(text_Date))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbl_x_2_1_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(text_total, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(232)
							.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDetalles, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_x)
								.addComponent(text_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_x1)
								.addComponent(text_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_kind)
								.addComponent(text_kind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_x_2_1)
								.addComponent(text_Date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_x_2_1_1)
								.addComponent(text_total, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		filltable(id_tipo);
	}
}
