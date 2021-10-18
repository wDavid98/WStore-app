import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * Write a description of class Agenda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Agenda
{
    // instance variables - replace the example below with your own
    private ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    /**
     * Constructor for objects of class Agenda
     */
    public Agenda()
    {
        // initialize instance variables
        
    }
    
    public ArrayList<Proveedor> getAgendaProveedores()
    {
        return proveedores;
    }
    
    public ArrayList<Cliente> getAgendaClientes()
    {
        return clientes;
    }

    public ArrayList<Proveedor> showProveedores()
    { 
    	//Comandos para consulta SQL
    	proveedores.clear();
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select nombre, telefono, direccion, NID from Proveedor";		
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();	
			
			while(rs.next())
			{				
				Proveedor prov = new Proveedor(rs.getInt(4),rs.getString(1),rs.getInt(2),rs.getString(3));
				proveedores.add(prov);
			}	
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla - 1: "+e);
		}
		return proveedores;
    }
    
    public Proveedor buscarProveedorbyName(String name)
    {
    	Proveedor prov = null;
    	PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select * from Proveedor where nombre='"+name+"';";	
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();
			//---------
			
			prov = new Proveedor(rs.getInt("NID"),rs.getString("nombre"),rs.getInt("telefono"),rs.getString("direccion"));
			
			//---------
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error obtener datos del proveedor");
		}
    	
    	
    	return prov;
    }
    
    public ArrayList<Cliente> showClientes()
    {
    	//Comandos para consulta SQL
    	clientes.clear();
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select CC, nombre, telefono, direccion from Cliente";		
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();	
			
			while(rs.next())
			{				
				Cliente cli = new Cliente(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));		
				clientes.add(cli);
			}	
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla - 1: "+e);
		}
		return clientes;
    }
    
    public void addProveedor(Proveedor prov) 
    {
    	Connection conne=sqliteconnection.dbconnector();
   		PreparedStatement pst;
   		int rs;
   		int id = prov.getNID();
   		String name = prov.getNombre();
   		int telph = prov.getTelefono();
   		String dir = prov.getDireccion();   	
   		
   		String comn1 = "Insert into Proveedor(NID,nombre,telefono,direccion) values('"+id+"','"+name+"','"+telph+"','"+dir+"');";	
   		try {
   			pst = conne.prepareStatement(comn1);
   			rs = pst.executeUpdate();	
   			rs=0;
   			pst.close();
   		} catch (SQLException e) {
   			JOptionPane.showMessageDialog(null,"No se puedo crear registro: "+name);
   		}	  
    }
    
    public void addCliente(Cliente client) //método temporar
    {
    	System.out.println("Entra a la función");
    	Connection conne=sqliteconnection.dbconnector();
   		PreparedStatement pst;
   		int rs;
   		int id = client.getCC();
   		String name = client.getNombre();
   		int telph = client.getTelefono();
   		String dir = client.getDireccion();   		
   		
   		String comn1 = "Insert into Cliente(cc,nombre,telefono,direccion) values('"+id+"','"+name+"','"+telph+"','"+dir+"');";	
   		try {
   			pst = conne.prepareStatement(comn1);
   			rs = pst.executeUpdate();	
   			rs=0;
   			pst.close();
   		} catch (SQLException e) {
   			JOptionPane.showMessageDialog(null,"No se puedo crear registro: "+name);
   		}	  
    }
    
    public Proveedor provNuevo(int nid,String prnom,int ptel,String dir)
    {       
        Proveedor prov = new Proveedor(nid,prnom,ptel,dir);
        return prov;
    }
    
    public Cliente clienteNuevo(int cc,String prnom,int ptel,String dir)
    {    
        Cliente cln = new Cliente(cc,prnom,ptel,dir);
        return cln;
    }
    
    public Cliente buscarCliente(int id)
    {
    	Cliente cln = null;
    	ArrayList<Cliente> clients = showClientes();
        for(Cliente pro : clients)
        {
            if(pro.getCC() == id)
            {
                cln = pro;
            }
        }
        return cln;
    }
    
    public Proveedor buscarProveedor(int nd)
    {
        Proveedor prov = null;
        ArrayList<Proveedor> provs = showProveedores();
        for(Proveedor pro : provs)
        {
            if(pro.getNID() == nd)
            {
                prov = pro;
            }
        }
        return prov;
    }
    
    public boolean existeCliente(int cc)    
    {
        boolean fnd = false;
        ArrayList<Cliente> clients = showClientes();
        for(Cliente pro : clients) 
        {
            if(pro.getCC() == cc)
            {
                fnd = true;
            }
        }
        return fnd;
    }
    
    public boolean existeProveedor(int nid)
    {
        boolean fnd = false;
        ArrayList<Proveedor> provs = showProveedores();
        for(Proveedor pro : provs)    
        {
            if(pro.getNID() == nid)
            {
                fnd = true;
            }
        }
        return fnd;
    }
    
    /*
    public ArrayList<Cliente> getClientes()
    {
        return clientes;
    }
    
    public ArrayList<Proveedor> getProveedores()
    {
        return proveedores;
    }
    */
    
    public void modificarCliente(int cc, String nNmb,int telf,String dir)
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "update Cliente set nombre='"+nNmb+"',telefono='"+telf+"',direccion='"+dir+"' where CC='"+cc+"';";	
		try {
			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();		
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al actualizar:");
		}
    }
    
    public void modificarProveedor(int nit, String nNmb,int telf,String dir)
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "update Proveedor set nombre='"+nNmb+"',telefono='"+telf+"',direccion='"+dir+"' where NID='"+nit+"';";	
		try {
			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();		
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al actualizar:");
		}           
    }
    
    public void eliminarCliente(int id)
    {   
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "delete from Cliente where cc='"+id+"';";	
		try {
			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();		
			JOptionPane.showMessageDialog(null,"Eliminado") ; 	
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al eliminar: "+e1);
		}
    }
    
    public void eliminarProveedor(int id)
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "delete from Proveedor where NID='"+id+"';";	
		try {
			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();		
			JOptionPane.showMessageDialog(null,"Eliminado") ; 	
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al eliminar: "+e1);
		}
    }
    
    public void agregarCliente(int cc,String prnom,int ptel,String dir)
    {
        clienteNuevo(cc,prnom,ptel,dir);
    }
    
    public void agregarProveedor(int nd,String prnom,int ptel,String dir)
    {   
        provNuevo(nd,prnom,ptel,dir);       
    }
    
    
       
}
