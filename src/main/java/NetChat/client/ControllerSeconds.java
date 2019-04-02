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


public class ControllerSeconds {

//    @FXML
//    VBox vBoxArea;
//
//    @FXML
//    TextField textField;
//
//    @FXML
//    Button btn1;
//
//    @FXML
//    HBox bottomPanel;
//
//    private boolean isAuthorized;
//
//    Socket socket;
//    DataInputStream in;
//    DataOutputStream out;
//
//    final String IP_ADRESS = "localhost";
//    final int PORT = 7175;
//
//    boolean myMsg;
//
//    public void connect() {
//
//        myMsg = false;
//
//        try {
//            socket = new Socket(IP_ADRESS, PORT);
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//
//                        while (true) {
//                            String str = in.readUTF();
//                            if (str.startsWith("/")){
//                                if (str.equals("/serverclosed")){
//
////                                    vBoxArea.getChildren().clear();
//
//                                    break;
//                                }
//
//                                /**
//                                 * Реализация ориентации сообщений
//                                 */
//
//                                if (str.equals("/mymsg")) {
//                                    myMsg = true;
//                                }
//                                if (str.startsWith("/clientlist ")) {
//                                    final String[] tokens = str.split(" ");
//
//                                }
//                            } else {
//                                myMsg = false;
//                                createMsg(str + "\n");
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            socket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }).start();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendPrivateMsg(){
//        try {
//            out.writeUTF(textField.getText());
//            textField.clear();
//            textField.requestFocus();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean isMyMsg() {
//        return myMsg;
//    }
//
//    /**
//     * Реализация ориентации сообщений
//     */
//    public void createMsg(final String msg) {
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                Label label = new Label(msg);
//                VBox vBoxChat = new VBox();
//
//                if (!isMyMsg()) {
//                    vBoxChat.setAlignment(Pos.TOP_LEFT);
//                } else {
//                    vBoxChat.setAlignment(Pos.TOP_RIGHT);
//                }
//
//                vBoxChat.getChildren().add(label);
//                vBoxArea.getChildren().add(vBoxChat);
//            }
//        });
//    }

}
