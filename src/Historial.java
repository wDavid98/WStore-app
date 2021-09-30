import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Write a description of class Historial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Historial
{
    // instance variables - replace the example below with your own    
    private ArrayList<Venta> ventas = new ArrayList<Venta>(); 
    private ArrayList<Compra> compras = new ArrayList<Compra>();
    private int idCompra;
    private int idVenta;
    private Scanner sc;

    /**
     * Constructor for objects of class Historial
     */
    public Historial()
    {
        // initialise instance variables
        idCompra = 0;
        idVenta = 0;
        sc = new Scanner(System.in);
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
        ventas.add(venta);
        idVenta++;
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
        compras.add(compra);    
        idCompra++;
    }
    
    public int getIDCompra()
    {
        return idCompra;
    }
    
    public int getIDVenta()
    {
        return idVenta;
    }    
    
    public void mostrarCompras()
    {
        if(compras.size()>0)
        {
            System.out.println("ID  |   Fecha  | Proveedor  | Total ");
            for(Compra prod : compras)
            {
                System.out.print(prod.getID()+"   |   ");
                System.out.print(prod.getFecha()+"   |   ");
                System.out.print(prod.getProveedorNombre()+"   |   ");
                System.out.println(prod.getTotal()+"   |   ");
            }
        }
        else
        {
            System.out.println("-----------------");
            System.out.println("No existen compras");
            System.out.println("-----------------");
        }
    }
    
    public void mostrarVentas()
    {
        if(ventas.size()>0)
        {
            System.out.println("ID  |   Fecha  | Cliente  | Total ");
            for(Venta prod : ventas)
            {
                System.out.print(prod.getID()+"   |   ");
                System.out.print(prod.getFecha()+"   |   ");
                System.out.print(prod.getClienteNombre()+"   |   ");
                System.out.println(prod.getTotal()+"   |   ");    
            }
        }
        else
        {
            System.out.println("-----------------");
            System.out.println("No existen ventas.");
            System.out.println("-----------------");
        }  
    }
    
    
    private boolean verificarIDCompra(int id)
    {
        boolean fnd = false;
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
        for(Compra cmp : compras)
        {
            if(cmp.getID() == id)
            {
                cpr = cmp;
            }
        }
        return cpr;
    }
    
    public void eliminarCompra(int id)
    {
        //verificar si el id está 
        boolean fnd = verificarIDCompra(id);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Compra no encontrado");
        }
        else
        {
           compras.remove(buscarCompra(id));
           mostrarCompras();
           System.out.println("----- Se eliminó la compra");
        }
    }
    
    public void verCompra()
    {
        mostrarCompras();
        System.out.println("¿Qué compra desea ver? (Digite ID de la compra)");
        int id = sc.nextInt();
        boolean fnd = verificarIDCompra(id);
        if(fnd)
        {
            Compra compra = buscarCompra(id);
            compra.imprimir();
        }
        else
        {
            System.out.println("ID de compra no encontrado.");
        }
    }
    
    private boolean verificarIDVenta(int id)
    {
        boolean fnd = false;
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
        for(Venta cmp : ventas)
        {
            if(cmp.getID() == id)
            {
                cpr = cmp;
            }
        }
        return cpr;
    }
    
    public void verVenta()
    {
        mostrarVentas();
        System.out.println("¿Qué venta desea ver? (Digite ID)");
        int id = sc.nextInt();
        boolean fnd = verificarIDVenta(id);
        if(fnd)
        {
            Venta venta = buscarVenta(id);
            venta.imprimir();
        }
        else
        {
            System.out.println("ID de venta no encontrado.");
        }
    }
    
    public void eliminarVenta(int id)
    {
        //verificar si el id está 
        boolean fnd = verificarIDVenta(id);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Venta no encontrado");
        }
        else
        {
           ventas.remove(buscarVenta(id));
           mostrarVentas();
           System.out.println("----- Se eliminó la venta");
        }
    }
    
    public ArrayList<Compra> getCompras()
    {
        return compras;
    }
    
    public ArrayList<Venta> getVentas()
    {
        return ventas;
    }
}