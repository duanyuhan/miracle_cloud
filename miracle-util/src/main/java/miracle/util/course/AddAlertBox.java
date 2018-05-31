package miracle.util.course;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;


public class AddAlertBox {
    public void display(String title, String message, final String courseName, final String courseNum, final String teacher, final String type) {
        final Stage window = new Stage();
        window.setTitle(title);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Button button = ButtonBuilder.create().text("CONFIRM").onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!PatternUtil.isMatch(courseName, 2)) {
                    new AlterBox().display("Tip", "course name must be a string");
                }
                if (!PatternUtil.isMatch(courseNum, 3)) {
                    new AlterBox().display("Tip", "course num must be a number");
                }

                if (!PatternUtil.isMatch(teacher, 2)) {
                    new AlterBox().display("Tip", "teacher name must be a string");
                }
                String queryString = "insert into course_table (course_name, course_num, course_teacher) VALUE('" + courseName + "', '" + courseNum + "','" + teacher + "')";
                DBConnection connection = new DBConnection();
                connection.initializeDB();
                try {
                    connection.preStmt = connection.connect.prepareStatement(queryString);
                    int a = connection.preStmt.executeUpdate();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                window.close();
                new Main(type).start(window);

            }
        }).build();

        Label label = new Label(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
