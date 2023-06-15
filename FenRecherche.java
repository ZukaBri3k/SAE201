import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FenRecherche extends Stage{
	
	private Label lbNom = new Label("Nom :");
	private Label lbPrenom = new Label("Prénom :");
	private Label lbNumTel = new Label("Numéro de téléphone :");
	private Label lbNumReser = new Label("Numéro de réservation :");
	private Label lbArrivee = new Label("Arrivée :");
	private Label lbDepart = new Label("Départ :");
	
	private TextField tfNom = new TextField();
	private TextField tfPrenom = new TextField();
	private TextField tfNumTel = new TextField();
	private TextField tfNumReser = new TextField();
	
	private Button btnRecherche = new Button("Rechercher");
	private Button btnModifier = new Button("Modifier");
	private Button btnSupprimer = new Button("Supprimer");
	
	private DatePicker dpArrivee = new DatePicker();
	private DatePicker dpDepart = new DatePicker();
	
	private VBox vbRoot = new VBox();
	private VBox vbRecherche = new VBox();
	
	private HBox hbLigne1 = new HBox();
	private HBox hbLigne2 = new HBox();
	
	private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
	private ListView<Reservation> lvRes;
	
	private ObservableList<Reservation> t = FXCollections.observableArrayList();

	public FenRecherche() {
		this.setWidth(900);
		this.setHeight(500);
		this.setTitle("Rechercher une réservation");
		this.setResizable(true);
		this.sizeToScene();
		
		final ArrayList<Reservation> ToutesReservations = AccesDonnees.getDonnees();
		
		lvRes = new ListView<Reservation>(this.t);
		
		this.reloadData();
		
		createResearch();
		
		this.btnRecherche.setOnAction(e -> {this.Rechercher(ToutesReservations);});
		
		this.btnModifier.setOnAction(e -> { 
			
			if(this.lvRes.getSelectionModel().getSelectedItem().getEstValide()) {
				Main.afficheRecapModif(this.lvRes.getSelectionModel().getSelectedItem());
			} else {
				Alert alert  = new Alert(AlertType.ERROR, "Impossible de modifer cette réservation car celle-ci n'est pas validée. Veuillez la validez puis réessayer.");
                alert.showAndWait();
			}
		});
		
		this.btnSupprimer.setOnAction(e -> {
			
			Alert confirmer = new Alert(AlertType.CONFIRMATION);
			Alert annuler = new Alert(AlertType.INFORMATION);
			Alert suppr = new Alert(AlertType.INFORMATION);
            confirmer.setTitle("Confirmation");
            confirmer.setHeaderText(null);
            confirmer.setContentText("Voulez-vous vraiment supprimer cette réservation ? Cette action est irréversible.");
            
            confirmer.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK && this.lvRes.getSelectionModel().getSelectedItem().getEstValide()) {
                    confirmer.close();
                    AccesDonnees.reservations.remove(this.lvRes.getSelectionModel().getSelectedItem());
    				Main.reloadRecherche();
    				suppr.setTitle("Suppression");
                    suppr.setHeaderText(null);
                    suppr.setContentText("La réservation a été supprimée.");
                    suppr.showAndWait();
                        
                    
                    
                } else if (response == ButtonType.OK && !this.lvRes.getSelectionModel().getSelectedItem().getEstValide()) {
                	Alert alert  = new Alert(AlertType.ERROR, "Impossible de supprimer cette réservation car celle-ci n'est pas validée. Veuillez la validez puis réessayer.");
                    alert.showAndWait();
                } else {
                	confirmer.close();
                	annuler.setTitle("Annulation");
                    annuler.setHeaderText(null);
                    annuler.setContentText("Vous avez annuler la suppression.");
                    annuler.showAndWait();
                        
                }
            });
			
			
		});
		
		this.lvRes.setOnMouseClicked(e -> {
	        if(e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2 && e.getTarget().getClass().toString().equals("class com.sun.javafx.scene.control.LabeledText")) {
	           this.afficherRecap();
	         }    
		});
		
		Scene fenetre = new Scene(this.vbRoot);
		this.setScene(fenetre);
		fenetre.setFill(Color.WHITE);
	}
	
	public void createResearch() {
		this.hbLigne1.getChildren().setAll(this.lbNom, this.tfNom, this.lbPrenom, this.tfPrenom, this.lbNumTel, this.tfNumTel);
		this.hbLigne1.setAlignment(Pos.CENTER);
		this.hbLigne1.setPadding(new Insets(10));
		this.hbLigne1.setSpacing(15);
		
		BooleanBinding rien = Bindings.equal(this.lvRes.getSelectionModel().selectedIndexProperty(), -1);
		
		btnSupprimer.disableProperty().bind(rien);
		btnModifier.disableProperty().bind(rien);
		
		this.hbLigne2.getChildren().addAll(this.lbNumReser, this.tfNumReser, this.lbArrivee, this.dpArrivee, lbDepart, this.dpDepart, this.btnModifier, this.btnSupprimer, this.btnRecherche);
		this.hbLigne2.setAlignment(Pos.CENTER);
		this.hbLigne2.setPadding(new Insets(10));
		this.hbLigne2.setSpacing(15);
		
		this.dpDepart.setShowWeekNumbers(false);
		this.dpArrivee.setShowWeekNumbers(false);
		
		this.vbRecherche.getChildren().add(this.hbLigne1);
		this.vbRecherche.getChildren().add(this.hbLigne2);
		
		this.vbRoot.getChildren().addAll(vbRecherche, lvRes);
	}
	
	public void ajouterResultat(Reservation r) {
		
		if(r.getEstValide()) {
			this.listeReservation.add(r);
			this.t.add(r);
		}
		this.listeReservation.add(r);
		this.t.add(r);
	}
	
	public void supprimerResultat(Reservation r) {
		if(this.listeReservation.contains(r)) {
			this.listeReservation.remove(r);
			this.t.remove(r);
		}
	}
	
	public void afficherRecap() {
		Main.afficheRecap(this.listeReservation.get(this.lvRes.getSelectionModel().getSelectedIndex()));
	}
	
	public void reloadData() {
		for (Reservation r : AccesDonnees.getDonnees()) {
			this.ajouterResultat(r);
		}
	}
	
	public void Rechercher(ArrayList<Reservation> dep) {
		
		this.listeReservation.clear();;
		this.t.clear();
		
		for(Reservation r : dep) {
			this.ajouterResultat(r);
		}
		
		
		String nom = this.tfNom.getText();
		String prenom = this.tfPrenom.getText();
		String numTl = this.tfNumTel.getText();
		String numReservation = this.tfNumReser.getText();
		LocalDate dateArr = this.dpArrivee.getValue();
		LocalDate dateDep = this.dpDepart.getValue();
		
		if(nom != "") {
			for(Reservation r : dep) {
				if(!r.getReserve().getNom().equalsIgnoreCase(nom)) {
					this.supprimerResultat(r);
				}
			}
		}
		
		if(prenom != "") {
			for(Reservation r : dep) {
				if(!r.getReserve().getPrenom().equalsIgnoreCase(prenom)) {
					this.supprimerResultat(r);
				}
			}
		}
		
		if(numTl != "") {
			for(Reservation r : dep) {
				if(!r.getReserve().getNumero_tel().equalsIgnoreCase(numTl)) {
					this.supprimerResultat(r);
				}
			}
		}
		
		if(numReservation != "") {
			for(Reservation r : dep) {
				if(!String.valueOf(r.getNumero_reservation()).equalsIgnoreCase(numReservation)) {
					this.supprimerResultat(r);
				}
			}
		}
		
		if(dateArr != null) {
			for(Reservation r : dep) {
				if(r.getDate_debut().compareTo(dateArr) != 0) {
					this.supprimerResultat(r);
				}
			}
		}
		
		if(dateDep != null) {
			for(Reservation r : dep) {
				if(r.getDate_fin().compareTo(dateDep) != 0) {
					this.supprimerResultat(r);
				}
			}
		}
		
		if(this.lvRes.getItems().size() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Aucun résultat pour votre recherche selon les critères sélectionnés. Essayer de changer un des critères de recherche rempli.");
			alert.setHeaderText("Aucun résultat");
			alert.setTitle("Aucun résultat");
			alert.showAndWait();
		}
		
	}
	
}
