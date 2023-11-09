package modelo;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Giacomo Salvador
 */
public class Operador {
    private int numVariables;
    private String funcionInfija;
    private String expresionPrefija;
    private ArrayList<Character> listaVariables;
    
    
    public Operador(JTextArea areaOperacion, JLabel label){
        this.funcionInfija = label.getText();
        this.expresionPrefija = "";
        this.listaVariables = new ArrayList();
    }
    
    //------------------------ Operadores Lógicos -------------------------
    
    public boolean and(boolean var1, boolean var2){
        return var1 && var2;
    }
    
    public boolean or(boolean var1, boolean var2){
        return var1 || var2;
    }
            
    public boolean nand(boolean var1, boolean var2){
        return !(var1 && var2);
    } 
    
    public boolean nor(boolean var1, boolean var2){
        return !(var1 || var2);
    } 
    
    public boolean xor(boolean var1, boolean var2){
        return (var1 && !var2) || (var2 && !var1);
    } 
    
    public boolean xnor(boolean var1, boolean var2){
        return !((var1 && !var2) || (var2 && !var1));
    }
    
    public boolean implica(boolean var1, boolean var2){
        return (!var1 || var2);
    }
    
    //------------------------------------- Métodos --------------------------------
    
    public int binario(boolean valorDeVerdad){
        if(valorDeVerdad == true){
            return 1;
            
        }else{
            return 0;
        }
    }
    
    //Pasos para resolver:
    public boolean generarPrefija(){
        if(verificarAgrupacion(funcionInfija) == true){
            Stack<Character> pila = new Stack();
                        
            expresionPrefija = "";
            String expresionInvertida = "";            
            
            //Invertir funcion
            for(int i = funcionInfija.length()-1; i>=0; i--){
                expresionInvertida += ""+funcionInfija.charAt(i);
            }
            funcionInfija = expresionInvertida;
            expresionInvertida = "";
            
            //Recorrer el string de la expresion invertida:
            for(int i = 0; i < funcionInfija.length(); i++){
                char simbolo = funcionInfija.charAt(i);
                
                if (!esOperador(simbolo)) {
                    expresionPrefija += simbolo;
                    
                } else {
                                  
                    if (simbolo == ')' || simbolo == ']') {
                        pila.push(simbolo);
                        
                        
                    } else if (simbolo == '(' || simbolo == '[') {
                        while (!pila.isEmpty() && ((pila.peek() != ')' && pila.peek() != ']'))) {
                            char elemento = pila.pop();
                            expresionPrefija += elemento;
                            
                        }
                        
                        if (!pila.isEmpty() && ((pila.peek() == ')' || pila.peek() == ']'))) {
                            pila.pop(); // Elimina el ')' o ']'
                            
                        }
                        
                    } else {
                        if(!pila.isEmpty()){
                            if(precedencia(simbolo, pila.peek())){
                                pila.push(simbolo);

                            }else{
                                char elemento = pila.pop(); 
                                expresionPrefija += elemento;
                                pila.push(simbolo);
                                
                            }  
                            
                        }else{
                            pila.push(simbolo);
                        }
                    }

                }
            }
            
            while (!pila.isEmpty()) {
                char elemento = pila.pop();
                expresionPrefija += elemento;
            }            
            
            for(int i = expresionPrefija.length()-1; i>=0; i--){
                expresionInvertida += ""+expresionPrefija.charAt(i);
            }
            expresionPrefija = expresionInvertida; 
            
            JOptionPane.showMessageDialog(null,"Expresion Prefija:"+ expresionPrefija);
            
            //Regresar Funcion Infija a la normalidad    
            expresionInvertida = "";
            for(int i = funcionInfija.length()-1; i>=0; i--){
                expresionInvertida += ""+funcionInfija.charAt(i);
            }
            funcionInfija = expresionInvertida;
            
            return true;
               
        }      
        return false;
    }
    
