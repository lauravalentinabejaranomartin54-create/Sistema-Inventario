package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Peluche;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC de PelucheDAO (tabla "peluches").
 *
 * @author Master
 */
public class PelucheDAOImpl implements PelucheDAO {

    private static final String SELECT =
            "SELECT id, referencia, producto, color, tamano, " + DaoUtil.COLUMNAS_COMUNES
            + " FROM peluches";

    @Override
    public boolean insertar(Peluche p) throws SQLException {
        String sql = "INSERT INTO peluches (referencia, producto, color, tamano, "
                + DaoUtil.COLUMNAS_COMUNES + ") VALUES (?,?,?,?,?,?,?,?,?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, p.getReferencia());
            ps.setString(2, p.getProducto());
            ps.setString(3, p.getColor());
            ps.setString(4, p.getTamano());
            DaoUtil.asignarComunes(ps, 5, p);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Peluche> listar() throws SQLException {
        List<Peluche> lista = new ArrayList<>();
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(SELECT + " ORDER BY id DESC");
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    @Override
    public Peluche buscarPorReferencia(String referencia) throws SQLException {
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(SELECT + " WHERE referencia = ?");) {
            ps.setString(1, referencia);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public boolean actualizar(Peluche p) throws SQLException {
        String sql = "UPDATE peluches SET referencia = ?, producto = ?, color = ?, tamano = ?, "
                + DaoUtil.SET_COMUNES + " WHERE id = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, p.getReferencia());
            ps.setString(2, p.getProducto());
            ps.setString(3, p.getColor());
            ps.setString(4, p.getTamano());
            int siguiente = DaoUtil.asignarComunes(ps, 5, p);
            ps.setInt(siguiente, p.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement("DELETE FROM peluches WHERE id = ?");) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Convierte la fila actual del ResultSet en un objeto Peluche
    private Peluche mapear(ResultSet rs) throws SQLException {
        Peluche p = new Peluche();
        p.setReferencia(rs.getString("referencia"));
        p.setProducto(rs.getString("producto"));
        p.setColor(rs.getString("color"));
        p.setTamano(rs.getString("tamano"));
        DaoUtil.leerComunes(rs, p);
        return p;
    }
}
