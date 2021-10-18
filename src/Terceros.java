import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ComboBox;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Terceros extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField textID;
	private JTextField txtCra;
	private JTextField textTEL;
	private JComboBox comboBoxItems;
	private JComboBox comboBoxNames;
	
	private String state;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terceros frame = new Terceros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void filldatawithProv(String name)
	{
		Agenda agenda = new Agenda();
		ArrayList<Proveedor> provs = agenda.showProveedores();
		Proveedor provdr = null;
		for(Proveedor prov : provs)
		{
			
			if(prov.getNombre().equals(name))
			{
				provdr = prov;
				
			}
		}
		
		txtName.setText(provdr.getNombre());
		txtCra.setText(provdr.getDireccion());
		textID.setText(""+provdr.getNID());
		textTEL.setText(""+provdr.getTelefono());
	}
	
	private void filldatawithClient(String name)
	{
		Agenda agenda = new Agenda();
		ArrayList<Cliente> clns = agenda.showClientes();						
		Cliente cliente = null;
		for(Cliente cln : clns)
		{
			
			if(cln.getNombre().equals(name))
			{
				cliente = cln;			
			}
		}
		
		txtName.setText(cliente.getNombre());
		txtCra.setText(cliente.getDireccion());
		textID.setText(""+cliente.getCC());
		textTEL.setText(""+cliente.getTelefono());
	}
	
	
	public void setpopUp(String tipo)
	{
		
	}
	
	private void fillpopUp(String type)
	{
		try
		{
			comboBoxNames.removeAllItems();
			if(type.equals("Clientes"))
			{			
				
				Agenda agenda = new Agenda();
				ArrayList<Cliente> clientes = agenda.showClientes();
				comboBoxNames.addItem("");			
				for(Cliente cl : clientes)
				{
					comboBoxNames.addItem(cl.getNombre());
				}
			}
			else if(type.equals("Proveedor"))
			{			
				
				Agenda agenda = new Agenda();
				ArrayList<Proveedor> provs = agenda.showProveedores();			
				comboBoxNames.addItem("");
				for(Proveedor prov : provs)
				{
					comboBoxNames.addItem(prov.getNombre());
				}
			}
			
		}catch(Exception e) {}
		
		
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	@SuppressWarnings("rawtypes")
	public Terceros() {
		setResizable(false);
		setTitle("Clientes y Proveedores");		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblClientesYProveedors = new JLabel("CLIENTES Y PROVEEDORES");
		lblClientesYProveedors.setFont(new Font("Tw Cen MT", Font.BOLD, 12));
		
		JLabel lblBuscar = new JLabel("Buscar");
		
		JButton btnEliminar = new JButton("Borrar");
		
		comboBoxNames = new JComboBox();
		comboBoxNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if(comboBoxNames.getSelectedItem().toString().equals(""))
					{
						txtName.setText("");
						txtCra.setText("");
						textID.setText("");
						textTEL.setText("");
					}
					else
					{						
						if(state == "Cliente")
						{
							filldatawithClient(comboBoxNames.getSelectedItem().toString());
							
						}
						else if(state =="Proveedor")
						{
							
							filldatawithProv(comboBoxNames.getSelectedItem().toString());
						}
					}
					
				}catch(Exception e)
				{
					
				}
			
				
			}
		});
		
		JLabel lblCc = new JLabel("C.C");
		
		txtID = new JTextField();
		txtID.setColumns(10);
		JButton btnGuardar = new JButton("Guardar");
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int id = Integer.parseInt(txtID.getText());
					System.out.println(id);
					Agenda agenda = new Agenda();
					if(state.equals("Cliente"))
					{
						Cliente cliente = agenda.buscarCliente(id);
						filldatawithClient(cliente.getNombre());						
					}
					else if(state.equals("Proveedor"))
					{
						Proveedor prov = agenda.buscarProveedor(id);
						filldatawithProv(prov.getNombre());
					}
				}catch(Exception e)
				{
					System.out.println("Error Excepeción: "+e);
				}
			
			}
		});
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n:");
		lblInformacin.setFont(new Font("Swis721 BT", Font.BOLD, 14));
		lblInformacin.setHorizontalAlignment(SwingConstants.CENTER);
		JCheckBox chckbxEditar = new JCheckBox("Editar");
		chckbxEditar.setBackground(Color.WHITE);
		chckbxEditar.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxEditar.isSelected())
				{
					//textID.setEditable(true);
					textTEL.setEditable(true);
					txtCra.setEditable(true);
					txtName.setEditable(true);		
					btnGuardar.setEnabled(true);
				}else if(!chckbxEditar.isSelected())
				{
					//textID.setEditable(false);
					textTEL.setEditable(false);
					txtCra.setEditable(false);
					txtName.setEditable(false);		
					btnGuardar.setEnabled(false);
				}				
			}
		});
		JCheckBox chckbxNuevo = new JCheckBox("Nuevo Cliente");
		chckbxNuevo.setBackground(Color.WHITE);
		chckbxNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNuevo.isSelected())
				{					
					comboBoxItems.setEnabled(false);
					comboBoxNames.setEnabled(false);
					btnBuscar.setEnabled(false);
					txtID.setEnabled(false);					
					chckbxEditar.setEnabled(false);
					chckbxEditar.setSelected(false);
					btnEliminar.setEnabled(false);
					
					txtID.setText("");
					txtCra.setText("");
					txtName.setText("");
					textTEL.setText("");
					textID.setText("");
					txtCra.setEditable(true);					
					txtName.setEditable(true);
					textTEL.setEditable(true);
					textID.setEditable(true);		
					btnGuardar.setEnabled(true);
				}
				else
				{					
					comboBoxItems.setEnabled(true);
					comboBoxNames.setEnabled(true);					
					txtID.setEnabled(true);
					btnGuardar.setEnabled(true);
					chckbxEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
					txtID.setText("");
					txtCra.setText("");
					txtName.setText("");
					textTEL.setText("");
					textID.setText("");
					txtCra.setEditable(false);					
					txtName.setEditable(false);
					textTEL.setEditable(false);
					textID.setEditable(false);
					btnBuscar.setEnabled(false);
					btnGuardar.setEnabled(false);
				}
			}
		});
	
		JLabel lblNombreInf = new JLabel("Nombre");
		lblNombreInf.setFont(new Font("Swis721 BT", Font.BOLD, 12));
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setColumns(10);
		
		JLabel lbIDinf = new JLabel("C.C");
		lbIDinf.setFont(new Font("Swis721 BT", Font.BOLD, 12));
		
		JLabel lblNombre_1_1_1 = new JLabel("Por Nombre:");
		
		JLabel lblDirInf = new JLabel("Direcci\u00F3n");
		lblDirInf.setFont(new Font("Swis721 BT", Font.BOLD, 12));
		
		JLabel lblTelInf = new JLabel("Telefono");
		lblTelInf.setFont(new Font("Swis721 BT", Font.BOLD, 12));
		
		comboBoxItems = new JComboBox();
		comboBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxItems.getSelectedItem().toString() == "Cliente")
				{					
					lblCc.setText("C.C");
					lbIDinf.setText("C.C");
					fillpopUp("Clientes");
					state = "Cliente";
					chckbxNuevo.setText("Nuevo: "+state);					
				}
				else if(comboBoxItems.getSelectedItem().toString() == "Proveedor")
				{					
					lblCc.setText("NIT");
					lbIDinf.setText("NIT");
					fillpopUp("Proveedor");
					state = "Proveedor";
					chckbxNuevo.setText("Nuevo: "+state);						
				}				
			}
		});
		comboBoxItems.setModel(new DefaultComboBoxModel(new String[] {"","Cliente", "Proveedor"}));
		
		
		
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setColumns(10);
		
		txtCra = new JTextField();
		txtCra.setEditable(false);
		txtCra.setColumns(10);
		
		textTEL = new JTextField();
		textTEL.setEditable(false);
		textTEL.setColumns(10);
		
		
		
		
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int nd = Integer.parseInt(textID.getText().toString());
					String prnom = txtName.getText().toString();
					int ptel = Integer.parseInt(textTEL.getText().toString());
					String dir = txtCra.getText().toString();	
					Agenda agenda = new Agenda();					
					if(chckbxEditar.isSelected())
					{
						//Editar Cliente o usuario 						
						if(state=="Cliente")
						{							
							agenda.modificarCliente(nd,prnom,ptel,dir);
							JOptionPane.showMessageDialog(null, "Modificación de cliente exitosa");
						}
						if(state=="Proveedor")
						{
							agenda.modificarProveedor(nd,prnom,ptel,dir);
							JOptionPane.showMessageDialog(null, "Modificación de proveedor exitosa");
						}
					}
					else if(chckbxNuevo.isSelected())
					{
						//Crear nuevo cliente
						if(state=="Cliente")
						{													
							Cliente cln = agenda.clienteNuevo(nd,prnom,ptel,dir);
							agenda.addCliente(cln);
							txtID.setText("");
							txtCra.setText("");
							txtName.setText("");
							textTEL.setText("");
							textID.setText("");
							JOptionPane.showMessageDialog(null, "Registro de cliente exitoso");
						}
						if(state=="Proveedor")
						{
							Proveedor prov = agenda.provNuevo(nd,prnom,ptel,dir);
							agenda.addProveedor(prov);
							txtID.setText("");
							txtCra.setText("");							
							txtName.setText("");
							textTEL.setText("");
							textID.setText("");
							JOptionPane.showMessageDialog(null, "Registro de proveedor exitoso");
							
						}
					}
				}catch(Exception e) {JOptionPane.showMessageDialog(null, "Formato incorrecto de los valores de entrada: "+e);}
			}
		});
			
		
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!textID.getText().toString().equals(""))
				{
					int rta = JOptionPane.showConfirmDialog(null,"Desea eliminar este elemento?") ; 		
					System.out.println(state);
					if(rta==0)
					{
						try
						{
							if(state.equals("Cliente"))
							{
								Agenda agenda = new Agenda();
								int id = Integer.parseInt(textID.getText());
								agenda.eliminarCliente(id);								
								txtID.setText("");
								txtCra.setText("");
								txtName.setText("");
								textTEL.setText("");
								textID.setText("");
								fillpopUp("Clientes");
							}
							else if(state.equals("Proveedor"))
							{
								Agenda agenda = new Agenda();
								int nit = Integer.parseInt(textID.getText());
								agenda.eliminarProveedor(nit);
								txtID.setText("");
								txtCra.setText("");	
								txtName.setText("");
								textTEL.setText("");
								textID.setText("");
								fillpopUp("Proveedor");		
							}
							
						}catch(Exception e)
						{
							System.out.println("Error al eliminar");
						}	
					}
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNombre_1_1_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblBuscar)
										.addGap(26)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblClientesYProveedors)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addComponent(comboBoxItems, 0, 147, Short.MAX_VALUE)))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblCc, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(comboBoxNames, Alignment.TRAILING, 0, 146, Short.MAX_VALUE)
											.addComponent(txtID, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))))
							.addGap(90)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblDirInf, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
											.addComponent(lblTelInf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addComponent(lbIDinf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNombreInf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(textID, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(textTEL, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addComponent(txtCra, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(10)
												.addComponent(chckbxEditar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInformacin, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addGap(79))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(95)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(chckbxNuevo))
								.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))))
					.addGap(21))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblClientesYProveedors)
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBuscar))
							.addGap(18)
							.addComponent(lblNombre_1_1_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxNames, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCc)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(lblInformacin)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombreInf)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbIDinf)
								.addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDirInf)
								.addComponent(txtCra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelInf)
								.addComponent(textTEL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBuscar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxNuevo))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxEditar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGuardar)
								.addComponent(btnEliminar))))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}
}
