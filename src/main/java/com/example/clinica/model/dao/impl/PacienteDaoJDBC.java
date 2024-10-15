package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.PacienteDao;
import com.example.clinica.model.entities.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PacienteDaoJDBC implements PacienteDao {

    private Connection connection;
    public PacienteDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Paciente paciente) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(
                    "INSERT INTO tb_pacientes "
                            +"(paciente_nome, paciente_email, paciente_numero, paciente_data_nascimento, paciente_cpf, endereco_id, anamnese_id)"
                            +" VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1,paciente.getName());
            st.setString(2,paciente.getEmail());
            st.setString(3,paciente.getNumber());
            st.setDate(4, Date.valueOf(paciente.getBirthDate()));
            st.setString(5, paciente.getCpf());
            st.setInt(  5, paciente.getAddress().getId());
            st.setInt(6, paciente.getAnamnese().getId());


            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                    paciente.setId(id);
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
    public void update(Paciente paciente) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Paciente findById(Integer id) {
        return null;
    }

    @Override
    public List<Paciente> findAll() {
        return List.of();
    }
}
