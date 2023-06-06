import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

public class RecapModif extends Stage{
	private GridPane root = new GridPane();
	
	private Label lblnumReserv = new Label("Numéro de reservation :");
	private Label lblReserv = new Label();
	
	private Label lblnbCh = new Label("Nombre de chambre :");
	private Label lblCh = new Label();
	
	private Label lbldateD = new Label("Date d'arrivée :");
	private Label lbldateF = new Label("Date de fin :");

	private DatePicker dateDebut = new DatePicker();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
	private DatePicker dateFin = new DatePicker();
	
	private Label lblChLib = new Label("Chambre non libre");
	
	private Label lblnumPla = new Label("Nombre de place :");
	private Label lblPlace = new Label();
	
	private Label lblprix = new Label("Prix :");
	private Label lblprice = new Label();
	
	private Label lblperArr = new Label("Personne pas encore arrivée");
	
	private Button bModifier = new Button("Modifier");
	private Button bFermer = new Button("Fermer");
	private Button bEnvoyer = new Button("Valider");
	
	public RecapModif(){
		this.setTitle("Récapitulatif de la réservation");
		this.setResizable(true);
		this.sizeToScene();
		
		bFermer.setOnAction(e -> this.close());
		
		this.setScene(new Scene(creerContenu()));
	}
	
	private Parent creerContenu() {
		boolean estLibre = false;
		boolean estArrive = true;
		
		String reserv = "1";
		lblReserv.setText(reserv);
		
		String chambre = "1";
		lblCh.setText(chambre);
		
		String dd = "24/01/2004";
	    LocalDate dateD = LocalDate.parse(dd, formatter);
		dateDebut.setValue(dateD);
		String df = "05/02/2004";
		LocalDate dateF = LocalDate.parse(df, formatter);
		dateFin.setValue(dateF);
		
		dateDebut.setShowWeekNumbers(false);
		
		lblChLib.setTextFill(Color.RED);
		
		String place = "4";
		lblPlace.setText(place);
		
		String price = "400€";
		lblprice.setText(price);
		
		lblperArr.setTextFill(Color.RED);
		
		root.addRow(0, lblnumReserv, lblReserv);
		root.addRow(1, lblnbCh, lblCh);
		root.addRow(2, lbldateD, dateDebut);
		root.addRow(3, lbldateF, dateFin);
		root.addRow(4, lblChLib);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(5, lblnumPla, lblPlace);
		root.addRow(6, lblprix, lblprice);
		root.addRow(7, lblperArr);
		if(estArrive == true) {
			lblperArr.setText("Personne arrivée");
			lblperArr.setTextFill(Color.GREEN);
		}
		
		root.add(bModifier, 0, 8);
		root.add(bFermer, 1, 8);
		root.add(bEnvoyer, 1, 8);
		
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
















