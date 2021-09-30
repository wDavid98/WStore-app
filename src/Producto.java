import java.util.Date;
/**
 * Write a description of class Producto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Producto
{
    // instance variables - replace the example below with your own
    private int ID;
    private String nombre;
    private int precio_compra;
    private int precio_venta;
    private int stock;
    private Date vence;

    /**
     * Constructor for objects of class Producto
     */
    
    
    public Producto(String nom,int P_com, int P_ven)
    {
        // initialize instance variables
        nombre = nom;
        precio_compra = P_com;
        precio_venta = P_ven;
    }

    

	public int getID()
    {
        return ID;
    }
    
    public int getStock()
    {
        return stock;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public int getPrecio_Compra()
    {
        return precio_compra;
    }
    
    public int getPrecio_Venta()
    {
       return precio_venta;
    }
    
    public Date getVencimiento()
    {
        return vence;
    }
    
    public void setID(int id)
    {
        ID = id;
    }
    
    public void setNombre(String nbre)
    {
        nombre = nbre;
    }
    
    public void setPre_Compra(int Pre)
    {
        precio_compra = Pre;
    }
    
    public void setPre_Venta(int Pre)
    {
        precio_venta = Pre;
    }
    
    public void setStock(int stk)
    {       
        stock = stk;
    }
    
    public void setVence(Date date)
    {
        vence = date;
    }
}
