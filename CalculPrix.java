import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CalculPrix {
	private LocalDate arrivee;
	private LocalDate depart;
	private int nbpersonnes;
	private ArrayList<Chambre> tabChambre =new ArrayList<Chambre>();
	public CalculPrix(LocalDate arrivee, LocalDate départ, int nbpersonnes,ArrayList<Chambre> tab) {
		super();
		this.arrivee = arrivee;
		this.depart = départ;
		this.nbpersonnes = nbpersonnes;
		this.tabChambre = tab;
	}
	
	public double prix() {
		double prixTT = 0;
		double prixTTchambre = 0;
		long nbJours ;
		nbJours = ChronoUnit.DAYS.between(this.arrivee, this.depart); // -1 pour avoir le nombre de nuits et non de jours
		System.out.println("nb de jours : "+nbJours);
		
		for (Chambre c :this.tabChambre) {
			prixTTchambre += c.getPrix()*nbJours;
		}
		prixTT=prixTTchambre+this.nbpersonnes*nbJours;
		
		return prixTT;
	}
	
	public static void main(String[] args) {
		Chambre c1 = new Chambre(101, 60);
		Chambre c2 = new Chambre(102, 80);
		Chambre c3 = new Chambre(103, 100);
		
		ArrayList<Chambre> tabChambre = new ArrayList<Chambre>();
		tabChambre.add(c1);
		tabChambre.add(c2);
		//tabChambre.add(c3);
		LocalDate  d1 = LocalDate.of(2023, 7, 30);
		LocalDate  d2 = LocalDate.of(2023, 8, 4);
		
		CalculPrix test = new CalculPrix(d1, d2, 4, tabChambre);
		
		System.out.println(test.prix());
	}
}
