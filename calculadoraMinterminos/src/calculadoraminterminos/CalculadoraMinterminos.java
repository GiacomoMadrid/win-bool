/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadoraminterminos;

import controlador.ControladorInicio;
import controlador.ControladorMensaje;
import vista.frmInicio;
import vista.frmMensaje;
public class CalculadoraMinterminos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frmInicio inicio = new frmInicio();
        ControladorInicio contInicio = new ControladorInicio(inicio); 
        
        frmMensaje mensaje = new frmMensaje();
        ControladorMensaje contMensaje = new ControladorMensaje(mensaje, contInicio);
        contMensaje.iniciar();
        
    }
    
  
    
}
