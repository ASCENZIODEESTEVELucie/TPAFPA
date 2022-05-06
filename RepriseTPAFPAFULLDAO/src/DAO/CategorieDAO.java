package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Categorie;
import entites.Db;

public class CategorieDAO {
	
	public void save(Categorie cat) {
		
			try {
				
				if(cat.getId() != 0) {
					System.out.println("UPDATE");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"UPDATE categorie set Type=? WHERE ID=?");
					ps.setString(1,cat.getTitre());
					ps.setInt(2,cat.getId());
					ps.executeUpdate();
				}else {
					System.out.println("ADD");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"INSERT INTO categorie (Type) VALUES (?)");
					ps.setString(1,cat.getTitre());
					ps.executeUpdate();
				}
				System.out.println("SAVED OK");
				
			} catch (Exception ex) {
	        	ex.printStackTrace();
	        	System.out.println("SAVED NO");
	        }
		
	}
	
	public Categorie getById(int id) {
		try {
		
				PreparedStatement ps  = Db.connexion.prepareStatement(
						"SELECT * FROM categorie WHERE ID=?");
				ps.setInt(1,id);
				
				ResultSet resultat=ps.executeQuery();
				
				Categorie cat = new Categorie();
				resultat.next();
				cat.setId(resultat.getInt( "ID" ));
				cat.setTitre(resultat.getString( "Type" ));
				return cat;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	
	public ArrayList<Categorie> getAll() {
		ArrayList<Categorie> col = new ArrayList<Categorie>();
		try {
			PreparedStatement ps  = Db.connexion.prepareStatement("SELECT * FROM categorie");
				
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Categorie cat = new Categorie();
				cat.setId(resultat.getInt( "ID" ));
				cat.setTitre(resultat.getString( "Type" ));
				col.add(cat);
			}
			return col;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void deleteById(int id) {
		try {
			
				PreparedStatement ps  = Db.connexion.prepareStatement("DELETE FROM categorie WHERE ID=?");
				ps.setInt(1,id);
				
				ps.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
