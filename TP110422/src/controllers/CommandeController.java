package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import connexion.DBConnexion;

public class CommandeController {

	String query;
	/*récuperation de l'objet connexion du package DBconnexion*/
	Connection cn= new DBConnexion().connect();

	/* Insertion d'une commande par l'Admin*/
	public void insertCom(String list, Float tot, String n, int idclient) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `commande`(`ListeProd`, `Total`, `Client`, `ID_CLIENT`)" + "VALUES ('"+list+"','"+tot+"','"+n+"','"+idclient+"')";
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

	/*Pour afficher toutes les commandes dans la BD*/
	public ResultSet seeCom() throws SQLException
	{
		Statement stmt= cn.createStatement();
		ResultSet rs= null;
		try{
			query = "SELECT ID, ListeProd, Total, Client, ID_CLIENT FROM commande";
			rs = stmt.executeQuery(query);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
	// update

	public void updateCom(int ID, String list, Float tot, String n, int idclient) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();
		try{
			String query = "update commande set ListeProd='"+list+"',Total='"+tot+"',Client='"+n+"',ID_CLIENT='"+idclient+"' where ID= '" + ID + "'";
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

	public void deleteCom(int ID) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt= cn.createStatement();
		try{
			String query = "delete from commande where ID= '" + ID + "'";
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