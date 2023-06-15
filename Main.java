import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{

	//private static Stage fenAnnualtion = new FenAnnulation();
	private static Stage recap;
	private static Stage fenRecherche;
	private static Stage recapModif;
	private static Stage courrier;
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		AccesDonnees.loadData();
		Main.fenRecherche = new FenRecherche();
		Main.fenRecherche.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	/*public static void afficheAnnulation() {
		Main.fenAnnualtion.show();
	}*/
	
	public static void afficheRecap(Reservation r) {
		Main.recap = new Recap(r);
		Main.recap.initModality(Modality.APPLICATION_MODAL);
		Main.recap.show();
	}
	
	public static Stage getFenRecherche() {
		return Main.fenRecherche;
	}
	
	public static void reloadRecherche() {
		Double posX = Main.fenRecherche.getX();
		Double posY = Main.fenRecherche.getY();
		Double height = Main.fenRecherche.getHeight();
		Double width = Main.fenRecherche.getWidth();
		
		Main.fenRecherche.close();
		Main.fenRecherche = new FenRecherche();
		Main.fenRecherche.show();
		Main.fenRecherche.setX(posX);
		Main.fenRecherche.setY(posY);
		Main.fenRecherche.setHeight(height);
		Main.fenRecherche.setWidth(width);
	}
	
	public static void afficheRecapModif(Reservation r) {
		Main.recapModif = new RecapModif(r);
		Main.recapModif.show();
	}
	
	
    
    public static void afficheCourrier(Reservation r) {
    	Main.courrier = new FenetreCourrier(r);
    	Main.courrier.show();
    }

}
