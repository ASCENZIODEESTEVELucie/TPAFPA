package models;

public class ClientModel {

	//attributs d'acces
	private int ID;
	private String Nom;
	private String Prenom;
	private String Adresse;
	private String City;
	private String Mail;
	private int Tel;

	//constructeur
	public ClientModel(int ID, String N, String Pn, String Adr, String Vil, String e, int t)
	{
		/*paramètre courant */
		this.ID = ID;
		this.Nom = N;
		this.Prenom = Pn;
		this.Adresse = Adr;
		this.City = Vil;
		this.Mail = e;
		this.Tel = t;
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

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public int getTel() {
		return Tel;
	}

	public void setTel(int tel) {
		Tel = tel;
	}
}

	