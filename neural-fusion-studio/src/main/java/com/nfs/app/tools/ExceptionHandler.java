package com.nfs.app.tools;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.Group;

/**
 * @author abdobella
 *         Date: Apr 01, 2024
 *         Time: 4:46:17 PM
 */

public class ExceptionHandler {
    private static boolean exceptionWindowEventsAdded = false;
    private static Scene scene; // You need to initialize this scene from your application

    public static void setScene(Scene scene) {
        ExceptionHandler.scene = scene;
    }

    public static void showExceptionWindow(Exception exception) {
        Pane exceptionOuterPane = (Pane) scene.lookup("#exceptionOuterPane");
        Pane exceptionWindow = (Pane) scene.lookup("#exceptionWindow");

        String exceptionMessage = exception.getMessage();
        String exceptionName = exception.getClass().getName();
        String exceptionStackTrace = exception.toString();

        Label exceptionMessageLabel = (Label) exceptionWindow.lookup("#exceptionMessage");
        exceptionMessageLabel.setText(exceptionMessage);

        Label exceptionNameLabel = (Label) exceptionWindow.lookup("#exceptionName");
        if (exceptionName.length() > 35) {
            exceptionName = exceptionName.substring(0, 35) + "...";
        }
        exceptionNameLabel.setText(exceptionName);

        Group showStackTrace = (Group) exceptionWindow.lookup("#showStackTrace");
        Button closeButton = (Button) exceptionWindow.lookup("#closeExceptionButton");

        Label stackTraceLabel = (Label) exceptionWindow.lookup("#stackTrace");
        stackTraceLabel.setVisible(false);

        if (!exceptionWindowEventsAdded) {
            showStackTrace.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
                toggleStackTrace(exceptionWindow, exceptionStackTrace);
            });

            Pane closeArea = (Pane) exceptionOuterPane.lookup("#closeArea");
            closeArea.setOnMouseClicked(e -> {
                hideExceptionWindow(exceptionOuterPane);
            });

            closeButton.setOnMouseClicked(e -> {
                hideExceptionWindow(exceptionOuterPane);
            });

            exceptionWindowEventsAdded = true;
        }

        exceptionOuterPane.setVisible(true);
    }

    private static void hideExceptionWindow(Pane exceptionWindow) {
        Label stackTraceLabel = (Label) exceptionWindow.lookup("#stackTrace");
        stackTraceLabel.setVisible(false);
        stackTraceLabel.setPrefHeight(0);
        stackTraceLabel.setLayoutY(0);
        exceptionWindow.setPrefHeight(80);

        Label exceptionMessageLabel = (Label) exceptionWindow.lookup("#exceptionMessage");
        exceptionMessageLabel.setText("");

        Label exceptionNameLabel = (Label) exceptionWindow.lookup("#exceptionName");
        exceptionNameLabel.setText("");

        stackTraceLabel.setText("");

        exceptionWindow.setVisible(false);
    }

    private static void toggleStackTrace(Pane exceptionWindow, String exceptionStackTrace) {
        Label stackTraceLabel = (Label) exceptionWindow.lookup("#stackTrace");
        System.out.println("toggleStackTrace : " + stackTraceLabel.isVisible());

        if (stackTraceLabel.isVisible()) {
            stackTraceLabel.setVisible(false);
            stackTraceLabel.setPrefHeight(0);
            stackTraceLabel.setLayoutY(0);
            exceptionWindow.setPrefHeight(80);
        } else {
            stackTraceLabel.setText(exceptionStackTrace);
            stackTraceLabel.setPrefHeight(320);
            stackTraceLabel.setLayoutY(80);
            stackTraceLabel.setVisible(true);
            exceptionWindow.setPrefHeight(400);
        }
    }
}
