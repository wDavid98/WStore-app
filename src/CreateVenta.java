import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CreateVenta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_5;

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

	/**
	 * Create the frame.
	 */
	public CreateVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblLista = new JLabel("Lista");
		
		JLabel lblInserteProducto = new JLabel("Inserte Producto");
		
		JLabel lblPorCdigo = new JLabel("Por C\u00F3digo:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta Unidad");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		JLabel lblTotal = new JLabel("Total");
		
		JButton btnAgregar = new JButton("Agregar");
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnVender = new JButton("VENDER");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		
		JLabel lblCc = new JLabel("C.C ");
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(115)
									.addComponent(lblInserteProducto))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblId, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNombre_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblPrecioVenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
										.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_4)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
										.addComponent(textField_1)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblPorCdigo, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField))
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAgregar))))
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLista, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(44)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblTelfono, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCc, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))))
							.addGap(222))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVender, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(347)))
					.addGap(0, 0, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPorCdigo)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre_1)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecioVenta)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCantidad))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotal)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addComponent(btnAgregar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInserteProducto)
								.addComponent(lblLista))
							.addGap(1)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCliente)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCc))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTelfono))))
					.addGap(18)
					.addComponent(btnVender)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
