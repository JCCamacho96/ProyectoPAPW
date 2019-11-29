/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.InputStream;
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
public class Articulo {

    private int idArticulo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private int unidades;
    private byte[] imagen1;
    private byte[] imagen2;
    private byte[] imagen3;
    private byte[] video;
    private String fecha;
    private int active;

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Articulo() {
    }

    public Articulo(int idArticulo, String nombre, String descripcion, String categoria, int unidades, byte[] imagen1, byte[] imagen2, byte[] imagen3, byte[] video, String fecha, int active) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.unidades = unidades;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.video = video;
        this.fecha = fecha;
        this.active = active;
    }

    public static boolean insertaArticulo(String nombre, String descripcion, String categoria, int unidades, InputStream imagen1, InputStream imagen2, InputStream imagen3, InputStream video, int active) {
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
            statement = con.prepareCall("call insertaArticulo(?,?,?,?,?,?,?,?,?);");
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setString(3, categoria);
            statement.setInt(4, unidades);
            if (imagen1 != null) {
                statement.setBinaryStream(5, imagen1);
            } else {
                statement.setNull(5, Types.BLOB);
            }
            if (imagen2 != null) {
                statement.setBinaryStream(6, imagen2);
            } else {
                statement.setNull(6, Types.BLOB);
            }
            if (imagen3 != null) {
                statement.setBinaryStream(7, imagen3);
            } else {
                statement.setNull(7, Types.BLOB);
            }
            if (video != null) {
                statement.setBinaryStream(8, video);
            } else {
                statement.setNull(8, Types.BLOB);
            }
            statement.setInt(9, active);
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

    public static boolean actualizaArticulo(int idArticulo, String nombre, String descripcion, String categoria, int unidades, InputStream imagen1, InputStream imagen2, InputStream imagen3, InputStream video, int active) {
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
            statement = con.prepareCall("call actualizaArticulo(?,?,?,?,?,?,?,?,?,?);");
            statement.setInt(1, idArticulo);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.setString(4, categoria);
            statement.setInt(5, unidades);
            statement.setBinaryStream(6, imagen1);
            statement.setBinaryStream(7, imagen2);
            statement.setBinaryStream(8, imagen3);
            statement.setBinaryStream(9, video);
            statement.setInt(10, active);
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

    public static boolean borraArticulo(int idArticulo) {
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
            statement = con.prepareCall("call borraArticulo(?);");
            statement.setInt(1, idArticulo);
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

    public static List<Articulo> consultaArticulos(int active, int idArticulo) {
        List<Articulo> articulos = new ArrayList<Articulo>();
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
            statement = con.prepareCall("call consultaArticulo(?,?)");
            statement.setInt(1, active);
            if (idArticulo == 0) {
                statement.setNull(2, Types.NULL);
            } else {
                statement.setInt(2, idArticulo);
            }
            rS = statement.executeQuery();

            //Conseguimos los datos y los agregamos a una lista
            while (rS.next()) {
                Articulo articulo = new Articulo(
                        rS.getInt("idArticulo"),
                        rS.getString("nombre"),
                        rS.getString("descripcion"),
                        rS.getString("categoria"),
                        rS.getInt("unidades"),
                        rS.getBytes("imagen1"),
                        rS.getBytes("imagen2"),
                        rS.getBytes("imagen3"),
                        rS.getBytes("video"),
                        rS.getString("fecha"),
                        rS.getInt("active"));
                articulos.add(articulo);
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
        return articulos;
    }
}
