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
    @Override
    public boolean insertar(Ropa r) throws SQLException {
        String sql = "INSERT INTO ropa (tipo_ropa, marca, color, tallas, "
                + DaoUtil.COLUMNAS_COMUNES + ") VALUES (?,?,?,?,?,?,?,?,?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, r.getTipoRopa());
            ps.setString(2, r.getMarca());
            ps.setString(3, r.getColor());
            ps.setString(4, r.getTallas());
            DaoUtil.asignarComunes(ps, 5, r);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Ropa> listar() throws SQLException {
        String sql = "SELECT id, tipo_ropa, marca, color, tallas, " + DaoUtil.COLUMNAS_COMUNES
                + " FROM ropa ORDER BY id DESC";
        List<Ropa> lista = new ArrayList<>();
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Ropa r = new Ropa();
                r.setTipoRopa(rs.getString("tipo_ropa"));
                r.setMarca(rs.getString("marca"));
                r.setColor(rs.getString("color"));
                r.setTallas(rs.getString("tallas"));
                DaoUtil.leerComunes(rs, r);
                lista.add(r);
            }
        }
        return lista;
    }

    @Override
    public boolean actualizar(Ropa r) throws SQLException {
        String sql = "UPDATE ropa SET tipo_ropa = ?, marca = ?, color = ?, tallas = ?, "
                + DaoUtil.SET_COMUNES + " WHERE id = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, r.getTipoRopa());
            ps.setString(2, r.getMarca());
            ps.setString(3, r.getColor());
            ps.setString(4, r.getTallas());
            int siguiente = DaoUtil.asignarComunes(ps, 5, r);
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
}
