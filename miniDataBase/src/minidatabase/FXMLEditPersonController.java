/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minidatabase;

import Logic.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLEditPersonController implements Initializable {
    
    private final String root = "C:\\Users\\Usuario\\Desktop\\Daniel programacion\\mBaseData\\miniDataBase\\root\\";
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnChange;
    @FXML
    private TextField entryN;
    @FXML
    private TextField entryS;
    @FXML
    private TextField entryL;
    @FXML
    private TextField entryA;
    @FXML
    private DatePicker entryB;
    @FXML
    private RadioButton radiobtnM;
    @FXML
    private RadioButton radiobtnF;
    
    
    private LinkedList<LinkedList> mainList;
    private FXMLDocumentController fxmlDoc;
    @FXML
    private TreeItem top;
    @FXML
    private Stage mainStage;
    
    public void setStagePrincipal(Stage stagePrincipal) {
        this.mainStage = stagePrincipal;
    }
    public void setTreeOption(TreeItem tp){
        this.top = tp;
    }
    public void setMainList(LinkedList<LinkedList> l){
        this.mainList = l;
    }
    public void setDocController(FXMLDocumentController fdc){
        this.fxmlDoc = fdc;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        try {
            System.out.println("hola");
            //        Person p;
//        Node_T<Person> temp;
//        Node_T<LinkedList> listPerson = this.fxmlDoc.getList().getHead();
//        while(listPerson != null){
//            if(listPerson.getValue().getId().equals(top.getParent().getValue().toString())){
//                temp = listPerson.getValue().getHead();
//
//                
//                while(temp!=null){
//                    if(temp.getValue().getName().equals(top.getValue().toString())){
//                        p = temp.getValue();
//                        this.entryN.setText(p.getName());
//                        this.entryS.setText(p.getSecondName());
//                        this.entryL.setText(p.getLastName());
//                        this.entryA.setText(String.valueOf(p.getAge()));
//                        this.entryB.setValue(LocalDate.of(p.getAa(), p.getMm(),p.getDd()));
//                }
//                    temp = temp.getNext();
//        }
//            }
//            listPerson = listPerson.getNext();
//        }

//        BufferedReader r;
//            r = new BufferedReader(
//                    new FileReader(root+top.getParent().getValue().toString()));
//            
//        String lecture = r.readLine();
//        if(lecture!=null){
//            JSONObject json = new JSONObject(r);
//            JSONObject list = new JSONObject(json.getString("LIST"));
//            int index = top.getChildren().indexOf(top);
//            
//            JSONObject person = new JSONObject(list.getString("Person"+String.valueOf(index)));
//            this.entryN.setText(person.getString("NAME"));
//            this.entryS.setText(person.getString("SECOND"));
//            this.entryL.setText(person.getString("LAST"));
//            this.entryA.setText(person.getString("AGE"));
//            this.entryB.setValue(LocalDate.of(Integer.valueOf(person.getString("AA")),Integer.valueOf(person.getString("MM")),Integer.valueOf(person.getString("DD"))));
//            
//        }
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FXMLEditPersonController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException | JSONException ex) {
//            Logger.getLogger(FXMLEditPersonController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
        
        

        


    }    
    
}
