import java.util.ArrayList;
import java.util.Scanner;
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

    public void showProveedores()
    { 
        if(proveedores.size()>0)    
        {
            System.out.println("NIT |   Nombre  |   Telefono    |   Dirección");
            for(Proveedor prov : proveedores)
            {
                System.out.print(prov.getNID()+"  |   ");
                System.out.print(prov.getNombre()+"  |   ");
                System.out.print(prov.getTelefono()+"  |   ");
                System.out.println(prov.getDireccion()+"  |   ");
            }  
        }
        else
        {
            System.out.println("-----------------");
            System.out.println("No existen proveedores");
            System.out.println("-----------------");
        }
    }
    
    public void showClientes()
    {
         if(clientes.size()>0)    
        {
            System.out.println("CC |   Nombre  |   Telefono    |   Dirección");
            for(Cliente prov : clientes)
            {
                System.out.print(prov.getCC()+"  |   ");
                System.out.print(prov.getNombre()+"  |   ");
                System.out.print(prov.getTelefono()+"  |   ");
                System.out.println(prov.getDireccion()+"  |   ");
            }  
        }
        else
        {
            System.out.println("-----------------");
            System.out.println("No existen clientes");
            System.out.println("-----------------");
        }
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
        for(Cliente pro : clientes)
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
        for(Proveedor pro : proveedores)
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
        for(Cliente pro : clientes) 
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
        for(Proveedor pro : proveedores)    
        {
            if(pro.getNID() == nid)
            {
                fnd = true;
            }
        }
        return fnd;
    }
    
    public ArrayList<Cliente> getClientes()
    {
        return clientes;
    }
    
    public ArrayList<Proveedor> getProveedores()
    {
        return proveedores;
    }
    
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
