package entites;

public class Categorie {
	private int id;
	private String titre;
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
	
	public Categorie() {

	}
	
	public Categorie(String n) {
		this.titre = n;
	}
	
	public Categorie(int ID, String n) {
		this.id = ID;
		this.titre = n;
	}
	
//	@Override //la c'est le toString() pour tt
//	public String toString() {
//		return "Categorie [id=" + id + ", titre=" + titre + "]";
//	}
	
	@Override
	public String toString() {
		return titre;
	}

}
