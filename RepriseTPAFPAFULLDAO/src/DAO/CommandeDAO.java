package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Commande;

public class CommandeDAO {
	
	public void save(Commande c) {
		
			try {
				
				if(c.getID() != 0) {
					System.out.println("UPDATE");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"UPDATE commande set ListeProd=?,Total=?,Client=?,ID_CLIENT=? WHERE ID=?");
					ps.setString(1,c.getListeProd());
					ps.setFloat(2,c.getTotal());
					ps.setString(3,c.getClient());
					ps.setInt(4,c.getID_CLIENT());
					ps.setInt(5,c.getID());
					ps.executeUpdate();
				}else {
					System.out.println("ADD");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"INSERT INTO commande (ListeProd,Total,Client,ID_CLIENT) VALUES(?,?,?,?)");
					ps.setString(1,c.getListeProd());
					ps.setFloat(2,c.getTotal());
					ps.setString(3,c.getClient());
					ps.setInt(4,c.getID_CLIENT());
					ps.executeUpdate();
				}
				System.out.println("SAVED OK");
				
			} catch (Exception ex) {
	        	ex.printStackTrace();
	        	System.out.println("SAVED NO");
	        }
		
	}
	
	public Commande getById(int id) {
		try {
		
				PreparedStatement ps  = Db.connexion.prepareStatement(
						"SELECT * FROM commande WHERE ID=?");
				ps.setInt(1,id);
				
				ResultSet resultat=ps.executeQuery();
				
				Commande c = new Commande();
				resultat.next();
				c.setID(resultat.getInt( "ID" ));
				c.setListeProd(resultat.getString( "ListeProd" ));
				c.setTotal(resultat.getFloat( "Total" ));
				c.setID_CLIENT(resultat.getInt( "ID_CLIENT" ));
				return c;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public ArrayList<Commande> getAll() {
		ArrayList<Commande> col = new ArrayList<Commande>();
		try {
			PreparedStatement ps  = Db.connexion.prepareStatement("SELECT * FROM commande");
				
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Commande c = new Commande();
				c.setID(resultat.getInt( "ID" ));
				c.setListeProd(resultat.getString( "ListeProd" ));
				c.setTotal(resultat.getFloat( "Total" ));
				c.setID_CLIENT(resultat.getInt( "ID_CLIENT" ));
				col.add(c);
			}
			return col;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void deleteById(int id) {
		try {
			
				PreparedStatement ps  = Db.connexion.prepareStatement("DELETE FROM commande WHERE ID=?");
				ps.setInt(1,id);
				
				ps.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
