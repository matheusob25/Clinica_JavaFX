package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.AnamneseDao;
import com.example.clinica.model.entities.Anamnese;
import com.example.clinica.model.entities.Pacient;

import java.sql.*;

public class AnamneseDaoJDBC implements AnamneseDao {
    private Connection connection;

    public AnamneseDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Anamnese anamnese) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
            "INSERT INTO tb_anamneses (anamnese_sens_anestesia," +
                "anamnese_sens_antibioticos, anamnese_uso_medicacao," +
                "anamnese_doenca_grave, anamnese_dente_sensivel," +
                "anamnese_gravidez, anamnese_diabete, anamnese_anotacoes_adicionais) " +
                "VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1,anamnese.getSensitivityAnesthesia());
            st.setString(2,anamnese.getSensitivityAntibiotics());
            st.setString(3,anamnese.getMedicationUse());
            st.setString(4,anamnese.getSeriousIllness());
            st.setBoolean(5,anamnese.getSensitiveTooth());
            st.setBoolean(6,anamnese.getPregnancy());
            st.setBoolean(7,anamnese.getDiabetes());
            st.setString(8,anamnese.getAdditionalAnnotations());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    Long id = rs.getLong(1);
                    System.out.println("Done! Id = " + id);
                    anamnese.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Erro ao inserir anamnese");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Anamnese anamnese) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Anamnese findByPacient(Pacient pacient) {
        return null;
    }
}
