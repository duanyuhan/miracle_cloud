package miracle.util.course;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login extends Application {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public void start(final Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50));
        pane.setHgap(5);
        pane.setVgap(5);

        final TextField userName = new TextField();
        final PasswordField password = new PasswordField();

        pane.add(new javafx.scene.control.Label("userName"), 0, 0);
        pane.add(userName, 1, 0);

        pane.add(new Label("password"), 0, 1);
        pane.add(password, 1, 1);
        Button button1 = ButtonBuilder.create().text("Login").onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String userNameStr = userName.getText();
                String passwordStr = password.getText();
                if (userNameStr.equals("") || passwordStr.equals("")) {
                    new AlterBox().display("ERROR!", "plase enter your username and password!");
                }

                String queryString = "select * from user_table where name='" + userNameStr + "'";
                DBConnection connection = new DBConnection();
                connection.initializeDB();

                try {
                    connection.preStmt = connection.connect.prepareStatement(queryString);
                    connection.rSet = connection.preStmt.executeQuery();
                    if (connection.rSet.next()) {
                        String passwordDb = connection.rSet.getString(3);
                        String pass = encryptMD5(passwordStr);
                        if (!passwordDb.equals(pass)) {
                            new AlterBox().display("ERROR!", "password is wrong!");
                        }
                    } else {
                        new AlterBox().display("ERROR!", " username is wrong!");
                    }
                    final String type = connection.rSet.getString(4);
                    new Main(type).start(primaryStage);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }).build();
        pane.add(button1, 2, 3);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("course");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);
    }

    public static String encryptMD5(String input) {
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(input.getBytes());
            byte[] md = mdInst.digest();
            return byteArrayToHexString(md);
        } catch (NoSuchAlgorithmException e) {

        }
        return "";
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte b) {
        int ret = b;
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }
}

