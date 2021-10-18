import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;   
/**
 * Write a description of class Compra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Compra
{   
    // instance variables - replace the example below with your own
    private int ID;
    private Date fecha;
    static private Proveedor proveedor;
    private HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();   
    private int total;  

    /** 
     * Constructor for objects of class Compra
     */
    public Compra(int id,Date fech,HashMap<Producto,Integer> produts)
    {   
        // initialize instance variable
        ID = id;    
        fecha = fech;
        productos = produts;
        calcularTotal();
    }   
    
    public void setProveedor(Proveedor prov)
    {   
        proveedor = prov;
    }

    public void añadirProducto(Producto prod,int cnt)
    {
       productos.put(prod,cnt);
    }
    
    public int getID()  
    {
        return ID;
    }
    
    public Proveedor getProveedor()
    {
    	return proveedor;
    }
    
    public HashMap<Producto,Integer> getProductos()
    {
    	return productos;
    }
    
    public Date getFecha()  
    {
        return fecha;
    }
    
    public static String getProveedorNombre()
    {
        String a = "Null";
        if(proveedor == null)
        {
            a = "Null";
        }
        else
        {
            a = proveedor.getNombre();
        }
        return a;
    }   
    
    public Integer getProveedorID()  
    {
        return proveedor.getNID();
    }   
    
    private void calcularTotal()    
    {
        for(Map.Entry<Producto,Integer> data : productos.entrySet())
        {
            total += data.getKey().getPrecio_Compra()*data.getValue();
        }
    }
    
    public int getTotal()   
    {
        return total;
    }
    
    
    public void imprimir()  
    {   
        System.out.println("---------------------------");
        System.out.println("ID Compra: "+getID());
        System.out.println("Fecha Compra: "+getFecha());
        System.out.println("Proveedor: "+getProveedorNombre()+" -- "+getProveedorID());
        System.out.println("Productos: ");
        System.out.println(" ID -   Nombre  -   Cantidad  - Total");
        for(Map.Entry<Producto,Integer> data : productos.entrySet())
        {   
            int tot = data.getKey().getPrecio_Compra()*data.getValue();
            System.out.println(data.getKey().getID()+"  -  "+data.getKey().getNombre()+" - "+data.getValue()+"  -  "+tot);
        }
        System.out.println("---------------------------");
    }
   
}