
package Objects;

/**
 *
 * @author Daniel
 */


import Logic.LinkedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TextFieldTreeCell;
//import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import minidatabase.FXMLDocumentController;
import minidatabase.Manager;

public class TreeOption extends TextFieldTreeCell<String> {

//Create ContextMenu
    private final ContextMenu contextMenuF = new ContextMenu();
    private final ContextMenu contextMenuP = new ContextMenu();
    private TextField textField;
    
    // Controlador y lista 
    private static  FXMLDocumentController ffpC;
    private static  LinkedList<LinkedList> list;
    
    public TreeOption(FXMLDocumentController ffPC , LinkedList<LinkedList> li) {
        TreeOption.ffpC = ffPC;
        TreeOption.list = li;
        MenuItem makeNewFolder = new MenuItem("make New Folder");
        MenuItem addNewPerson = new MenuItem("add New Person");
        makeNewFolder.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        addNewPerson.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        /**MenuItem MenuOption2 = new MenuItem("Update");
        MenuOption2.setAccelerator(KeyCombination.keyCombination("Ctrl+U"));

        SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();

        MenuItem MenuOption3 = new MenuItem("ShowAll_Items");
        MenuOption3.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        MenuItem Nuevo = new MenuItem("Add Object");
        Nuevo.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));

        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();

        Menu MenuEliminar = new Menu("delete");
        MenuItem childEliminarUno = new MenuItem("DeleteThis");
        
        MenuItem childEliminarTodos = new MenuItem("DeleteAll");
        childEliminarTodos.setAccelerator(KeyCombination.keyCombination("Shift+Delete"));
        MenuEliminar.getItems().addAll(childEliminarUno, childEliminarTodos);
*/
        makeNewFolder.setOnAction(new newFolder());
        addNewPerson.setOnAction(new newPerson());
        
        
        
        /**contextMenu.getItems().addAll(MenuOption1, MenuOption2, separatorMenuItem1,
                MenuOption3, Nuevo, separatorMenuItem2, MenuEliminar);
                */
        contextMenuF.getItems().add(makeNewFolder);
        contextMenuP.getItems().add(addNewPerson);
    } 

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                    setContextMenu(contextMenuP);
                }
            }
            if (!empty && getTreeItem().getParent() == null) {
            setContextMenu(contextMenuF);
        }

        }
    }


    private String getString() {
        return getItem() == null ? "" : getItem();
   }
    
    
    class newPerson implements EventHandler{
        
        public newPerson(){
            
        }
        @Override
        public void handle(Event event) {
            Manager m = new Manager();
            m.showPersonf(getTreeItem());
        }
        
    }
    class newFolder implements EventHandler{
        
        public newFolder(){
            
        }
        @Override
        public void handle(Event event) {
            TreeItem nuevo = new TreeItem<>("NewFolder");
            getTreeItem().getChildren().add(nuevo);
        }
        
    }
}







