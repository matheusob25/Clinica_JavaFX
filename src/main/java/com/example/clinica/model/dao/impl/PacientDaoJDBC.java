package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.PacientDao;
import com.example.clinica.model.entities.Address;
import com.example.clinica.model.entities.Anamnese;
import com.example.clinica.model.entities.Pacient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
                         +"(paciente_nome, paciente_email, paciente_numero,paciente_segundo_numero, paciente_data_nascimento, "
                         +"paciente_cpf,paciente_inicio_tratamento,paciente_termino_tratamento,paciente_profissao,"
                         +"paciente_estado_civil,paciente_dlne, endereco_id, anamnese_id)"
                         +" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, pacient.getName());
            st.setString(2, pacient.getEmail());
            st.setString(3, pacient.getNumber());
            st.setString(4,pacient.getNumberTwo());
            st.setDate(5, Date.valueOf(pacient.getBirthDate()));
            st.setString(6, pacient.getCpf());
            st.setDate(7,Date.valueOf(pacient.getStartTreatment()));
            st.setDate(8,Date.valueOf(pacient.getEndTreatment()));
            st.setString(9,pacient.getProfession());
            st.setString(10,pacient.getMaritalStatus());
            st.setString(11,pacient.getDlne());
            st.setLong(12, pacient.getAddress().getId());
            st.setLong(13, pacient.getAnamnese().getId());


            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    Long id = rs.getLong(1);
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
    public void deleteById(Long id) {

    }

    @Override
    public Pacient findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT pac.*, ana.*, ende.* FROM tb_pacientes as pac " +
                        "LEFT JOIN tb_anamneses AS ana ON pac.anamnese_id = ana.anamnese_id " +
                        "LEFT JOIN tb_enderecos AS ende ON pac.endereco_id = ende.endereco_id " +
                        "WHERE pac.paciente_id = ?" +
                        " ORDER BY paciente_nome"

            );

            st.setLong(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Anamnese anamnese = instantiateAnamnese(rs);
                Address address = instantiateAddress(rs);
                return instantiatePacient(rs, anamnese,address);
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Address instantiateAddress(ResultSet rs) {
        Address address = new Address();



        return address;
    }

    private Anamnese instantiateAnamnese(ResultSet rs)throws SQLException {
        Anamnese anamnese = new Anamnese();
        anamnese.setId(rs.getLong("anamnese_id"));
        anamnese.setSensitivityAnesthesia(rs.getString("anamnese_sens_anestesia "));
        anamnese.setSensitivityAntibiotics(rs.getString("anamnese_sens_antibioticos"));
        anamnese.setMedicationUse(rs.getString("anamnese_uso_medicacao"));
        anamnese.setSeriousIllness(rs.getString("anamnese_doenca_grave"));
        anamnese.setSensitiveTooth(rs.getBoolean("anamnese_dente_sensivel "));
        anamnese.setPregnancy(rs.getBoolean("anamnese_gravidez"));
        anamnese.setDiabetes(rs.getBoolean("anamnese_diabete"));
        anamnese.setAdditionalAnnotations(rs.getString("anamnese_anotacoes_adicionais"));
        return anamnese;
    }

    @Override
    public List<Pacient> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT paciente_id, paciente_nome, paciente_email, paciente_numero, paciente_data_nascimento, paciente_cpf " +
                        "FROM tb_pacientes ORDER BY paciente_nome"
            );
            rs = st.executeQuery();
            List<Pacient> pacients = new ArrayList<>();
            while(rs.next()){
                pacients.add(instantiatePacient(rs));
            }
            return pacients;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Pacient instantiatePacient(ResultSet rs) throws SQLException {
        Pacient pacient = new Pacient();
        pacient.setId(rs.getLong("paciente_id"));
        pacient.setName(rs.getString("paciente_nome"));
        pacient.setEmail(rs.getString("paciente_email"));
        pacient.setNumber(rs.getString("paciente_numero"));

        Date birthDate = rs.getDate("paciente_data_nascimento"); // recebendo a data de sql em tipo Date.Sql
        pacient.setBirthDate(birthDate.toLocalDate());  // convertendo em LocalDate
        pacient.setCpf(rs.getString("paciente_cpf"));
        return pacient;
    }
    private Pacient instantiatePacient(ResultSet rs, Anamnese anamnese, Address address)throws SQLException {
        Pacient pacient = instantiatePacient(rs);
        pacient.setAnamnese(anamnese);
        pacient.setAddress(address);
        return pacient;
    }

}
