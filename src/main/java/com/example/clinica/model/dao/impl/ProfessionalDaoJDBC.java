package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.ProfessionalDao;
import com.example.clinica.model.entities.Professional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessionalDaoJDBC implements ProfessionalDao {
    private Connection connection;

    public ProfessionalDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Professional professional) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO tb_profissionais (profissional_nome, profissional_numero, profissional_descricao) "
                       + "VALUES (?,?,?)"
                    , Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, professional.getName());
            st.setString(2, professional.getNumber());
            st.setString(3, professional.getDescricao());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    professional.setId(rs.getLong(1));
                    System.out.println("Done! id = " + professional.getId());
                }
                DB.closeResultSet(rs);
            }

        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Professional professional) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "UPDATE tb_profissionais SET profissional_nome = ?, profissional_numero = ?"
                       + ", profissional_descricao = ? WHERE profissional_id = ? "
            );
            st.setString(1, professional.getName());
            st.setString(2, professional.getNumber());
            st.setString(3, professional.getDescricao());
            st.setLong(4, professional.getId());
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletebyId(Long id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "DELETE FROM tb_profissionais WHERE profissional_id = ? "
            );
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Professional findbyId(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("SELECT * FROM tb_profissionais WHERE profissional_id = ?");

            st.setLong(1, id);

            rs = st.executeQuery();
            if(rs.next()){
                return instantiateProfessional(rs);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Professional> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("SELECT * FROM tb_profissionais;");
            rs = st.executeQuery();
            List<Professional> professionals = new ArrayList<>();
            while(rs.next()){
                professionals.add(instantiateProfessional(rs));
            }
            return professionals;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Long count() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT count(*) FROM tb_profissionais;"
            );
            rs = st.executeQuery();
            if(rs.next()){
                return rs.getLong(1);
            }else{
                return null;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Professional instantiateProfessional(ResultSet rs) throws SQLException {
        Professional professional = new Professional();
        professional.setId(rs.getLong("profissional_id"));
        professional.setName(rs.getString("profissional_nome"));
        professional.setNumber(rs.getString("profissional_numero"));
        professional.setDescricao(rs.getString("profissional_descricao"));
        return professional;
    }
}
