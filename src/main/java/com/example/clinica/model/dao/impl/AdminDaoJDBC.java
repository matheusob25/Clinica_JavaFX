package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.AddressDao;
import com.example.clinica.model.dao.AdminDao;
import com.example.clinica.model.entities.Admin;

import java.sql.*;
import java.util.List;

public class AdminDaoJDBC implements AdminDao {

    private Connection connection;

    public AdminDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Admin> findAll() {
        return List.of();
    }

    @Override
    public Admin findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT adms.admin_nome, adms.admin_senha FROM tb_admins as adms WHERE id = ?" );

            st.setLong(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                return instantiateAdmin(rs);
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
    public Admin findByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT adms.admin_id, adms.admin_nome, adms.admin_senha FROM tb_admins as adms WHERE adms.admin_nome = ?" );

            st.setString(1, name);
            rs = st.executeQuery();
            if(rs.next()){
                return instantiateAdmin(rs);
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Admin instantiateAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getLong("admin_id"));
        admin.setName(rs.getString("admin_nome"));
        admin.setPassword(rs.getString("admin_senha"));
        return admin;

    }

    @Override
    public void insert(Admin admin) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(
                    "INSERT INTO tb_admins (admin_nome, admin_senha) " +
                        "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, admin.getName());
            st.setString(2, admin.getPassword());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("Done! Id = " + id);
                    admin.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Unexpected error! No rows affected");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Admin admin) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                 "UPDATE tb_admins SET admin_nome = ?, admin_senha = ?"
                    + " WHERE admin_id = ?;");
            st.setString(1, admin.getName());
            st.setString(2, admin.getPassword());
            st.setLong(3, admin.getId());
            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }
}

