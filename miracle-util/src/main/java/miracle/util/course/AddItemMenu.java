package miracle.util.course;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by naonao on 18/5/31.
 */
public class AddItemMenu extends Application {
    private String type;

    public AddItemMenu(String type) {
        this.type = type;
    }

    public void start(final Stage primaryStage) {
        //窗体
        GridPane addPane = new GridPane();
        addPane.setAlignment(Pos.TOP_LEFT);
        addPane.setPadding(new Insets(11, 12, 13, 14));
        addPane.setHgap(20);
        addPane.setVgap(5);

        Label nameLabel = new Label("COURSE NAME");
        Label numLabel = new Label("COURSE NUM");
        Label CourseLabel = new Label("COURSE TEACHER");

        final TextField nameText = new TextField();
        final TextField numText = new TextField();
        final TextField tecText = new TextField();

        addPane.add(nameLabel, 1, 1);
        addPane.add(nameText, 2, 1);
        addPane.add(numLabel, 1, 2);
        addPane.add(numText, 2, 2);
        addPane.add(CourseLabel, 1, 3);
        addPane.add(tecText, 2, 3);

        Button addButton = ButtonBuilder.create().text("ADD").onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String nameStr = nameText.getText();
                String numStr = numText.getText();
                String tecStr = tecText.getText();

                if (nameStr.length() > 32) {
                    new AlterBox().display("Tip", "the length of name is to long , need < 32");
                    return;
                }
                if (numStr.length() > 32) {
                    new AlterBox().display("Tip", "the length of num is to long , need < 32");
                    return;
                }
                if (tecStr.length() > 32) {
                    new AlterBox().display("Tip", "the length of teacher is to long , need < 32");
                    return;
                }
                new AddAlertBox().display("Tip", "are you sure to add an Item", nameStr, numStr, tecStr, type);
            }
        }).build();

        Button backButton = ButtonBuilder.create().text("back").onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                new Main(type).start(primaryStage);
            }
        }).build();
        addPane.add(addButton, 1, 4);
        addPane.add(backButton, 1, 6);
        Scene scene1 = new Scene(addPane);
        primaryStage.setTitle("add pane");
        primaryStage.setScene(scene1);
        primaryStage.setWidth(800);
        primaryStage.setHeight(400);
        primaryStage.show();

    }


    public static void main(String[] args) {

        Application.launch(args);

    }
}
