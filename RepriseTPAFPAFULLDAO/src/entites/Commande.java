package entites;

public class Commande {

	//attributs d'acces
	private int ID;
	private String ListeProd;
	private Float Total;
	private String Client;
	private int ID_CLIENT;

	//constructeur
	public Commande()
	{
		
	}

	public Commande(int ID, String l, Float t, String n, int idcli)
	{
		/*paramètre courant */
		this.ID = ID;
		this.ListeProd = l;
		this.Total = t;
		this.Client = n;
		this.ID_CLIENT = idcli;
		
	}
	
	public Commande(String l, Float t, String n, int idcli)
	{
		/*paramètre courant */
		this.ListeProd = l;
		this.Total = t;
		this.Client = n;
		this.ID_CLIENT = idcli;
		
	}

	//getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getListeProd() {
		return ListeProd;
	}

	public void setListeProd(String listeProd) {
		ListeProd = listeProd;
	}

	public Float getTotal() {
		return Total;
	}

	public void setTotal(Float total) {
		Total = total;
	}

	public String getClient() {
		return Client;
	}

	public void setClient(String client) {
		Client = client;
	}

	public int getID_CLIENT() {
		return ID_CLIENT;
	}

	public void setID_CLIENT(int iD_CLIENT) {
		ID_CLIENT = iD_CLIENT;
	}
	
	@Override
	public String toString() {
		return "Commande [ID=" + ID + ", ListeProd=" + ListeProd + ", Total=" + Total + ", Client=" + Client
				+ ", ID_CLIENT=" + ID_CLIENT + "]";
	}

}
