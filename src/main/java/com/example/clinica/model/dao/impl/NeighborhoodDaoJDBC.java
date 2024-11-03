package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.NeighborhoodDao;
import com.example.clinica.model.entities.Address;
import com.example.clinica.model.entities.Neighborhood;

import java.sql.*;

public class NeighborhoodDaoJDBC implements NeighborhoodDao {
    private Connection connection;
    public NeighborhoodDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Neighborhood neighborhood) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("INSERT INTO tb_bairros(bairro_nome, cidade_id) " +
                    "VALUES (?,?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, neighborhood.getNome());
            st.setLong(2, neighborhood.getCity().getId());


            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("Done! Id = " + id);
                    neighborhood.setId(id);
                }
                DB.closeResultSet(rs);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Neighborhood neighborhood) {

    }

    @Override
    public void delete(Neighborhood neighborhood) {

    }

    @Override
    public Neighborhood findByAddress(Address address) {
        return null;
    }
}