package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import connexion.DBConnexion;

public class CategorieController {

	String query;
	/*récuperation de l'objet connexion du package DBconnexion*/
	Connection cn= new DBConnexion().connect();

	/* Insertion d'une categorie*/
	public void insertCat(String type) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `categorie`(`Type`)" + "VALUES ('"+type+"')";
			int newrow=stmt.executeUpdate(query);
			System.out.println(newrow);
			if(newrow>0)
			{
				JOptionPane.showMessageDialog(null, "Informations insérées");
				//JOptionPane.showMessageDialog(null, this,"Informations insérées", newrow);
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		stmt.close();
	}
	
	public void insertCatID(int ID, String type) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `categorie`(`ID`,`Type`)" + "VALUES ('"+ID+"','"+type+"')";
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

	/*Pour afficher toutes les categories dans la BD*/
	public ResultSet seeCat() throws SQLException
	{
		Statement stmt= cn.createStatement();
		ResultSet rs= null;
		try{
			query = "SELECT ID, Type FROM categorie";
			rs = stmt.executeQuery(query);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
	// update

	public void updateCat(int ID, String Type) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();//supp les rs inutiles
		try{
			String query = "update categorie set Type='"+Type+"' where ID= '" + ID + "'";
			int row=stmt.executeUpdate(query);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Modification Validée");
				System.out.println("Modification validée");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Veuillez supprimer les produits liées à cette catégorie en premier lieu");
			System.out.println(e);
		}
	}
	
	public void updateCatID(int IDfirst, int IDnew, String Type) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();//supp les rs inutiles
		try{
			String query = "update categorie set ID='"+IDnew+"',Type='"+Type+"' where ID= '" + IDfirst + "'";
			int row=stmt.executeUpdate(query);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Modification Validée");
				System.out.println("Modification validée");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Veuillez supprimer les produits liées à cette catégorie en premier lieu");
			System.out.println(e);
		}
	}

	//Delete

	public void deleteCat(int ID) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt= cn.createStatement();//rs inutile
		try{
			String query = "delete from categorie where ID= '" + ID + "'";
			int row=stmt.executeUpdate(query);
			System.out.println(row);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Suppression Validée");
				System.out.println("Suppression validée");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Veuillez supprimer les produits liées à cette catégorie en premier lieu");
			System.out.println(e);
		}
	}
}