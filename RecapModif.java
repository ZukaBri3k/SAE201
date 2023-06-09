import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RecapModif extends Stage{
	private GridPane root = new GridPane();
	//Les HBox utile pour aligner les informations si il y'a plusieurs chambre
	private HBox num_chambre = new HBox();
	private HBox hboxprix = new HBox();
	private HBox hboxplace = new HBox();
	
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
	private TextField txtnumChambre= new TextField();
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
	private TextField txtPlace= new TextField();
	
	private Label lblprix = new Label("Prix :");
	private TextField txtprice= new TextField();
	
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	//combobox pour le choix de liste déroulante
	private ComboBox<String> cbChambre = new ComboBox<String>();
	
	public RecapModif(Reservation reserv){
		boolean paslibre = true;
		//initialisation des textes avec les informations de la reservation
		this.txtReserv.setText(String.valueOf(reserv.getNumero_reservation()));
		this.txtnom.setText(reserv.getReserve().getNom());
		this.txtpren.setText(reserv.getReserve().getPrenom());
		this.txtNumTel.setText(reserv.getReserve().getNumero_tel());
		this.txtNbPer.setText(String.valueOf(reserv.getNb_personne()));
		txtnumChambre.setText(String.valueOf(reserv.getListe_chambre().get(0).getNumChambre()));
		txtPlace.setText(String.valueOf(reserv.getListe_chambre().get(0).getNbPlace()));
		txtprice.setText(String.valueOf(reserv.getListe_chambre().get(0).getPrix()));
		//parcours de tableau pour récupérer les informations de la liste de chambre de la réservation
		for (int i = 0; i < reserv.getListe_chambre().size(); i++) {
		    Chambre c = reserv.getListe_chambre().get(i);
		    //le if ici sert à savoir si une chambre dans la liste est pas libre alors la reservation n'est pas libre du tout
		    if (c.isEstLibre() == false) {
		        paslibre = false;
		    }
			//les séparateurs avec un ";" entre chaque données 
			if (i >= 1) {
				txtnumChambre.setText(txtnumChambre.getText()+";"+c.getNumChambre());
				txtPlace.setText(txtPlace.getText()+";"+c.getNbPlace());
		        txtprice.setText(txtprice.getText()+";"+c.getPrix());
		    }
		}
		//hbox pour aligner les informations qui seront à modifier
		this.hboxplace.getChildren().add(txtPlace);
		this.hboxprix.getChildren().add(txtprice);
		this.num_chambre.getChildren().add(txtnumChambre);
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
		//pour retirer le numéro des semaine sur le calendrier
		dateDebut.setShowWeekNumbers(false);
		
		lblChLib.setTextFill(Color.RED);
		
		//ajout des libelle et des textes ainsi que des hbox dans le gridpane
		root.addRow(0, lblnumReserv, txtReserv);
		root.addRow(1, lblNom, txtnom);
		root.addRow(2, lblPrenom, txtpren);
		root.addRow(3, lblNum, txtNumTel);
		root.addRow(4, lblNb, txtNbPer);
		lblNumCh.setAlignment(Pos.TOP_CENTER);
		root.addRow(5, lblNumCh, num_chambre);
		root.addRow(6, lblnbCh, txtCh);
		root.addRow(7, lbldateD, dateDebut);
		root.addRow(8, lbldateF, dateFin);
		
		root.addRow(9, lblChLib, cbChambre);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(10, lblnumPla, hboxplace);
		root.addRow(11, lblprix, hboxprix);
		
		
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
