/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author edo16
 */
public class ValidaUsuario {
    private boolean existeUser;
    private boolean existeCorreo;

    public boolean isExisteUser() {
        return existeUser;
    }

    public void setExisteUser(boolean existeUser) {
        this.existeUser = existeUser;
    }

    public boolean isExisteCorreo() {
        return existeCorreo;
    }

    public void setExisteCorreo(boolean existeCorreo) {
        this.existeCorreo = existeCorreo;
    }

    public ValidaUsuario() {
    }

    public ValidaUsuario(boolean existeUser, boolean existeCorreo) {
        this.existeUser = existeUser;
        this.existeCorreo = existeCorreo;
    }
    
    public static ValidaUsuario validar(String user, String correo) {
        ValidaUsuario valida = new ValidaUsuario();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();
            statement = con.prepareCall("call validaUsuario(?,?);");
            statement.setString(1, user);
            statement.setString(2, correo);
            rS = statement.executeQuery();
            
            while(rS.next()){
                valida.setExisteUser(rS.getBoolean("existeUser"));
                valida.setExisteCorreo(rS.getBoolean("existeCorreo"));
                break;
            }
        }catch(SQLException ex){
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally{
            try{
                if(rS != null)
                {
                    rS.close();
                }
                if(statement != null)
                {
                    statement.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }catch(SQLException ex){
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return valida;
    }
}
