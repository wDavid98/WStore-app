import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Write a description of class Venta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Venta
{
    // instance variables - replace the example below with your own
    private int ID;
    private Date fecha;
    private Cliente cliente;
    private HashMap<Producto,Integer> productos =  new HashMap<Producto,Integer>();
    private int total;

    /**
     * Constructor for objects of class Venta
     */
    public Venta(int id,Date fech,HashMap<Producto,Integer> produts)
    {
       // initialize instance variables
       fecha = fech;
       ID = id;
       productos = produts;
       calcularTotal();
    }
    
    public void setCliente(Cliente cln){cliente = cln;}
    
    public int getID(){return ID;}
    
    public Date getFecha(){ return fecha;}
    
    public int getTotal(){ return total;}
    
    public void añadirProducto(Producto prod,int cnt)
    {
        productos.put(prod,cnt);
    }
    
    public String getClienteNombre()
    {
        String a = "Null";
        if(cliente == null)
        {
            a = "Null";
        }
        else
        {
            a = cliente.getNombre();
        }
        return a;
    }   
    
    public String getClienteID()
    {
        String a = "null";
        if(cliente == null)
        {
            a = "null";
        }
        else        
        {   
            a = ""+cliente.getCC();
        }   
        return a;
    }   
    
    private void calcularTotal()
    {
        for(Map.Entry<Producto,Integer> data : productos.entrySet())
        {
            total += data.getKey().getPrecio_Venta()*data.getValue();
        }
    }
    
    public void imprimir()
    {
        System.out.println("---------------------------");
        System.out.println("ID Venta: "+getID());
        System.out.println("Fecha Venta: "+getFecha());
        System.out.println("Cliente: "+getClienteNombre()+" -- "+getClienteID());
        System.out.println("Productos: ");
        System.out.println(" ID -   Nombre  -   Cantidad  - Total");
        for(Map.Entry<Producto,Integer> data : productos.entrySet())
        {
            int tot = data.getKey().getPrecio_Venta()*data.getValue();
            System.out.println(data.getKey().getID()+"  -  "+data.getKey().getNombre()+" - "+data.getValue()+" - "+tot);
        }
        System.out.println("---------------------------");
    }
}