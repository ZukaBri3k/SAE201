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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RecapModif extends Stage{
	private GridPane root = new GridPane();
	
	private Label lblnumReserv = new Label("Numéro de reservation :");
	private TextField txtReserv = new TextField();
	
	private Label lblnbCh = new Label("Nombre de chambre :");
	private TextField txtCh = new TextField();
	
	private Label lbldateD = new Label("Date d'arrivée :");
	private Label lbldateF = new Label("Date de fin :");

	private DatePicker dateDebut = new DatePicker();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
	private DatePicker dateFin = new DatePicker();
	
	private Label lblChNonLib = new Label("Chambre non libre");
	private Label lblChLib = new Label("Chambre libre");
	
	private Label lblnumPla = new Label("Nombre de place :");
	private TextField txtPlace = new TextField();
	
	private Label lblprix = new Label("Prix :");
	private TextField txtprice = new TextField();
	
	private Label lblperArr = new Label("Personne pas encore arrivée");
	
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	public RecapModif(){
		this.setTitle("Récapitulatif de la réservation");
		this.setResizable(false);
		this.sizeToScene();
		
		bFermer.setOnAction(e -> this.close());
		
		this.setScene(new Scene(creerContenu()));
	}
	
	private Parent creerContenu() {
		boolean estLibre = false;
		boolean estArrive = true;
		
		String reserv = "1";
		txtReserv.setText(reserv);
		
		String chambre = "1";
		txtCh.setText(chambre);
		
		String dd = "24/01/2004";
	    LocalDate dateD = LocalDate.parse(dd, formatter);
		dateDebut.setValue(dateD);
		String df = "05/02/2004";
		LocalDate dateF = LocalDate.parse(df, formatter);
		dateFin.setValue(dateF);
		
		dateDebut.setShowWeekNumbers(false);
		
		lblChLib.setTextFill(Color.RED);
		
		String place = "4";
		txtPlace.setText(place);
		
		String price = "400€";
		txtprice.setText(price);
		
		lblperArr.setTextFill(Color.RED);
		
		root.addRow(0, lblnumReserv, txtReserv);
		root.addRow(1, lblnbCh, txtCh);
		root.addRow(2, lbldateD, dateDebut);
		root.addRow(3, lbldateF, dateFin);
		
		final ComboBox cbChambre = new ComboBox();
		cbChambre.getItems().addAll(
				"Libre", 
				"Pas libre"
				);
		
		root.addRow(4, lblChLib, cbChambre);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(5, lblnumPla, txtPlace);
		root.addRow(6, lblprix, txtprice);
		
		final ComboBox cbArrive = new ComboBox();
		cbArrive.getItems().addAll(
				"Arrivée", 
				"Pas arrivée"
				);
		root.addRow(7, lblperArr, cbArrive);
		if(estArrive == true) {
			lblperArr.setText("Personne arrivée");
			lblperArr.setTextFill(Color.GREEN);
		}
		
		root.add(bFermer, 1, 8);
		root.add(bEnvoyer, 0, 8);
		
		GridPane.setHalignment(bFermer, HPos.RIGHT);
        GridPane.setValignment(bFermer, VPos.CENTER);
		
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10));
		//root.setGridLinesVisible(true);
		root.setStyle("-fx-font-size:13;");
		return root;
	}
}
















