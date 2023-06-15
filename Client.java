import java.util.Objects;

public class Client{
	private String nom;
	private String prenom;
	private String numero_tel;
	private String adresseMail;
	public Client(String nom, String prenom, String numero_tel,String adresseMail) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numero_tel = numero_tel;
		this.adresseMail = adresseMail;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nom, numero_tel, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(numero_tel, other.numero_tel)
				&& Objects.equals(prenom, other.prenom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumero_tel() {
		return numero_tel;
	}

	public void setNumero_tel(String numero_tel) {
		this.numero_tel = numero_tel;
	}
	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
}
