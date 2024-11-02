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
//        PreparedStatement st = null;
//        try {
//            st = connection.prepareStatement(
//            "INSERT INTO tb_anamneses (anamnese_cor_dentes, anamnese_esc_dentes," +
//                "anamnese_forma_dentes, anamnese_sens_anestesia, anamnese_TC, anamnese_TS, " +
//                "anamnese_PA_MAX,anamnese_PA_MIM, anamnese_sens_antibioticos, anamnese_uso_medicacao," +
//                "anamnese_relato_cancer, anamnese_doenca_grave, anamnese_higiene_oral, anamnese_dente_sensivel," +
//                "anamnese_gravidez, anamnese_diabete, anamnese_anotacoes_adicionais) " +
//                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
//            );
//            st.setString(1,anamnese.getToothColor());
//            st.setString(2,anamnese.getToothEsc());
//            st.setString(3,anamnese.getToothShape());
//            st.setString(4,anamnese.getSensitivityAnesthesia());
//            st.setString(5,anamnese.getTc());
//            st.setString(6,anamnese.getTs());
//            st.setString(7,anamnese.getPaMax());
//            st.setString(8,anamnese.getPaMin());
//            st.setString(9,anamnese.getSensitivityAntibiotics());
//            st.setString(10,anamnese.getMedicationUse());
//            st.setString(11,anamnese.getCancerHistory());
//            st.setString(12,anamnese.getSeriousIllness());
//            st.setString(13,anamnese.getOralHygiene());
//            st.setBoolean(14,anamnese.getSensitiveTooth());
//            st.setString(15,anamnese.getPregnancy());
//            st.setString(16,anamnese.getDiabetes());
//            st.setString(17,anamnese.getAdditionalAnnotations());
//
//            int rowsAffected = st.executeUpdate();
//            if (rowsAffected > 0) {
//                ResultSet rs = st.getGeneratedKeys();
//                while (rs.next()) {
//                    Long id = rs.getLong(1);
//                    System.out.println("Done! Id = " + id);
//                    anamnese.setId(id);
//                }
//                DB.closeResultSet(rs);
//            }else{
//                throw new DbException("Erro ao inserir anamnese");
//            }
//
//        }catch (SQLException e){
//            throw new DbException(e.getMessage());
//        }finally {
//            DB.closeStatement(st);
//        }
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