    //Para escalabilidad, aumentar si se desea agregar más de 4 variables:
    public int contarVariables(){
        boolean contA = false, contB = false, contC = false, contD = false, contE = false, contF = false;
        numVariables = 0;
        
        for(int i=0; i<expresionPrefija.length(); i++){
            switch(expresionPrefija.charAt(i)){
                case'a' -> {
                    contA = true;
                }
                case 'b' -> {
                    contB = true;
                }
                case 'c' -> {
                    contC = true;
                }
                case 'd' -> {
                    contD = true;
                } 
                
                case 'e' -> {
                    contE = true;
                }
                
                case 'f' -> {
                    contF = true;
                }
            }            
        }
        
        if(contA){
            numVariables++;
        }
        
        if(contB){
            numVariables++;
        }
        
        if(contC){
            numVariables++;
        }
        
        if(contD){
            numVariables++;
        }
        
        if(contE){
            numVariables++;
        }
        
        if(contF){
            numVariables++;
        }
        
        return numVariables;
    }   
    
    //------------------------ Imprimir Tabla de Verdad -------------------------
    
    public void resolverPorCaso(JTextArea areaTexto, JTextArea lblVariable, JLabel lblFuncion){
        
        for(int i = 0; i<6; i++){  
            listaVariables.add('M');
        }
        
        
        switch(numVariables){
            case 1:{
                try{                    
                    boolean var1 = false;
                    agregarVariables(expresionPrefija);
                    lblFuncion.setText("Tabla Logica: "+funcionInfija);
                    lblVariable.setText(""+listaVariables.get(0)+"\tR");
                    
                    for(int i = 0; i<2; i++){  
                        areaTexto.append(""+binario(var1)+"\t"+binario(resolverUnaVariable(var1))+"\n");
                        var1 = !var1;
                    }
                    
                    
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "¡Error! Revise la sintaxis.");    
                }
                break;
            }
            
            case 2:{                
                try{
                    boolean var1 = false;
                    boolean var2 = false;

                    agregarVariables(expresionPrefija);
                    lblFuncion.setText("Tabla Logica: "+funcionInfija);
                    lblVariable.setText(""+listaVariables.get(0)+"\t"+listaVariables.get(1)+"\tR");

                    for(int i = 0; i<2; i++){ 
                        for(int j = 0; j<2; j++){

                            areaTexto.append(""+binario(var1)+"\t"+binario(var2)+"\t"
                                    +binario(resolverDosVariables(var1, var2))
                                    +"\n");
                            var2 = !var2;
                        }
                        var1= !var1;  

                    }
                    
                    break;
                }catch (Exception e){  
                }
            }
            
            case 3:{
                try{
                    boolean var1 = false;
                    boolean var2 = false;
                    boolean var3 = false;

                    agregarVariables(expresionPrefija);
                    lblFuncion.setText("Tabla Logica: "+funcionInfija);
                    lblVariable.setText(""+listaVariables.get(0)+"\t"+listaVariables.get(1)+"\t"+listaVariables.get(2)+"\tR");
                    
                    for(int k = 0; k<2; k++){ 
                        for(int i = 0; i<2; i++){ 
                            for(int j = 0; j<2; j++){

                                areaTexto.append(""
                                        +binario(var1)+"\t"+binario(var2)+"\t"+binario(var3)
                                        +"\t"+binario(resolverTresVariables(var1, var2, var3))+"\n");
                                var3 = !var3; 
                            }
                            var2 = !var2; 
                        }
                        var1 = !var1; 
                    }
                    
                    break; 
                }catch (Exception e){ 
                    JOptionPane.showMessageDialog(null, "¡Error! Revise la sintaxis.");
                }
            }
            
            case 4:{
                try{
                    
                    boolean var1 = false;
                    boolean var2 = false;
                    boolean var3 = false;
                    boolean var4 = false;

                    agregarVariables(expresionPrefija);
                    lblFuncion.setText("Tabla Logica: "+funcionInfija);
                    lblVariable.setText(""+listaVariables.get(0)+"\t"+listaVariables.get(1)+"\t"+listaVariables.get(2)+
                            "\t"+listaVariables.get(3)+"\tR");
                    
                    for(int l = 0; l<2; l++){ 
                        for(int k = 0; k<2; k++){ 
                            for(int i = 0; i<2; i++){ 
                                for(int j = 0; j<2; j++){

                                    areaTexto.append(""
                                        +binario(var1)+"\t"+binario(var2)+"\t"+binario(var3)+
                                        "\t"+binario(var4)+"\t"+binario(resolverCuatroVariables(var1,var2,var3,var4))+
                                        "\n");

                                    var4 = !var4; 
                                }
                                var3 = !var3;   
                            }
                            var2 = !var2;   
                        }
                        var1 = !var1;    
                    }
                                                
                }catch (Exception e){                    
                    JOptionPane.showMessageDialog(null, "¡Error! Revise la sintaxis.");
                }
                break;
                
            }  
            
            case 5:{
                try{
                    
                    boolean var1 = false;
                    boolean var2 = false;
                    boolean var3 = false;
                    boolean var4 = false;
                    boolean var5 = false;

                    agregarVariables(expresionPrefija);
                    lblFuncion.setText("Tabla Logica: "+funcionInfija);
                    lblVariable.setText(""+listaVariables.get(0)+"\t"+listaVariables.get(1)+"\t"+listaVariables.get(2)+
                            "\t"+listaVariables.get(3)+"\t"+listaVariables.get(4)+"\tR");
                    
                    for(int l = 0; l<2; l++){ 
                        for(int k = 0; k<2; k++){ 
                            for(int i = 0; i<2; i++){ 
                                for(int j = 0; j<2; j++){
                                    for(int m = 0; m<2; m++){
                                        areaTexto.append(""
                                            +binario(var1)+"\t"+binario(var2)+"\t"+binario(var3)+
                                            "\t"+binario(var4)+"\t"+binario(var5)+"\t"+binario(resolverCincoVaribles(var1,var2,var3,var4, var5))+
                                            "\n");

                                        var5 = !var5; 
                                    }
                                    var4 = !var4; 
                                }
                                var3 = !var3;   
                            }
                            var2 = !var2;   
                        }
                        var1 = !var1;    
                    }
                    
                }catch (Exception e){                    
                    JOptionPane.showMessageDialog(null, "¡Error! Revise la sintaxis.");
                }
                break;
                
            }  
            
            case 6:{
                try{
                    boolean var1 = false;
                    boolean var2 = false;
                    boolean var3 = false;
                    boolean var4 = false;
                    boolean var5 = false;
                    boolean var6 = false;
                    
                    agregarVariables(expresionPrefija);
                    lblFuncion.setText("Tabla Logica: "+funcionInfija);
                    lblVariable.setText(""+listaVariables.get(0)+"\t"+listaVariables.get(1)+"\t"+listaVariables.get(2)+
                            "\t"+listaVariables.get(3)+"\t"+listaVariables.get(4)+"\t"+listaVariables.get(5)+"\tR");

                    for(int l = 0; l<2; l++){ 
                        for(int k = 0; k<2; k++){ 
                            for(int i = 0; i<2; i++){ 
                                for(int j = 0; j<2; j++){
                                    for(int m = 0; m<2; m++){
                                        for(int n = 0; n<2; n++){
                                            areaTexto.append(""
                                                +binario(var1)+"\t"+binario(var2)+"\t"+binario(var3)+
                                                "\t"+binario(var4)+"\t"+binario(var5)+"\t"+binario(var6)+"\t"+
                                                    binario(resolverSeisVaribles(var1,var2,var3,var4, var5, var6))+
                                                "\n");
                                            var6 = !var6; 

                                        }
                                        var5 = !var5; 
                                    }
                                    var4 = !var4; 
                                }
                                var3 = !var3;   
                            }
                            var2 = !var2;   
                        }
                        var1 = !var1;    
                    }
                    
                    
                }catch (Exception e){                    
                    JOptionPane.showMessageDialog(null, "¡Error! Revise la sintaxis.");
                }
                break;
                
            }  
            
        }
        
