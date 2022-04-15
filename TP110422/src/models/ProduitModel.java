package models;

public class ProduitModel {

	//attributs d'acces
	private int ID;
	private String Nom;
	private String Description;
	private Float Prix;
	private int ID_CATEGORIE;

	//constructeur
	public ProduitModel(int ID, String N, String D, Float P, int cat)
	{
		/*paramètre courant */
		this.ID = ID;
		this.Nom = N;
		this.Description = D;
		this.Prix = P;
		this.ID_CATEGORIE = cat;
	}

	//getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Float getPrix() {
		return Prix;
	}

	public void setPrix(Float prix) {
		Prix = prix;
	}

	public int getID_CATEGORIE() {
		return ID_CATEGORIE;
	}

	public void setID_CATEGORIE(int iD_CATEGORIE) {
		ID_CATEGORIE = iD_CATEGORIE;
	}

}
