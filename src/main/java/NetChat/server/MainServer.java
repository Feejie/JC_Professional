package NetChat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServer {
    private Vector<ClientHandler> clients;

    public MainServer() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();
            server = new ServerSocket(7175);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this,socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    public void broadcastMsg(ClientHandler from, String msg) throws SQLException {

        // Запись в историю
        try {
            AuthService.addToHistory(from.getNick(), msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())){
                if (o.getNick().equals(from.getNick())) {
                    o.sendMsg(msg);
                } else {
                    o.sendMsg(from.getNick() + " : " + msg);
                }
            }
        }
    }

    public void privateMsg(ClientHandler from, String nickTo, String msg) throws SQLException {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)){

                // Проверка черного списка при отправке ЛС
                if (!o.checkBlackList(from.getNick())){
                    o.sendMsg("from " + from.getNick() + ": " + msg);
                    from.sendMsg("to " + nickTo + ": " + msg);
                    return;
                } else {

                    // Оповещение пользователя о том, что он в ЧС
                    from.sendMsg("Вы в находитесь в черном списке пользователя " + nickTo);
                    return;
                }
            }
        }
        from.sendMsg("Пользователь с ником " + nickTo + " не найден в чате!");
    }

    public boolean checkNick(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler o: clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();

        for (ClientHandler o: clients) {
            o.sendMsg(out);
        }
    }

}