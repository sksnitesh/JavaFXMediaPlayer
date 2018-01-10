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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author lue
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label home, order, module, help, logout;

    @FXML
    private Button downloadBtn, continueBtn;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TreeView treeView;

    TreeItem treeItem = new TreeItem();
    TreeItem treeItem1 = new TreeItem();
    TreeItem treeItem2 = new TreeItem();
    TreeItem treeItem3 = new TreeItem();
    TreeItem treeItem4 = new TreeItem();

    DropShadow shadow = new DropShadow();
    ApiUrlResponse apiUrlResponse = new ApiUrlResponse();
    FXMLController fXMLController = new FXMLController();
    public static String currentVideoUrl = "";
    List<ModuleVideo> moduleVideoList1 = new ArrayList<ModuleVideo>();
    List<String> moduleVideoUrl = new ArrayList<String>();
    List<String> moduleVideoId = new ArrayList<String>();
    List<String> imageFkId = new ArrayList<String>();
    List<String> videoImage = new ArrayList<String>();
    List<String> imageDesc = new ArrayList<String>();
    List<String> imageList = new ArrayList<String>();
    List<String> videoDesc = new ArrayList<String>();
    static List<String> currentVideoList = new ArrayList<String>();
    public static List<String> currentImageDesc = new ArrayList<String>();
    public static List<String> currentVideoImage = new ArrayList<String>();
    List<ImgDesc> imgDescsList1 = new ArrayList<ImgDesc>();
    String videoId = "";
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    String moduleTitle = "";
    String categoryTitle = "";
    String packageName = "";
    boolean checked = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            currentVideoList = new ArrayList<String>();
            currentVideoList.clear();
            continueBtn.setDisable(true);
            downloadBtn.setDisable(true);
            ButtonEffect();
            getVideoList();
            progressBar.setVisible(false);
            progressBar.setProgress(0.0);
            //downloadSelectedVideo(stage);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getVideoList() throws SQLException {
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
                            //treeItem4 = new TreeItem(mVList.getVideoUrl());
                            String treeItems4 = mVList.getVideoUrl();
                            CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>(treeItems4);
                            rootItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                                System.out.println(rootItem.getValue() + " selection state: " + newVal);
                                checked = newVal;
                                if (checked == true) {
                                    downloadBtn.setDisable(false);
                                } else {
                                    downloadBtn.setDisable(true);
                                }
//                                continueBtn.setDisable(false);
                                currentVideoUrl = rootItem.getValue();
                            });
                            treeView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
                                @Override
                                public TreeCell<String> call(TreeView<String> param) {
                                    return new CheckBoxTreeCell<String>() {
                                        @Override
                                        public void updateItem(String item, boolean empty) {
                                            super.updateItem(item, empty);
                                            if (empty) {
                                                setGraphic(null);
                                                setText(null);
                                            } else if (!(getTreeItem() instanceof CheckBoxTreeItem)) {
                                                setGraphic(null);
                                            }
                                        }
                                    };
                                }
                            });
                            treeItem3.getChildren().add(rootItem);
                            //treeItem3.getChildren().add(treeItem4);
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
            currentVideoUrl = name;
            getTotalVideoList(currentVideoUrl);
            if (currentVideoUrl.contains(".mp4")) {
                continueBtn.setDisable(false);
                getDetailsDB(currentVideoUrl);
            }
            for (int index = 0; index < moduleVideoUrl.size(); index++) {
                if (name.equals(moduleVideoUrl.get(index))) {
                    videoId = moduleVideoId.get(index);
                }
            }
            getImageListById();
        }
    }

    public void getDetailsDB(String videoTitle) throws SQLException {
        conn = SqLiteConnect.connect();
        String sql = "SELECT * FROM videos where VideoTitle=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, videoTitle);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            moduleTitle = resultSet.getString("ModuleTitle");
            categoryTitle = resultSet.getString("CategoryTitle");
            packageName = resultSet.getString("PackageName");

        }

    }

    public void getTotalVideoList(String currentVideoUrl) throws SQLException {
        conn = SqLiteConnect.connect();
        String sql = "SELECT * FROM videos where VideoTitle=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, currentVideoUrl);
        resultSet = preparedStatement.executeQuery();
        String moduleTitle = "";
        while (resultSet.next()) {
            moduleTitle = resultSet.getString("ModuleTitle");
        }
        String sql1 = "SELECT DISTINCT VideoTitle FROM videos where ModuleTitle=?";
        preparedStatement = conn.prepareStatement(sql1);
        preparedStatement.setString(1, moduleTitle);
        resultSet1 = preparedStatement.executeQuery();
        while (resultSet1.next()) {
            currentVideoList.add(resultSet1.getString("VideoTitle"));
        }
    }

    public void getImageListById() {
        for (int index = 0; index < imageFkId.size(); index++) {
            if (videoId.equals(imageFkId.get(index))) {
                currentVideoImage.add(videoImage.get(index));
                currentImageDesc.add(imageDesc.get(index));
            }
        }
    }

    public void downloadSelectedVideo() {
        progressBar.setVisible(true);
        Thread th = new Thread(new bg_Thread());
        th.start();
        File currDir = new File(System.getProperty("user.home") + "\\VideoLibrary");
        currDir.mkdir();
        String fromFile = "http://wbaclientdesign4.com/mdyamics/doc/products/package/module/video/videos/" + currentVideoUrl;
        String toFile = currDir + "\\" + currentVideoUrl;
        try {
            //connectionTimeout, readTimeout = 10 seconds
            FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
        } catch (IOException e) {
            e.printStackTrace();

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

    class bg_Thread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    progressBar.setProgress(i / 100.0);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void ContinueBtn(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Player.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Movement Library Player");
        stage.setMaximized(true);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void ButtonEffect() {

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

        continueBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                continueBtn.setEffect(shadow);
                continueBtn.setCursor(Cursor.HAND);
            }
        });

        continueBtn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                continueBtn.setEffect(null);
            }
        });
        downloadBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                downloadBtn.setEffect(shadow);
                downloadBtn.setCursor(Cursor.HAND);
            }
        });

        downloadBtn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                downloadBtn.setEffect(null);
            }
        });

    }

}
