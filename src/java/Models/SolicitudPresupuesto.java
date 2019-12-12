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

public class SolicitudPresupuesto {
    protected int idSolicitud;
    protected int idUsuario;
    protected int estatus;
    protected String fecha;
    protected String metodoPago;
    protected List<ItemSolicitudPresupuesto> items;

    public SolicitudPresupuesto() {
    }

    public SolicitudPresupuesto(int idSolicitud, int idUsuario, int estatus, String fecha, String metodoPago, List<ItemSolicitudPresupuesto> items) {
        this.idSolicitud = idSolicitud;
        this.idUsuario = idUsuario;
        this.estatus = estatus;
        this.fecha = fecha;
        this.items = items;
        this.metodoPago = metodoPago;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<ItemSolicitudPresupuesto> getItems() {
        return items;
    }

    public void setItems(List<ItemSolicitudPresupuesto> items) {
        this.items = items;
    }
    
    public static boolean generaSolicitudPresupuesto(int idUsuario) {
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
            statement = con.prepareCall("call generaSolicitudPresupuesto(?);");
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
    
    public static List<SolicitudPresupuesto> consultaSolicitudesPresupuesto(int idUsuario, int estatus) {
        List<SolicitudPresupuesto> solicitudes = new ArrayList<SolicitudPresupuesto>();
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

            statement = con.prepareCall("call consultaSolicitudesPresupuesto(?,?)");
            if (idUsuario == 0) {
                statement.setNull(1, Types.NULL);
            } else {
                statement.setInt(1, idUsuario);
            }
            if (estatus == -1) {
                statement.setNull(2, Types.NULL);
            } else {
                statement.setInt(2, estatus);
            }
            rS = statement.executeQuery();

            while (rS.next()) {
                SolicitudPresupuesto solicitud = new SolicitudPresupuesto(
                        rS.getInt("idSolicitud"),
                        rS.getInt("idUsuario"),
                        rS.getInt("estatus"),
                        rS.getString("fecha"),
                        rS.getString("metodoPago"),
                        ItemSolicitudPresupuesto.consultaItemsSolicitudPresupuesto(rS.getInt("idSolicitud")));
                solicitudes.add(solicitud);
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
        return solicitudes;
    }
    
    public static boolean actualizaEstatus(int idSolicitud, int estatus, String metodoPago) {
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
            statement = con.prepareCall("call actualizaEstatusSolicitudPresupuesto(?,?,?);");
            statement.setInt(1, idSolicitud);
            statement.setInt(2, estatus);
            if(metodoPago.equals("")) {
                statement.setNull(3, Types.NULL);
            } else {
                statement.setString(3, metodoPago);
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
}
