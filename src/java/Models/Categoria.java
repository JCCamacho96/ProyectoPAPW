/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.DBUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author edo16
 */
public class Categoria {
    private int idCategoria;
    private String nombre;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria() {
    }

    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }
    
    public static List<Categoria> consultaCategorias() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try{
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            //Esto dependera del nombre de su conexion recuerden
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            //Creamos el llamado
            statement = con.prepareCall("call consultaCategorias()");
            rS = statement.executeQuery();
            
            //Conseguimos los datos y los agregamos a una lista
            while(rS.next()){
                Categoria categoria = new Categoria(rS.getInt("idCategoria"), rS.getString("nombre"));
                categorias.add(categoria);
            }
        }catch(SQLException ex){
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        }catch(NamingException ex){
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally{
            //Si se finalizo bien cerramos todo
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
        return categorias;
    }
}
