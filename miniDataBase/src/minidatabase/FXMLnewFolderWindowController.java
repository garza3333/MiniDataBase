
package minidatabase;

import Logic.LinkedList;
import Objects.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLnewFolderWindowController implements Initializable {
    private LinkedList<LinkedList> mainList;
    @FXML
    private TreeItem top;
    @FXML
    private Stage Currentstage;
    @FXML
    private Button btnMake;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField entryFolderName;
    
    public void setStagePrincipal(Stage stagePrincipal) {
        this.Currentstage = stagePrincipal;
    }
    public void setTreeOption(TreeItem tp){
        this.top = tp;
    }
    public String getEntryName(){
        return this.entryFolderName.getText();
    }   
    public void setMainList(LinkedList<LinkedList> l){
        this.mainList = l;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.btnCancel.setOnAction((EventHandler)->{
            this.Currentstage.close();
        });
        
        
        this.btnMake.setOnAction((EventHandler) -> {
            if(!entryFolderName.getText().isEmpty()){
            this.top.getChildren().add(new TreeItem(this.entryFolderName.getText()));
            this.Currentstage.close();
            LinkedList<Person> newListPerson = new LinkedList<>();
            newListPerson.setId(entryFolderName.getText());
            this.mainList.add(newListPerson);
            
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error de entrada");
                 alert.setHeaderText("No ingresó valor");
                 alert.setContentText("Ingrese algún nombre para su folder");
                 alert.showAndWait(); 
                 

                    }
            
        });
    }    

}
