import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Recap extends Stage{
	private GridPane root = new GridPane();
	
	private VBox num_chambre = new VBox();
	
	private Label lblnumReserv = new Label("Numéro de reservation :");
	private Label lblReserv = new Label();
	
	private Label lblNom = new Label("Nom :");
	private Label lblnom = new Label();
	private Label lblPrenom = new Label("Prenom :");
	private Label lblpren = new Label();
	private Label lblNum = new Label("Numéro de téléphone :");
	private Label lblNumTel = new Label();
	private Label lblNb = new Label("Nombre de personne :");
	private Label lblNbPer = new Label();
	
	private Label lblNumCh = new Label("Numéro de(s) chambre(s) :");
	private Label lblNuCh = new Label();
	private Label lblnbCh = new Label("Nombre de chambre :");
	private Label lblCh = new Label();
	
	private Label lbldateD = new Label("Date :");

	private DatePicker dateDebut = new DatePicker();
	private LocalDate dateD;
	private LocalDate dateF;
	private DatePicker dateFin = new DatePicker();
	
	private Label lblChLib = new Label("Chambre non libre");
	private boolean estLibre;
	
	private Label lblnumPla = new Label("Nombre de place :");
	private Label lblPlace = new Label();
	
	private Label lblprix = new Label("Prix :");
	private Label lblprice = new Label();
	
	private Label lblAdresseMail = new Label("Adresse mail :");
	private Label lblMail = new Label();
	
	private Button bModifier = new Button("Modifier");
	private Button bSupprimer = new Button("Supprimer");
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	private Reservation reserv;
	
	
	public Recap(Reservation reserv){
		
		this.reserv = reserv;
		
		this.lblReserv.setText(String.valueOf(reserv.getNumero_reservation()));
		
		this.lblnom.setText(reserv.getReserve().getNom());
		this.lblpren.setText(reserv.getReserve().getPrenom());
		this.lblNumTel.setText(reserv.getReserve().getNumero_tel());
		this.lblMail.setText(reserv.getReserve().getAdresseMail());
		this.lblNbPer.setText(String.valueOf(reserv.getNb_personne()));
		
		int total = 0;
		this.lblNuCh.setText(String.valueOf(reserv.getListe_chambre().get(0).getNumChambre()));
		
		for(int i = 1; i < reserv.getNb_chambre(); i++) {
			this.lblNuCh.setText(this.lblNuCh.getText() + ";" + String.valueOf(reserv.getListe_chambre().get(i).getNumChambre()));
		}
		
		for (Chambre c : reserv.getListe_chambre()) {
			
			total += c.getNbPlace();
			this.lblPlace.setText(String.valueOf(total));
		}
		
		this.lblprice.setText(String.valueOf(this.prix(reserv)));
		
		this.lblCh.setText(String.valueOf(reserv.getNb_chambre()));
		
		this.dateD = reserv.getDate_debut();
		this.dateF = reserv.getDate_fin();
		this.dateDebut.setValue(dateD);
		this.dateFin.setValue(dateF);
		
		this.setTitle("Récapitulatif de la réservation");
		this.setResizable(false);
		this.sizeToScene();
		
		bFermer.setOnAction(e -> {
			this.close();
		});
		
		bModifier.setOnAction(e -> {
			
			if(!reserv.getEstValide()) {
				Alert alert  = new Alert(AlertType.ERROR, "Impossible de modifer cette réservation car celle-ci n'est pas validée. Veuillez la validez puis réessayer.");
                alert.showAndWait();
			} else {
				Main.afficheRecapModif(reserv);
				this.close();
			}
		});
		
		this.bSupprimer.setOnAction(e -> {
					
					Alert confirmer = new Alert(AlertType.CONFIRMATION);
					Alert annuler = new Alert(AlertType.INFORMATION);
					Alert suppr = new Alert(AlertType.INFORMATION);
		            confirmer.setTitle("Confirmation");
		            confirmer.setHeaderText(null);
		            confirmer.setContentText("Voulez-vous vraiment supprimer cette réservation ? Cette action est irréversible.");
		            
		            confirmer.showAndWait().ifPresent(response -> {
		                if (response == ButtonType.OK && reserv.getEstValide()) {
		                    confirmer.close();
		                    AccesDonnees.reservations.remove(this.reserv);
		    				Main.reloadRecherche();
		    				suppr.setTitle("Suppression");
		                    suppr.setHeaderText(null);
		                    suppr.setContentText("La réservation a été supprimée.");
		                    suppr.showAndWait();
		    				this.close();
		                    
		                } else if (response == ButtonType.OK && !reserv.getEstValide()) {
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
		
		this.bEnvoyer.setOnAction(e -> {
			
			Alert confirmer = new Alert(AlertType.CONFIRMATION);
            confirmer.setTitle("Confirmation afficher le mail");
            confirmer.setHeaderText(null);
            confirmer.setContentText("Voulez-vous afficher le mail récapitulatif de cette réservation ?");
            reserv.setEstValide(true);
            
            ButtonType oui = new ButtonType("Oui");
            ButtonType non = new ButtonType("Non");
            
            confirmer.getButtonTypes().set(1, oui);
            confirmer.getButtonTypes().set(0, non);
            confirmer.showAndWait().ifPresent(response -> {
                if (response == oui) {
                    confirmer.close();
                    Main.afficheCourrier(reserv);
                } else {
                	confirmer.close();
                	Alert alert2 = new Alert(AlertType.INFORMATION, "La réservation a bien été validée");
                    alert2.showAndWait();
                }
            });
		});
		
		this.setScene(new Scene(creerContenu()));
	}
	
	private Parent creerContenu() {
		dateDebut.setShowWeekNumbers(false);
		
		final Callback<DatePicker, DateCell> dayCellFactory =
			    new Callback<DatePicker, DateCell>() {
			        @Override
			        public DateCell call(final DatePicker datePicker) {
			            return new DateCell() {
			                @Override
			                public void updateItem(LocalDate item, boolean empty) {
			                    super.updateItem(item, empty);

			                    if (item.isBefore(dateD) || item.isAfter(dateF)) {
			                        setDisable(false);
			                        setStyle("");
			                    } else {
			                        setDisable(true);
			                        setStyle("-fx-background-color: #ffc0cb;");
			                    }
			                }
			            };
			        }
			    };

			dateDebut.setDayCellFactory(dayCellFactory);

			dateDebut.valueProperty().addListener((observable, oldValue, newValue) -> {
			    dateDebut.setValue(newValue);
			});
		
		lblChLib.setTextFill(Color.RED);
		
		root.addRow(0, lblnumReserv, lblReserv);
		root.addRow(1, lblNom, lblnom);
		root.addRow(2, lblPrenom, lblpren);
		root.addRow(3, lblNum, lblNumTel);
		root.addRow(4, lblAdresseMail, lblMail);
		root.addRow(5, lblNb, lblNbPer);
		root.addRow(6, lblNumCh, lblNuCh);
		root.addRow(7, lblnbCh, lblCh);
		root.addRow(8, lbldateD, dateDebut);
		root.addRow(9, lblnumPla, lblPlace);
		root.addRow(10, lblprix, lblprice);
		
		root.add(new HBox(bModifier, bSupprimer), 0, 12);
		root.add(bFermer, 1, 12);
		root.add(bEnvoyer, 1, 12);
		
		GridPane.setHalignment(bFermer, HPos.RIGHT);
        GridPane.setValignment(bFermer, VPos.CENTER);
        GridPane.setHalignment(bEnvoyer, HPos.CENTER);
        GridPane.setValignment(bEnvoyer, VPos.CENTER);
		
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10));
		//root.setGridLinesVisible(true);
		root.setStyle("-fx-font-size:13;");
		return root;
	}
	
	public double prix(Reservation r) {
	    double prixTT = 0;
	    double prixTTchambre = 0;
	    long nbJours;
	    
	    this.dateD = reserv.getDate_debut();
		this.dateF = reserv.getDate_fin();

	    if (this.dateD != null && this.dateF != null) {
	        nbJours = ChronoUnit.DAYS.between(this.dateD, this.dateF); // -1 pour avoir le nombre de nuits et non de jours
	        //System.out.println("nb de jours : " + nbJours);

	        for (Chambre c : r.getListe_chambre()) {
	            prixTTchambre += c.getPrix() * nbJours;
	        }

	        prixTT = prixTTchambre + (r.getNb_personne() * nbJours);
	    } else {
	        //System.out.println("Les dates de début et de fin ne sont pas définies.");
	    }

	    return prixTT;
	}

}