package Models;

import java.math.BigDecimal;
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

public class ItemSolicitudPresupuesto {
    protected int idSolicitud;
    protected Articulo articulo;
    protected int cantidad;
    protected BigDecimal precio;
    protected ValoracionCompra valoracion;

    public ItemSolicitudPresupuesto() {
    }

    public ItemSolicitudPresupuesto(int idSolicitud, Articulo articulo, int cantidad, BigDecimal precio, ValoracionCompra valoracion) {
        this.idSolicitud = idSolicitud;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.valoracion = valoracion;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public ValoracionCompra getValoracion() {
        return valoracion;
    }

    public void setValoracion(ValoracionCompra valoracion) {
        this.valoracion = valoracion;
    }
    
    public static boolean insertaItemSolicitudPresupuesto(int idUsuario) {
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
            statement = con.prepareCall("call insertaItemSolicitudPresupuesto(?);");
            statement.setInt(1, idUsuario);
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
    
    public static List<ItemSolicitudPresupuesto> consultaItemsSolicitudPresupuesto(int idSolicitud) {
        List<ItemSolicitudPresupuesto> items = new ArrayList<ItemSolicitudPresupuesto>();
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

            statement = con.prepareCall("call consultaItemsSolicitudPresupuesto(?)");
            statement.setInt(1, idSolicitud);
            rS = statement.executeQuery();

            while (rS.next()) {
                ItemSolicitudPresupuesto item = new ItemSolicitudPresupuesto(
                        rS.getInt("idSolicitud"),
                        Articulo.consultaArticulos(1, rS.getInt("idArticulo"), "", "", "").get(0),
                        rS.getInt("cantidad"),
                        rS.getBigDecimal("precio"),
                        ValoracionCompra.consultaValoracionCompra(rS.getInt("idSolicitud"), rS.getInt("idArticulo")) != null ? ValoracionCompra.consultaValoracionCompra(rS.getInt("idSolicitud"), rS.getInt("idArticulo")).get(0) : null);
                items.add(item);
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
        return items;
    }
    
    public static boolean actualizaPrecioItem(int idSolicitud, int idArticulo, BigDecimal precio) {
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
            statement = con.prepareCall("call actualizaPrecioItemSolicitudPresupuesto(?,?,?);");
            statement.setInt(1, idSolicitud);
            statement.setInt(2, idArticulo);
            statement.setBigDecimal(3, precio);
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
    
    public static boolean actualizaCantidadItem(int idSolicitud, int idArticulo, int cantidad) {
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
            statement = con.prepareCall("call actualizaCantidadItemSolicitudPresupuesto(?,?,?);");
            statement.setInt(1, idSolicitud);
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
}
