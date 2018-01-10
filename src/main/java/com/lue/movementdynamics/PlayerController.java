package com.lue.movementdynamics;

import com.google.gson.Gson;
import com.lue.dto.ImgDesc;
import com.lue.dto.Info;
import com.lue.dto.ModuleVideo;
import com.lue.dto.Modules;
import com.lue.dto.Packages;
import com.lue.dto.ResponseVideo;
import com.lue.util.ApiUrlResponse;
import com.lue.util.SqLiteConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lue
 */
public class PlayerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView webView;

    @FXML
    private TreeView treeView;

    TreeItem treeItem = new TreeItem();
    TreeItem treeItem1 = new TreeItem();
    TreeItem treeItem2 = new TreeItem();
    TreeItem treeItem3 = new TreeItem();
    TreeItem treeItem4 = new TreeItem();

    @FXML
    private Label home, order, module, help, logout, video;

    @FXML
    private FlowPane flowPane, flowPane1;
    private HBox hBox, hBox1;
    ImageView imageView;
    Image image;
    Button button;

    FXMLController fXMLController = new FXMLController();
    HomeController homeController = new HomeController();
    ApiUrlResponse apiUrlResponse = new ApiUrlResponse();

    List<ModuleVideo> moduleVideoList1 = new ArrayList<ModuleVideo>();
    List<String> moduleVideoUrl = new ArrayList<String>();
    List<String> moduleVideoId = new ArrayList<String>();
    List<String> imageFkId = new ArrayList<String>();
    List<String> videoImage = new ArrayList<String>();
    List<String> imageDesc = new ArrayList<String>();
    List<String> imageList = new ArrayList<String>();
    List<ImgDesc> imgDescsList1 = new ArrayList<ImgDesc>();
    List<String> currentImageDesc = new ArrayList<String>();
    List<String> currentVideoImage = new ArrayList<String>();
    List<String> currentVideoList1 = new ArrayList<String>();
    String videoId = "";
    int videoListSize = 0;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ButtonEffect();
        getPlayer();
        getVideoList();
        getImageList(homeController.currentImageDesc, homeController.currentVideoImage);
        videoListSize = homeController.currentVideoList.size();
        getVideoButton();
    }

    public void getPlayer() {
        String url = "http://wbaclientdesign4.com/mdyamics/doc/products/package/module/video/videos/" + homeController.currentVideoUrl;
        webView.getEngine().load(
                url
        );
    }

    public void getVideoButton() {
        flowPane1.getChildren().clear();
        flowPane1.setPadding(new Insets(5.0D, 0.0D, 0.0D, 0.0D));
        flowPane1.setHgap(15.0D);
        for (int i = 0; i < videoListSize; i++) {
            final int index = i;
            String s = String.valueOf(i + 1);
            button = new Button(s);
            button.setId(s);
            button.setPrefWidth(70.0D);
            button.setPrefHeight(40.0D);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
            hBox1 = new HBox();
            hBox1.setSpacing(6.0D);
            hBox1.getChildren().add(button);
            hBox1.setAlignment(Pos.CENTER);
            flowPane1.getChildren().add(hBox1);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handleButton(homeController.currentVideoList.get(index));
                }
            });
        }
    }
    
    private class MyEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt) {
           System.out.println("############"+((Control)evt.getSource()).getId());
           ((Control)evt.getSource()).setStyle("-fx-background-color: #db061f;");
        }
    }
    
    public void getTotalVideoList(String currentVideoUrl) throws SQLException {
        currentVideoList1 = new ArrayList<String>();
        currentVideoList1.clear();
        conn = SqLiteConnect.connect();
        String sql = "SELECT * FROM videos where VideoTitle=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, currentVideoUrl);
        resultSet = preparedStatement.executeQuery();
        String moduleTitle = "";
        while (resultSet.next()) {
            moduleTitle = resultSet.getString("ModuleTitle");
        }
        String sql1 = "SELECT VideoTitle FROM videos where ModuleTitle=?";
        preparedStatement = conn.prepareStatement(sql1);
        preparedStatement.setString(1, moduleTitle);
        resultSet1 = preparedStatement.executeQuery();
        while (resultSet1.next()) {
            currentVideoList1.add(resultSet1.getString("VideoTitle"));
        }
        videoListSize = currentVideoList1.size();
        getVideoButton();
    }

    public void handleButton(String name) {
        String url = "http://wbaclientdesign4.com/mdyamics/doc/products/package/module/video/videos/" + name;
        webView.getEngine().load(
                url
        );
    }

    public void getVideoList() {
        String api_url = "http://wbaclientdesign4.com/mdyamics/organization/GetVideoPackageDetail";
        String LicenceUserId = fXMLController.LicenceUserId;
        try {
            apiUrlResponse.videoApiUrl(api_url, LicenceUserId);
            Gson gson = new Gson();
            ResponseVideo responseVideo = gson.fromJson(apiUrlResponse.finalVideoResult, ResponseVideo.class);
            List<Info> infoList = responseVideo.getInfo();
            for (Info iList : infoList) {
                treeItem1 = new TreeItem(iList.getCategoryTitle());
                List<Packages> packageList = iList.getPackages();
                for (Packages pList : packageList) {
                    treeItem2 = new TreeItem(pList.getPackageName());
                    treeItem1.getChildren().add(treeItem2);
                    List<Modules> modulesesList = pList.getModules();
                    for (Modules mList : modulesesList) {
                        treeItem3 = new TreeItem(mList.getModuleTitle());
                        treeItem2.getChildren().add(treeItem3);
                        List<ModuleVideo> moduleVideoList = mList.getModuleVideo();
                        moduleVideoList1 = mList.getModuleVideo();
                        for (ModuleVideo mVList : moduleVideoList) {
                            treeItem4 = new TreeItem(mVList.getVideoUrl());
                            treeItem3.getChildren().add(treeItem4);
                            moduleVideoId.add(mVList.getProductVideoId());
                            moduleVideoUrl.add(mVList.getVideoUrl());
                            List<ImgDesc> imgDescsList = mVList.getImgDesc();
                            imgDescsList1 = mVList.getImgDesc();
                            for (ImgDesc iDList : imgDescsList) {
                                imageList.add(iDList.getVideoImage());
                                imageFkId.add(iDList.getFkProductVideoId());
                                videoImage.add(iDList.getVideoImage());
                                imageDesc.add(iDList.getVideoDescriptions());
                            }

                        }
                    }
                }
                treeView.setRoot(treeItem);
                treeItem.setExpanded(true);
                treeView.setShowRoot(false);
                treeItem.getChildren().add(treeItem1);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleTreeClicked(MouseEvent event) throws SQLException {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();
            if (name.contains(".mp4")) {
                getTotalVideoList(name);
                String url = "http://wbaclientdesign4.com/mdyamics/doc/products/package/module/video/videos/" + name;
                webView.getEngine().load(
                        url
                );
            }
            for (int index = 0; index < moduleVideoUrl.size(); index++) {
                if (name.equals(moduleVideoUrl.get(index))) {
                    videoId = moduleVideoId.get(index);
                }
            }
            getImageListById();
        }
    }

    public void getImageListById() {
        for (int index = 0; index < imageFkId.size(); index++) {
            if (videoId.equals(imageFkId.get(index))) {
                currentVideoImage.add(videoImage.get(index));
                currentImageDesc.add(imageDesc.get(index));
            }
        }
        getImageList(currentImageDesc, currentVideoImage);
    }

    public void getImageList(List<String> currentDesc, List<String> currentImage) {
        flowPane.getChildren().clear();

        for (int i = 0; i < currentDesc.size(); i++) {
            imageView = new ImageView();
            String urlImg = "http://wbaclientdesign4.com/mdyamics/doc/products/package/module/video/descs/" + currentImage.get(i);
            image = new Image(urlImg);
            imageView.setImage(image);
            imageView.setFitWidth(180.0D);
            imageView.setFitHeight(150.0D);
            Label label = new Label();
            label.setText(currentDesc.get(i));
            hBox = new HBox();

            hBox.setSpacing(6.0D);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(label);
            hBox.setAlignment(Pos.CENTER);
            flowPane.getChildren().add(hBox);

        }
        currentDesc.clear();
        currentImage.clear();
    }

    public void getVideo(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Library.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Movement Library Player");
        stage.setMaximized(true);
        ((Node) (event.getSource())).getScene().getWindow().hide();

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

        video.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                video.setCursor(Cursor.HAND);
            }
        });

        video.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                video.setEffect(null);
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
