package Logic;
/**
 * Clase Node para almacenamiento de información
 * @author Daniel
 * @param <T> generico
 */
public class Node_T <T> { 
    private Node_T previous;
    private Node_T next;
    private T value;
    
    
    
    public Node_T(){
        this(null);
    }
    /**
     * 
     * @param value - es el valor a almacenar
     */
    public Node_T(T value){
        this.value = value;
        this.next = null;
        this.previous = null;
     
    }
    /**
     * 
     * @param value - edita el valor del Node
     */
    public void setValue(T value){
        this.value =  value;
    }
    /**
     * 
     * @return - retorna el valor dentro del nodo 
     */
    public T getValue(){
        return this.value;
    }
    /**
     * 
     * @return retorna la posicion del siguiente Node
     */
    public Node_T getNext(){
        return this.next;
    }
    public void setNext(Node_T node){
        this.next = node;
    }
    public void setPrevious(Node_T node){
        
        this.previous = node;
    }
    public Node_T getPrevious(){
    return this.previous;
    }

    
}
