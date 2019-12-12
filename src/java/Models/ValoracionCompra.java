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
import java.sql.Types;
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
public class ValoracionCompra {
    protected int idSolicitud;
    protected int idArticulo;
    protected String comentarios;
    protected int calificacion;
    protected String fecha;
    protected String nombreUsuario;

    public ValoracionCompra() {
    }

    public ValoracionCompra(int idSolicitud, int idArticulo, String comentarios, int calificacion, String fecha, String nombreUsuario) {
        this.idSolicitud = idSolicitud;
        this.idArticulo = idArticulo;
        this.comentarios = comentarios;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public static boolean insertaValoracionCompra(int idSolicicud, int idArticulo, String comentarios, int calificacion) {
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
            statement = con.prepareCall("call insertaValoracionCompra(?,?,?,?);");
            statement.setInt(1, idSolicicud);
            statement.setInt(2, idArticulo);
            statement.setString(3, comentarios);
            statement.setInt(4, calificacion);
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
    
    public static List<ValoracionCompra> consultaValoracionCompra(int idSolicitud, int idArticulo) {
        List<ValoracionCompra> valoraciones = null;
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            statement = con.prepareCall("call consultaValoracionCompra(?,?)");
            if(idSolicitud != 0) {
                statement.setInt(1, idSolicitud);
            } else {
                statement.setNull(1, Types.NULL);
            }
            statement.setInt(2, idArticulo);
            rS = statement.executeQuery();
            if(rS.next()) {
                valoraciones = new ArrayList<ValoracionCompra>();
                rS.beforeFirst();
            }
            while (rS.next()) {
                ValoracionCompra valoracion = new ValoracionCompra(
                        rS.getInt("idSolicitud"),
                        rS.getInt("idArticulo"),
                        rS.getString("comentarios"),
                        rS.getInt("calificacion"),
                        rS.getString("fecha"),
                        rS.getString("nombre") + " " + rS.getString("apellido"));
                valoraciones.add(valoracion);
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            try {
                if (rS != null) {
                    rS.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
            }
        }
        return valoraciones;
    }
}
