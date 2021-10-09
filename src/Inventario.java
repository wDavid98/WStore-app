import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.sqlite.SQLiteConnection;
import java.util.Scanner;
import javax.swing.table.TableModel;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 * Write a description of class Inventario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)	
 */
public class Inventario 
{   
    private ArrayList<Producto> productos = new ArrayList<Producto>();    
    private int newID;
    private Scanner sc;
    

    /**
     * Constructor for objects of class Inventario
     */
    public Inventario()
    {
        // initialise instance variables   
        sc = new Scanner(System.in);
    }
    
    public ArrayList<Producto> getInventario()
    {
        return productos;
    }
         
    public void addNewProducts(ArrayList<Producto> cprs)
    {
       for(Producto prod : cprs)
       {
    	   	Connection conne=sqliteconnection.dbconnector();
	   		PreparedStatement pst;
	   		int rs;
	   		int id = prod.getID();
	   		String name = prod.getNombre();
	   		int pcom = prod.getPrecio_Compra();
	   		int pven = prod.getPrecio_Venta();
	   		int stk = prod.getStock();
	   		
	   		String comn1 = "Insert into Producto(ID,nombre,precio_compra,precio_venta,stock) values('"+id+"','"+name+"','"+pcom+"','"+pven+"','"+stk+"');";	
	   		try {
	   			pst = conne.prepareStatement(comn1);
	   			rs = pst.executeUpdate();	
	   			rs=0;
	   			pst.close();
	   		} catch (SQLException e) {
	   			JOptionPane.showMessageDialog(null,"No se puedo crear registro: "+name);
	   		}	    	   
    	   
       }
    }
    
    public int getNextID()
    {
    	int id = -1;
        ArrayList<Producto> products = mostrarProductos();
        for(Producto prod : products)
        {
        	if(prod.getID()>id)
        	{
        		id = prod.getID();
        	}
        }
        return id+1;
    }
    
    public void addOldProducts(ArrayList<Producto> cprs)
    {
        for(Producto prod : cprs)
        {
            ModificarProducto(prod.getID(), prod.getNombre(), prod.getPrecio_Compra(), prod.getPrecio_Venta(), prod.getStock());
        }
    }   
    
     
    public Producto ProductoNuevo(String nombre,int Pcom,int Pvent,int stk)     
    {   
        Producto prod = new Producto(nombre,Pcom,Pvent);
        prod.setID(getNextID());        
        prod.setStock(stk);
        return prod;
    }
    
    
    public Producto productoAntiguo(int id,int stkps)
    {
        String nombre = buscarProducto(id).getNombre();
        int Pcom = buscarProducto(id).getPrecio_Compra();
        int Pvent = buscarProducto(id).getPrecio_Venta();
        Producto prod = new Producto(nombre,Pcom,Pvent); 
        int olStk = buscarProducto(id).getStock();
        int nStk = olStk + stkps;
        prod.setID(id);
        prod.setStock(nStk);
        return prod;
    }
    
    //Comprueba si el ID existe en el inventario
    public boolean verificarID(int id)
    {
    	ArrayList<Producto> productos = mostrarProductos();
        boolean fnd = false;
        if(!productos.isEmpty())
        {
                for(Producto prod : productos)
            {
                if(prod.getID() == id)
                {
                    fnd = true;
                }            
            }
        }
        else{
        }
        return fnd; 
    }
    
    //Verifica si se puede vender determinada cantidad de algún producto
    public boolean verificarExistencia(int idpd,int cnt)
    {
        boolean ext = false;
        Producto prod = buscarProducto(idpd);   
        if(cnt<=prod.getStock())
            {
                ext=true;
            }
        return ext;
    }
    
    /*Retorna un objeto producto con las características para actualizar 
    el inventario*/
    public Producto productoAVender(int id,int cnt)  
    {
        String nombre = buscarProducto(id).getNombre();
        int Pcom = buscarProducto(id).getPrecio_Compra();
        int Pvent = buscarProducto(id).getPrecio_Venta();
        Producto prod = new Producto(nombre,Pcom,Pvent);   
        int olStk = buscarProducto(id).getStock();
        int nStk = olStk - cnt;
        prod.setStock(nStk);
        prod.setID(id);
        return prod; 
    }    
    
    public Producto buscarProducto(int id)
    {
    	Producto prod = null;
    	ArrayList<Producto> productos = mostrarProductos();  
        for(Producto pr : productos)
        {
            if(pr.getID()==id)
            {
                prod = pr;                
            }
        }
        return prod;
    }
    
    public ArrayList<Producto> mostrarProductos()
    {   
    	//Comandos para consulta SQL
    	productos.clear();
		PreparedStatement pst = null;	
		ResultSet rs = null;
		String comn1 = "select ID, nombre, precio_compra, precio_venta, stock, vence from Producto";		
		try {	
			Connection conne=sqliteconnection.dbconnector();
			pst = conne.prepareStatement(comn1);
			rs = pst.executeQuery();	
			
			while(rs.next())
			{				
				Producto prod = new Producto(rs.getString(2),rs.getInt(3),rs.getInt(4));
				prod.setID(rs.getInt(1));
				prod.setStock(rs.getInt(5));
				productos.add(prod);
			}	
			rs.close();
			pst.close();	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al llenar la tabla - 1: "+e);
		}
		return productos;
    }
    
    public void ModificarProducto(int id,String nNmb,int nPcm, int nPvnt,int nStk )
    {
    	Connection conne=sqliteconnection.dbconnector();
		PreparedStatement pst = null;			
		int rsu=0;				
		String comn1 = "update Producto set nombre='"+nNmb+"',precio_compra='"+nPcm+"',precio_venta='"+nPvnt+"',stock='"+nStk+"' where ID='"+id+"';";	
		try {
			
			pst = conne.prepareStatement(comn1);
			rsu = pst.executeUpdate();
			//JOptionPane.showMessageDialog(null,"Guardado con éxito");
			rsu = 0;
			pst.close();
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Error al actualizar: "+nNmb+"--"+nPcm+"--"+id);
		}
    }
    
    public void EliminarProducto()
    {   
        System.out.println("¿Qué producto desea eliminar? -- Ingrese ID");
        int id = sc.nextInt();  
        //verificar si el id está 
        boolean fnd = verificarID(id);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Producto no encontrado");
        }
        else    
        {
            System.out.println("¿Seguro desea eliminar el producto? -- (1)Sí");
            int conf = sc.nextInt();
            if(conf == 1)
            {
                productos.remove(buscarProducto(id));
                mostrarProductos();
                System.out.println("------ Producto eliminado");
            }
        }
    }   
    
}