        reiniciarComponentes();
        
    }    
    
    //Resolver para 1 varaible:
    public boolean resolverUnaVariable(boolean var1) {
        Stack<Boolean> solucion = new Stack();

        for (int i = expresionPrefija.length() - 1; i>=0; i--) {
            char simbolo = expresionPrefija.charAt(i);
            
            if(!esOperador(simbolo)){
                listaVariables.set(0, simbolo);
                solucion.push(var1);
            
            } else if(simbolo == '¬'){
                boolean operand1 = solucion.pop();
                boolean respuesta = operar(simbolo, operand1, operand1);
                solucion.push(respuesta);
                
            }else{
                try{
                    boolean operand2 = solucion.pop();
                    boolean operand1 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand2);
                    solucion.push(respuesta);
                    
                }catch (Exception e){
                }
            }
            
        }
        return solucion.pop();
    }
    
    //Resolver para 2 varaibles:
    public boolean resolverDosVariables(boolean var1, boolean var2) {
        Stack<Boolean> solucion = new Stack();
        char op1 ='q', op2='q';
        
        //Asignar una letra segun el orden
        for (int i = 0; i < expresionPrefija.length() - 1; i++) {
            char simbolo = expresionPrefija.charAt(i);
            if(!esOperador(simbolo)){
                if(op1 == 'q' || op1 == simbolo){
                    op1 = simbolo;
                
                }else if(op2 == 'q' || op2 == simbolo){
                    op2 = simbolo;
                    
                }
            }
        }
        
        //Resolver la expresion usando pilas
        for (int i = expresionPrefija.length() - 1; i>=0; i--) {
            char simbolo = expresionPrefija.charAt(i);
            
            if(!esOperador(simbolo)){
                if(op2 == 'q'){
                    op2 = simbolo;
                    solucion.push(var2);
                    
                }else if(op2 == simbolo){
                    solucion.push(var2);
                    
                }else if(op1 == 'q'){
                    op1 = simbolo;
                    solucion.push(var1);
                    
                }else if(op1 == simbolo){
                    solucion.push(var1);
                    
                }
                
            
            } else if(simbolo == '¬'){
                try{
                    boolean operand1 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand1);
                    solucion.push(respuesta);
                }catch (Exception e){   
                }
            }else{
                try{
                    boolean operand1 = solucion.pop();
                    boolean operand2 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand2);
                    solucion.push(respuesta);
                }catch (Exception e){
                }
                
            }
            
        }
        return solucion.pop();
    }
    
    //Resolver para 3 varaibles:
    public boolean resolverTresVariables(boolean var1, boolean var2, boolean var3) {
        Stack<Boolean> solucion = new Stack();
        char op1 ='q', op2='q', op3 = 'q';
        
        //Asignar una letra segun el orden
        for (int i = 0; i < expresionPrefija.length() - 1; i++) {
            char simbolo = expresionPrefija.charAt(i);
            if(!esOperador(simbolo)){
                if(op1 == 'q' || op1 == simbolo){
                    op1 = simbolo;
                
                }else if(op2 == 'q' || op2 == simbolo){
                    op2 = simbolo;
                    
                }else if(op3 == 'q' || op3 == simbolo){
                    op3 = simbolo;
                    
                }
            }
        }
        
        for (int i = expresionPrefija.length() - 1; i>=0; i--) {
            char simbolo = expresionPrefija.charAt(i);
            
            if(!esOperador(simbolo)){
                if(op3 == 'q'){
                    op3 = simbolo;
                    solucion.push(var3);
                    
                }else if(op3 == simbolo){
                    solucion.push(var3);
                    
                }else if(op2 == 'q'){
                    op2 = simbolo;
                    solucion.push(var2);
               
                }else if(op2 == simbolo){
                    solucion.push(var2);
               
                }else if(op1 == 'q'){
                    op1 = simbolo;
                    solucion.push(var1);
                  
                }else if(op1 == simbolo){
                    solucion.push(var1);
                    
                }                
                
            } else if(simbolo == '¬'){
                try{
                    boolean operand1 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand1);
                    solucion.push(respuesta);
                }catch (Exception e){    
                }
            }else{
                try{
                    boolean operand1 = solucion.pop();
                    boolean operand2 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand2);
                    solucion.push(respuesta);
                }catch (Exception e){
                }
            }
            
        }
        return solucion.pop();
    }
    
    //Resolver para 4 varaibles:
    public boolean resolverCuatroVariables(boolean var1, boolean var2, boolean var3, boolean var4) {
        Stack<Boolean> solucion = new Stack();
        char op1 ='q', op2='q', op3 = 'q', op4 = 'q';
        
        
        //Asignar una letra segun el orden
        for (int i = 0; i < expresionPrefija.length() - 1; i++) {
            char simbolo = expresionPrefija.charAt(i);
            if(!esOperador(simbolo)){
                if(op1 == 'q' || op1 == simbolo){
                    op1 = simbolo;
                
                }else if(op2 == 'q' || op2 == simbolo){
                    op2 = simbolo;
                    
                }else if(op3 == 'q' || op3 == simbolo){
                    op3 = simbolo;
                    
                }else if(op4 == 'q' || op4 == simbolo){
                    op4 = simbolo;
                    
                }
            }
        }
        
        for (int i = expresionPrefija.length() - 1; i>=0; i--) {
            char simbolo = expresionPrefija.charAt(i);
            
            if(!esOperador(simbolo)){
                if(op4 == 'q'){
                    op4 = simbolo;
                    solucion.push(var4);
                    
                }else if(op4 == simbolo){
                    solucion.push(var4);
                    
                }else if(op3 == 'q'){
                    op3 = simbolo;
                    solucion.push(var3);
                    
                }else if(op3 == simbolo){
                    solucion.push(var3);
                    
                }else if(op2 == 'q'){
                    op2 = simbolo;
                    solucion.push(var2);
                    
                }else if(op2 == simbolo){
                    solucion.push(var2);
                    
                }else if(op1 == 'q'){
                    op1 = simbolo;
                    solucion.push(var2);
                    
                }else if(op1 == simbolo){
                    solucion.push(var1);
                    
                }               
                            
            } else if(simbolo == '¬'){
                try{
                    boolean operand1 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand1);
                    solucion.push(respuesta);
                }catch (Exception e){
                }
            }else{
                try{
                    boolean operand1 = solucion.pop();
                    boolean operand2 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand2);
                    solucion.push(respuesta);
                    
                }catch (Exception e){
                }
            }
            
        }
        return solucion.pop();
    }
     
    //Resolver para 5 varaibles:
    public boolean resolverCincoVaribles(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5) {
        Stack<Boolean> solucion = new Stack();
        char op1 ='q', op2='q', op3 = 'q', op4 = 'q', op5 ='q';
        
        
        //Asignar una letra segun el orden
        for (int i = 0; i < expresionPrefija.length() - 1; i++) {
            char simbolo = expresionPrefija.charAt(i);
            if(!esOperador(simbolo)){
                if(op1 == 'q' || op1 == simbolo){
                    op1 = simbolo;
                
                }else if(op2 == 'q'|| op2 == simbolo){
                    op2 = simbolo;
                    
                }else if(op3 == 'q' || op3 == simbolo){
                    op3 = simbolo;
                    
                }else if(op4 == 'q' || op4 == simbolo){
                    op4 = simbolo;
                    
                }else if(op5 == 'q' || op5 == simbolo){
                    op5 = simbolo;
                    
                }
            }
        }
        
        for (int i = expresionPrefija.length() - 1; i>=0; i--) {
            char simbolo = expresionPrefija.charAt(i);
            
            if(!esOperador(simbolo)){
                if(op5 == 'q'){
                    op5 = simbolo;
                    solucion.push(var5);
                    
                }else if(op5 == simbolo){
                    solucion.push(var5);
                    
                }else if(op4 == 'q'){
                    op4 = simbolo;
                    solucion.push(var4);
                    
                }else if(op4 == simbolo){
                    solucion.push(var4);
                    
                }else if(op3 == 'q'){
                    op3 = simbolo;
                    solucion.push(var3);
                    
                }else if(op3 == simbolo){
                    solucion.push(var3);
                    
                }else if(op2 == 'q'){
                    op2 = simbolo;
                    solucion.push(var2);
                    
                }else if(op2 == simbolo){
                    solucion.push(var2);
                    
                }else if(op1 == 'q'){
                    op1 = simbolo;
                    solucion.push(var2);
                    
                }else if(op1 == simbolo){
                    solucion.push(var1);
                    
                }               
                            
            } else if(simbolo == '¬'){
                try{
                    boolean operand1 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand1);
                    solucion.push(respuesta);
                    
                }catch (Exception e){
                }
            }else{
                try{
                    boolean operand1 = solucion.pop();
                    boolean operand2 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand2);
                    solucion.push(respuesta);
                    
                }catch (Exception e){
                }
            }
            
        }
        return solucion.pop();
    }
    
    //Resolver para 6 varaibles:
    public boolean resolverSeisVaribles(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
        Stack<Boolean> solucion = new Stack();
        char op1 ='q', op2='q', op3 = 'q', op4 = 'q', op5 ='q', op6='q';
        
        
        //Asignar una letra segun el orden
        for (int i = 0; i < expresionPrefija.length() - 1; i++) {
            char simbolo = expresionPrefija.charAt(i);
            if(!esOperador(simbolo)){
                if(op1 == 'q' || op1 == simbolo){
                    op1 = simbolo;
                
                }else if(op2 == 'q' || op2 == simbolo){
                    op2 = simbolo;
                    
                }else if(op3 == 'q' || op3 == simbolo){
                    op3 = simbolo;
                    
                }else if(op4 == 'q' || op4 == simbolo){
                    op4 = simbolo;
                    
                }else if(op5 == 'q' || op5 == simbolo){
                    op5 = simbolo;
                    
                }else if(op6 == 'q' || op6 == simbolo){
                    op6 = simbolo;
                    
                }
            }
        }
        
        for (int i = expresionPrefija.length() - 1; i>=0; i--) {
            char simbolo = expresionPrefija.charAt(i);
            
            if(!esOperador(simbolo)){
                if(op6 == 'q'){
                    op6 = simbolo;
                    solucion.push(var6);
                    
                }else if(op6 == simbolo){
                    solucion.push(var6);
                    
                }else if(op5 == 'q'){
                    op5 = simbolo;
                    solucion.push(var5);
                    
                }else if(op5 == simbolo){
                    solucion.push(var5);
                    
                }else if(op4 == 'q'){
                    op4 = simbolo;
                    solucion.push(var4);
                    
                }else if(op4 == simbolo){
                    solucion.push(var4);
                    
                }else if(op3 == 'q'){
                    op3 = simbolo;
                    solucion.push(var3);
                    
                }else if(op3 == simbolo){
                    solucion.push(var3);
                    
                }else if(op2 == 'q'){
                    op2 = simbolo;
                    solucion.push(var2);
                    
                }else if(op2 == simbolo){
                    solucion.push(var2);
                    
                }else if(op1 == 'q'){
                    op1 = simbolo;
                    solucion.push(var2);
                    
                }else if(op1 == simbolo){
                    solucion.push(var1);
                    
                }               
                            
            } else if(simbolo == '¬'){
                try{
                    boolean operand1 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand1);
                    solucion.push(respuesta);
                }catch (Exception e){
                }
            }else{
                try{
                    boolean operand1 = solucion.pop();
                    boolean operand2 = solucion.pop();
                    boolean respuesta = operar(simbolo, operand1, operand2);
                    solucion.push(respuesta);
                    
                }catch (Exception e){
                }
            }
            
        }
        return solucion.pop();
    }
      
    
    public boolean operar(char operador, boolean valor1, boolean valor2){   
        
        switch (operador) {
            case '¬' -> {
                return !valor1;
            }
            case '∧' -> {
                return and(valor1, valor2);
            }
            case '∨' -> {
                return or(valor1, valor2);
            }
            case '↑' ->{
                return nand(valor1, valor2);
            }
            case '↓' -> {
                return nor(valor1, valor2);
            }
            case '⊕' -> {
                return xor(valor1, valor2);
            }
            case '⊙' -> {
                return xnor(valor1, valor2);
            }
            case '→' -> {
                return implica(valor1, valor2);
            }
            default -> throw new IllegalArgumentException("Operador no válido: " + operador);                
        }
    }
    
    //---------------------------------------------------------------------------------
    
    
    public boolean esOperador(char letra) {
        boolean retorno = false;
        switch(letra){
            case '¬': //not
            case '∧': //and
            case '∨': //or
            case '↑': //nand
            case '↓': //nor
            case '⊕': //xor
            case '⊙': //xnor 
            case '→': //implica
            case '(':
            case ')':
            case '[':
            case ']': retorno = true;
            break; 
                
        }
        return retorno;
    } 
    
     public int getPrecedencia(char op) {
        switch (op) {
            case '¬': //not
                return 3;
            case '∧': //and
            case '∨': //or
                return 2;
            case '↑': //nand
            case '↓': //nor
            case '⊕': //xor
            case '⊙': //xnor
            case '→': //implica
                return 1;
            default:
                return 0;
        }
    }
    
    public boolean precedencia(char op, char op2){
        int prec1 = getPrecedencia(op);
        int prec2 = getPrecedencia(op2);
        
        return prec1 >= prec2;
    }
     
    public boolean verificarAgrupacion(String texto){
        int cont = 0;
        Stack<Character> parentesis = new Stack();
        Stack<Character> corchetes = new Stack();
        
        for(int i=0; i<texto.length(); i++){
            switch (texto.charAt(i)) {
                case '(':
                    parentesis.push('(');
                    cont++;
                    break;
                    
                case ')':
                    if(parentesis.isEmpty()){
                        break;
                    }else{
                        try{
                            parentesis.pop();
                        }catch (Exception e){
                        
                        }
                    }
                    cont--;
                    break;
                    
                case '[':
                    corchetes.push('[');
                    cont++;
                    break;
                    
                case ']':                    
                    if(corchetes.isEmpty()){
                        break;
                    }else{
                        try{
                            corchetes.pop();
                        }catch (Exception e){
                        
                        }
                    }
                    cont--;
                    break;
                    
                default:
                    break;
            }            
        }         
        return ((parentesis.isEmpty() == true) && (corchetes.isEmpty() == true) && (cont == 0));
    }
    
    
    public void reiniciarComponentes(){
        this.expresionPrefija = "";
        this.funcionInfija = "";
        this.numVariables = 0; 
        for(int i = 5; i>=0; i--){  
            listaVariables.remove(i);
        }
    }
    
    public void agregarVariables(String textoVar){
        for(int i=0; i<textoVar.length(); i++){
            char simbolo = textoVar.charAt(i);
                        
            if(!esOperador(simbolo)){
                if(listaVariables.get(0) == 'M' || listaVariables.get(0) == simbolo){
                    listaVariables.set(0, simbolo);
                
                }else if(listaVariables.get(1) == 'M' || listaVariables.get(1) == simbolo){
                    listaVariables.set(1, simbolo);
                    
                }else if(listaVariables.get(2) == 'M' || listaVariables.get(2) == simbolo){
                    listaVariables.set(2, simbolo);
                    
                }else if(listaVariables.get(3) == 'M' || listaVariables.get(3) == simbolo){
                    listaVariables.set(3, simbolo);
                    
                }else if(listaVariables.get(4) == 'M' || listaVariables.get(4) == simbolo){
                    listaVariables.set(4, simbolo);
                    
                }else if(listaVariables.get(5) == 'M' || listaVariables.get(5) == simbolo){
                    listaVariables.set(5, simbolo);
                    
                }
            }
        }
    
    }
            
    
    public int getNumVariables() {
        return numVariables;
    }

    public void setNumVariables(int numVariables) {
        this.numVariables = numVariables;
    }
   
    public String getFuncionInfija() {
        return funcionInfija;
    }

    public void setFuncionInfija(String funcionInfija) {
        this.funcionInfija = funcionInfija;
    }

    public String getExpresionPrefija() {
        return expresionPrefija;
    }

    public void setExpresionPrefija(String expresionPrefija) {
        this.expresionPrefija = expresionPrefija;
    }

    
    
    /*
     I. Convertir la infija a prefija o a posfija
        1. Obtener los caracteres de la expresion infija
        2. Si es operando, va a la expresion posfija
        3. Si es operador
                Si la pila está vacia
                    va a la pila y volver al paso (1.).
                Si la pila NO estavacia
                    Si la prioridad del operador nuevo es MAYOR a la que está en la pila:
                        va a la pila y volver al paso (1.)
                    Si la prioridad del operador nuevo en la pila es MENOR O IGUAL a la que esta en la pila:
                        Sacar el operador de la pila y pasarlo a la expresion posfija
                        colocar el operador nuevo en la pila.
                        volver al paso (3.)
        4. Colocar los operadores de la pila en la expresion posfija
     II. Procesar:
        
           
    */
    
    
    
    
    
    
    
    
    
    
    
    
}
