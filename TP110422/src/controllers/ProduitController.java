package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import connexion.DBConnexion;

public class ProduitController {

	String query;
	/*récuperation de l'objet connexion du package DBconnexion*/
	Connection cn= new DBConnexion().connect();

	/* Insertion d'un produit par l'Admin*/
	public void insertProd(String n, String d, Float p, int cat) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `produit`(`Nom`, `Description`, `Prix`, `ID_CATEGORIE`)" + "VALUES ('"+n+"','"+d+"','"+p+"','"+cat+"')";
			int newrow=stmt.executeUpdate(query);
			System.out.println(newrow);
			if(newrow>0)
			{
				JOptionPane.showMessageDialog(null, "Informations insérées");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		stmt.close();
	}
	
	public void insertProdID(int ID, String n, String d, Float p, int cat) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `produit`(`ID`, `Nom`, `Description`, `Prix`, `ID_CATEGORIE`)" + "VALUES ('"+ID+"','"+n+"','"+d+"','"+p+"','"+cat+"')";
			int newrow=stmt.executeUpdate(query);
			System.out.println(newrow);
			if(newrow>0)
			{
				JOptionPane.showMessageDialog(null, "Informations insérées");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		stmt.close();
	}

	/*Pour afficher tous les produits dans la BD*/
	public ResultSet seeProd() throws SQLException
	{
		Statement stmt= cn.createStatement();
		ResultSet rs= null;
		try{
			query = "SELECT ID, Nom, Description, Prix, ID_CATEGORIE FROM produit";
			rs = stmt.executeQuery(query);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
	// update

	public void updateProd(int ID, String n, String d, Float p, int cat) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();
		try{
			String query = "update produit set Nom='"+n+"',Description='"+d+"',Prix='"+p+"',ID_CATEGORIE='"+cat+"' where ID= '" + ID + "'";
			int row=stmt.executeUpdate(query);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Modification Validée");
				System.out.println("Modification validée");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void updateProdID(int IDfirst, int IDnew, String n, String d, Float p, int cat) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();
		try{
			String query = "update produit set ID='"+IDnew+"',Nom='"+n+"',Description='"+d+"',Prix='"+p+"',ID_CATEGORIE='"+cat+"' where ID= '" + IDfirst + "'";
			int row=stmt.executeUpdate(query);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Modification Validée");
				System.out.println("Modification validée");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Delete

	public void deleteProd(int ID) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt= cn.createStatement();
		try{
			String query = "delete from produit where ID= '" + ID + "'";
			int row=stmt.executeUpdate(query);
			System.out.println(row);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Suppression Validée");
				System.out.println("Suppression validée");
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}