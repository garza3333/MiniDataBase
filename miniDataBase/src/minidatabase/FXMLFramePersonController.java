/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minidatabase;

import Objects.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

/**
 *
 * @author dgarcia
 */
public class FXMLFramePersonController implements Initializable {
    private final String root = "C:\\Users\\dgarcia\\Documents\\NetBeansProjects\\PersonData\\root\\";
    
    
    @FXML
    private TreeItem top;
    @FXML
    private Stage mainStage;
    @FXML
    private Button btnAddPerson;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField entryName;
    @FXML
    private TextField entrySecond;
    @FXML
    private TextField entryLast;
    @FXML
    private TextField entryAge;
    @FXML
    private DatePicker entryBirthday;
    @FXML
    private RadioButton radiobtnMale;
    @FXML
    private RadioButton radiobtnFemale;
    
    
    public void setStagePrincipal(Stage stagePrincipal) {
        this.mainStage = stagePrincipal;
    }
    public void setTreeOption(TreeItem tp){
        this.top = tp;
    }
    public String getEntryName(){
        return this.entryName.getText();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Funcion para cancelar el agregadi
        btnCancel.setOnAction((ActionEvent ActionEvent) -> {
               mainStage.close();
        });
        //Funcion para añadir a una nueva persona
        
        btnAddPerson.setOnAction((ActionEvent)->{
            
            //Validacion de espacios vacios
            if(entryName.getText().isEmpty() || entrySecond.getText().isEmpty()
                    || entryLast.getText().isEmpty() || entryAge.getText().isEmpty()
                    || entryBirthday.getValue() == null){
                this.infoBox("Si te faltan datos porfavor rellenalos con 'NONE'", "Error de entrada", "Error al ingresar valores");}
            // Validacion de int en vez de String
//            }else if(!entryName.getText().toLowerCase().matches("[a-z]")||!entrySecond.getText().toLowerCase().matches("[a-z]")
//                    || !entryLast.getText().toLowerCase().matches("[a-z]")){
//                this.infoBox("Ingresaste un valor númerico donde no debe ir", "Error de entrada", "Error al ingresar valores");
//                
//            // Validacion de String en vez de int
//            }else if(entryAge.getText().toLowerCase().matches("[a-z]")){
//                this.infoBox("Ingresaste una palabra donde deberia ir un valor númerico", "Error de entrada", "Error al ingresar valores");
//            }
            //Creacion de nuevo objeto persona
            else{
            String[] array = this.entryBirthday.getValue().toString().split("-");
            for(String s : array){
                System.out.println(s);
            }
            String result;
            if(this.radiobtnMale.isSelected()){
                result = "Male";
            }else if(this.radiobtnFemale.isSelected()){
                result = "Female";
            }else{
                result = "None";
            }
            Person p = new Person(entryName.getText(),
                                  entrySecond.getText(),
                                  entryLast.getText(),
                                  Integer.valueOf(entryAge.getText()),
                                  Integer.valueOf(array[2]),
                                  Integer.valueOf(array[1]),
                                  Integer.valueOf(array[0]),
                                  result);
            
            p.showInfo();
            top.getChildren().add(new TreeItem(entryName.getText()));
            System.out.println(top.getValue().toString());
            mainStage.close();

            
            }});
        
    }   
    
    /// Creador de mensajes de error
    
        public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    
    
}