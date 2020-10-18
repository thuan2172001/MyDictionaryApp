package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    DictionaryManagement management = new DictionaryManagement();

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setTitle("My dictionary app");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // vô hiệu hóa resize app
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}







