import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Reservation {
	private static int numero_reservation = 0;
	private int idReservation;
	private int nb_chambre;
	private LocalDate date_debut;
	private LocalDate date_fin;
	private int nb_personne;
	private ArrayList<Chambre> liste_chambre;
	private boolean estValidee;
	private Client reserve;
	private boolean estValide;
	
	public Reservation(LocalDate date_debut, LocalDate date_fin, int nb_personne, Client c, Chambre ch) {
		super();
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.nb_personne = nb_personne;
		this.reserve = c;
		this.liste_chambre = new ArrayList<Chambre>();
		this.addChambre(ch);
		this.idReservation = Reservation.numero_reservation;
		Reservation.numero_reservation++;
		estValide = false;
	}
	
	public void addChambre(Chambre c) {
		this.liste_chambre.add(c);
		this.nb_chambre++;
	}
	
	public void delChambre(Chambre c) {
		if(this.liste_chambre.contains(c)) {
			this.liste_chambre.remove(c);
			this.nb_chambre--;
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
		//Client c = new Client("Dumerchat", "Kyrill", "0613024751");
		
	}

	public int getNumero_reservation() {
		return this.idReservation;
	}

	public void setNumero_reservation(int numero_reservation) {
		this.idReservation = numero_reservation;
	}

	public int getNb_chambre() {
		return nb_chambre;
	}

	public void setNb_chambre(int nb_chambre) {
		this.nb_chambre = nb_chambre;
	}

	public LocalDate getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}

	public LocalDate getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(LocalDate date_fin) {
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

	public Client getReserve() {
		return reserve;
	}

	public void setReserve(Client reserve) {
		this.reserve = reserve;
	}
	public boolean getEstValide() {
		return estValide;
	}

	public void setEstValide(boolean estValide) {
		this.estValide = estValide;
	}

	
	@Override
	public String toString() {
		return "Réservation n°" + this.idReservation + " | nom: " + this.getReserve().getNom() + " | numéro de téléphone: " + this.getReserve().getNumero_tel() + " | nombre de personne: " + this.nb_personne + " | nombre de chambre: " + this.getNb_chambre();
	}
}
