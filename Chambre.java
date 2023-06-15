import java.util.Objects;

public class Chambre {
	@Override
	public String toString() {
		return "Chambre [numChambre=" + numChambre + ", estLibre=" + estLibre + ", nbPlace=" + nbPlace + ", prix="
				+ prix + "]";
	}

	private int numChambre;
	private boolean estLibre;
	private int nbPlace;
	private double prix;
	
	public Chambre(int num, boolean estLibre, int nbPlace, double prix) {
		super();
		this.numChambre = num;
		this.estLibre = estLibre;
		this.nbPlace = nbPlace;
		this.prix = prix;
	}
	
	public void setOccupee(boolean b) {
		this.estLibre = b;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numChambre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chambre other = (Chambre) obj;
		return numChambre == other.numChambre;
	}

	public int getNumChambre() {
		return numChambre;
	}

	public void setNumChambre(int numChambre) {
		this.numChambre = numChambre;
	}

	public boolean isEstLibre() {
		return estLibre;
	}

	public void setEstLibre(boolean estLibre) {
		this.estLibre = estLibre;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
}
