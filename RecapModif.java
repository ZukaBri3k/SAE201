import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RecapModif extends Stage{
	private GridPane root = new GridPane();
	private VBox num_chambre = new VBox();
	
	private Label lblnumReserv = new Label("Numéro de reservation :");
	private TextField txtReserv = new TextField();
	
	private Label lblNom = new Label("Nom :");
	private TextField txtnom = new TextField();
	private Label lblPrenom = new Label("Prenom :");
	private TextField txtpren = new TextField();
	private Label lblNum = new Label("Numéro de téléphone :");
	private TextField txtNumTel = new TextField();
	private Label lblNb = new Label("Nombre de personne :");
	private TextField txtNbPer = new TextField();
	
	private Label lblNumCh = new Label("Numéro de(s) chambre(s) :");
	private TextField txtNuCh = new TextField();
	private Label lblnbCh = new Label("Nombre de chambre :");
	private TextField txtCh = new TextField();
	
	private Label lbldateD = new Label("Date d'arrivée :");
	private Label lbldateF = new Label("Date de fin :");

	private DatePicker dateDebut = new DatePicker();
	private LocalDate dateD;
	private LocalDate dateF;
	private DatePicker dateFin = new DatePicker();
	
	private Label lblChLib = new Label("Chambre non libre");
	private boolean estLibre;
	
	private Label lblnumPla = new Label("Nombre de place :");
	private TextField txtPlace = new TextField();
	
	private Label lblprix = new Label("Prix :");
	private TextField txtprice = new TextField();
	
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	private ComboBox cbChambre = new ComboBox();
	
	public RecapModif(Reservation reserv){
		boolean paslibre = true;
		this.txtReserv.setText(String.valueOf(reserv.getNumero_reservation()));
		this.txtnom.setText(reserv.getReserve().getNom());
		this.txtpren.setText(reserv.getReserve().getPrenom());
		this.txtNumTel.setText(reserv.getReserve().getNumero_tel());
		this.txtNbPer.setText(String.valueOf(reserv.getNb_personne()));
		
		int total = 0;
		int prixto = 0;
		for (Chambre c : reserv.getListe_chambre()) {
			this.txtNuCh.setText(String.valueOf(c.getNumChambre()));
			this.num_chambre.getChildren().addAll(this.txtNuCh);
			if(c.isEstLibre() == false) {
				paslibre = false;
			}
			total += c.getNbPlace();
			this.txtPlace.setText(String.valueOf(total));
			prixto += c.getPrix();
			this.txtprice.setText(String.valueOf(prixto));
		}
		this.estLibre = paslibre;
		
		this.txtCh.setText(String.valueOf(reserv.getNb_chambre()));
		
		this.dateD = reserv.getDate_debut();
		this.dateF = reserv.getDate_fin();
		this.dateDebut.setValue(dateD);
		this.dateFin.setValue(dateF);
		
		cbChambre.getItems().addAll(
				"Libre", 
				"Pas libre"
				);
		
		cbChambre.setOnAction(e -> {
			if(cbChambre.getSelectionModel().getSelectedItem().equals("Libre")) {
				lblChLib.setText("Chambre libre");
				lblChLib.setTextFill(Color.GREEN);
			} else {
				lblChLib.setText("Chambre non libre");
				lblChLib.setTextFill(Color.RED);
			}
		});
		
		this.setTitle("Récapitulatif de la réservation");
		this.setResizable(false);
		this.sizeToScene();
		
		bFermer.setOnAction(e -> this.close());
		
		this.setScene(new Scene(creerContenu()));
	}
	
	private Parent creerContenu() {
		
		dateDebut.setShowWeekNumbers(false);
		
		lblChLib.setTextFill(Color.RED);
		
		root.addRow(0, lblnumReserv, txtReserv);
		root.addRow(1, lblNom, txtnom);
		root.addRow(2, lblPrenom, txtpren);
		root.addRow(3, lblNum, txtNumTel);
		root.addRow(4, lblNb, txtNbPer);
		root.addRow(5, lblNumCh, num_chambre);
		root.addRow(6, lblnbCh, txtCh);
		root.addRow(7, lbldateD, dateDebut);
		root.addRow(8, lbldateF, dateFin);
		
		root.addRow(9, lblChLib, cbChambre);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(10, lblnumPla, txtPlace);
		root.addRow(11, lblprix, txtprice);
		
		
		root.add(bFermer, 1, 12);
		root.add(bEnvoyer, 0, 12);
		
		GridPane.setHalignment(bFermer, HPos.RIGHT);
        GridPane.setValignment(bFermer, VPos.CENTER);
		
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10));
		
		root.setStyle("-fx-font-size:13;");
		return root;
	}
}
