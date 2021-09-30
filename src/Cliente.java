
/**
 * Write a description of class Cliente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cliente
{
    // instance variables - replace the example below with your own
    private int CC;
    private String Nombre;
    private int telefono;
    private String direccion;

    /**
     * Constructor for objects of class Cliente
     */
    public Cliente(int id, String nom,int tel,String dir)
    {
        // initialize instance variables
        CC = id;
        Nombre = nom;
        telefono = tel;
        direccion = dir;        
    }

    //Métodos mutadores y de acceso para los atributos de clase
    public int getCC()
    {return CC;}
    public int getTelefono()
    {return  telefono;}    
    public String getNombre()
    {return Nombre;}
    public String getDireccion()
    {return direccion;}
    public void setCC(int id)
    {CC = id;}
    public void setTelefono(int tel)
    {telefono = tel;}    
    public void setNombre(String nom)
    {Nombre = nom;}
    public void setDireccion(String dir)
    {direccion = dir;}
    
    
}