package ru.spb.nicetu.oestik;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StatusWindow extends Application {
    
    /** Путь до иконки окна */
    private final static String WINDOW_ICON_PATH = "imgs/control.png";
    /** Размер отступа */
    private final static int PAD = 5;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ПК ОЭСТИК Статус");
        BorderPane root = new BorderPane();
        root.setTop(createMenu());
        root.setCenter(createCenterNode());
        root.setBottom(createCommonNode());
        Scene scene = new Scene(root, 600, 400);
        Image windowImage = new Image(WINDOW_ICON_PATH);
        primaryStage.getIcons().add(windowImage);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("ПК ОЭСТИК Статус");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Создать меню
     * @return меню
     */
    private ToolBar createMenu() {
        Button theodoliteButton1 = new Button();
        theodoliteButton1.setTooltip(new Tooltip("Переключиться на теодолит номер 1"));
        theodoliteButton1.setText("Теодолит 1");
        
        Button theodoliteButton2 = new Button();
        theodoliteButton2.setTooltip(new Tooltip("Переключиться на теодолит номер 2"));
        theodoliteButton2.setText("Теодолит 2");
        
        Button theodoliteButton3 = new Button();
        theodoliteButton3.setTooltip(new Tooltip("Переключиться на теодолит номер 3"));
        theodoliteButton3.setText("Теодолит 3");
        
        Button theodoliteButton4 = new Button();
        theodoliteButton4.setTooltip(new Tooltip("Переключиться на теодолит номер 4"));
        theodoliteButton4.setText("Теодолит 4");
        
        Button aboutButton = new Button();
        aboutButton.setTooltip(new Tooltip("Вывести окно о программе"));
        aboutButton.setText("О программе");
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAboutDialog();
            }
        });
        
        ToolBar toolBar = new ToolBar(theodoliteButton1, theodoliteButton2,
                theodoliteButton3, theodoliteButton4, new Separator(),
                aboutButton);
        
        return toolBar;
    }
    
    /**
     * Создать и отобразить окно о программе
     */
    private void showAboutDialog() {
        Image image = new Image("imgs/nic_logo.png");
        ImageView img = new ImageView(image);
        
        Image windowImage = new Image(WINDOW_ICON_PATH);
        
        Alert dialog = new Alert(AlertType.INFORMATION);
//        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("О программе");
        dialog.setHeaderText("ПК ОЭСТИК Статус");
        dialog.setContentText("Программа для отображения статуса теодолитов");
        dialog.setGraphic(img);
        
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(windowImage);
        
        dialog.showAndWait();
    }
    
    /**
     * Создать центральный узел
     * @return центральный узел
     */
    private Node createCenterNode() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        return btn;
    }
    
    /**
     * Создать нижний узел
     * @return нижний узел
     */
    private Node createCommonNode() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(PAD));
        hbox.setSpacing(PAD);
        
        Label frequencyTextLabel = new Label("Частота:");
        Label timeTextLabel = new Label("Время:");
        Label timeSourceTextLabel = new Label("Источник точного времени:");
        Label frequencyLabel = new Label("");
        Label timeLabel = new Label("");
        Label timeSourceLabel = new Label("");
        
        hbox.getChildren().addAll(frequencyTextLabel, frequencyLabel, timeTextLabel, timeLabel, timeSourceTextLabel, timeSourceLabel);

        return hbox;
    }
}
