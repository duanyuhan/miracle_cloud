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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private String type;

    public Main(String type) {
        this.type = type;
    }

    public void start(final Stage primaryStage) {
        //窗体
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(20);
        pane.setVgap(5);

        String queryString = "select * from course_table";
        DBConnection connection = new DBConnection();
        connection.initializeDB();
        Label idLabel = new Label("ID");
        Label nameLabel = new Label("COURSE NAME");
        Label numLabel = new Label("COURSE NUM");
        Label CourseLabel = new Label("COURSE TEACHER");

        pane.add(idLabel, 1, 1);
        pane.add(nameLabel, 2, 1);
        pane.add(numLabel, 3, 1);
        pane.add(CourseLabel, 4, 1);
        final List<Course> courseList = new ArrayList<Course>();
        try {
            connection.preStmt = connection.connect.prepareStatement(queryString);
            connection.rSet = connection.preStmt.executeQuery();

            while (connection.rSet.next()) {
                Course course = new Course();
                course.setId(connection.rSet.getString(1));
                course.setCourseName(connection.rSet.getString(2));
                course.setCourseNum(connection.rSet.getString(3));
                course.setTeacher(connection.rSet.getString(4));
                courseList.add(course);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        int size = courseList.size();
        for (int i = 0; i < size; i++) {
            final Course c = courseList.get(i);
            final Label label = new Label(c.getId());
            final TextField nameText = new TextField();
            final TextField numText = new TextField();
            final TextField tecText = new TextField();
            numText.setText(c.getCourseNum());
            nameText.setText(c.getCourseName());
            tecText.setText(c.getTeacher());

            pane.add(label, 1, i + 2);
            pane.add(nameText, 2, i + 2);
            pane.add(numText, 3, i + 2);
            pane.add(tecText, 4, i + 2);

            Button updateButton = ButtonBuilder.create().text("update").onAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (!type.equals("1")) {
                        new AlterBox().display("Tip", "you have no access to update this item");
                        return;
                    }
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
                    new UpdateAlertBox().display("Tip", "are you sure to update this item?", label.getText(), nameStr, numStr, tecStr, type + "");
                    primaryStage.close();
                }
            }).build();
            pane.add(updateButton, 5, i + 2);

            Button deleteButton = ButtonBuilder.create().text("delete").onAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (!type.equals("1")) {
                        new AlterBox().display("Tip", "you have no access to delete this item");
                        return;
                    }

                    primaryStage.close();
                }
            }).build();
            pane.add(deleteButton, 6, i + 2);
        }


        Button addButton = ButtonBuilder.create().text("add an Item").onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (!type.equals("1")) {
                    new AlterBox().display("Tip", "you have no access to add an item");
                    return;
                }
                new AddItemMenu(type).start(primaryStage);
                //primaryStage.close();
            }
        }).build();
        Button exportButton = ButtonBuilder.create().text("export").onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    ExportUtil.export("course.xml", courseList);
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } catch (TransformerException e1) {
                    e1.printStackTrace();
                }
            }
        }).build();
        pane.add(addButton, 1, size + 2);
        pane.add(exportButton, 1, size+3);
        Scene scene1 = new Scene(pane);
        primaryStage.setTitle("list of course");
        primaryStage.setScene(scene1);
        primaryStage.setWidth(800);
        primaryStage.setHeight(400);
        primaryStage.show();

    }


    public static void main(String[] args) {

        Application.launch(args);

    }
}



