
package minidatabase;

import Logic.LinkedList;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author dgarcia
 */
public class Manager {
    private final Stage stage;
    private final Stage second;
    private final Stage terc;
    private final Stage cuar;
    
    private FXMLFramePersonController cp;
    private FXMLnewFolderWindowController nfC;
    private FXMLEditPersonController fxmlEditPC;
    
   
    
        public Manager() {
        this.stage = new Stage();
        this.second = new Stage();
        this.terc = new Stage();
        this.cuar = new Stage();
        
    }
    public Manager(Stage stage){
        this.stage = stage;
        this.terc = new Stage();
        this.cuar = new Stage();
        this.second = new Stage();
    }


    public void showMain() throws IOException{
        System.out.println("aqui");
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();        
    }
    public void showPersonf(TreeItem tOp, LinkedList<LinkedList> l,FXMLDocumentController fxmlDoc){
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFramePerson.fxml"));
            AnchorPane ruta = (AnchorPane) loader.load();
            
            
            cp=(FXMLFramePersonController) loader.getController();
            cp.setTreeOption(tOp);
            cp.setStagePrincipal(second);
            cp.setMainList(l);
            cp.setDocController(fxmlDoc);
            Scene scene = new Scene(ruta);
            second.setScene(scene);
            second.setResizable(false);                                            ///////// BLOQUEAR TAMAÑO
            second.initModality(Modality.APPLICATION_MODAL);                            ///////// Ventana tipo moda (no deja usar principal hasta no cerrar la secundaria

            second.show();

        } catch (IOException e) {
        }
    }
    public void showFolderN(TreeItem tOp ,LinkedList<LinkedList> l){
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLnewFolderWindow.fxml"));
            AnchorPane ruta = (AnchorPane) loader.load();
            
           
            nfC=(FXMLnewFolderWindowController) loader.getController();
            nfC.setTreeOption(tOp);
            nfC.setStagePrincipal(terc);
            nfC.setMainList(l);
            Scene scene = new Scene(ruta);
            terc.setScene(scene);
            terc.setResizable(false);                                            ///////// BLOQUEAR TAMAÑO
            terc.initModality(Modality.APPLICATION_MODAL);                            ///////// Ventana tipo moda (no deja usar principal hasta no cerrar la secundaria

            terc.show();

        } catch (IOException e) {
        }
    }
    public void showPersonEdit(FXMLDocumentController epc ,TreeItem top,LinkedList<LinkedList> l){
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEditPerson.fxml"));
            AnchorPane ruta = (AnchorPane) loader.load();
            
           
            FXMLEditPersonController f = loader.getController();
           
            f.setDocController(epc);
            f.setTreeOption(top);
            f.setStagePrincipal(cuar);
            f.setMainList(l);
            Scene scene = new Scene(ruta);
            cuar.setScene(scene);
            cuar.setResizable(false);                                            ///////// BLOQUEAR TAMAÑO
            cuar.initModality(Modality.APPLICATION_MODAL);                            ///////// Ventana tipo moda (no deja usar principal hasta no cerrar la secundaria
            
            cuar.show();

        } catch (IOException e) {
        }
    }
    

    public FXMLFramePersonController getCp() {
        return cp;
    }
    
    

    public Stage getStage() {
        return stage;
    }

    public Stage getSecond() {
        return second;
    }
    
    
    
}
