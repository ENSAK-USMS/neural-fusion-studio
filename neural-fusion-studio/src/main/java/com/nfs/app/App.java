package com.nfs.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.nfs.app.tools.ExceptionHandler;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("base"), 900, 600);
        stage.initStyle(StageStyle.UNDECORATED); // Remove the default window header
        // chage scene bg color to 191919
        scene.setFill(javafx.scene.paint.Color.valueOf("#191919"));
        stage.setScene(scene);
        switchPage("views/dashboard/index");
        setPageBottons();
        stage.show();
        ExceptionHandler.setScene(scene);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws Exception {
        launch();
    }



    /**
     * Switches the current page to the specified FXML file.
     *
     * @param fxml the path to the FXML file to load
     * @throws IOException if an error occurs while loading the FXML file
     */
    public static void switchPage(String fxml) throws IOException {
        // load fxml file
        Parent root = loadFXML(fxml);
        // get contentPane by fx:id
        AnchorPane contentPane = (AnchorPane) scene.lookup("#contentPane");
        // remove all children
        contentPane.getChildren().clear();
        // add new page
        contentPane.getChildren().add(root);
    }


    /**
     * Set the page buttons actions
     *
     * @throws IOException if an error occurs while loading the FXML file
     */
    public static void setPageBottons() throws IOException {
        // get closeAppwindow by fx:id
        Button closeApp = (Button) scene.lookup("#closeApp");
        // get iconife by fx:id
        Button iconifieApp = (Button) scene.lookup("#iconifieApp");

        // close window fx:id closeAppwindow
        closeApp.setOnMouseClicked(e -> {
            System.exit(0);
        });

        // iconifie window fx:id iconifieApp
        iconifieApp.setOnMouseClicked(e -> {
            Stage stage = (Stage) iconifieApp.getScene().getWindow();
            stage.setIconified(true);
        });
    }

    // a function to load images from resources
    public static ImageView loadImage(String path) {
        Image image = new Image(App.class.getResource(path).toString());
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public static void showExceptionWindow(Exception exception) {
        ExceptionHandler.showExceptionWindow(exception);
    }
}