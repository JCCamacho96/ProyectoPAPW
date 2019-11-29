/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.DBUtils;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author edo16
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String user;
    private String correo;
    private String password;
    private String telefono;
    private String direccion;
    private byte[] imagen;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String user, String correo, String password, String telefono, String direccion, byte[] imagen) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.imagen = imagen;
    }
    
    public static Usuario iniciaSesion(String user, String password) {
        Usuario usuario = null;
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
            statement = con.prepareCall("call iniciarSesion(?,?);");
            statement.setString(1, user);
            statement.setString(2, password);
            rS = statement.executeQuery();
            
            while(rS.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rS.getInt("idUsuario"));
                usuario.setNombre(rS.getString("nombre"));
                usuario.setApellido(rS.getString("apellido"));
                usuario.setUser(rS.getString("user"));
                usuario.setCorreo(rS.getString("correo"));
                usuario.setPassword(rS.getString("password"));
                usuario.setTelefono(rS.getString("telefono"));
                usuario.setDireccion(rS.getString("direccion"));
                usuario.setImagen(rS.getBytes("imagen"));
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
        return usuario;
    }
    
    public static boolean registraUsuario(String nombre, String apellido, String user, String correo, String password, String telefono, String direccion, InputStream imagen) {
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        boolean rS = false;
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();
            statement = con.prepareCall("call registraUsuario(?,?,?,?,?,?,?,?);");
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, user);
            statement.setString(4, correo);
            statement.setString(5, password);
            if (telefono.equals("")) {
                statement.setNull(6, Types.NULL);
            } else {
                statement.setString(6, telefono);
            }
            if (direccion.equals("")) {
                statement.setNull(7, Types.NULL);
            } else {
                statement.setString(7, direccion);
            }
            statement.setBinaryStream(8, imagen);
            if (statement.executeUpdate() > 0) {
                rS = true;
            }
        }catch(SQLException ex){
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally{
            try{
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
        return rS;
    }
}
