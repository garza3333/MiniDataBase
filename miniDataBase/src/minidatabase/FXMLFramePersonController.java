/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minidatabase;

import Logic.LinkedList;
import Logic.Node_T;
import Objects.Person;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.json.JSONException;

/**
 *
 * @author dgarcia
 */
public class FXMLFramePersonController implements Initializable {
    private final String root = "C:\\Users\\Usuario\\Desktop\\Daniel programacion\\mBaseData\\miniDataBase\\root\\";
    
    private LinkedList<LinkedList> mainList;
    private FXMLDocumentController fxmlDoc;
    @FXML
    private TreeItem top;
    @FXML
    private Stage mainStage;
    @FXML
    private Button btnAddPerson;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDefault;
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
        public void setMainList(LinkedList<LinkedList> l){
        this.mainList = l;
    }
        public void setDocController(FXMLDocumentController fdc){
            this.fxmlDoc = fdc;
        }
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Funcion para cancelar el agregadi
        btnCancel.setOnAction((ActionEvent ActionEvent) -> {
               mainStage.close();
        });
        //Funcion para añadir a una nueva persona
        
        btnAddPerson.setOnAction((ActionEvent ActionEvent) -> {
            //Validacion de espacios vacios
            if (entryName.getText().isEmpty() || entrySecond.getText().isEmpty()
                    || entryLast.getText().isEmpty() || entryAge.getText().isEmpty()
                    || entryBirthday.getValue() == null) {
                FXMLFramePersonController.infoBox("Si te faltan datos porfavor rellenalos con 'None'", "Error de entrada", "Error al ingresar valores");
            } else {
                String[] array = FXMLFramePersonController.this.entryBirthday.getValue().toString().split("-");
                for(String s : array){
                    System.out.println(s);
                }       String result;
                if (FXMLFramePersonController.this.radiobtnMale.isSelected()) {
                    result = "Male";
                } else if (FXMLFramePersonController.this.radiobtnFemale.isSelected()) {
                    result = "Female";
                } else {
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
                Node_T<LinkedList> lTemp = FXMLFramePersonController.this.mainList.getHead();
                while(lTemp!=null){
                    if(lTemp.getValue().getId().equals(top.getValue().toString())){
                        lTemp.getValue().add(p);
                    }
                    lTemp = lTemp.getNext();
                }       p.showInfo();
                top.getChildren().add(new TreeItem(entryName.getText()));
                System.out.println(top.getValue().toString());
                Node_T<LinkedList> lTempp = FXMLFramePersonController.this.mainList.getHead();
                while(lTempp!=null){
                    if(lTempp.getValue().getId().equals(top.getValue().toString())){
                        lTempp.getValue().see();
                        System.out.println(lTempp.getValue().getId());
                    }
                    lTempp = lTempp.getNext();
                }
                try {
                    FXMLFramePersonController.this.fxmlDoc.saveOnDisc();
                }catch (IOException | JSONException ex) {
                    Logger.getLogger(FXMLFramePersonController.class.getName()).log(Level.SEVERE, null, ex);
                }   mainStage.close();
            }
        });
        
        this.btnDefault.setOnAction((ActionEvent EventHandler) -> {
            
            FXMLFramePersonController.this.entrySecond.setText("None");
            FXMLFramePersonController.this.entryLast.setText("None");
            FXMLFramePersonController.this.entryAge.setText("0");
            FXMLFramePersonController.this.entryBirthday.setValue(LocalDate.now());
        });
        
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