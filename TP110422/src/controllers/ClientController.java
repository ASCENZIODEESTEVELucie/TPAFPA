package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import connexion.DBConnexion;

public class ClientController {

	String query;
	/*récuperation de l'objet connexion du package DBconnexion*/
	Connection cn= new DBConnexion().connect();

	/* Insertion d'un client*/
	public void insertClient(String n, String pn, String adr, String vil, String mail, int tel) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `client`(`Nom`, `Prenom`, `Adresse`, `City`, `Mail`, `Tel`)" + "VALUES ('"+n+"','"+pn+"','"+adr+"','"+vil+"','"+mail+"','"+tel+"')";
			int newrow=stmt.executeUpdate(query);
			System.out.println(newrow);
			if(newrow>0)
			{
				JOptionPane.showMessageDialog(null, "Informations insérées");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "veuillez selectionner une ligne et completer chaque champs");
			System.out.println(e);
		}
		stmt.close();
	}
	
	public void insertClientID(int ID,String n, String pn, String adr, String vil, String mail, int tel) throws SQLException
	{
		/*Instanciation d'un objet statement*/
		Statement stmt= cn.createStatement();
		try{
			query="INSERT INTO `client`(`ID`,`Nom`, `Prenom`, `Adresse`, `City`, `Mail`, `Tel`)" + "VALUES ('"+ID+"','"+n+"','"+pn+"','"+adr+"','"+vil+"','"+mail+"','"+tel+"')";
			int newrow=stmt.executeUpdate(query);
			System.out.println(newrow);
			if(newrow>0)
			{
				JOptionPane.showMessageDialog(null, "Informations insérées");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "veuillez selectionner une ligne et completer chaque champs");
			System.out.println(e);
		}
		stmt.close();
	}

	/*Pour afficher tout les clients dans la BD*/
	public ResultSet seeClient() throws SQLException
	{
		Statement stmt= cn.createStatement();
		ResultSet rs= null;
		try{
			query = "SELECT ID, Nom, Prenom, Adresse, City, Mail, Tel FROM client";
			rs = stmt.executeQuery(query);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
	// update

	public void updateClient(int ID, String n, String pn, String adr, String vil, String mail, int tel) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();
		try{
			String query = "update client SET Nom='"+n+"',Prenom='"+pn+"',Adresse='"+adr+"',City='"+vil+"',Mail='"+mail+"',Tel='"+tel+"' where ID= '" + ID + "'";
			int row=stmt.executeUpdate(query);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Modification Validée");
				System.out.println("Modification validée");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Veuillez supprimer les commandes liées à ce client en premier lieu");
			System.out.println(e);
		}
	}
	
	public void updateClientID(int IDfirst, int IDnew, String n, String pn, String adr, String vil, String mail, int tel) throws SQLException {

		// TODO Auto-generated method stub

		Statement stmt= cn.createStatement();
		try{
			String query = "update client SET ID='"+IDnew+"',Nom='"+n+"',Prenom='"+pn+"',Adresse='"+adr+"',City='"+vil+"',Mail='"+mail+"',Tel='"+tel+"' where ID= '" + IDfirst + "'";
			int row=stmt.executeUpdate(query);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Modification Validée");
				System.out.println("Modification validée");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Veuillez supprimer les commandes liées à ce client en premier lieu");
			System.out.println(e);
		}
	}

	//Delete

	public void deleteClient(int ID) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt= cn.createStatement();
		try{
			String query = "delete  from client where ID= '" + ID + "'";
			int row=stmt.executeUpdate(query);
			System.out.println(row);
			if(row>0)
			{
				JOptionPane.showMessageDialog(null, "Suppression Validée");
				System.out.println("Suppression validée");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Veuillez supprimer les commandes liées à ce client en premier lieu");
			System.out.println(e);
		}
	}
}