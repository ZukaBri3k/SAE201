import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
	
	private Button bModifier = new Button("Modifier");
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	
	public Recap(Reservation reserv){
		boolean paslibre = true;
		this.lblReserv.setText(String.valueOf(reserv.getNumero_reservation()));
		
		this.lblnom.setText(reserv.getReserve().getNom());
		this.lblpren.setText(reserv.getReserve().getPrenom());
		this.lblNumTel.setText(reserv.getReserve().getNumero_tel());
		this.lblNbPer.setText(String.valueOf(reserv.getNb_personne()));
		
		int total = 0;
		int prixto = 0;
		for (Chambre c : reserv.getListe_chambre()) {
			this.lblNuCh.setText(String.valueOf(c.getNumChambre()));
			this.num_chambre.getChildren().addAll(this.lblNuCh);
			if(c.isEstLibre() == false) {
				paslibre = false;
			}
			total += c.getNbPlace();
			this.lblPlace.setText(String.valueOf(total));
			prixto += c.getPrix();
			this.lblprice.setText(String.valueOf(prixto));
		}
		
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
		root.addRow(4, lblNb, lblNbPer);
		root.addRow(5, lblNumCh, num_chambre);
		root.addRow(6, lblnbCh, lblCh);
		root.addRow(7, lbldateD, dateDebut);
		root.addRow(8, lblChLib);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(9, lblnumPla, lblPlace);
		root.addRow(10, lblprix, lblprice);
		
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
















