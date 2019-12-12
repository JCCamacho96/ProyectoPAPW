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
public class CarritoCompra {
    private int idUsuario;
    private int idArticulo;
    private int cantidad;
    private int disponible;
    private Articulo articulo;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public CarritoCompra() {
    }

    public CarritoCompra(int idUsuario, int idArticulo, int cantidad, int disponible, Articulo articulo) {
        this.idUsuario = idUsuario;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.disponible = disponible;
        this.articulo = articulo;
    }
    
    public static boolean insertaItemCarrito(int idUsuario, int idArticulo, int cantidad) {
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
            statement = con.prepareCall("call insertaItemCarrito(?,?,?);");
            statement.setInt(1, idUsuario);
            statement.setInt(2, idArticulo);
            statement.setInt(3, cantidad);
            if (statement.executeUpdate() > 0) {
                rS = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            try {
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
        return rS;
    }
    
    public static boolean actualizaCantidadItemCarrito(int idUsuario, int idArticulo, int cantidad) {
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
            statement = con.prepareCall("call actualizaCantidadItemCarrito(?,?,?);");
            statement.setInt(1, idUsuario);
            statement.setInt(2, idArticulo);
            statement.setInt(3, cantidad);
            if (statement.executeUpdate() > 0) {
                rS = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            try {
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
        return rS;
    }
    
    public static boolean borraItemCarrito(int idUsuario, int idArticulo) {
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
            statement = con.prepareCall("call borraItemCarrito(?,?);");
            statement.setInt(1, idUsuario);
            if(idArticulo != 0) {
                statement.setInt(2, idArticulo);
            } else {
                statement.setNull(2, Types.NULL);
            }
            if (statement.executeUpdate() > 0) {
                rS = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            try {
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
        return rS;
    }
    
    public static List<CarritoCompra> consultaCarritoCompra(int idUsuario) {
        List<CarritoCompra> carrito = new ArrayList<CarritoCompra>();
        InitialContext iC = null;
        Context context = null;
        Connection con = null;
        ResultSet rS = null;
        CallableStatement statement = null;
        try {
            iC = new InitialContext();
            context = (Context) iC.lookup("java:comp/env");

            //Esto dependera del nombre de su conexion recuerden
            DataSource dS = (DataSource) context.lookup("jdbc/myDB");
            con = dS.getConnection();

            //Creamos el llamado
            statement = con.prepareCall("call consultaCarritoCompra(?)");
            statement.setInt(1, idUsuario);
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                CarritoCompra item = new CarritoCompra(
                        rS.getInt("idUsuario"),
                        rS.getInt("idArticulo"),
                        rS.getInt("cantidad"),
                        rS.getInt("disponible"),
                        Articulo.consultaArticulos(-1, rS.getInt("idArticulo"), "", "", "").get(0));
                carrito.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger("ERROR (" + ex.getErrorCode() + "): " + ex.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger("ERROR al intentar obtener el DataSource: " + ex.getMessage());
        } finally {
            //Si se finalizo bien cerramos todo
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
        return carrito;
    }
}
