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
    private Scanner sc; 
    /**
     * Constructor for objects of class Agenda
     */
    public Agenda()
    {
        // initialize instance variables
        sc = new Scanner(System.in);
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
    
    public void addProveedor(Proveedor prov) //Método temporar
    {
        proveedores.add(prov);
    }
    
    public void addCliente(Cliente client) //método temporar
    {
        clientes.add(client);
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
       if(nNmb =="+"){}
       else 
       {
           buscarCliente(cc).setNombre(nNmb);
       }
       if(telf == 0){}
       else
       {
           buscarCliente(cc).setTelefono(telf);
       }
       if(dir == "+"){}
       else
       {
           buscarCliente(cc).setDireccion(dir);
       }
    }
    
    public void modificarProveedor(int nit, String nNmb,int telf,String dir)
    {
       if(nNmb =="+"){}
       else 
       {
           buscarProveedor(nit).setNombre(nNmb);
       }
       if(telf == 0){}
       else
       {
           buscarProveedor(nit).setTelefono(telf);
       }
       if(dir == "+"){}
       else
       {
           buscarProveedor(nit).setDireccion(dir);
       }
           
    }
    
    public void eliminarCliente(int id)
    {   
        //verificar si el id está 
        boolean fnd = existeCliente(id);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)    
        {
            System.out.println("Cliente no encontrado");
        }
        else    
        {
            clientes.remove(buscarCliente(id));
            showClientes();
            System.out.println("------ Cliente eliminado");         
        }
    }
    
    public void eliminarProveedor(int id)
    {
        //verificar si el id está       
        boolean fnd = existeProveedor(id);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Proveedor no encontrado**");
        }
        else    
        {
            proveedores.remove(buscarProveedor(id));
            showProveedores();
            System.out.println("------ Proveedor eliminado");
           
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
