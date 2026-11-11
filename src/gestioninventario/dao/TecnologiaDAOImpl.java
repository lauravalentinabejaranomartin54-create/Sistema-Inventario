package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Tecnologia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TecnologiaDAOImpl implements TecnologiaDAO {
    private static final String SELECT =
            "SELECT id, referencia, producto, marca, " + DaoUtil.COLUMNAS_COMUNES
            + " FROM tecnologia";

    @Override
    public boolean insertar(Tecnologia t) throws SQLException {
        String sql = "INSERT INTO tecnologia (referencia, producto, marca, "
                + DaoUtil.COLUMNAS_COMUNES + ") VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, t.getReferencia());
            ps.setString(2, t.getProducto());
            ps.setString(3, t.getMarca());
            DaoUtil.asignarComunes(ps, 4, t);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Tecnologia> listar() throws SQLException {
        List<Tecnologia> lista = new ArrayList<>();
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
    public Tecnologia buscarPorReferencia(String referencia) throws SQLException {
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
    public boolean actualizar(Tecnologia t) throws SQLException {
        String sql = "UPDATE tecnologia SET referencia = ?, producto = ?, marca = ?, "
                + DaoUtil.SET_COMUNES + " WHERE id = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, t.getReferencia());
            ps.setString(2, t.getProducto());
            ps.setString(3, t.getMarca());
            int siguiente = DaoUtil.asignarComunes(ps, 4, t);
            ps.setInt(siguiente, t.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement("DELETE FROM tecnologia WHERE id = ?");) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Tecnologia mapear(ResultSet rs) throws SQLException {
        Tecnologia t = new Tecnologia();
        t.setReferencia(rs.getString("referencia"));
        t.setProducto(rs.getString("producto"));
        t.setMarca(rs.getString("marca"));
        DaoUtil.leerComunes(rs, t);
        return t;
    }
}
