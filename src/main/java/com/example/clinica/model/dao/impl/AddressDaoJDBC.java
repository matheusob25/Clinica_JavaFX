package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.AddressDao;
import com.example.clinica.model.entities.Address;
import com.example.clinica.model.entities.Pacient;

import java.sql.*;

public class AddressDaoJDBC implements AddressDao {
    private Connection connection;
    public AddressDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Address address) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("INSERT INTO tb_enderecos(endereco_descricao,endereco_referencia,bairro_id) " +
                    "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1,address.getDescription());
            st.setString(2, address.getReference());
            st.setLong(3, address.getNeighborhood().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("Done! Id = " + id);
                    address.setId(id);
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
    public void update(Address address) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
              "UPDATE tb_enderecos SET endereco_descricao = ?, endereco_referencia = ?, bairro_id = ? "
                 + "WHERE endereco_id = ?"
            );
            st.setString(1,address.getDescription());
            st.setString(2, address.getReference());
            st.setLong(3, address.getNeighborhood().getId());
            st.setLong(4, address.getId());

            st.executeUpdate();
        }catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Address address) {

    }

    @Override
    public Address getByPacient(Pacient pacient) {
        return null;
    }
}
