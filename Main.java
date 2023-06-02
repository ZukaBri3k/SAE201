import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		arg0 = new FenRecherche();
		arg0.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch();
	}

}
