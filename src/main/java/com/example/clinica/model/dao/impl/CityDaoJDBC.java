package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.CityDao;
import com.example.clinica.model.entities.City;

import java.sql.*;

public class CityDaoJDBC implements CityDao {
    private final Connection connection;

    public CityDaoJDBC(Connection connection) {
        this.connection = connection;

    }
    @Override
    public void insert(City city) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("INSERT INTO tb_cidades(cidade_nome) " +
                    "VALUES (?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, city.getName());



            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("Done! Id = " + id);
                    city.setId(id);
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
    public void update(City city) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("UPDATE tb_cidades SET cidade_nome = ? WHERE cidade_id = ?");
            st.setString(1, city.getName());
            st.setLong(2, city.getId());
            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(City city) {

    }
}
