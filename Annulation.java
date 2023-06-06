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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Annulation extends Application {

    private Stage primaryStage;
    private BorderPane root = new BorderPane();
    private Button boutonSup = new Button("Supprimer");
    private Alert confirmer = new Alert(AlertType.CONFIRMATION);
    private Label texte = new Label ("Réservation n°21548\n"
            + "Nom : Lebeau\n"
            + "Du 14/06/2023 au 19/06/2023\n"
            + "Chambre 308 : Chambre économique"
            + "   1 occupant\n" + "Chambre 403 : Chambre standard"
            + "   2 occupants");
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
            confirmer.setContentText("Voulez-vous vraiment supprimer cette réservation ? Cette action est irréversible.");
            
            confirmer.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    confirmer.close();
                    primaryStage.close();
                    ConfirmationPage();
                } else {
                	confirmer.close();
                    NonConfirmationPage();
                }
            });
        });

        laScene = new Scene(root, 400, 300);
        primaryStage.setScene(laScene);
        primaryStage.show();
    }

    private void ConfirmationPage() {
    	Stage confirmationStage = new Stage();
        VBox confirmationRoot = new VBox();
        Label confirmationLabel = new Label("La réservation a été supprimée.");
        confirmationRoot.getChildren().add(confirmationLabel);
        Scene confirmationScene = new Scene(confirmationRoot, 400, 300);
        confirmationStage.setScene(confirmationScene);
        confirmationStage.show();
    }
    
    private void NonConfirmationPage() {
    	Stage confirmationStage = new Stage();
        VBox confirmationRoot = new VBox();
        Label confirmationLabel = new Label("Vous avez annuler la suppression.");
        confirmationRoot.getChildren().add(confirmationLabel);
        Scene confirmationScene = new Scene(confirmationRoot, 400, 300);
        confirmationStage.setScene(confirmationScene);
        confirmationStage.show();
    }
}
