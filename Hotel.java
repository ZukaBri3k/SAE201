import java.util.ArrayList;

public class Hotel {

	private ArrayList<Reservation> liste_reservation;

	public Hotel(Reservation liste_reservation) {
		super();
	}
	
	public void ajouterReservation(Reservation r) {
		this.liste_reservation.add(r);
	}
	
	public void supprimerReservation(Reservation r) {
		if(this.liste_reservation.contains(r)) {
			this.liste_reservation.remove(r);
		}
	}

	public ArrayList<Reservation> getListe_reservation() {
		return liste_reservation;
	}

	public void setListe_reservation(ArrayList<Reservation> liste_reservation) {
		this.liste_reservation = liste_reservation;
	}
}
