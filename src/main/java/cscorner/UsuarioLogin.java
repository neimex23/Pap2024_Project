/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cscorner;
import javax.servlet.annotation.WebServlet;
import org.pap.publicadores.*;


public class UsuarioLogin { // Esta Clase se utilizara para consultar datos sobre el usuario logeado en el sistema
    
    private static UsuarioLogin instancia = null;
    private UsuarioLogin() {}
    public static UsuarioLogin GetInstancia() {
        if (instancia == null)
            instancia = new UsuarioLogin();
        return instancia;
    }
    
    private DtUsuario usuario = null;

    public DtUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtUsuario usuario) {
        this.usuario = usuario;
    }

    public boolean isLogin(){return usuario!=null;}
    
    public void Logout() {setUsuario(null);}
    
     
}
