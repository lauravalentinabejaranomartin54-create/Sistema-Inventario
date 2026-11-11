package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Portarretrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PortarretratoDAOImpl implements PortarretratoDAO {
    private static final String SELECT =
            "SELECT id, referencia, producto, " + DaoUtil.COLUMNAS_COMUNES
            + " FROM portarretratos";

    @Override
    public boolean insertar(Portarretrato p) throws SQLException {
        String sql = "INSERT INTO portarretratos (referencia, producto, "
                + DaoUtil.COLUMNAS_COMUNES + ") VALUES (?,?,?,?,?,?,?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, p.getReferencia());
            ps.setString(2, p.getProducto());
            DaoUtil.asignarComunes(ps, 3, p);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Portarretrato> listar() throws SQLException {
        List<Portarretrato> lista = new ArrayList<>();
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
    public Portarretrato buscarPorReferencia(String referencia) throws SQLException {
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
    public boolean actualizar(Portarretrato p) throws SQLException {
        String sql = "UPDATE portarretratos SET referencia = ?, producto = ?, "
                + DaoUtil.SET_COMUNES + " WHERE id = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, p.getReferencia());
            ps.setString(2, p.getProducto());
            int siguiente = DaoUtil.asignarComunes(ps, 3, p);
            ps.setInt(siguiente, p.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement("DELETE FROM portarretratos WHERE id = ?");) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Portarretrato mapear(ResultSet rs) throws SQLException {
        Portarretrato p = new Portarretrato();
        p.setReferencia(rs.getString("referencia"));
        p.setProducto(rs.getString("producto"));
        DaoUtil.leerComunes(rs, p);
        return p;
    }
}
