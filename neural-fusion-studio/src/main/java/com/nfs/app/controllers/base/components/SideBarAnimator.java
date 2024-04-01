package com.nfs.app.controllers.base.components;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * @author abdobella
 * Date: Apr 01, 2024
 * Time: 5:01:18 PM
*/

public class SideBarAnimator {
    public void toggleSidebar(Pane sideBar, Pane sideBarShadow1, Pane sideBarShadow2, Group sidebarLabels) {
        Line sideBareHline1 = (Line) sideBar.lookup("#sideBarHline1");
        Line sideBareHline2 = (Line) sideBar.lookup("#sideBarHline2");
        Line sideBareHline3 = (Line) sideBar.lookup("#sideBarHline3");
        Line sideBareHline4 = (Line) sideBar.lookup("#sideBarHline4");
        double targetWidth = sideBar.getPrefWidth() == 210 ? 70 : 210;
        double targetLayoutX = targetWidth + 0.5;
        boolean isClosed = targetWidth == 70;

        // Create a timeline for the animation
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                createKeyFrame(Duration.ZERO, sideBar.prefWidthProperty(), sideBar.getPrefWidth()),
                createKeyFrame(Duration.ZERO, sideBar.minWidthProperty(), sideBar.getMinWidth()),
                createKeyFrame(Duration.ZERO, sideBar.maxWidthProperty(), sideBar.getMaxWidth()),
                createKeyFrame(Duration.ZERO, sideBarShadow1.layoutXProperty(), sideBarShadow1.getLayoutX()),
                createKeyFrame(Duration.ZERO, sideBarShadow2.layoutXProperty(), sideBarShadow2.getLayoutX()),
                createKeyFrame(Duration.ZERO, sideBareHline1.endXProperty(), sideBareHline1.getEndX()),
                createKeyFrame(Duration.ZERO, sideBareHline2.endXProperty(), sideBareHline2.getEndX()),
                createKeyFrame(Duration.ZERO, sideBareHline3.endXProperty(), sideBareHline3.getEndX()),
                createKeyFrame(Duration.ZERO, sideBareHline4.endXProperty(), sideBareHline4.getEndX()),
                createKeyFrame(Duration.seconds(0.3), sideBar.prefWidthProperty(), targetWidth),
                createKeyFrame(Duration.seconds(0.3), sideBar.minWidthProperty(), targetWidth),
                createKeyFrame(Duration.seconds(0.3), sideBar.maxWidthProperty(), targetWidth),
                createKeyFrame(Duration.seconds(0.3), sideBarShadow1.layoutXProperty(), targetLayoutX),
                createKeyFrame(Duration.seconds(0.3), sideBarShadow2.layoutXProperty(), targetLayoutX+2),
                createKeyFrame(Duration.seconds(0.3), sideBareHline1.endXProperty(), targetWidth),
                createKeyFrame(Duration.seconds(0.3), sideBareHline2.endXProperty(), targetWidth),
                createKeyFrame(Duration.seconds(0.3), sideBareHline3.endXProperty(), targetWidth),
                createKeyFrame(Duration.seconds(0.3), sideBareHline4.endXProperty(), targetWidth)
        );

        // Play the timeline animation
        timeline.play();

        if (isClosed) {
            // hide sidebar labels
            sidebarLabels.setVisible(false);
        } else {
            // wait for the animation to finish
            timeline.setOnFinished(e -> fadeInSidebarLabels(sidebarLabels));
        }
    }

    private KeyFrame createKeyFrame(Duration duration, DoubleProperty doubleProperty, double value) {
        return new KeyFrame(duration, new KeyValue(doubleProperty, value));
    }

    private void fadeInSidebarLabels(Group sidebarLabels) {
        // set the opacity 0 and set it visible and then animate it to 1
        sidebarLabels.setOpacity(0);
        sidebarLabels.setVisible(true);
        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(sidebarLabels.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(sidebarLabels.opacityProperty(), 1))
        );
        fadeIn.play();
    }
}
