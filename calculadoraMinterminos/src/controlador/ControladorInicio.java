/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;
import vista.frmInicio;
import modelo.Operador;



public class ControladorInicio {
    protected frmInicio inicio;
    private String texto;
    private Operador operador;   
    
    public ControladorInicio (frmInicio inicio){
        this.inicio = inicio;
        this.texto = "";
        this.operador = new Operador(inicio.txtTabla, inicio.lblCircuito);
        this.inicio.setIconImage(getIconImage());
        
        //------------------------------------------ Agregar variables:
        
        
        
        
        this.inicio.btnA.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"a");                
                
            }
        });
        
        this.inicio.btnB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"b");                 
            }
        });
        
        this.inicio.btnC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"c");               
            }
        });
        
        this.inicio.btnD.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"d");               
            }
        });
        
        this.inicio.btnE.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"e");               
            }
        });
        
        this.inicio.btnF.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"f");               
            }
        });
        
        //------------------------------------ Operadores
        this.inicio.btnNot.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"¬");
            }
        });
        
        this.inicio.btnAnd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"∧");
            }
        });
        
        
        this.inicio.btnOr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               texto = inicio.lblCircuito.getText();
               inicio.lblCircuito.setText(texto+"∨"); 
            }
        });
        
        this.inicio.btnNand.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"↑");
            }
        });
        
        this.inicio.btnNor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"↓");
            }
        });
        
        this.inicio.btnXor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"⊕");
            }
        });
        
        
        this.inicio.btnXnor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"⊙");
            }
        });
        
        this.inicio.btnImplica.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"→");
            }
        });
        
        //---------------------------------------- Agrupadores
        this.inicio.btnParentesisAbierto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"(");
            }
        });
        
        this.inicio.btnParentesisCerrado.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+")");
            }
        });
        
        this.inicio.btnCorcheteAbierto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"[");
            }
        });
        
        this.inicio.btnCorcheteCerrado.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = inicio.lblCircuito.getText();
                inicio.lblCircuito.setText(texto+"]");
            }
        });
        
        this.inicio.btnResolver.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                inicio.txtTabla.setText("");
                operador.setFuncionInfija(inicio.lblCircuito.getText());
                if(operador.verificarAgrupacion(operador.getFuncionInfija())==true){

                    operador.generarPrefija();                     
                    operador.contarVariables();
                    operador.resolverPorCaso(inicio.txtTabla, inicio.lblVariables, inicio.lblExpresion);                    
                    inicio.txtTabla.append("\n");
 
                }else{
                    JOptionPane.showMessageDialog(null, "Error: Verifique el balanceo de los signos de agrupación.");
                }
                
                
            }
        });
        
        this.inicio.btnBorrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inicio.lblCircuito.getText().length() >0){
                    texto = inicio.lblCircuito.getText().substring(0, inicio.lblCircuito.getText().length()-1);
                    inicio.lblCircuito.setText(texto);
                }
                
            }
        });
        
        this.inicio.btnBorrarTodo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inicio.lblCircuito.getText().length() >0){
                   inicio.lblCircuito.setText("");
  
                }
                
            }
        });
        
        
    }
    
    //------------------------------------------ Métodos --------------------------------------
    
    public void iniciar(){
        this.inicio.setLocationRelativeTo(null);
        this.inicio.setVisible(true);
        this.inicio.lblCircuito.setText("");
        this.inicio.txtTabla.setText("");
        this.inicio.txtTabla.setEditable(false);
    }
    
    
    public Image getIconImage(){
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/Logo.png"));
        return redValue;
    }
        
    //------------------------------------------ Get & Set ------------------------------------
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
}
