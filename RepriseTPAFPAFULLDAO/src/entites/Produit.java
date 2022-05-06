package entites;

public class Produit {
	private int id;
	private String titre;
	private String description;
	private Float prix;
	private int cat;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public Produit() {

	}
	
	public Produit(String n, String d, Float p, int c) {
		this.titre = n;
		this.description = d;
		this.prix = p;
		this.cat = c;
	}
	
	public Produit(int ID, String n, String d, Float p, int c) {
		this.id = ID;
		this.titre = n;
		this.description = d;
		this.prix = p;
		this.cat = c;
	}

	public String toString() {
		return "Produit [id=" + id + ", titre=" + titre + ", titre=" + description + ", prix=" + prix + ", cat=" + cat + "]";
	}
	
	
}
