package com.spaghettic0der;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application
{
    private final int textButtonWidth = 100;
    private final int textButtonHeight = 50;
    private final int diceButtonSize = 50;
    private final int buttonFontSize = 20;
    private final String versionNumber = "0.0.6";
    private Game game;
    private BorderPane root;
    private HBox remainingDiceHBox;
    private HBox drawnDiceHBox;
    private Label currentPlayerLabel;
    private Label scoreLabel;
    private Label scoreInRoundLabel;
    private Stage settingsStage;
    private Scene mainScene;
    private JsonHelper jsonHelper;
    private Settings settings;

    public Main()
    {
        jsonHelper = new JsonHelper();
        settings = new Settings();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    private void updateUI()
    {
        remainingDiceHBox.getChildren().clear();
        drawnDiceHBox.getChildren().clear();
        createRemainingDiceButtons();
        createDrawnDiceButtons();
        currentPlayerLabel.setText("Current Player: " + (game.getCurrentPlayer().getPlayerNumber() + 1));
        scoreLabel.setText("Score: " + (game.getCurrentPlayer().getScore() + Scoring.getScoreFromAllDicesInRound(game.getCurrentPlayer().getLastTurn().getRoundArrayList(), game.getSettings())));
        scoreInRoundLabel.setText("Score in Round: " + Scoring.getScoreFromAllDicesInRound(game.getCurrentPlayer().getLastTurn().getRoundArrayList(), game.getSettings()));
        System.out.println(Scoring.getScoreFromAllDices(game.getCurrentPlayer().getTurnArrayList(), game.getSettings()));
    }

    private void createDrawnDiceButtons()
    {
        final ArrayList<Dice> dices = game.getCurrentPlayer().getLastTurn().getLastRound().getDrawnDices();

        for (int i = 0; i < dices.size(); i++)
        {
            Dice currentDice = dices.get(i);
            Button diceButton = new Button();
            diceButton.setPrefWidth(diceButtonSize);
            diceButton.setPrefHeight(diceButtonSize);
            diceButton.setFont(new Font(buttonFontSize));
            if (settings.isDiceImageShown())
            {
                String diceImageLocation = "res/" + dices.get(i).getDiceNumber() + ".png";
                setDiceImage(diceButton, diceImageLocation);
            }
            else
            {
                diceButton.setText("" + dices.get(i).getDiceNumber());
            }

            //checks if dices are in last roll -> if so you can still move them back --> yellowgreen color
            if (game.getCurrentPlayer().getLastTurn().getLastRound().getLastRoll().getDrawnDices().contains(dices.get(i)))
            {
                diceButton.setStyle("-fx-background-color: yellowgreen; -fx-border-color: gray");
            }
            else
            {
                diceButton.setStyle("-fx-background-color: #ff5f50; -fx-border-color: gray");
            }

            diceButton.setId("" + i);
            diceButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    moveToRemainingDices(currentDice);
                }
            });
            drawnDiceHBox.getChildren().add(diceButton);
        }
    }

    private void setDiceImage(Button diceButton, String diceImageLocation)
    {

        Image image = new Image(getClass().getResourceAsStream(diceImageLocation));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(diceButtonSize / 2);
        imageView.setFitHeight(diceButtonSize / 2);
        diceButton.setGraphic(imageView);
    }

    private void createRemainingDiceButtons()
    {
        final ArrayList<Dice> dices = game.getCurrentPlayer().getRemainingDices();
        for(int i=0; i < game.getCurrentPlayer().getRemainingDices().size(); i++)
        {
            Dice currentDice = dices.get(i);
            Button diceButton = new Button();
            diceButton.setPrefWidth(diceButtonSize);
            diceButton.setPrefHeight(diceButtonSize);
            diceButton.setStyle("-fx-background-color: greenyellow; -fx-border-color: gray");
            diceButton.setFont(new Font(buttonFontSize));
            diceButton.setId("" + i);
            if (settings.isDiceImageShown())
            {
                String diceImageLocation = "res/" + dices.get(i).getDiceNumber() + ".png";
                setDiceImage(diceButton, diceImageLocation);
            }
            else
            {
                diceButton.setText("" + game.getCurrentPlayer().getRemainingDices().get(i).getDiceNumber());
            }
            diceButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    moveToDrawnDices(currentDice);
                }
            });
            remainingDiceHBox.getChildren().add(diceButton);
        }
    }

    private void moveToDrawnDices(Dice dice)
    {
        ArrayList<Dice> remainingDices = game.getCurrentPlayer().getRemainingDices();
        remainingDices.remove(dice);
        game.getCurrentPlayer().getLastTurn().getLastRound().getLastRoll().getDrawnDices().add(dice);
        updateUI();
    }

    private void moveToRemainingDices(Dice dice)
    {
        ArrayList<Dice> drawnDices = game.getCurrentPlayer().getLastTurn().getLastRound().getLastRoll().getDrawnDices();
        if (drawnDices.size() > 0)
        {
            if (drawnDices.contains(dice))
            {
                game.getCurrentPlayer().getLastTurn().getLastRound().getLastRoll().removeDiceWithNumber(dice.getDiceNumber());
                game.getCurrentPlayer().getRemainingDices().add(dice);
                updateUI();
            }
        }
    }

    private void showInvalidMoveAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Invalid Move!");
        alert.setContentText("That's not allowed");
        alert.show();
    }

    private void initSettingsStage(Stage primaryStage)
    {
        Settings settings = game.getSettings();
        BorderPane borderPane = new BorderPane();
        Scene settingsScene = new Scene(borderPane, settings.getWidth(), settings.getHeight());
        VBox vBox = new VBox();

        //player count
        HBox playerHBox = new HBox();
        Label playerLabel = new Label("Players:");
        Slider playerSlider = new Slider(2, 6, settings.getTotalPlayers());
        playerSlider.setMajorTickUnit(1);
        playerSlider.setMinorTickCount(0);
        playerSlider.setSnapToTicks(true);
        playerSlider.setShowTickLabels(true);
        playerHBox.getChildren().addAll(playerLabel, playerSlider);
        HBox.setHgrow(playerSlider, Priority.ALWAYS);
        HBox.setMargin(playerLabel, new Insets(0, 20, 0, 0));
        VBox.setMargin(playerHBox, new Insets(20, 20, 20, 20));
        vBox.getChildren().add(playerHBox);

        //dice count
        HBox diceHBox = new HBox();
        Label diceLabel = new Label("Dices:");
        Slider diceSlider = new Slider(4, 10, settings.getTotalDiceNumber());
        diceSlider.setMajorTickUnit(1);
        diceSlider.setMinorTickCount(0);
        diceSlider.setSnapToTicks(true);
        diceSlider.setShowTickLabels(true);
        diceHBox.getChildren().addAll(diceLabel, diceSlider);
        HBox.setHgrow(diceSlider, Priority.ALWAYS);
        HBox.setMargin(diceLabel, new Insets(0, 20, 0, 0));
        VBox.setMargin(diceHBox, new Insets(20, 20, 20, 20));
        vBox.getChildren().add(diceHBox);

        //win score
        HBox winScoreHBox = new HBox();
        Label winScoreLabel = new Label("Win Score:");
        TextField winScoreTextField = new TextField("" + settings.getMinScoreRequiredToWin());
        winScoreHBox.getChildren().addAll(winScoreLabel, winScoreTextField);
        HBox.setHgrow(winScoreTextField, Priority.ALWAYS);
        HBox.setMargin(winScoreTextField, new Insets(-3, 0, 0, 0));
        HBox.setMargin(winScoreLabel, new Insets(0, 20, 0, 0));
        VBox.setMargin(winScoreHBox, new Insets(20, 20, 20, 20));
        vBox.getChildren().add(winScoreHBox);

        //minsave Score
        HBox minScoreHBox = new HBox();
        Label minScoreLabel = new Label("Min Score to save:");
        TextField minScoreTextField = new TextField("" + settings.getMinScoreRequiredToSaveInRound());
        minScoreHBox.getChildren().addAll(minScoreLabel, minScoreTextField);
        HBox.setHgrow(minScoreTextField, Priority.ALWAYS);
        HBox.setMargin(minScoreTextField, new Insets(-3, 0, 0, 0));
        HBox.setMargin(minScoreLabel, new Insets(0, 20, 0, 0));
        VBox.setMargin(minScoreHBox, new Insets(20, 20, 20, 20));
        vBox.getChildren().add(minScoreHBox);

        //rules

        //street
        HBox streetHBox = new HBox();
        ToggleButton streetToggleButton = new ToggleButton("Street");
        streetToggleButton.setSelected(game.getSettings().isStreetEnabled());

        TextField streetTextField = new TextField("" + settings.getScoreStreet());
        streetHBox.getChildren().addAll(streetToggleButton, streetTextField);
        vBox.getChildren().add(streetHBox);
        HBox.setMargin(streetToggleButton, new Insets(0, 20, 0, 20));
        HBox.setMargin(streetTextField, new Insets(0, 20, 0, 20));
        HBox.setHgrow(streetTextField, Priority.ALWAYS);
        VBox.setMargin(minScoreHBox, new Insets(20, 20, 20, 20));

        if (!streetToggleButton.isSelected())
        {
            streetTextField.setDisable(true);
        }
        else
        {
            streetTextField.setDisable(false);
        }


        streetToggleButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (!streetToggleButton.isSelected())
                {
                    streetTextField.setDisable(true);
                }
                else
                {
                    streetTextField.setDisable(false);
                }
            }
        });


        //save and cancel button
        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        Button saveSettingsButton = new Button("Save");
        saveSettingsButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Settings settings = game.getSettings();
                settings.setTotalPlayers((int) playerSlider.getValue());
                settings.setTotalDiceNumber((int) diceSlider.getValue());
                settings.setMinScoreRequiredToWin(Integer.parseInt(winScoreTextField.getText()));

                settings.setStreetEnabled(streetToggleButton.isSelected());
                settings.setScoreStreet(Integer.parseInt(streetTextField.getText()));
                game = new Game(settings);
                updateUI();
                settingsStage.close();
            }
        });
        Button cancelSettingsButton = new Button("Cancel");
        buttonHBox.getChildren().addAll(cancelSettingsButton, saveSettingsButton);
        vBox.getChildren().add(buttonHBox);

        borderPane.setCenter(vBox);

        settingsStage = new Stage();
        settingsStage.initOwner(primaryStage);
        settingsStage.centerOnScreen();
        settingsStage.setScene(settingsScene);
        settingsStage.showAndWait();
    }

    private void initMenu(Stage primaryStage)
    {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Menu gameMenu = new Menu("Game");

        MenuItem newGameItem = new MenuItem("New");
        newGameItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                game = new Game(settings);
                updateUI();
            }
        });
        MenuItem settingsItem = new MenuItem("Settings");
        settingsItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                initSettingsStage(primaryStage);
            }
        });
        MenuItem loadItem = new MenuItem("Load");
        loadItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                game = jsonHelper.loadGameState();
                updateUI();
            }
        });
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                jsonHelper.saveGameState(game);
            }
        });
        gameMenu.getItems().addAll(newGameItem, settingsItem, loadItem, saveItem);

        Menu aboutMenu = new Menu("About");
        MenuItem infoItem = new MenuItem("Zehntausend");
        infoItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("About Zehntausend\nVersion: " + versionNumber);
                alert.setContentText("Made by spaghettic0der in 2016\nMade possible with the help of Deadlocker");
                alert.show();
            }
        });

        aboutMenu.getItems().add(infoItem);

        menuBar.getMenus().addAll(gameMenu, aboutMenu);
        root.setTop(menuBar);
    }

    private void initUI(Stage primaryStage)
    {
        root = new BorderPane();
        VBox vBox = new VBox();
        root.setCenter(vBox);
        initMenu(primaryStage);
        remainingDiceHBox = new HBox();
        remainingDiceHBox.setMinHeight(diceButtonSize);
        remainingDiceHBox.setAlignment(Pos.CENTER);
        drawnDiceHBox = new HBox();
        drawnDiceHBox.setMinHeight(diceButtonSize);
        drawnDiceHBox.setAlignment(Pos.CENTER);
        Button rollButton = new Button("ROLL");
        rollButton.setPrefWidth(textButtonWidth);
        rollButton.setPrefHeight(textButtonHeight);
        rollButton.setFont(new Font(buttonFontSize));
        rollButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (game.isValidState(State.ROLL))
                {
                    game.getCurrentPlayer().rollDice();
                    updateUI();
                }
                else
                {
                    showInvalidMoveAlert();
                }
            }
        });
        Button nextButton = new Button("NEXT");

        nextButton.setPrefHeight(textButtonHeight);
        nextButton.setPrefWidth(textButtonWidth);
        nextButton.setFont(new Font(buttonFontSize));
        nextButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (game.isValidState(State.NEXT))
                {
                    game.nextPlayer();
                    updateUI();
                }
                else
                {
                    showInvalidMoveAlert();
                }
            }
        });

        currentPlayerLabel = new Label("Current Player: 0");
        currentPlayerLabel.setFont(new Font(buttonFontSize));
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(new Font(buttonFontSize));
        scoreInRoundLabel = new Label("Score in Round: 0");
        scoreInRoundLabel.setFont(new Font(buttonFontSize));
        VBox.setMargin(rollButton, new Insets(-50, 0, 10, 0));
        VBox buttonBox = new VBox(rollButton, nextButton);
        buttonBox.setPadding(new Insets(0, 0, 20, 0));
        buttonBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(buttonBox);
        createRemainingDiceButtons();
        vBox.getChildren().add(remainingDiceHBox);
        vBox.getChildren().add(drawnDiceHBox);
        VBox.setMargin(drawnDiceHBox, new Insets(5, 0, 20, 0));

        vBox.getChildren().add(currentPlayerLabel);
        vBox.getChildren().add(scoreLabel);
        vBox.getChildren().add(scoreInRoundLabel);

        vBox.setAlignment(Pos.CENTER);
        mainScene = new Scene(root, game.getSettings().getWidth(), game.getSettings().getHeight());
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        game = new Game(settings);
        initUI(primaryStage);
        game.getCurrentPlayer().rollDice();
        updateUI();
    }
}
