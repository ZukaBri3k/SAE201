import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FenRecherche extends Stage{
	
	private Label lbNom = new Label("Nom :");
	private Label lbPrenom = new Label("Prénom :");
	private Label lbNumTel = new Label("Numéro de téléphone :");
	private Label lbNumReser = new Label("Numéro de réservation :");
	private Label lbArrivee = new Label("Départ :");
	private Label lbDepart = new Label("Arrivée :");
	
	private TextField tfNom = new TextField();
	private TextField tfPrenom = new TextField();
	private TextField tfNumTel = new TextField();
	private TextField tfNumReser = new TextField();
	
	private Button btnRecherche = new Button("Rechercher");
	
	private DatePicker dpArrivee = new DatePicker();
	private DatePicker dpDepart = new DatePicker();
	
	private ListView lvRes;
	
	private VBox vbRoot = new VBox();
	private VBox vbRecherche = new VBox();
	
	private HBox hbLigne1 = new HBox();
	private HBox hbLigne2 = new HBox();
	
	private ObservableList<Reservation> t = FXCollections.observableArrayList();

	public FenRecherche() {
		this.initStyle(StageStyle.UTILITY);
		this.setWidth(Screen.getPrimary().getBounds().getWidth());
		this.setHeight(Screen.getPrimary().getBounds().getWidth());
		this.setTitle("Rechercher une réservation");
		this.setResizable(true);
		this.sizeToScene();
		
		lvRes = new ListView<Reservation>(this.t);
		
		createResearch();
		
		t.add(new Reservation(new Date("2023/07/01"), new Date("2323/07/01"), 5, new Client("Dumerchat", "Kyrill", "0631207415"), new Chambre(1, true, 5, 50.0)));
		
		Scene fenetre = new Scene(this.vbRoot);
		this.setScene(fenetre);
		fenetre.setFill(Color.WHITE);
	}
	
	public void createResearch() {
		this.hbLigne1.getChildren().setAll(this.lbNom, this.tfNom, this.lbPrenom, this.tfPrenom, this.lbNumTel, this.tfNumTel);
		this.hbLigne1.setAlignment(Pos.CENTER);
		this.hbLigne1.setPadding(new Insets(10));
		this.hbLigne1.setSpacing(15);
		
		this.hbLigne2.getChildren().addAll(this.lbNumReser, this.tfNumReser, this.lbArrivee, this.dpArrivee, lbDepart, this.dpDepart, this.btnRecherche);
		this.hbLigne2.setAlignment(Pos.CENTER);
		this.hbLigne2.setPadding(new Insets(10));
		this.hbLigne2.setSpacing(15);
		
		this.vbRecherche.getChildren().add(this.hbLigne1);
		this.vbRecherche.getChildren().add(this.hbLigne2);
		
		this.vbRoot.getChildren().addAll(vbRecherche, lvRes);
	}
	
}
