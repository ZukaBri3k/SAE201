import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Reservation {
	private static int numero_reservation = 0;
	private int nb_chambre;
	private Date date_debut;
	private Date date_fin;
	private int nb_personne;
	private ArrayList<Chambre> liste_chambre;
	private boolean estValidee;
	private Client reserve;
	
	public Reservation(Date date_debut, Date date_fin, int nb_personne, Client c, Chambre ch) {
		super();
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.nb_personne = nb_personne;
		this.reserve = c;
		this.liste_chambre = new ArrayList<Chambre>();
		this.liste_chambre.add(ch);
		Reservation.numero_reservation++;
	}
	
	public void addChambre(Chambre c) {
		this.liste_chambre.add(c);
	}
	
	public void delChambre(Chambre c) {
		if(this.liste_chambre.contains(c)) {
			this.liste_chambre.remove(c);
		}
	}
	
	public void setNbPersonne(int nb) {
		this.nb_personne = nb;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date_debut, date_fin, liste_chambre, nb_chambre, nb_personne, reserve);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(date_debut, other.date_debut) && Objects.equals(date_fin, other.date_fin)
				&& Objects.equals(liste_chambre, other.liste_chambre) && nb_chambre == other.nb_chambre
				&& nb_personne == other.nb_personne && Objects.equals(reserve, other.reserve);
	}
	
	public static void main(String[] args) {
		
		Chambre ch = new Chambre(1, false, 2, 30.0);
		Client c = new Client("Dumerchat", "Kyrill", "0613024751");
		Hotel CB = new Hotel(new Reservation(new Date("01/07/2004"), new Date("03/07/2004"), 4, c, ch));
		
	}

	public static int getNumero_reservation() {
		return numero_reservation;
	}

	public static void setNumero_reservation(int numero_reservation) {
		Reservation.numero_reservation = numero_reservation;
	}

	public int getNb_chambre() {
		return nb_chambre;
	}

	public void setNb_chambre(int nb_chambre) {
		this.nb_chambre = nb_chambre;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public int getNb_personne() {
		return nb_personne;
	}

	public void setNb_personne(int nb_personne) {
		this.nb_personne = nb_personne;
	}

	public ArrayList<Chambre> getListe_chambre() {
		return liste_chambre;
	}

	public void setListe_chambre(ArrayList<Chambre> liste_chambre) {
		this.liste_chambre = liste_chambre;
	}

	public boolean isEstValidee() {
		return estValidee;
	}

	public void setEstValidee(boolean estValidee) {
		this.estValidee = estValidee;
	}

	public Client getReserve() {
		return reserve;
	}

	public void setReserve(Client reserve) {
		this.reserve = reserve;
	}

	@Override
	public String toString() {
		return "Reservation [nb_chambre=" + nb_chambre + ", date_debut=" + date_debut + ", date_fin=" + date_fin
				+ ", nb_personne=" + nb_personne + ", liste_chambre=" + liste_chambre + ", estValidee=" + estValidee
				+ ", reserve=" + reserve + "]";
	}
}
