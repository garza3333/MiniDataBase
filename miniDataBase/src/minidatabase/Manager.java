/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minidatabase;

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
    private FXMLFramePersonController cp;
    
        public Manager() {
        this.stage = new Stage();
        this.second = new Stage();
    }
    public Manager(Stage stage){
        this.stage = stage;
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
    public void showPersonf(TreeItem tOp){
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFramePerson.fxml"));
            AnchorPane ruta = (AnchorPane) loader.load();
            
           
            cp=(FXMLFramePersonController) loader.getController();
            cp.setTreeOption(tOp);
            cp.setStagePrincipal(second);
            Scene scene = new Scene(ruta);
            second.setScene(scene);
            second.setResizable(false);                                            ///////// BLOQUEAR TAMAÑO
            second.initModality(Modality.APPLICATION_MODAL);                            ///////// Ventana tipo moda (no deja usar principal hasta no cerrar la secundaria

            second.show();

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
