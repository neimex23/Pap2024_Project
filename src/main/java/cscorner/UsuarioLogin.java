/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cscorner;
import org.pap.publicadores.*;

enum LoginL {NoLogin, Beneficiario, Repartidor}

public class UsuarioLogin { // Esta Clase se utilizara para consultar datos sobre el usuario logeado en el sistema
    private static UsuarioLogin instancia = null;
    private UsuarioLogin() {}
    public static UsuarioLogin GetInstancia() {
        if (instancia == null)
            instancia = new UsuarioLogin();
        return instancia;
    }
    
    private DtUsuario usuario = null;
    private LoginL tipo = LoginL.NoLogin; //Se va a usar para hacer la historia de cerrar secion

    public DtUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtUsuario usuario) {
        this.usuario = usuario;
        if (usuario != null){
            if (usuario instanceof DtRepartidor) {
                tipo = LoginL.Repartidor;
            } 
            else 
            {
                tipo = LoginL.Beneficiario;
            }
        }
    }

    public LoginL getTipo() {
        return tipo;
    }

    public void setTipo(LoginL tipo) {
        this.tipo = tipo;
        if (tipo == LoginL.NoLogin) { //Reiniciar datos
            usuario = null;
        }
    }
    
     
}
