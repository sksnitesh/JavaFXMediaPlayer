package com.lue.movementdynamics;

import com.lue.util.SqLiteConnect;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lue
 */
public class LibraryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label home, order, module, help, logout;
    @FXML
    private Pane mediaPane, pane1;
    @FXML
    private TreeView treeView;
    @FXML
    private FlowPane flowPane, flowPane1;
    @FXML
    private HBox hBox, hBox1;

    TreeItem treeItem = new TreeItem();
    TreeItem treeItem1 = new TreeItem();
    TreeItem treeItem2 = new TreeItem();
    TreeItem treeItem3 = new TreeItem();
    TreeItem treeItem4 = new TreeItem();
    Button button;
    ImageView imageView;
    Image image;

    String fileName = "";
    String mediaSource = "";
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<String> categoryTitleList = new ArrayList<String>();
    List<String> packageNameList = new ArrayList<String>();
    List<String> moduleTitleList = new ArrayList<String>();
    List<String> videoTitleList = new ArrayList<String>();
    List<String> videoDescriptionsList = new ArrayList<String>();
    public ObservableList data = FXCollections.observableArrayList();
    List<String> ImageListDb = new ArrayList<String>();
    List<String> ImageDescDb = new ArrayList<String>();
    int videoListSize = 0;
    List<String> currentVideoList1 = new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ButtonEffect();
            getVideoList();
            getLib();
            getVideoButton();
        } catch (SQLException ex) {
            Logger.getLogger(LibraryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getVideoList() throws SQLException {

        String workingDir = (System.getProperty("user.home") + "\\VideoLibrary");
        File folder = new File(workingDir);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileName = listOfFiles[i].getName();
                getVideosLib(fileName);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory--------------- " + listOfFiles[i].getName());
            }
        }
    }

    public void getVideoTitle(String VideoDescriptions) throws SQLException {
        conn = SqLiteConnect.connect();
        String sql = "SELECT VideoTitle FROM videos where VideoDescriptions=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, VideoDescriptions);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String videoTitleDB = resultSet.getString("VideoTitle");
            String workingDir = (System.getProperty("user.home") + "\\VideoLibrary");
            String offlineVideoUrl = workingDir + "\\" + videoTitleDB;
            mediaPlayer(offlineVideoUrl);
        }

    }

    public void mediaPlayer(String fileName) {
        pane1.setStyle("-fx-background-color:#000000");
        Media media = new Media(new File(fileName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1354);
        mediaView.setFitHeight(352);
        mediaPane.getChildren().add(mediaView);
    }

    public void handleTreeClicked(MouseEvent event) throws SQLException {
        try {
            Node node = event.getPickResult().getIntersectedNode();
            // Accept clicks only on node cells, and not on empty spaces of the TreeView
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();
                String workingDir = (System.getProperty("user.home") + "\\VideoLibrary");
                String offlineVideoUrl = workingDir + "\\" + name;
                mediaPlayer(offlineVideoUrl);
                getVideoButton();
                getTableData(name);
            }
        } catch (Exception e) {

        }
    }

    public void getTableData(String VideoTitle) throws SQLException {
        conn = SqLiteConnect.connect();
        String sql = "SELECT * FROM videos where VideoTitle=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, VideoTitle);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ImageListDb.add(resultSet.getString("ImageList"));
            ImageDescDb.add(resultSet.getString("ImageDesc"));

        }
        getImageList(ImageDescDb, ImageListDb);
    }

    public void getImageList(List<String> currentDesc, List<String> currentImage) {
        flowPane1.getChildren().clear();
        for (int i = 0; i < currentDesc.size(); i++) {
            imageView = new ImageView();
            String urlImg = "http://wbaclientdesign4.com/mdyamics/doc/products/package/module/video/descs/" + currentImage.get(i);
            image = new Image(urlImg);
            imageView.setImage(image);
            imageView.setFitWidth(180.0D);
            imageView.setFitHeight(150.0D);
            Label label = new Label();
            label.setText(currentDesc.get(i));
            hBox1 = new HBox();

            hBox1.setSpacing(6.0D);
            hBox1.getChildren().add(imageView);
            hBox1.getChildren().add(label);
            hBox1.setAlignment(Pos.CENTER);
            flowPane1.getChildren().add(hBox1);

        }
        currentDesc.clear();
        currentImage.clear();
    }

    public void getLib() {
        for (int i = 0; i < categoryTitleList.size(); i++) {
            treeItem1 = new TreeItem(moduleTitleList.get(i));
            treeItem2 = new TreeItem(videoTitleList.get(i));
            treeItem1.getChildren().add(treeItem2);
            treeView.setRoot(treeItem);
            treeItem.setExpanded(true);
            treeView.setShowRoot(false);
            treeItem.getChildren().add(treeItem1);
        }
    }

    public void getVideosLib(String VideoTitle) throws SQLException {

        conn = SqLiteConnect.connect();
        String sql = "SELECT DISTINCT CategoryTitle, PackageName, ModuleTitle,VideoTitle,VideoDescriptions FROM videos where VideoTitle=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, VideoTitle);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            packageNameList.add(resultSet.getString("PackageName"));
            moduleTitleList.add(resultSet.getString("ModuleTitle"));
            categoryTitleList.add(resultSet.getString("CategoryTitle"));
            videoTitleList.add(resultSet.getString("VideoTitle"));
            videoDescriptionsList.add(resultSet.getString("VideoDescriptions"));
        }
    }

    public void getVideoButton() {
        flowPane.getChildren().clear();
        flowPane.setPadding(new Insets(5.0D, 0.0D, 0.0D, 0.0D));
        flowPane.setHgap(15.0D);
        for (int i = 0; i < videoTitleList.size(); i++) {
            final int index = i;
            String s = String.valueOf(i + 1);
            button = new Button(s);
            button.setPrefWidth(70.0D);
            button.setPrefHeight(40.0D);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            hBox = new HBox();
            hBox.setSpacing(6.0D);
            hBox.getChildren().add(button);
            hBox.setAlignment(Pos.CENTER);
            flowPane.getChildren().add(hBox);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handleButton(videoTitleList.get(index));
                }
            });
        }
    }

    private class MyEventHandler implements EventHandler<Event> {

        @Override
        public void handle(Event evt) {
            System.out.println("############" + ((Control) evt.getSource()).getId());
            ((Control) evt.getSource()).setStyle("-fx-background-color: #db061f;");
        }
    }

    public void handleButton(String name) {
        try {
            getTableData(name);
            String workingDir = (System.getProperty("user.home") + "\\VideoLibrary");
            String offlineVideoUrl = workingDir + "\\" + name;
            mediaPlayer(offlineVideoUrl);
        } catch (SQLException ex) {
            Logger.getLogger(LibraryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Logout(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Movement Library Player");
        stage.setMaximized(true);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void getModule(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Movement Library Player");
        stage.setMaximized(true);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void ButtonEffect() {

        module.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                module.setCursor(Cursor.HAND);
            }
        });

        module.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                module.setEffect(null);
            }
        });

        logout.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                logout.setCursor(Cursor.HAND);
            }
        });

        logout.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logout.setEffect(null);
            }
        });

    }

}
