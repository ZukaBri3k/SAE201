
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Annulation extends Application {

    private Stage primaryStage;
    private BorderPane root = new BorderPane();
    private Button boutonSup = new Button("Supprimer");
    private Alert confirmer = new Alert(AlertType.CONFIRMATION);
    private Alert suppr = new Alert(AlertType.INFORMATION);
    private Alert annuler = new Alert(AlertType.INFORMATION);
    private Label texte = new Label ("Réservation n°21548\n"
            + "Nom : Lebeau\n"
            + "Du 14/06/2023 au 19/06/2023\n"
            + "Chambre 308 : Chambre économique"
            + "   1 occupant\n" + "Chambre 403 : Chambre standard"
            + "   2 occupants");
    

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        Scene laScene;
        boutonSup.setPrefWidth(100);
        root.setPadding(new Insets(10));
        root.setBottom(boutonSup);
        root.setTop(texte);
        BorderPane.setAlignment(boutonSup, Pos.BOTTOM_RIGHT);
        BorderPane.setAlignment(texte, Pos.TOP_LEFT);

        boutonSup.setOnAction(e -> {
            confirmer.setTitle("Confirmation");
            confirmer.setHeaderText(null);
            confirmer.setContentText("Voulez-vous vraiment supprimer cette réservation? Cette action est irréversible.");
            
            confirmer.showAndWait().ifPresent(response -> {
            	if (response == ButtonType.OK) {
            		confirmer.close();
                    this.primaryStage.close();
                   
                    suppr.setTitle("Suppression");
                    suppr.setHeaderText(null);
                    suppr.setContentText("La réservation a été supprimée.");
                        
                    suppr.showAndWait().ifPresent(ok -> {
                    	suppr.close();

                 });
                } else {
                	confirmer.close();

                    this.primaryStage.close();
                        
                    annuler.setTitle("Annulation");
                    annuler.setHeaderText(null);
                    annuler.setContentText("Vous avez annuler la suppression.");
                             
                    annuler.showAndWait().ifPresent(ok -> {
                                annuler.close();
                      });
                }
            });
        });

        laScene = new Scene(root, 400, 300);
        this.primaryStage.setScene(laScene);
        AfficherAnnulation();
    }
    
    private void AfficherAnnulation() {
        this.primaryStage.show();
    }

    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
