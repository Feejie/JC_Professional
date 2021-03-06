package NetChat.client;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;


public class Controller {

//    @FXML
//    TextArea textArea;

    @FXML
    VBox vBoxArea;

//    @FXML
//    ScrollPane vBoxPane = new ScrollPane(vBoxArea);

    @FXML
    TextField textField;

    @FXML
    Button btn1;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    ListView<String> clientList;

    private boolean isAuthorized;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADRESS = "localhost";
    final int PORT = 7175;

    boolean myMsg;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
        }
    }

    public void tryToAuth(javafx.event.ActionEvent actionEvent) {
        if(socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {

        myMsg = false;

        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                createMsg(str + "\n");
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/")){
                                if (str.equals("/serverclosed")){

//                                    Неудачная попытка чистить окно сообщений после /end
//                                    vBoxArea.getChildren().clear();

                                    break;
                                }

                                /**
                                 * Реализация ориентации сообщений
                                 */

                                if (str.equals("/mymsg")) {
                                    myMsg = true;
                                }
                                if (str.startsWith("/clientlist ")) {
                                    final String[] tokens = str.split(" ");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
//                                            MiniStage miniStage = new MiniStage();
//                                            miniStage.show();

                                            clientList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent mouseEvent) {
                                                    MiniStage miniStage = new MiniStage();
                                                    miniStage.show();
                                                }
                                            });

                                            clientList.getItems().clear();
                                            for (int i = 1; i < tokens.length; i++) {
                                                clientList.getItems().add(tokens[i]);
                                            }
                                        }
                                    });
                                }
                            } else {
                                myMsg = false;
                                createMsg(str + "\n");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(){
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isMyMsg() {
        return myMsg;
    }

    /**
     * Реализация ориентации сообщений
     */
    public void createMsg(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label label = new Label(msg);
                VBox vBoxChat = new VBox();

                if (!isMyMsg()) {
                    vBoxChat.setAlignment(Pos.TOP_LEFT);
                } else {
                    vBoxChat.setAlignment(Pos.TOP_RIGHT);
                }

                vBoxChat.getChildren().add(label);
                vBoxArea.getChildren().add(vBoxChat);
            }
        });
    }

}