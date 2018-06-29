/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minidatabase;

import Logic.LinkedList;
import Logic.Node_T;
import Objects.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import Objects.TreeOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author dgarcia
 */
public class FXMLDocumentController implements Initializable {
    private final String root = "C:\\Users\\Usuario\\Desktop\\Daniel programacion\\mBaseData\\miniDataBase\\root\\";
    
    //Manager
    private Manager manager;
    
    //ComboBox lists
    ObservableList<Integer> listAge = FXCollections.observableArrayList(-1,1,2,3
    ,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19
    ,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34
    ,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49
    ,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64
    ,65,66,67,68,69,70,71,72,37,74,75,76,77,78,79
    ,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,
    95,96,97,98,99,100);
    ObservableList<String> listSex = FXCollections.observableArrayList("Male","Female","None");
    
    //Lista enlazada de Folders
    private LinkedList<LinkedList> folderList;
    
    
    //ComboBox Objects (Filres)
    
    @FXML
    private ComboBox cboxSex;
    @FXML
    private ComboBox cboxAge;
    @FXML
    private TreeView<String> treeView;
    private TreeItem<String> troot;
    //Buttons

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Iniciando lista
        this.folderList = new LinkedList<>();
        this.folderList.setId("root");
        // Creando Manager para manejo de ventanas
        this.manager = new Manager();

        //Inicializando los combobox
        cboxAge.setItems(listAge);
        cboxSex.setItems(listSex);
        
        //Inicializando el TreeView
        troot = new TreeItem<>("root");

        treeView.setRoot(troot);
        
        //Imprimiendo valor actual
        treeView.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
                        if(newValue != null && !"root".equals(newValue.getValue())){
//                            System.out.println(newValue.getValue());
                        }
        });
        
        
        //Imprimiendo si estripo click derecho encima del treeview
        treeView.setOnMouseClicked((MouseEvent event) -> {
            MouseButton button = event.getButton();
            
            if(button == MouseButton.SECONDARY){
//                System.out.println("Right click pressed in treeview");
            }
        
        });
        
         
         
        try {
            this.init();
        } catch (IOException | JSONException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        treeView.setCellFactory(new CallbackImpl(this,this.folderList)); // Set Right Click options
         

    }

        //Functions 

    /**
     *
     * @param e
     */


    private void init() throws IOException, JSONException, JSONException{
        
            BufferedReader r = new BufferedReader(
                               new FileReader(root+"folderList.txt"));
             
             String ussers = r.readLine();
             
             if(ussers != null){
                    String b []= ussers.split("@");       
                    for (String b1 : b) {
                        BufferedReader reader = new BufferedReader(new FileReader(root + b1 + "/personList.txt"));
                        JSONObject json = new JSONObject(reader.readLine());
                        int cont = json.getInt("CONT");
                        JSONObject listPerson = new JSONObject(json.getString("LIST"));
                        LinkedList<Person> listI = new LinkedList();
                        listI.setId(b1);
                        for(int i = 0; i<cont ; i++){
                            JSONObject jpersonI = new JSONObject(listPerson.getString("Person"+i));
                            Person personI = new Person(jpersonI.getString("NAME"),
                                                        jpersonI.getString("SECOND"),
                                                        jpersonI.getString("LAST"),
                                                        jpersonI.getInt("AGE"),
                                                        jpersonI.getInt("DD"),
                                                        jpersonI.getInt("MM"),
                                                        jpersonI.getInt("AA"),
                                                        jpersonI.getString("GEN"));
                            
                            listI.add(personI);
                        }
                        
                        this.folderList.add(listI);
                        TreeItem<String> t = new TreeItem(b1);
                       
                        this.troot.getChildren().add(t);
                        Node_T<Person> temp= listI.getHead();
                        if(temp!=null){
                            while(temp!=null){
                                TreeItem<String> t1 = new TreeItem(temp.getValue().getName());
                                t.getChildren().add(t1);
                                temp = temp.getNext();
                            }
                        }
                        this.treeView.setCellFactory(new CallbackImpl(this,this.folderList));
                    }
                    

}}
    public void saveOnDisc() throws IOException, JSONException{
        Object[] result;
        result = this.troot.getChildren().toArray();
        
        
        
        String r = "";
        for(int i =0 ; i<result.length;i++){
            r += this.troot.getChildren().get(i).getValue()+"@";
        }
        
           
        try (PrintWriter print = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(
                                new File(root+"folderList.txt"))))) {
            print.write(r);
        }
        
        
        
        String b []= r.split("@");
        for(int i =0;i<b.length;i++){
            
                JSONObject finalJson = new JSONObject();
                File folder = new File(root+b[i]);
                folder.mkdir();
                        
                try (BufferedWriter bw = new BufferedWriter(
                                         new FileWriter(
                                         new File(root+b[i]+"/personList.txt")))){
                            
                    LinkedList<Person> uu = (LinkedList)this.folderList.findI(i);
                    
                    JSONObject jPersonList = new JSONObject();
                    int cont =0;
                    Node_T<Person> temp = uu.getHead();
                    while(temp!=null){
                        JSONObject jPerson = new JSONObject();
                        jPerson.put("NAME",temp.getValue().getName());
                        jPerson.put("SECOND",temp.getValue().getSecondName());
                        jPerson.put("LAST",temp.getValue().getLastName());
                        jPerson.put("AGE",temp.getValue().getAge());
                        jPerson.put("DD",temp.getValue().getDd());
                        jPerson.put("MM",temp.getValue().getMm());
                        jPerson.put("AA",temp.getValue().getAa());                        
                        jPerson.put("GEN",temp.getValue().getSex());     
                        jPersonList.put("Person"+cont,jPerson.toString());
                        cont++;
                        temp = temp.getNext();
                        
                    }
                    finalJson.put("CONT",cont);
                    finalJson.put("LIST",jPersonList.toString());
                    
                    bw.write(finalJson.toString());
                    bw.close();
                                            

    }}}
 
    public LinkedList<LinkedList> getList(){
        return this.folderList;
    }
    
    private static class CallbackImpl implements Callback<TreeView<String>, TreeCell<String>> {
        private LinkedList<LinkedList> list;
        private FXMLDocumentController ffpc;
        public CallbackImpl() {
        }
        public CallbackImpl(FXMLDocumentController pc ,LinkedList<LinkedList> ll){
            this.list = ll;
            this.ffpc =pc;
        }

        @Override
        public TreeCell<String> call(TreeView<String> arg0) {
            return new TreeOption(this.ffpc,this.list);
        }
    }
    
    
}
