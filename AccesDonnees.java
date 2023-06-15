import java.time.LocalDate;
import java.util.ArrayList;

public abstract class AccesDonnees {
	protected static ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	public static void add(Reservation r) {
		AccesDonnees.reservations.add(r);
	}
	
	public static ArrayList<Reservation> getDonnees() {
		return AccesDonnees.reservations;
	}
	
	public static void loadData() {
		Reservation r1 = new Reservation( LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 31), 5, new Client("Vaillant", "Denis", "0631207415","denis.vaillant@gmail.com"), new Chambre(206, true, 5, 50.0));
		AccesDonnees.add(r1);
		Reservation r2 = new Reservation(LocalDate.of(2023, 6, 1), LocalDate.of(2023, 7, 1), 1, new Client("Porhiel", "Clément", "0750458789","clement.porhiel@gmail.com"), new Chambre(201, false, 2, 20.0));
		r2.addChambre(new Chambre(203, true, 4, 40.0));
		AccesDonnees.add(r2);
		Reservation r3 = new Reservation(LocalDate.of(2023, 8, 15), LocalDate.of(2023, 8, 16), 1, new Client("Tottereau", "Benoit", "3221655498","benoit.tottereau@gmail.com"), new Chambre(113, true, 3, 30.0));
		r2.addChambre(new Chambre(101, true, 3, 30.0));
		r2.addChambre(new Chambre(4, false, 2, 20.0));
		AccesDonnees.add(r3);
		Reservation r4 = new Reservation(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 1), 1, new Client("Lietard", "Ludovic", "4578895623","ludovic.lietard@gmail.com"), new Chambre(108, false, 1, 10.0));
		AccesDonnees.add(r4);
		Reservation r5 = new Reservation(LocalDate.of(2023, 12, 24), LocalDate.of(2023, 12, 25), 1, new Client("Thierry", "Constance", "5841256998","constance.thierry@gmail.com"), new Chambre(4, true, 2, 20.0));
		AccesDonnees.add(r5);
		Reservation r6 = new Reservation(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 12), 1, new Client("Lemlouma", "Tayeb", "7452306851","tayeb.lemlouma@gmail.com"), new Chambre(209, true, 3, 30.0));
		AccesDonnees.add(r6);
	}
}
