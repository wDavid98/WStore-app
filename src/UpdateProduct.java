import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateProduct extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textNbr;
	private JTextField textPcom;
	private JTextField textPven;
	private JTextField textCnt;
	
	

		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProduct frame = new UpdateProduct(1);
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
	
	//Método para llenar la info
	private void fillData(int idPrd)
	{
		Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select * from Producto where ID='"+idPrd+"';";		
		try {					
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();
			textID.setText(rs.getString("ID"));
			textNbr.setText(rs.getString("nombre"));
			textPcom.setText(rs.getString("precio_compra"));
			textPven.setText(rs.getString("precio_venta"));
			textCnt.setText(rs.getString("cantidad"));	
			rs.close();
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"No existe este ID: "+idPrd);
		}
	}

	/**
	 * Create the frame.
	 */
	public UpdateProduct(int idProd) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 312, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		
		//SQL Connection
		
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra");
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de venta");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		JLabel lblActualizacinDeProducto = new JLabel("Actualizaci\u00F3n de Producto");
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Connection conne=sqliteconnection.dbconnector();
				PreparedStatement pst = null;			
				int rsu=0;				
				String comn1 = "update Producto set nombre='"+textNbr.getText()+"',precio_compra='"+textPcom.getText()+"',precio_venta='"+textPven.getText()+"',cantidad='"+textCnt.getText()+"' where ID='"+idProd+"';";	
				try {
					
					pst = conne.prepareStatement(comn1);
					rsu = pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Guardado con éxito");
					rsu = 0;
					pst.close();
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Error al actualizar: "+e1+"--"+textNbr.getText()+"--"+textPcom.getText()+"--"+idProd);
				}
				cerrarVentana();	
			}
		});
		
		textID = new JTextField();
		textID.setEnabled(false);
		textID.setEditable(false);
		textID.setColumns(10);
		
		textNbr = new JTextField();
		textNbr.setColumns(10);
		
		textPcom = new JTextField();
		textPcom.setColumns(10);
		
		textPven = new JTextField();
		textPven.setColumns(10);
		
		textCnt = new JTextField();
		textCnt.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblPrecioDeCompra, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPrecioDeVenta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblCantidad, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textPcom)
						.addComponent(textPven)
						.addComponent(textCnt)
						.addComponent(textID)
						.addComponent(textNbr, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(39, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(btnActualizar)
					.addGap(106))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(88, Short.MAX_VALUE)
					.addComponent(lblActualizacinDeProducto, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblActualizacinDeProducto)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(textID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textNbr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioDeCompra)
						.addComponent(textPcom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioDeVenta)
						.addComponent(textPven, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad)
						.addComponent(textCnt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnActualizar)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		fillData(idProd);
		
		
	}
}
