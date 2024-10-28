package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.PacientDao;
import com.example.clinica.model.entities.Pacient;

import java.sql.*;
import java.util.List;

public class PacientDaoJDBC implements PacientDao {

    private Connection connection;

    public PacientDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Pacient pacient) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(
                    "INSERT INTO tb_pacientes "
                            +"(paciente_nome, paciente_email, paciente_numero, paciente_data_nascimento, paciente_cpf, endereco_id, anamnese_id)"
                            +" VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, pacient.getName());
            st.setString(2, pacient.getEmail());
            st.setString(3, pacient.getNumber());
            st.setDate(4, Date.valueOf(pacient.getBirthDate()));
            st.setString(5, pacient.getCpf());
            st.setLong(  5, pacient.getAddress().getId());
            st.setInt(6, pacient.getAnamnese().getId());


            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                    pacient.setId(id);
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
    public void update(Pacient pacient) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Pacient findById(Integer id) {
        return null;
    }

    @Override
    public List<Pacient> findAll() {
        return List.of();
    }
}
