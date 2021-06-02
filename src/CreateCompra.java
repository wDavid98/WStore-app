import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class CreateCompra extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Producto Nuevo");
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		
		JLabel lblPrecioCompra = new JLabel("Precio Compra");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblCantidad_1 = new JLabel("TOTAL:");
		
		JLabel lblCantidad_2 = new JLabel("----");
		
		JLabel lblCantidad_1_1 = new JLabel("Cantidad");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnGuardar = new JButton("GUARDAR");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(85)
							.addComponent(chckbxNewCheckBox))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblCantidad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPrecioCompra, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTipo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPrecioVenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
										.addComponent(lblDescripcin, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
								.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
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
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
							.addGap(138))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxNewCheckBox)
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipo)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecioVenta)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecioCompra)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCantidad)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblDescripcin))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCantidad_1_1)
								.addComponent(btnEliminar)
								.addComponent(lblCantidad_2)
								.addComponent(lblCantidad_1))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addComponent(btnGuardar)))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		JComboBox comboBox = new JComboBox();
		scrollPane_1.setViewportView(comboBox);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
