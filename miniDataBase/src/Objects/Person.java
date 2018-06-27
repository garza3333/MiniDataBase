/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author dgarcia
 */
public class Person {
    
    private int age,dd,mm,aa;
    private String name,secondName,lastName,sex;
    
    //Constructor por defecto
    public Person(){
        this("","","",-1,0,0,0,"");
    }
    //Constructor personalizado
    public Person(String n , String secondN,String last  , int ag,
            int d ,int m  ,int a, String se){
        
        this.name=n; //nombre
        this.secondName = secondN; //segundo nombre
        this.lastName = last; //apellido
        this.age = ag; //edad
        this.dd = d; //day
        this.mm = m; //month
        this.aa = a; //age
        this.sex = se; // sexo
        
        
    }
    
    /**
     * Retorna la edad
     * @return age
     */
    public int getAge() {
        return age;
    }
    /**
     * Agrega la edad
     * @param age edad a añadir
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Retorna el numero del dia
     * @return int
     */
    public int getDd() {
        return dd;
    }
    /**
     * Agrega un int al atributo dd
     * @param dd numero a setear
     */
    public void setDd(int dd) {
        this.dd = dd;
    }
    /**
     * Obtiene el numero del mes
     * @return 
     */
    public int getMm() {
        return mm;
    }
    /**
     * Añade un numero al mes
     * @param mm numero
     */
    public void setMm(int mm) {
        this.mm = mm;
    }
    /**
     * Obtiene el numero del año
     * @return int
     */
    public int getAa() {
        return aa;
    }
    /**
     * Añade un numero al atributo age
     * @param aa numero a añadir
     */
    public void setAa(int aa) {
        this.aa = aa;
    }
    /**
     * Obtiene el nombre
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * Añade valor al atributo name
     * @param name nombre a agregar
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retorna el segundo nombre
     * @return String
     */
    public String getSecondName() {
        return secondName;
    }
    /**
     * Añade valor al atributo secondName
     * @param secondName nombre a añadir
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    /**
     * Retorna el Apellido
     * @return String
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Añade valor al atributo lastName
     * @param lastName apellido a añadir
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Obtiene el genero de la persona
     * @return sex
     */
    public String getSex() {
        return sex;
    }
    /**
     * Añade valor al atributo sex
     * @param sex genero male/female 
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    //Imprime la informacion de la persona
    public void showInfo(){
        System.out.println("Name: "+this.name+" "+
                           "Second: "+this.secondName+" "+
                           "Last: "+this.lastName+" "+
                           "Age: "+this.age+" "+
                           "DD: "+this.dd+" "+
                           "MM: "+this.mm+" "+
                           "AA: "+this.aa+" "+
                           "Genere: "+this.sex);
    }
    
    
    
}
