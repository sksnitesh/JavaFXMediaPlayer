package com.lue.movementdynamics;

import com.google.gson.Gson;
import com.lue.dto.ImgDesc;
import com.lue.dto.Info;
import com.lue.dto.ModuleVideo;
import com.lue.dto.Modules;
import com.lue.dto.Packages;
import com.lue.dto.Response;
import com.lue.dto.ResponseVideo;
import com.lue.util.ApiUrlResponse;
import com.lue.util.MessageHelper;
import com.lue.util.SqLiteConnect;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable {

    @FXML
    private TextField register, licence;

    @FXML
    private Button okBtn, clearBtn;

    @FXML
    private CheckBox remember, auto;

    @FXML
    private Label validLabel;

    File file = new File(System.getProperty("user.home") + "/userCredential.txt");
    DropShadow shadow = new DropShadow();
    String RegisteredBy = "";
    String LicenceKey = "";
    ApiUrlResponse apiUrlResponse = new ApiUrlResponse();
    public String LicenceUserId = "";

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSet1 = null;
    final int N_SECS = 20;

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            UPDATE();
            ButtonEffect();
            deleteDbTable(); // remove db table record. .
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private HBox createLayout(Task task) {
        HBox layout = new HBox(10);

        layout.getChildren().setAll(
                createProgressIndicator(task)
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        return layout;
    }

    private Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            public Void call() {
                for (int i = 0; i < N_SECS; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    // uncomment updateProgress call if you want to show progress
                    // rather than let progress remain indeterminate.
                    // updateProgress(i, N_SECS);
                    updateMessage((N_SECS - i) + "");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        return null;
                    }
                }

                updateMessage(0 + "");
                updateProgress(N_SECS, N_SECS);

                return null;
            }
        };
    }

    private ProgressIndicator createProgressIndicator(Task task) {
        ProgressIndicator progress = new ProgressIndicator();

        progress.progressProperty().bind(task.progressProperty());

        return progress;
    }

    public void doLogin(MouseEvent event) throws IOException, SQLException {

        Stage stage = new Stage();

        stage.initStyle(StageStyle.UNDECORATED);
        Task task = createTask();

        stage.setScene(
                new Scene(
                        createLayout(
                                task
                        )
                )
        );
        stage.show();

        new Thread(task).start();

        Runnable expensiveTask = () -> {
            try {

                String apiUrl = "http://wbaclientdesign4.com/mdyamics/organization/AppLogin";
                RegisteredBy = register.getText();
                LicenceKey = licence.getText();
                System.out.println(RegisteredBy);
                System.out.println(LicenceKey);
                String regexEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                boolean resultEmail = Pattern.matches(regexEmail, RegisteredBy);
                //System.out.println("Email valid :" + resultEmail);

                if (resultEmail == true) {
                    System.out.println("Email valid :" + resultEmail);
                    if (LicenceKey.length() == 16) {
                        System.out.println("LicenceKey is valid");

                        apiUrlResponse.loginApiUrl(apiUrl, RegisteredBy, LicenceKey);

                        Gson gson = new Gson();
                        Response response = gson.fromJson(apiUrlResponse.finalResult, Response.class);
                        LicenceUserId = response.getInfo().getLicenceUserId();
                        String message = response.getMessage();
                        if (message.equals("Successfully Login")) {
                            Platform.runLater(() -> {
                                try {

                                    getVideoList();
                                    stage.close();
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
                                    Parent root = (Parent) fxmlLoader.load();
                                    Stage stage1 = new Stage();
                                    stage1.setScene(new Scene(root));
                                    stage1.show();
                                    stage1.setTitle("Movement Library Player");
                                    stage1.setMaximized(true);
                                    ((Node) (event.getSource())).getScene().getWindow().hide();

                                    if (remember.isSelected()) {
                                        SAVE();
                                    }
                                } catch (Exception ex) {
                                    MessageHelper.showMessage(AlertType.ERROR, ex.getMessage());
                                }
                            });
                        } else {
                            System.out.println("Invalid credentials");
                        }

                    } else {
                        Platform.runLater(() -> {
                            stage.close();
                            validLabel.setText("Enter Licence Key not Matched");
                            System.out.println("Enter Licence Key not Matched");
                        });
                    }
                } else {
                    Platform.runLater(() -> {
                        stage.close();
                        validLabel.setText("Entered Email ID not in Correct Format");
                        System.out.println("Entered Email ID not in Correct Format");
                    });
                }

            } catch (Exception ex) {
                stage.close();
                ex.printStackTrace();
                MessageHelper.showMessage(AlertType.ERROR, ex.getMessage());
            }
        };

        // start new thread for expensiveTask
        new Thread(expensiveTask).start();

    }

    public void SAVE() {      //Save the UserName and Password (for one user)
        try {
            if (!file.exists()) {
                file.createNewFile();  //if the file !exist create a new one
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(register.getText()); //write the name
            bw.newLine(); //leave a new Line
            bw.write(licence.getText()); //write the password
            bw.close(); //close the BufferdWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End Of Save

    public void UPDATE() { //UPDATE ON OPENING THE APPLICATION
        try {
            if (file.exists()) {    //if this file exists
                Scanner scan = new Scanner(file);   //Use Scanner to read the File
                register.setText(scan.nextLine());  //append the text to name field

                licence.setText(scan.nextLine()); //append the text to password field
                scan.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//End OF UPDATE

    public void doClear() {
        register.setText("");
        licence.setText("");
        register.requestFocus();
    }

    public void getVideoList() throws SQLException {
        String api_url = "http://wbaclientdesign4.com/mdyamics/organization/GetVideoPackageDetail";
        try {
            apiUrlResponse.videoApiUrl(api_url, LicenceUserId);
            Gson gson = new Gson();
            ResponseVideo responseVideo = gson.fromJson(apiUrlResponse.finalVideoResult, ResponseVideo.class);
            List<Info> infoList = responseVideo.getInfo();
            for (Info iList : infoList) {
                String CategoryTitle = iList.getCategoryTitle();
                List<Packages> packageList = iList.getPackages();
                for (Packages pList : packageList) {
                    String PackageName = pList.getPackageName();
                    List<Modules> modulesesList = pList.getModules();
                    for (Modules mList : modulesesList) {
                        String ModuleTitle = mList.getModuleTitle();
                        List<ModuleVideo> moduleVideoList = mList.getModuleVideo();
                        for (ModuleVideo mVList : moduleVideoList) {
                            String VideoTitle = mVList.getVideoUrl();
                            String VideoDescriptions = mVList.getVideoTitle();
                            List<ImgDesc> imgDescsList = mVList.getImgDesc();
                            for (ImgDesc iDList : imgDescsList) {
                                String ImageList = iDList.getVideoImage();
                                String ImageDesc = iDList.getVideoDescriptions();
                                String ImageFkId = iDList.getFkProductVideoId();
                                Platform.runLater(() -> {
                                    try {
                                        saveVideoToDb(CategoryTitle, PackageName, ModuleTitle, VideoTitle, VideoDescriptions, ImageList, ImageDesc, ImageFkId); // ..........video save in db!
                                        //db insert
                                    } catch (SQLException ex) {
                                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDbTable() throws SQLException {
        conn = SqLiteConnect.connect();
        String sql = "delete FROM videos";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
    }

    public void saveVideoToDb(String CategoryTitle, String PackageName, String ModuleTitle, String VideoTitle, String VideoDescriptions, String ImageList, String ImageDesc, String ImageFkId) throws SQLException {
        conn = SqLiteConnect.connect();
        String sql = "insert into videos(CategoryTitle,PackageName,ModuleTitle,VideoTitle,VideoDescriptions,ImageList,ImageDesc,ImageFkId) values(?,?,?,?,?,?,?,?)";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, CategoryTitle);
        preparedStatement.setString(2, PackageName);
        preparedStatement.setString(3, ModuleTitle);
        preparedStatement.setString(4, VideoTitle);
        preparedStatement.setString(5, VideoDescriptions);
        preparedStatement.setString(6, ImageList);
        preparedStatement.setString(7, ImageDesc);
        preparedStatement.setString(8, ImageFkId);
        preparedStatement.executeUpdate();
    }

    public void ButtonEffect() {
        okBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                okBtn.setEffect(shadow);
                okBtn.setCursor(Cursor.HAND);
            }
        });

        okBtn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                okBtn.setEffect(null);
            }
        });
        clearBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                clearBtn.setEffect(shadow);
                clearBtn.setCursor(Cursor.HAND);
            }
        });

        clearBtn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                clearBtn.setEffect(null);
            }
        });
        auto.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                auto.setCursor(Cursor.HAND);
            }
        });

        auto.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                auto.setEffect(null);
            }
        });
        remember.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                remember.setCursor(Cursor.HAND);
            }
        });

        remember.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                remember.setEffect(null);
            }
        });

    }

}
