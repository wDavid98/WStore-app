import java.util.ArrayList;
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
    private int newID = 0;
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
           prod.setID(newID);
           productos.add(prod);     
           newID++;
       }
    }
    
    public int getNextID()
    {
        return newID;
    }
    
    public void addProduct(Producto prod) //Método temporal
    {
        prod.setID(newID);
        productos.add(prod);
        newID++;
    }
    
    public void addOldProducts(ArrayList<Producto> cprs)
    {
        for(Producto prod : cprs)
        {
            for(Producto producto : productos)
            {
                if(prod.getID() == producto.getID())
                {
                    producto.setStock(prod.getStock());
                }
            }
        }
    }   
    
    public Producto ProductoNuevo()     
    {       
        System.out.println("Nombre: "); 
        String nombre = sc.next();
        System.out.println("Precio de compra: ");
        int Pcom = sc.nextInt();
        System.out.println("Precio de venta: ");
        int Pvent = sc.nextInt();
        System.out.println("Cantidad: ");
        int stk = sc.nextInt();
        Producto prod = new Producto(nombre,Pcom,Pvent);
        prod.setID(newID);
        newID++;
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
        try
        {
            if(nNmb =="-"){}         
            else    
            {
            buscarProducto(id).setNombre(nNmb);
            }
            if(nPcm == 999){}
            else
            {
             buscarProducto(id).setPre_Compra(nPcm);
            }
            
            if(nPvnt == 999){}
            else
            {
             buscarProducto(id).setPre_Venta(nPvnt);
            }   
            
            if(nStk == 999){}
            else
            {    
             buscarProducto(id).setStock(nStk);
            }
            System.out.println("");
            //System.out.print("Fecha de vencimiento: ");
            //Date ndate= sc.next();
            System.out.println("Edición terminada del producto: "+buscarProducto(id).getNombre());
                
        }catch(Exception e)
        {
            System.out.println("Formato Erroneo");
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