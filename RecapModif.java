import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RecapModif extends Stage{
	private GridPane root = new GridPane();
	//Les HBox utile pour aligner les informations si il y'a plusieurs chambre
	private HBox num_chambre = new HBox();
	private HBox hboxprix = new HBox();
	private HBox hboxplace = new HBox();
	
	private Label lblnumReserv = new Label("Numéro de reservation :");
	private Label txtReserv = new Label();
	String numeroReservation;
	
	private Label lblNom = new Label("Nom :");
	private TextField txtnom = new TextField();
	String name;
	private Label lblPrenom = new Label("Prenom :");
	private TextField txtpren = new TextField();
	String lastname;
	private Label lblNum = new Label("Numéro de téléphone :");
	private TextField txtNumTel = new TextField();
	String tel;
	private Label lblNb = new Label("Nombre de personne :");
	private TextField txtNbPer = new TextField();
	String nbper;
	
	private Label lblNumCh = new Label("Numéro de(s) chambre(s) :");
	private TextField txtnumChambre= new TextField();
	String numeroChambre;
	private Label lblnbCh = new Label("Nombre de chambre :");
	private TextField txtCh = new TextField();
	String nbChambre;
	
	private Label lbldateD = new Label("Date d'arrivée :");
	private Label lbldateF = new Label("Date de fin :");

	private DatePicker dateDebut = new DatePicker();
	private LocalDate dateD;
	private LocalDate dateF;
	private DatePicker dateFin = new DatePicker();
	
	private Label lblChLib = new Label("Chambre(s) non libre(s)");
	private boolean estLibre;
	
	private Label lblnumPla = new Label("Nombre de place :");
	private TextField txtPlace= new TextField();
	String place;
	
	private Label lblprix = new Label("Prix :");
	private Label txtprice= new Label();
	
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	//combobox pour le choix de liste déroulante
	private ComboBox<String> cbChambre = new ComboBox<String>();
	
	//Kyrill, met ça dans le bouton valide
	/*if (txtnumChambre.getText().endsWith(";")) {
				txtnumChambre.deletePreviousChar();
			}
	if (txtnumChambre.getText().endsWith(";")) {
		txtnumChambre.deletePreviousChar();
	}*/
	
	public RecapModif(Reservation reserv){
		boolean paslibre = true;
		
		
		//initialisation des textes avec les informations de la reservation avec les contraintes aussi
		
		this.txtReserv.setText(String.valueOf(reserv.getNumero_reservation()));
		
		
		this.txtnom.setText(reserv.getReserve().getNom());
		txtnom.setTooltip(new Tooltip("Maximum 15 caractères et que des lettres."));
		//Lorsque la propriété text du champ de texte "txtNbPer" change, j'ajoute un écouteur qui prend en compte les 
		//anciennes valeurs et la nouvelle valeur.
		// Écouteur : objet qui détecte et réagit aux événements se produisant sur un composant
		//Les regex et tout, sont les mêmes pour chaque valeur à modifier
		txtnom.textProperty().addListener((observable, odlvalue, newValue) -> {
			//Contrainte pour mettre que des chiffres, je crée une nouvelle valeur qui prend le regex alphabétique ainsi que les 
			//espace et '.
			//De même pour les prenoms
			name = newValue.replaceAll("[^\\p{L}\\s'-]", "");
			if(!name.matches("[^\\p{L}\\s'-]")) {
				txtnom.setText(name);
			}
		});
		txtnom.setOnKeyTyped(e ->{
			//pour éviter que ça dépasse un certain nombre de caractère ici 15
			if (txtnom.getText().length() > 15) {
				txtnom.deletePreviousChar();
			}
		});
		
		
		this.txtpren.setText(reserv.getReserve().getPrenom());
		txtpren.setTooltip(new Tooltip("Maximum 15 caractères et que des lettres."));
		txtpren.textProperty().addListener((observable, odlvalue, newValue) -> {
			lastname = newValue.replaceAll("[^\\p{L}\\s'-]", "");
			if(!lastname.matches("[^\\p{L}\\s'-]")) {
				txtpren.setText(lastname);
			}
		});
		txtpren.setOnKeyTyped(e ->{
			if (txtpren.getText().length() > 15) {
				txtpren.deletePreviousChar();
			}
		});
		
		
		this.txtNumTel.setText(reserv.getReserve().getNumero_tel());
		txtNumTel.setTooltip(new Tooltip("Maximum 10 caractères et que des chiffres."));
		txtNumTel.textProperty().addListener((observable, odlvalue, newValue) -> {
			tel = newValue.replaceAll("\\D", "");
			if(!tel.matches("\\D")) {
				txtNumTel.setText(tel);
			}
		});

		txtNumTel.setOnKeyTyped(e ->{
			if (txtNumTel.getText().length() > 10) {
				txtNumTel.deletePreviousChar();
			}
		});
		
		
		this.txtNbPer.setText(String.valueOf(reserv.getNb_personne()));
		txtNbPer.setTooltip(new Tooltip("Maximum 2 caractères et que des chiffres."));
		txtNbPer.textProperty().addListener((observable, odlvalue, newValue) -> {
			nbper = newValue.replaceAll("\\D", "");
			if(!nbper.matches("\\D")) {
				txtNbPer.setText(nbper);
			}
		});
		txtNbPer.setOnKeyTyped(e -> {
			if (txtNbPer.getText().length() > 2) {
				txtNbPer.deletePreviousChar();
			}
		});
		
		
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
		txtPlace.setTooltip(new Tooltip("Maximum 15 caractères et que des chiffres ou ';'."));
		txtPlace.textProperty().addListener((observable, odlvalue, newValue) -> {
			//Contrainte pour mettre que des chiffres et des ;
			place = newValue.replaceAll("[^\\d;]+", "");
			if(!place.matches("[^\\d;]+")) {
				txtPlace.setText(place);
			}
			//pour éviter d'avoir des ;; qui s'enchaine sans rien entre, la même pour le suivant
			if (txtnumChambre.getText().endsWith(";;")) {
				txtnumChambre.deletePreviousChar();
			}
		});
		txtPlace.setOnKeyTyped(e -> {
			if (txtPlace.getText().length() > 15) {
				txtPlace.deletePreviousChar();
			}
		});
		
		
		this.hboxprix.getChildren().add(txtprice);
		
		
		this.num_chambre.getChildren().add(txtnumChambre);
		txtnumChambre.setTooltip(new Tooltip("Maximum 30 caractères et que des chiffres ou ';'."));
		txtnumChambre.textProperty().addListener((observable, odlvalue, newValue) -> {
			numeroChambre = newValue.replaceAll("[^\\d;]+", "");
			if(!numeroChambre.matches("[^\\d;]+")) {
				txtnumChambre.setText(numeroChambre);
			}
		});
		txtnumChambre.setOnKeyTyped(e -> {
			if (txtnumChambre.getText().length() > 30) {
				txtnumChambre.deletePreviousChar();
			}
			if (txtnumChambre.getText().endsWith(";;")) {
				txtnumChambre.deletePreviousChar();
			}
		});
		
		
		this.estLibre = paslibre;
		
		
		this.txtCh.setText(String.valueOf(reserv.getNb_chambre()));
		txtCh.setTooltip(new Tooltip("Maximum 1 caractères et que des chiffres."));
		txtCh.textProperty().addListener((observable, odlvalue, newValue) -> {
			nbChambre = newValue.replaceAll("\\D", "");
			if(!nbChambre.matches("\\D")) {
				txtCh.setText(nbChambre);
			}
		});
		txtCh.setOnKeyTyped(e -> {
			if (txtCh.getText().length() > 1) {
				txtCh.deletePreviousChar();
			}
		});
		
		
		this.dateD = reserv.getDate_debut();
		this.dateF = reserv.getDate_fin();
		this.dateDebut.setValue(dateD);
		this.dateFin.setValue(dateF);
		dateDebut.setTooltip(new Tooltip("Format dd/mm/yyyy, pas de lettres."));
		dateFin.setTooltip(new Tooltip("Format dd/mm/yyyy, pas de lettres."));
		
		//Combobox, pour 
		cbChambre.getItems().addAll(
				"Libre", 
				"Pas libre"
				);
		if(paslibre == false) {
			cbChambre.setValue("Pas libre");
		} else {
			cbChambre.setValue("Libre");
		}
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
		dateFin.setShowWeekNumbers(false);
		
		
		
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
