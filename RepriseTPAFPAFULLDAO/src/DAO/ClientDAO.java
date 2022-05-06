package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Client;

public class ClientDAO {
	
	public void save(Client cl) {
		
			try {
				
				if(cl.getID() != 0) {
					System.out.println("UPDATE");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"UPDATE client set Nom=?,Prenom=?,Adresse=?,City=?,Mail=?,Tel=? WHERE ID=?");
					ps.setString(1,cl.getNom());
					ps.setString(2,cl.getPrenom());
					ps.setString(3,cl.getAdresse());
					ps.setString(4,cl.getCity());
					ps.setString(5,cl.getMail());
					ps.setInt(6,cl.getTel());
					ps.setInt(7,cl.getID());
					ps.executeUpdate();
				}else {
					System.out.println("ADD");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"INSERT INTO client (Nom,Prenom,Adresse,City,Mail,Tel) VALUES(?,?,?,?,?,?)");
					ps.setString(1,cl.getNom());
					ps.setString(2,cl.getPrenom());
					ps.setString(3,cl.getAdresse());
					ps.setString(4,cl.getCity());
					ps.setString(5,cl.getMail());
					ps.setInt(6,cl.getTel());
					ps.executeUpdate();
				}
				System.out.println("SAVED OK");
				
			} catch (Exception ex) {
	        	ex.printStackTrace();
	        	System.out.println("SAVED NO");
	        }
		
	}
	
	public Client getById(int id) {
		try {
		
				PreparedStatement ps  = Db.connexion.prepareStatement(
						"SELECT * FROM client WHERE ID=?");
				ps.setInt(1,id);
				
				ResultSet resultat=ps.executeQuery();
				
				Client cl = new Client();
				resultat.next();
				cl.setID(resultat.getInt( "ID" ));
				cl.setNom(resultat.getString( "Nom" ));
				cl.setPrenom(resultat.getString( "Prenom" ));
				cl.setAdresse(resultat.getString( "Adresse" ));
				cl.setCity(resultat.getString( "City" ));
				cl.setTel(resultat.getInt( "Tel" ));
				return cl;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	
	public ArrayList<Client> getAll() {
		ArrayList<Client> col = new ArrayList<Client>();
		try {
			PreparedStatement ps  = Db.connexion.prepareStatement("SELECT * FROM client");
				
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Client cl = new Client();
				cl.setID(resultat.getInt( "ID" ));
				cl.setNom(resultat.getString( "Nom" ));
				cl.setPrenom(resultat.getString( "Prenom" ));
				cl.setAdresse(resultat.getString( "Adresse" ));
				cl.setCity(resultat.getString( "City" ));
				cl.setTel(resultat.getInt( "Tel" ));
				col.add(cl);
			}
			return col;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void deleteById(int id) {
		try {
			
				PreparedStatement ps  = Db.connexion.prepareStatement("DELETE FROM client WHERE ID=?");
				ps.setInt(1,id);
				
				ps.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
