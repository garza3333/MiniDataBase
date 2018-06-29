
package Objects;

/**
 *
 * @author Daniel
 */


import Logic.LinkedList;
import Logic.Node_T;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTreeCell;
//import javafx.scene.input.KeyCode;
import minidatabase.FXMLDocumentController;
import minidatabase.Manager;

public class TreeOption extends TextFieldTreeCell<String> {

//Create ContextMenu
    private final ContextMenu contextMenuF = new ContextMenu();
    private final ContextMenu contextMenuP = new ContextMenu();
    private final ContextMenu contextMenuE = new ContextMenu();
    private TextField textField;
    
    // Controlador y lista 
    private   FXMLDocumentController fxmlDoc;
    private   LinkedList<LinkedList> listMAIN;
    
    public TreeOption(FXMLDocumentController ffPC , LinkedList<LinkedList> li) {
        fxmlDoc = ffPC;
        listMAIN = li;
        MenuItem makeNewFolder = new MenuItem("Make New Folder");
        MenuItem delFolder = new MenuItem("Delete Folder");
        MenuItem addNewPerson = new MenuItem("Add New Person");
        MenuItem editPerson = new MenuItem("Edit");
        MenuItem delPerson = new MenuItem("Delete");
//        makeNewFolder.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
//        addNewPerson.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
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
        makeNewFolder.setOnAction(new newFolder(listMAIN));
        delFolder.setOnAction(new deleteFolder());
        addNewPerson.setOnAction(new newPerson(listMAIN,fxmlDoc));
        editPerson.setOnAction(new editExistPerson(listMAIN, fxmlDoc));
        
        
        
        
        /**contextMenu.getItems().addAll(MenuOption1, MenuOption2, separatorMenuItem1,
                MenuOption3, Nuevo, separatorMenuItem2, MenuEliminar);
                */
        contextMenuF.getItems().addAll(makeNewFolder);
        contextMenuP.getItems().addAll(addNewPerson,delFolder);
        contextMenuE.getItems().addAll(editPerson,delPerson);
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
                if (getTreeItem().isLeaf()) {
                    setContextMenu(contextMenuE);
                }
            }
            if (!empty && getTreeItem().getParent() == null ) {
            setContextMenu(contextMenuF);
        }
            if (!empty && getTreeItem().getParent() != null && this.verify(getTreeItem().getValue())) {
            setContextMenu(contextMenuP);
        }

        }
    }
    private boolean verify(String s){
        Node_T<LinkedList> temp = listMAIN.getHead();
        while(temp!=null){
            if(temp.getValue().getId().equals(s)){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }


    private String getString() {
        return getItem() == null ? "" : getItem();
   }
    
    
    class newPerson implements EventHandler{
        private final LinkedList<LinkedList> mainL;
        FXMLDocumentController fxmlDoc;
        public newPerson(LinkedList<LinkedList> l,FXMLDocumentController fxmlDo){
            this.mainL = l;
            this.fxmlDoc =fxmlDo;
        }
        @Override
        public void handle(Event event) {
            Manager m = new Manager();
            m.showPersonf(getTreeItem(),this.mainL,this.fxmlDoc);
        }
        
    }
    class editExistPerson implements EventHandler{
        private final  LinkedList<LinkedList> lmain;
        private final FXMLDocumentController fxmlDo;
        public editExistPerson(LinkedList<LinkedList> l,FXMLDocumentController fxmlDo){
            this.lmain = l;
            this.fxmlDo =fxmlDo;
        }
        @Override
        public void handle(Event event) {
            Manager m = new Manager();
            m.showPersonEdit(this.fxmlDo,getTreeItem(),this.lmain);
        }
        
    }
    class newFolder implements EventHandler{
        private final LinkedList<LinkedList> mainL;
        public newFolder(LinkedList<LinkedList> l){
            this.mainL = l;
        }
        @Override
        public void handle(Event event) {
            Manager mm = new Manager();
            mm.showFolderN(getTreeItem(),this.mainL);
            
        }
        
    }
    
    class deleteFolder implements EventHandler{
        
        public deleteFolder(){
            
        }
        @Override
        public void handle(Event event) {
            System.out.println("delete");

        }
        
    }
}







