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
	
	public void fillpopUp(String type)
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
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	@SuppressWarnings("rawtypes")
	public Terceros() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblClientesYProveedors = new JLabel("CLIENTES Y PROVEEDORES");
		lblClientesYProveedors.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblBuscar = new JLabel("Buscar");
		JLabel lblNombre = new JLabel("Nombre");
		
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
		
		JCheckBox chckbxNuevo = new JCheckBox("Nuevo Cliente");
	
		JLabel lblNombreInf = new JLabel("Nombre");
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setColumns(10);
		
		JLabel lbIDinf = new JLabel("C.C");
		
		JLabel lblNombre_1_1_1 = new JLabel("Por:");
		
		JLabel lblDirInf = new JLabel("Direcci\u00F3n");
		
		JLabel lblTelInf = new JLabel("Telefono");
		
		comboBoxItems = new JComboBox();
		comboBoxItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxItems.getSelectedItem().toString() == "Cliente")
				{
					
					lblCc.setText("C.C");
					lbIDinf.setText("C.C");
					fillpopUp("Clientes");
					state = "Cliente";
					
				}
				else if(comboBoxItems.getSelectedItem().toString() == "Proveedor")
				{
					
					lblCc.setText("NIT");
					lbIDinf.setText("NIT");
					fillpopUp("Proveedor");
					state = "Proveedor";
					
				}				
			}
		});
		comboBoxItems.setModel(new DefaultComboBoxModel(new String[] {"Cliente", "Proveedor"}));
		
		
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setColumns(10);
		
		txtCra = new JTextField();
		txtCra.setEditable(false);
		txtCra.setColumns(10);
		
		textTEL = new JTextField();
		textTEL.setEditable(false);
		textTEL.setColumns(10);
		
		JCheckBox chckbxEditar = new JCheckBox("Editar");
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(53)
							.addComponent(lblClientesYProveedors))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBoxNames, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblBuscar)
									.addGap(26)
									.addComponent(comboBoxItems, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCc, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtID, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNombre_1_1_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(90)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbIDinf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textID, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNombreInf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDirInf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtCra, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTelInf, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textTEL, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(19)
											.addComponent(chckbxEditar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)
							.addComponent(lblInformacin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(101, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(95)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(chckbxNuevo)
							.addPreferredGap(ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGap(149))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(424, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblClientesYProveedors)
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxItems, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBuscar))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNombre_1_1_1)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(comboBoxNames, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCc)
								.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(lblInformacin)
							.addPreferredGap(ComponentPlacement.UNRELATED)
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
							.addComponent(chckbxEditar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGuardar)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	
}
