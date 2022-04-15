package connexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnexion {
	/* declaration d'une variable connection*/
    private Connection DBConnection;
    /* fonction qui retourne un objet connection*/
    public Connection connect(){
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Success");            
        }
        catch(ClassNotFoundException cnfe){
            //System.out.println("Driver not found" + cnfe);
        	System.out.println("Probleme de connexion");
        }        
        String url="jdbc:mysql://localhost:3306/bdtp?characterEncoding=utf8";        
        try{
          DBConnection=DriverManager.getConnection(url,"root","");
          System.out.println("Connexion reussie");
        }
        catch(SQLException se){
          System.out.println("Soucis de connexion");  
        }
        /*Retourne l'objet de type connexion*/
        return DBConnection;
    }
}