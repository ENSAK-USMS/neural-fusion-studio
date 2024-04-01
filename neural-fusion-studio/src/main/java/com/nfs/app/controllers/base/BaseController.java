package com.nfs.app.controllers.base;

import java.io.IOException;
import java.util.ArrayList;

import com.nfs.app.App;
import com.nfs.app.controllers.base.components.SideBarAnimator;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class BaseController {

    @FXML
    private Pane sideBar, sideBarShadow1, sideBarShadow2;
    @FXML
    private ImageView closeImage;
    @FXML
    private AnchorPane basePane;
    @FXML
    private Group sidebarLabels;

    @FXML
    private void toggleSidebar() {
        // use sideBarAnimator class to toggle sidebar
        SideBarAnimator sideBarAnimator = new SideBarAnimator();
        sideBarAnimator.toggleSidebar(sideBar, sideBarShadow1, sideBarShadow2, sidebarLabels);
    }

    public void setHoverEffect() {
        // get the scene
        Scene scene = App.getScene();
        // get all buttons with css class hoverable
        ArrayList<Button> hoverableButtons = new ArrayList<>();
        Parent root = (Parent) scene.getRoot();
        root.lookupAll(".hoverable").forEach(node -> {
            if (node instanceof Button) {
                hoverableButtons.add((Button) node);
            }
        });
        // add hover effect to all buttons
        for (Button button : hoverableButtons) {
            button.setOnMouseEntered(e -> {
                // get the parent group
                Group parent = (Group) button.getParent();
                // get the image view
                ImageView imageView = (ImageView) parent.getChildren().get(1);
                // get the image name
                String imageName = imageView.getImage().getUrl();
                // remove everything before "/images"
                String imagePath = imageName.substring(imageName.indexOf("images"));
                // get the new image name
                String newImageName = imagePath.replace(".png", "-hover.png");
                // load the new image
                ImageView newImageView = App.loadImage(newImageName);
                // set the new image
                imageView.setImage(newImageView.getImage());
            });
            button.setOnMouseExited(e -> {
                // get the parent group
                Group parent = (Group) button.getParent();
                // get the image view
                ImageView imageView = (ImageView) parent.getChildren().get(1);
                // get the image name
                String imageName = imageView.getImage().getUrl();
                // remove everything before "/images"
                String imagePath = imageName.substring(imageName.indexOf("images"));
                // get the new image name
                String newImageName = imagePath.replace("-hover.png", ".png");
                // load the new image
                ImageView newImageView = App.loadImage(newImageName);
                // set the new image
                imageView.setImage(newImageView.getImage());
            });
        }
    }

    // blur base page
    public static void blurBasePage() {
        AnchorPane pagePane = (AnchorPane) App.getScene().lookup("#pagePane");
        pagePane.setEffect(new javafx.scene.effect.BoxBlur(1, 1, 3));
    }

    // unblur base page
    public static void unblurBasePage() {
        AnchorPane pagePane = (AnchorPane) App.getScene().lookup("#pagePane");
        pagePane.setEffect(null);
    }

    // add page to base pane
    public static void addPageToBasePane(Parent root) {
        AnchorPane basePane = (AnchorPane) App.getScene().lookup("#basePane");
        basePane.getChildren().add(root);
    }

    // remove page from base pane check if it is not pagepane
    public static void removePageFromBasePane() {
        AnchorPane basePane = (AnchorPane) App.getScene().lookup("#basePane");
        if (basePane.getChildren().size() > 1) {
            basePane.getChildren().remove(basePane.getChildren().size() - 1);
        }
    }

    @FXML
    public void swithToHistoryPage() throws IOException {
        App.switchPage("views/history_view/History");
    }

    @FXML
    public void swithToHomePage() throws IOException {
        App.switchPage("views/dashboard/index");
    }

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            setHoverEffect();
        }));
        timeline.play();
        System.out.println("BaseController initialized");
    }
}
