
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreCourrier extends Stage{
	private final String ADRESSE_HOTEL ="hotel@cheval-blanc.fr";
	
	private Label texte;
	private Label objet;
	private Label monsieur;
	private Label adresseMail ;
	private Label cordial = new Label("Cordialement");
	private Label signature = new Label("Hotel Le Cheval Blanc");
	private Button fermer = new Button("Fermer");
	private GridPane racine	= new GridPane();
	
	public FenetreCourrier(Reservation r) {
		this.setWidth(700);
		this.setHeight(500);
		
		this.setResizable(false);
		
		this.monsieur = new Label("Bonjour Madame, Monsieur ​" + r.getReserve().getNom() +",");
		this.objet = new Label("Objet : Réservation au Cheval Blanc");
		this.texte =new Label("	Je me permet de vous confirmer la réservation de "+r.getNb_chambre()+" chambre(s) \npour "+r.getNb_personne()+" personne(s) pour la période du "+r.getDate_debut()+" au "+r.getDate_fin()+".");
		this.adresseMail = new Label("A "+r.getReserve().getAdresseMail());
		this.setTitle("Email de Confirmation");
		
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		
		
	}
	
	public Parent creerContenu(){
		
		racine.setAlignment(Pos.TOP_CENTER);
		racine.setPadding(new Insets(10));
		
		//racine.setGridLinesVisible(true); 
		Label adresse_hotel = new Label("De "+ADRESSE_HOTEL);
		
		this.adresseMail.setStyle("-fx-font-size : 15 ;");
		adresse_hotel.setStyle("-fx-font-size : 15 ;");
		texte.setStyle("-fx-font-size : 15 ;");
		cordial.setStyle("-fx-font-size : 15 ;");
		signature.setStyle("-fx-font-size : 15 ;");
		objet.setStyle("-fx-font-size : 15 ;");
		monsieur.setStyle("-fx-font-size : 15 ;");
		
		fermer.setOnAction(e -> this.close());
		
		racine.add(adresse_hotel, 0, 1);
		racine.add(this.objet, 0, 3);
		racine.add(this.monsieur, 0, 4);
		racine.add(this.texte, 0, 5);
		racine.add(this.cordial, 0, 7);
        racine.add(this.signature, 0, 8);
        
        racine.setVgap(10);
        
        AnchorPane bnFermer= new AnchorPane();
        AnchorPane.setRightAnchor(fermer, 15.0);
        bnFermer.getChildren().add(fermer);
        
        AnchorPane gauche = new AnchorPane();
        AnchorPane.setRightAnchor(adresseMail, 15.0);
        gauche.getChildren().add(adresseMail);
        
        
        
        VBox vbox = new VBox();
       
       
        vbox.getChildren().addAll(gauche,racine,bnFermer);
        vbox.setAlignment(Pos.CENTER_LEFT);
        
        
        this.setWidth(560);
		this.setHeight(350);
        
		return vbox;
	}

}