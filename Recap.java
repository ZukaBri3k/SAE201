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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Recap extends Stage{
	private GridPane root = new GridPane();
	
	private Label lblnumReserv = new Label("Numéro de reservation :");
	private Label lblReserv = new Label();
	
	private Label lblnbCh = new Label("Nombre de chambre :");
	private Label lblCh = new Label();
	
	private Label lbldateD = new Label("Date :");

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
	
	public Recap(){
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
		
		String place = "4";
		lblPlace.setText(place);
		
		String price = "400€";
		lblprice.setText(price);
		
		lblperArr.setTextFill(Color.RED);
		
		root.addRow(0, lblnumReserv, lblReserv);
		root.addRow(1, lblnbCh, lblCh);
		root.addRow(2, lbldateD, dateDebut);
		root.addRow(3, lblChLib);
		if(estLibre == true) {
			lblChLib.setText("Chambre libre");
			lblChLib.setTextFill(Color.GREEN);
		}
		root.addRow(4, lblnumPla, lblPlace);
		root.addRow(5, lblprix, lblprice);
		root.addRow(6, lblperArr);
		if(estArrive == true) {
			lblperArr.setText("Personne arrivée");
			lblperArr.setTextFill(Color.GREEN);
		}
		
		root.add(bModifier, 0, 7);
		root.add(bFermer, 1, 7);
		root.add(bEnvoyer, 1, 7);
		
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
















