import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Recap extends Stage{
	private GridPane root = new GridPane();
	//Les HBox utile pour aligner les informations si il y'a plusieurs chambre
	private HBox num_chambre = new HBox();
	private HBox hboxprix = new HBox();
	private HBox hboxplace = new HBox();
	
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
	private Label lblnumChambre = new Label("");
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
	
	private Button bModifier = new Button("Modifier");
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	
	public Recap(Reservation reserv){
		boolean paslibre = true;
		//initialisation des textes avec les informations de la reservation
		this.lblReserv.setText(String.valueOf(reserv.getNumero_reservation()));
		
		this.lblnom.setText(reserv.getReserve().getNom());
		this.lblpren.setText(reserv.getReserve().getPrenom());
		this.lblNumTel.setText(reserv.getReserve().getNumero_tel());
		this.lblNbPer.setText(String.valueOf(reserv.getNb_personne()));
		lblnumChambre.setText(String.valueOf(reserv.getListe_chambre().get(0).getNumChambre()));
		lblPlace.setText(String.valueOf(reserv.getListe_chambre().get(0).getNbPlace()));
		lblprice.setText(String.valueOf(reserv.getListe_chambre().get(0).getPrix()));
		//parcours de tableau pour récupérer les informations de la liste de chambre de la réservation
		for (int i = 0; i < reserv.getListe_chambre().size(); i++) {
		    Chambre c = reserv.getListe_chambre().get(i);
		  //le if ici sert à savoir si une chambre dans la liste est pas libre alors la reservation n'est pas libre du tout
		    if (c.isEstLibre() == false) {
		        paslibre = false;
		    }
			//les séparateurs avec un ";" entre chaque données
			if (i >= 1) {
				lblnumChambre.setText(lblnumChambre.getText()+";"+c.getNumChambre());
				lblPlace.setText(lblPlace.getText()+";"+c.getNbPlace());
		        lblprice.setText(lblprice.getText()+";"+c.getPrix());
		    }
		}
		//hbox pour aligner les informations qui seront à modifier
		this.hboxplace.getChildren().add(lblPlace);
		this.hboxprix.getChildren().add(lblprice);
		this.num_chambre.getChildren().add(lblnumChambre);
				
		this.estLibre = paslibre;
		
		this.lblCh.setText(String.valueOf(reserv.getNb_chambre()));
		
		this.dateD = reserv.getDate_debut();
		this.dateF = reserv.getDate_fin();
		this.dateDebut.setValue(dateD);
		this.dateFin.setValue(dateF);
		
		this.setTitle("Récapitulatif de la réservation");
		this.setResizable(false);
		this.sizeToScene();
		
		bFermer.setOnAction(e -> this.close());
		
		this.setScene(new Scene(creerContenu()));
	}
	
	private Parent creerContenu() {
		//pour retirer le numéro des semaines sur le calendrier
		dateDebut.setShowWeekNumbers(false);
		dateFin.setShowWeekNumbers(false); 
		//C'est pour faire une séléction de date 
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
		//ajout des libelle et des textes ainsi que des hbox dans le gridpane
		root.addRow(0, lblnumReserv, lblReserv);
		root.addRow(1, lblNom, lblnom);
		root.addRow(2, lblPrenom, lblpren);
		root.addRow(3, lblNum, lblNumTel);
		root.addRow(4, lblNb, lblNbPer);
		lblNumCh.setAlignment(Pos.CENTER);
		root.addRow(5, lblNumCh, num_chambre);
		root.addRow(6, lblnbCh, lblCh);
		root.addRow(7, lbldateD, dateDebut);
		root.addRow(8, lblChLib);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(9, lblnumPla, this.hboxplace);
		root.addRow(10, lblprix, this.hboxprix);
		
		root.add(bModifier, 0, 11);
		root.add(bFermer, 1, 11);
		root.add(bEnvoyer, 1, 11);
		
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
}
