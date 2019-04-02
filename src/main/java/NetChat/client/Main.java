package NetChat.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(new File("A:\\Mine\\Java\\JC_Professional\\src\\main\\resources\\sample.fxml").toURI().toURL());
        Parent root = loader.load();
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("NetChat");
        primaryStage.setScene(new Scene(root, 420, 420));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                //primaryStage.close();
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}