package models;

public class CategorieModel {
	
	//attributs d'acces
	private int ID;
	private String Type;
	
	//constructeur
	public CategorieModel(int ID, String t)
	{
		/*paramètre courant */
		this.ID = ID;
		this.Type = t;
	}

	//getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

}
