
package Objects;

/**
 *
 * @author Daniel
 */


import Logic.LinkedList;
import Logic.Node_T;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTreeCell;
//import javafx.scene.input.KeyCode;
import minidatabase.FXMLDocumentController;
import minidatabase.Manager;
import org.json.JSONException;

public class TreeOption extends TextFieldTreeCell<String> {

//Create ContextMenu
    private final ContextMenu contextMenuF = new ContextMenu();
    private final ContextMenu contextMenuP = new ContextMenu();
    private final ContextMenu contextMenuE = new ContextMenu();
    private final String root = "C:\\Users\\dgarcia\\Documents\\NetBeansProjects\\minDATAbase\\miniDataBase\\root\\";
    private TextField textField;
    
    // Controlador Principal de la aplicacion 
    private final   FXMLDocumentController fxmlDoc;
    //Lista principal que contiene nodos con listas enlazadas de tipo Person
    private final   LinkedList<LinkedList> listMAIN; 
    
    public TreeOption(FXMLDocumentController ffPC , LinkedList<LinkedList> li) {
        fxmlDoc = ffPC;
        listMAIN = li;
        /*
        Son los items que se mostraran al darle click derecho al arbol
        */
        MenuItem makeNewFolder = new MenuItem("Make New Folder");
        MenuItem delFolder = new MenuItem("Delete Folder");
        MenuItem addNewPerson = new MenuItem("Add New Person");
        MenuItem editPerson = new MenuItem("Edit");
        MenuItem delPerson = new MenuItem("Delete");
        
        //Aceleradores para los menus
//        makeNewFolder.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
//        addNewPerson.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

        /*
            Se le añade una clase a cada menu , cada clase representa la funcion 
            que se realizara al darle click a uno de los items del arbol
        */
        makeNewFolder.setOnAction(new newFolder(listMAIN));
        delFolder.setOnAction(new deletePerson(listMAIN,fxmlDoc));
        addNewPerson.setOnAction(new newPerson(listMAIN,fxmlDoc));
        editPerson.setOnAction(new editExistPerson(listMAIN, fxmlDoc));
        delFolder.setOnAction(new deleteFolder(listMAIN,root,fxmlDoc));
        
        //Añadiendo items a cada menu
        contextMenuF.getItems().addAll(makeNewFolder);
        contextMenuP.getItems().addAll(addNewPerson,delFolder);
        contextMenuE.getItems().addAll(editPerson,delPerson);
    } 

    /**
     * Este método se encarga de mostrar el menu (a la hora de dar click derecho)
     * correspondiente dependiendo de si el item del arbol es de 
     * folder(hijo de la ruta) o de  persona (hoja)
     * @param item
     * @param empty 
     */
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
                //Si es una hoja añade el menu de editar
                if (getTreeItem().isLeaf()) {
                    setContextMenu(contextMenuE);
                }
            }
            /* Esto añade el menu de nuevo folder a la ruta ya que esta siempre 
               siempre tendra el padre como nulo (no tiene padre)
            */
            if (!empty && getTreeItem().getParent() == null ) {
            setContextMenu(contextMenuF);
        }
            /* si tiene padre pero no es hoja entonces se le asigna un menu
               de creacion de personas , ademas se verifica que esté en la lista 
               de folders
            */
            if (!empty && getTreeItem().getParent() != null && this.verify(getTreeItem().getValue())) {
            setContextMenu(contextMenuP);
        }

        }
    }
    /**
     * Verifica si el nombre de un item del arbol se encuentra dentro de la lista
     * perincipal si es asi devuelver un valor booleano
     * @param s nombre del item
     * @return true si está en la lista sino false
     */
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

    //Verifica si el item es nulo entonces devuelve un string vacio / sino el nombre
    private String getString() {
        return getItem() == null ? "" : getItem();
   }
    
    
    /**
     * Clase que permite crear una nueva persona (se genera una nueva ventana
     * para poder ingresar los datos de la persona, luego se agrega a la lista
     * correspindiende dependiendo del nombre del folder)
     */
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
    /**
     * Clase que permite editar una persona(abre una ventana con los datos ya 
     * cargados de la persona listos para corregir y volver a guardar)
     */
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
    /**
     * Clase que permite Crear un nuveo folder (Se crea una ventana que solicita
     * el nombre del nuevo folder y este se agrega a la lista principal)
     */
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
    /**
     * Clase que busca dentro de todas las listas el nombre del item seleccionado
     * para asi poder eliminarlo de la lista principal y de la memoria fisica, tambien
     * del arbol de vista
     */
    class deletePerson implements EventHandler{
        private final LinkedList<LinkedList> mainL;

        private final FXMLDocumentController document;
        public deletePerson(LinkedList<LinkedList> l,FXMLDocumentController doc){
            this.mainL = l;
            this.document = doc;
        }
        @Override
        public void handle(Event event) {
                Node_T<LinkedList>  temp = this.mainL.getHead();
                while(temp!=null){
                    if(temp.getValue().getId().equals(getTreeItem().getValue())){
                        Node_T<Person> tempPerson = temp.getValue().getHead();
                        int cont =0;
                        while(tempPerson!=null){
                            if(tempPerson.getValue().getName().equals(getTreeItem().getChildren().get(cont).getValue())){
                                System.out.println("TREEITEM: "+getTreeItem().getChildren().get(cont).getValue());
                                getTreeItem().getChildren().remove(getTreeItem().getChildren().get(cont));
                                System.out.println("RemovedOFTree");
                                try {
                                    this.document.saveOnDisc();
                                    System.out.println("AllSaved");
                                } catch (IOException | JSONException ex) {
                                    Logger.getLogger(TreeOption.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            cont++;
                            tempPerson = tempPerson.getNext();
                    }
                    
                }
                temp = temp.getNext();
            }
        
        }
    }
     /**
     * Clase que busca dentro de todas las listas el nombre del item seleccionado
     * para asi poder eliminarlo de la lista principal y de la memoria fisica, tambien
     * del arbol de vista
     */
    class deleteFolder implements EventHandler{
        private final LinkedList<LinkedList> mainL;
        private final String r;
        private final FXMLDocumentController document;
        public deleteFolder( LinkedList<LinkedList> l, String root,FXMLDocumentController doc){
            this.mainL = l;
            this.r =root;
            this.document = doc;
        }
        @Override
        public void handle(Event event) {
            File f = new File(r+getTreeItem().getValue());
            
            if(f.exists()){
                String n = f.getName();

                    Node_T<LinkedList>  temp = this.mainL.getHead();
                    while(temp!=null){
                        if(temp.getValue().getId().equals(getTreeItem().getValue())){
                            this.mainL.delete(temp.getValue());
                            System.out.println("deletedOFList");
                        }
                        temp = temp.getNext();
                    }
                    getTreeItem().getParent().getChildren().remove(getTreeItem());
                    System.out.println("removedOFTree");
                try {
                    document.saveOnDisc();
                    System.out.println("AllSaved");
                    this.deleteDir(f);
                    System.out.println("FolderDeleted: "+n);
                } catch (IOException | JSONException ex) {
                    Logger.getLogger(TreeOption.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                
            }else{
                System.out.println("FileUnExists");
            }
            

        }
        /**
         * Método recursivo para elininar todos los archivos dentro de un 
         * directorio y finalmente deshacerse del directorio vacío
         * @param file 
         */
       public  void deleteDir(File file) {
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    if (! Files.isSymbolicLink(f.toPath())) {
                        deleteDir(f);
                    }
                }
            }
            file.delete();
        }
        
        }
    }








