import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import javax.swing.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
/**
 * Write a description of class Historial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Balance
{
    // instance variables - replace the example below with your own        p
    private int idCompra;
    private int idVenta;
    private Scanner sc;

    /**
     * Constructor for objects of class Historial
     */
    public Balance()
    {
        // initialise instance variables
        idCompra = 0;
        idVenta = 0;
        sc = new Scanner(System.in);
    }    
    
    public String prodToString(HashMap<Producto,Integer> prods)
    {
    	String result= "";
    	for(Map.Entry<Producto,Integer> data : prods.entrySet())
        {   
            result = result+data.getKey().getID()+":"+data.getValue()+"-";
        }
    	return result;
    }
    
    
    public Venta crearVenta(HashMap<Producto,Integer> cprs)
    {
        Date date = new Date();
        int idCpr = idVenta;   
        Venta venta = new Venta(idCpr,date,cprs); 
        return venta;
    }
    
    public void agregarVenta(Venta venta) //temporal
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;
		int rs;
		Date date = venta.getFecha();
		HashMap<Producto,Integer> prods = venta.getProductos();
		String prodStr = prodToString(prods);
		Integer idcln;
		if(venta.getCliente()== null)
		{
			idcln = null;
		}
		else
		{
			idcln = venta.getCliente().getCC();
		}		 
		int tot = venta.getTotal();
		
		String comn1 = "Insert into Venta(fecha,productos,id_cliente,total) values('"+date+"','"+prodStr+"','"+idcln+"','"+tot+"');";	
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeUpdate();	
			rs=0;
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al guardar venta(): "+e);
		}	
    }
    
    public Compra crearCompra(HashMap<Producto,Integer> cprs)
    {
        Date date = new Date();
        int idCpr = idCompra;   
        Compra compra = new Compra(idCpr,date,cprs);
        return compra;
    }
    
    public void agregarCompra(Compra compra)
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst;
		int rs;
		Date date = compra.getFecha();
		HashMap<Producto,Integer> prods = compra.getProductos();
		String prodStr = prodToString(prods);
		Integer idprov;
		if(compra.getProveedor()== null)
		{
			idprov = null;
		}
		else
		{
			idprov = compra.getProveedor().getNID();
		}		 
		int tot = compra.getTotal();
		
		String comn1 = "Insert into Compra(fecha,productos,id_proveedor,total) values('"+date+"','"+prodStr+"','"+idprov+"','"+tot+"');";	
		try {
			pst = conne.prepareStatement(comn1);
			rs = pst.executeUpdate();	
			rs=0;
			pst.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error BuynewProduct(): "+e);
		}	
    }
    
    public int getIDCompra()
    {
        return idCompra;
    }
    
    public int getIDVenta()
    {
        return idVenta;
    }    
    
    public HashMap<Producto,Integer> StringtoHashMap(String str)
    {
    	HashMap<Producto,Integer> prods = new HashMap<Producto,Integer>();
    	try
    	{    		
        	Inventario inventario = new Inventario();
        	String[] chts= str.split("-");
        	int i = 1;
        	while(i<=chts.length)
        	{
        		String[] cht2 = chts[i-1].split(":");        		
        		prods.put(inventario.buscarProducto(Integer.parseInt(cht2[0])),Integer.parseInt(cht2[1]));
        		i++;
        	}    	    		
    	}catch(Exception e)
    	{}
    	return prods;
    }
    
    public Date StringtoDate(String str)
    {
    	Date date = null;
    	SimpleDateFormat formatter= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);  
    	try {
			date=formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return date;
    }   
    
    public  ArrayList<Compra> mostrarCompras()
    {
    	
       ArrayList<Compra> compras = new ArrayList<Compra>();
       PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select * from Compra";		
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();	
			while(rs.next())
			{							
				Agenda agenda = new Agenda();
				int id = rs.getInt(1);
				Date date = StringtoDate(rs.getString(2));
				HashMap<Producto,Integer> prods = StringtoHashMap(rs.getString(3));				
				int total = rs.getInt(5);		
				Integer id_prov = rs.getInt(4);				
				Compra compra = new Compra(id,date,prods);	
				compra.setProveedor(agenda.buscarProveedor(id_prov));				
				compras.add(compra);			
			}							
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla (Ventas): "+e);
		}
       return compras;
    }
    
    public ArrayList<Venta> mostrarVentas()
    {
    	ArrayList<Venta> ventas =  new ArrayList<Venta>();    	
    	PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select * from Venta";		
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();	
			while(rs.next())
			{							
				Agenda agenda = new Agenda();
				int id = rs.getInt(1);
				Date date = StringtoDate(rs.getString(2));
				HashMap<Producto,Integer> prods = StringtoHashMap(rs.getString(4));				
				int total = rs.getInt(5);		
				Integer id_cln = rs.getInt(3);				
				Venta venta = new Venta(id,date,prods);	
				venta.setCliente(agenda.buscarCliente(id_cln));				
				ventas.add(venta);			
			}							
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla (Ventas): "+e);
		}
		return ventas;
    }
    
    
    private boolean verificarIDCompra(int id)
    {
        boolean fnd = false;
        ArrayList<Compra> compras = mostrarCompras();;
        for(Compra cmp : compras)
        {
            if(cmp.getID() == id)
            {
                fnd = true;
            }
        }
        return fnd;
    }
    
    public Compra buscarCompra(int id)
    {
        Compra cpr = null;
        ArrayList<Compra> compras = mostrarCompras();
        for(Compra cmp : compras)
        {
            if(cmp.getID() == id)
            {
                cpr = cmp;
            }
        }
        return cpr;
    }
    
    public void eliminarCompra(int id) //Modificar para utilizar consulta SQL
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "delete from Compra where id='"+id+"';";	
		try {			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();		
			JOptionPane.showMessageDialog(null,"Compra eliminada") ; 	
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al eliminar: "+e1);
		}
    }
    
    
    
    private boolean verificarIDVenta(int id)
    {
        boolean fnd = false;
        ArrayList<Venta> ventas = mostrarVentas();    	
        for(Venta cmp : ventas)
        {
            if(cmp.getID() == id)
            {
                fnd = true;
            }
        }
        return fnd;
    }
    
    public Venta buscarVenta(int id)
    {
        Venta cpr = null;
        ArrayList<Venta> ventas = mostrarVentas();    	
        for(Venta cmp : ventas)
        {
            if(cmp.getID() == id)
            {
                cpr = cmp;
            }
        }
        return cpr;
    }
    
       
    public void eliminarVenta(int id) //Modificar para utilizar consulta SQL
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "delete from Venta where id='"+id+"';";	
		try {			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();		
			JOptionPane.showMessageDialog(null,"Venta eliminada") ; 	
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al eliminar: "+e1);
		}
    }    
   
}
