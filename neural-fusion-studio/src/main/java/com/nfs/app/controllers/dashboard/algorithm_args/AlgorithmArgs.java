package com.nfs.app.controllers.dashboard.algorithm_args;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;

import java.util.List;

import com.nfs.app.App;
import com.nfs.app.algorithms.Algorithm_Abstract;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import weka.core.Option;

public class AlgorithmArgs extends Pane {

    public AlgorithmArgs(Algorithm_Abstract algorithm_Abstract) {
        List<Option> options = algorithm_Abstract.getListOfOptions();
        // print options
        for (Option option : options) {
            System.out.println(option.name());
            System.out.println(option.synopsis());
            System.out.println(option.description());
            System.out.println(option.numArguments());
            System.out.println("--------");
        }
        setPrefHeight(435.0);
        setPrefWidth(350.0);
        setLayoutX(237);
        setLayoutY(40);
        setStyle("-fx-background-color: #313131; -fx-background-radius: 5;");

        Line verticalLine = createVerticalLine();

        HBox mainContainer = createMainContainer();

        Line verticalLine2 = createVerticalLine();

        Pane contentPane = createContentPane();

        ImageView logoImageView = createLogoImageView();

        VBox inputContainer = createInputContainer();

        for (Option option : options) {
            String name = option.name();
            String description = option.description();
            VBox hyperParamContainer = createHyperParamContainer();
            HBox inputRow1 = createInputRow1();
            Label optionLabel = createOptionLabel(name);
            VBox optionInputContainer = createOptionInputContainer();
            TextField optionTextField = createOptionTextField();
            Button optionAiButton = createOptionAiButton();
            optionInputContainer.getChildren().addAll(optionTextField, optionAiButton);
            inputRow1.getChildren().addAll(optionLabel, optionInputContainer);
            hyperParamContainer.getChildren().add(inputRow1);
            inputContainer.getChildren().add(hyperParamContainer);

        }

        // add 5px margin to the right of the inputContainer
        inputContainer.setStyle("-fx-padding: 0 5 0 0;");

        // add scroll to inputContainer
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(inputContainer);
        scrollPane.setPrefHeight(260.0);
        scrollPane.setPrefWidth(330.0);
        scrollPane.setLayoutX(4.0);
        scrollPane.setLayoutY(59.0);


        scrollPane.getStyleClass().add("scroll_pane");

        // add css file
        scrollPane.getStylesheets().add(App.class.getResource("/com/nfs/app/styles/index.css").toExternalForm());

        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        

        contentPane.getChildren().addAll(logoImageView, scrollPane);
        ImageView aiGenerateImageView = createAIGenerateImageView();
        contentPane.getChildren().add(aiGenerateImageView);

        mainContainer.getChildren().addAll(verticalLine, contentPane, verticalLine2);

        getChildren().add(mainContainer);
    }

    private ImageView createAIGenerateImageView() {
        ImageView aiGenerateImageView = new ImageView();
        aiGenerateImageView.setFitHeight(52.0);
        aiGenerateImageView.setFitWidth(78.0);
        aiGenerateImageView.setLayoutX(146.0);
        aiGenerateImageView.setLayoutY(305.0);
        aiGenerateImageView.setPickOnBounds(true);
        aiGenerateImageView.setPreserveRatio(true);
        aiGenerateImageView.setImage(App.loadImage("/com/nfs/app/images/AI-generate-hover.png").getImage());
        aiGenerateImageView.setCursor(Cursor.HAND);
        return aiGenerateImageView;
    }

    private Line createVerticalLine() {
        Line verticalLine = new Line();
        verticalLine.setEndY(432.0);
        verticalLine.setStrokeLineCap(StrokeLineCap.ROUND);
        verticalLine.setStrokeLineJoin(StrokeLineJoin.ROUND);
        verticalLine.setStrokeWidth(3.0);
        LinearGradient verticalLineGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(46, 46, 46)),
                new Stop(0.49125168236877503, Color.rgb(137, 46, 48)),
                new Stop(1, Color.rgb(46, 46, 46)));
        verticalLine.setStroke(verticalLineGradient);
        return verticalLine;
    }

    private HBox createMainContainer() {
        HBox mainContainer = new HBox();
        mainContainer.setPrefHeight(435.0);
        mainContainer.setPrefWidth(350.0);
        return mainContainer;
    }

    private Pane createContentPane() {
        Pane contentPane = new Pane();
        contentPane.setPrefWidth(344.0);
        return contentPane;
    }

    private ImageView createLogoImageView() {
        ImageView logoImageView = new ImageView();
        logoImageView.setFitHeight(52.0);
        logoImageView.setFitWidth(250.0);
        logoImageView.setLayoutX(52.0);
        logoImageView.setLayoutY(4.0);
        logoImageView.setPickOnBounds(true);
        logoImageView.setPreserveRatio(true);
        logoImageView.setImage(App.loadImage("/com/nfs/app/images/text-images/image.png").getImage());
        return logoImageView;
    }

    private VBox createInputContainer() {
        VBox inputContainer = new VBox();
        inputContainer.setLayoutX(4.0);
        inputContainer.setLayoutY(59.0);
        inputContainer.setMinHeight(260.0);
        inputContainer.setPrefWidth(330.0);
        inputContainer.setSpacing(10.0);
        return inputContainer;
    }

    private VBox createHyperParamContainer() {
        VBox labelContainer = new VBox();
        labelContainer.setAlignment(Pos.CENTER_RIGHT);
        labelContainer.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        return labelContainer;
    }

    private HBox createInputRow1() {
        HBox inputRow1 = new HBox();
        inputRow1.setAlignment(Pos.CENTER);
        inputRow1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        inputRow1.setPrefHeight(26.0);
        inputRow1.setPrefWidth(287.0);
        inputRow1.setSpacing(10.0);
        return inputRow1;
    }

    private Label createOptionLabel(String name) {
        Label Label = new Label();
        Label.setAlignment(Pos.CENTER_RIGHT);
        Label.setContentDisplay(ContentDisplay.CENTER);
        Label.setPrefWidth(130.0);
        Label.setText(name);
        Label.setTextAlignment(TextAlignment.CENTER);
        Label.setTextFill(Color.WHITE);
        Label.setWrapText(true);
        Label.setFont(new Font(11.0));
        return Label;
    }

    private VBox createOptionInputContainer() {
        VBox InputContainer = new VBox();
        InputContainer.setAlignment(Pos.CENTER);
        InputContainer.setPrefWidth(250.0);
        return InputContainer;
    }

    private TextField createOptionTextField() {
        TextField TextField = new TextField();
        TextField.setPrefHeight(25.0);
        TextField.setPrefWidth(250.0);
        TextField.setStyle("-fx-background-radius: 0;");
        return TextField;
    }

    private Button createOptionAiButton() {
        Button batchSizeButton = new Button();
        batchSizeButton.setMnemonicParsing(false);
        batchSizeButton.setPrefWidth(250.0);
        batchSizeButton
                .setStyle("-fx-background-color: c644c6; -fx-background-radius: 0 0 5 5; -fx-border-radius: 0 0 5 5;");
        batchSizeButton.setText("You Could Gain 175% Accuracy By Changing Value To 50");
        batchSizeButton.setTextFill(Color.WHITE);
        batchSizeButton.setFont(new Font(8.0));
        batchSizeButton.setCursor(Cursor.HAND);
        return batchSizeButton;
    }

}
