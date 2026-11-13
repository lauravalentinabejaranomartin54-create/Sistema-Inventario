package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Ropa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RopaDAOImpl implements RopaDAO {
    private static final String COLUMNAS =
            "id, referencia, tipo_ropa, marca, color, tallas, stock_curvas, " + DaoUtil.COLUMNAS_COMUNES;

    @Override
    public boolean insertar(Ropa r) throws SQLException {
        String sql = "INSERT INTO ropa (referencia, tipo_ropa, marca, color, tallas, stock_curvas, "
                + DaoUtil.COLUMNAS_COMUNES + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            int siguiente = asignarPropios(ps, r);
            DaoUtil.asignarComunes(ps, siguiente, r);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Ropa> listar() throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM ropa ORDER BY id DESC";
        List<Ropa> lista = new ArrayList<>();
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                lista.add(leer(rs));
            }
        }
        return lista;
    }

    @Override
    public Ropa buscarPorReferencia(String referencia) throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM ropa WHERE referencia = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, referencia);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? leer(rs) : null;
            }
        }
    }

    @Override
    public boolean actualizar(Ropa r) throws SQLException {
        String sql = "UPDATE ropa SET referencia = ?, tipo_ropa = ?, marca = ?, color = ?, tallas = ?, "
                + "stock_curvas = ?, " + DaoUtil.SET_COMUNES + " WHERE id = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            int siguiente = asignarPropios(ps, r);
            siguiente = DaoUtil.asignarComunes(ps, siguiente, r);
            ps.setInt(siguiente, r.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement("DELETE FROM ropa WHERE id = ?");) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private int asignarPropios(PreparedStatement ps, Ropa r) throws SQLException {
        ps.setString(1, r.getReferencia());
        ps.setString(2, r.getTipoRopa());
        ps.setString(3, r.getMarca());
        ps.setString(4, r.getColor());
        ps.setString(5, r.getTallas());
        ps.setInt(6, r.getStockCurvas());
        return 7;
    }

    private Ropa leer(ResultSet rs) throws SQLException {
        Ropa r = new Ropa();
        r.setReferencia(rs.getString("referencia"));
        r.setTipoRopa(rs.getString("tipo_ropa"));
        r.setMarca(rs.getString("marca"));
        r.setColor(rs.getString("color"));
        r.setTallas(rs.getString("tallas"));
        r.setStockCurvas(rs.getInt("stock_curvas"));
        DaoUtil.leerComunes(rs, r);
        return r;
    }
}
