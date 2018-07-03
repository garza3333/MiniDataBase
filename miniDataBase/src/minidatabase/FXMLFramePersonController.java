
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
    private final String root = "C:\\Users\\dgarcia\\Documents\\NetBeansProjects\\minDATAbase\\miniDataBase\\root\\";
    
    private LinkedList<LinkedList> mainList; //Lista de informacion
    private FXMLDocumentController fxmlDoc; //Controlador principal
    @FXML
    private TreeItem top; //Árbol de vista
    @FXML
    private Stage mainStage; //ventana actual
    @FXML
    private Button btnAddPerson; //Botón de añadir
    @FXML
    private Button btnCancel; // Botón para cancelar
    @FXML
    private Button btnDefault; //Botón de default
    @FXML
    private TextField entryName; // Entrada del nombre
    @FXML
    private TextField entrySecond; // Entrada del segundo nombre
    @FXML
    private TextField entryLast; // Entrada del apellido
    @FXML
    private TextField entryAge; // Entrada de la edad
    @FXML
    private DatePicker entryBirthday; // Entrada de la fecha de cumpleaños
    @FXML
    private RadioButton radiobtnMale; // Botón de sexo masculino
    @FXML
    private RadioButton radiobtnFemale; // Botón de sexo femenino
    
    /**
     * Setea la ventana principal para luego poder cerrarla
     * @param stagePrincipal ventana actual
     */
    public void setStagePrincipal(Stage stagePrincipal) {
        this.mainStage = stagePrincipal;
    }
    /**
     * Setea el árbol de vista para poder actualizarlo
     * @param tp árbol de vista del primer controlador
     */
    public void setTreeOption(TreeItem tp){
        this.top = tp;
    }
    /**
     * Obtiene el texto en la entrada del nombre
     * @return 
     */
    public String getEntryName(){
        return this.entryName.getText();
    }
    /**
     * Setea la lista principal del controlador principal para añadir la informacion
     * a una sola lista
     * @param l 
     */
        public void setMainList(LinkedList<LinkedList> l){
        this.mainList = l;
    }
        /**
         * Setea el controlador para poder utilizar los metodos de guardado en disco
         * @param fdc 
         */
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
            //Validacion de espacios vacios(si estan vacios muestra un mensaje de error)
            if (entryName.getText().isEmpty() || entrySecond.getText().isEmpty()
                    || entryLast.getText().isEmpty() || entryAge.getText().isEmpty()
                    || entryBirthday.getValue() == null) {
                FXMLFramePersonController.infoBox("Si te faltan datos porfavor rellenalos con 'None'", "Error de entrada", "Error al ingresar valores");
            } else {
                // Añadiendo los datos de la entrada de fecha a un array para luego
                // poder separar cada número en día, mes y año
                String[] array = FXMLFramePersonController.this.entryBirthday.getValue().toString().split("-");
                for(String s : array){
                    System.out.println(s);
                }       String result;
                //Verificando el sexo elegido
                if (FXMLFramePersonController.this.radiobtnMale.isSelected()) {
                    result = "Male";
                } else if (FXMLFramePersonController.this.radiobtnFemale.isSelected()) {
                    result = "Female";
                }else{
                    result = "None";
                }
                //Creando un nuevo objeto persona con los datos de los entradas
                Person p = new Person(entryName.getText(),
                        entrySecond.getText(),
                        entryLast.getText(),
                        Integer.valueOf(entryAge.getText()),
                        Integer.valueOf(array[2]),
                        Integer.valueOf(array[1]),
                        Integer.valueOf(array[0]),
                        result);
                
                // Buscando nodo con el nombre de la carpeta en la cual añadir la
                // informacion de la nueva persona
                Node_T<LinkedList> lTemp = FXMLFramePersonController.this.mainList.getHead();
                while(lTemp!=null){
                    if(lTemp.getValue().getId().equals(top.getValue().toString())){
                        lTemp.getValue().add(p);
                    }
                    lTemp = lTemp.getNext();
                }       
                p.showInfo(); // se imprimen los datos de la persona en consola
                
                //se añade un item visual en el arbol
                top.getChildren().add(new TreeItem(entryName.getText()));
              
                //Guardando datos en memoria física
                try {
                    FXMLFramePersonController.this.fxmlDoc.saveOnDisc();
                }catch (IOException | JSONException ex) {
                    Logger.getLogger(FXMLFramePersonController.class.getName()).log(Level.SEVERE, null, ex);
                }   mainStage.close();
            }
        });
        
        //Asigna valores por default en las entradas de la ventana de creacion
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