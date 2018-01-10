/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lue.util;

import javafx.scene.control.Alert;

/**
 *
 * @author lue
 */
public class MessageHelper {
    
    
    public static void showMessage(Alert.AlertType alertType,String message){
         Alert alert = new Alert(alertType);
alert.setTitle("Movement Dynamica Information");
alert.setHeaderText(null);
alert.setContentText(message);

alert.showAndWait();
    }
    
}
