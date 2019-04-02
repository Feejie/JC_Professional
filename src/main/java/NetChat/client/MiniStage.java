package NetChat.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MiniStage extends Stage {
    public MiniStage() {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(new File("A:\\Mine\\Java\\JC_Professional\\src\\main\\resources\\secondWindow.fxml").toURI().toURL());
            root = loader.load();
            setTitle("private chat");
            setScene(new Scene(root, 350, 300));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
