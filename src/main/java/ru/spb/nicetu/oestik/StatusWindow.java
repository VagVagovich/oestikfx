package ru.spb.nicetu.oestik;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class StatusWindow extends Application {
    
    /** ���� �� ������ ���� */
    private final static String WINDOW_ICON_PATH = "imgs/control.png";
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("�� ������ ������");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        BorderPane root = new BorderPane();
        root.setCenter(btn);
        root.setTop(createMenu());
        Scene scene = new Scene(root, 600, 400);
        Image windowImage = new Image(WINDOW_ICON_PATH);
        primaryStage.getIcons().add(windowImage);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("�� ������ ������");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * ������� ����
     * @return ����
     */
    private ToolBar createMenu() {
        Button theodoliteButton1 = new Button();
        theodoliteButton1.setTooltip(new Tooltip("������������� �� �������� ����� 1"));
        theodoliteButton1.setText("�������� 1");
        
        Button theodoliteButton2 = new Button();
        theodoliteButton2.setTooltip(new Tooltip("������������� �� �������� ����� 2"));
        theodoliteButton2.setText("�������� 2");
        
        Button theodoliteButton3 = new Button();
        theodoliteButton3.setTooltip(new Tooltip("������������� �� �������� ����� 3"));
        theodoliteButton3.setText("�������� 3");
        
        Button theodoliteButton4 = new Button();
        theodoliteButton4.setTooltip(new Tooltip("������������� �� �������� ����� 4"));
        theodoliteButton4.setText("�������� 4");
        
        Button aboutButton = new Button();
        aboutButton.setTooltip(new Tooltip("������� ���� � ���������"));
        aboutButton.setText("� ���������");
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
     * ������� � ���������� ���� � ���������
     */
    private void showAboutDialog() {
        Image image = new Image("imgs/nic_logo.png");
        ImageView img = new ImageView(image);
        
        Image windowImage = new Image(WINDOW_ICON_PATH);
        
        Alert dialog = new Alert(AlertType.INFORMATION);
//        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("� ���������");
        dialog.setHeaderText("�� ������ ������");
        dialog.setContentText("��������� ��� ����������� ������� ����������");
        dialog.setGraphic(img);
        
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(windowImage);
        
        dialog.showAndWait();
    }
}
