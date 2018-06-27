/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minidatabase;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author dgarcia
 */
public class MiniDataBase extends Application {
    
    private Manager manager; //Administrador de ventanas 
    @Override
    public void start(Stage stage) throws Exception {
        
          this.manager = new Manager(stage);
          
          manager.showMain();
 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
