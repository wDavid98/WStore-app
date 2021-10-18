
/**
 * Write a description of class Proveedor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Proveedor
{
    // instance variables - replace the example below with your own
    private String Nombre;
    private int telefono;
    private String direccion;
    private Integer NID;

    /**
     * Constructor for objects of class Proveedor
     */
    public Proveedor(int nid, String nom,int tel,String dir)
    {
        // initialize instance variables        
        Nombre = nom;
        telefono = tel;
        direccion = dir;
        NID = nid;
    }

    //Métodos mutadores y de acceso para los atributos de clase
    public int getTelefono()
    {return  telefono;}
    public Integer getNID()
    {return NID;}
    public String getNombre()
    {return Nombre;}
    public String getDireccion()
    {return direccion;}
    public void setTelefono(int tel)
    {telefono = tel;}
    public void setNID(int nid)
    {NID = nid;}
    public void setNombre(String nom)
    {Nombre = nom;}
    public void setDireccion(String dir)
    {direccion = dir;}
    
   
}