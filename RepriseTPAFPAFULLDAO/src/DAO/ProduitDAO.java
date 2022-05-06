package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Db;
import entites.Produit;

public class ProduitDAO {
	
	public void save(Produit prod) {
		
			try {
				
				if(prod.getId() != 0) {
					System.out.println("UPDATE");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"UPDATE produit set Nom=?,Description=?,Prix=?,ID_CATEGORIE=? WHERE ID=?");
					ps.setString(1,prod.getTitre());
					ps.setString(2,prod.getDescription());
					ps.setFloat(3,prod.getPrix());
					ps.setInt(4,prod.getCat());
					ps.setInt(5,prod.getId());
					ps.executeUpdate();
				}else {
					System.out.println("ADD");
					PreparedStatement ps  = Db.connexion.prepareStatement(
							"INSERT INTO produit (Nom,Description,Prix,ID_CATEGORIE) VALUES(?,?,?,?)");
					ps.setString(1,prod.getTitre());
					ps.setString(2,prod.getDescription());
					ps.setFloat(3,prod.getPrix());
					ps.setInt(4,prod.getCat());
					ps.executeUpdate();
				}
				System.out.println("SAVED OK");
				
			} catch (Exception ex) {
	        	ex.printStackTrace();
	        	System.out.println("SAVED NO");
	        }
		
	}
	
	public Produit getById(int id) {
		try {
		
				PreparedStatement ps  = Db.connexion.prepareStatement(
						"SELECT * FROM produit WHERE ID=?");
				ps.setInt(1,id);
				
				ResultSet resultat=ps.executeQuery();
				
				Produit p = new Produit();
				resultat.next();
				p.setId(resultat.getInt( "ID" ));
				p.setTitre(resultat.getString( "Nom" ));
				p.setDescription(resultat.getString( "Description" ));
				p.setPrix(resultat.getFloat( "Prix" ));
				p.setCat(resultat.getInt( "ID_CATEGORIE" ));
				return p;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	
	public ArrayList<Produit> getAll() {
		ArrayList<Produit> col = new ArrayList<Produit>();
		try {
			PreparedStatement ps  = Db.connexion.prepareStatement("SELECT * FROM produit");
				
			ResultSet resultat=ps.executeQuery();

			while(resultat.next()) {
				Produit p = new Produit();
				p.setId(resultat.getInt( "ID" ));
				p.setTitre(resultat.getString( "Nom" ));
				p.setDescription(resultat.getString( "Description" ));
				p.setPrix(resultat.getFloat( "Prix" ));
				p.setCat(resultat.getInt( "ID_CATEGORIE" ));
				col.add(p);
			}
			return col;
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	public void deleteById(int id) {
		try {
			
				PreparedStatement ps  = Db.connexion.prepareStatement("DELETE FROM produit WHERE ID=?");
				ps.setInt(1,id);
				
				ps.executeUpdate();
				
				System.out.println("DELETED OK");
			
		} catch (Exception ex) {
        	ex.printStackTrace();
        	System.out.println("DELETED NO");
        }
	}
}
