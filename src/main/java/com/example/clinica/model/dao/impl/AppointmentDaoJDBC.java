package com.example.clinica.model.dao.impl;

import com.example.clinica.db.DB;
import com.example.clinica.db.DbException;
import com.example.clinica.model.dao.AppointmentDao;
import com.example.clinica.model.entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoJDBC implements AppointmentDao {
    private final Connection connection;
    public AppointmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Appointment appointment) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO tb_atendimentos (atendimento_descricao, atendimento_data_horario,"
                        +" atendimento_duracao, atendimento_status, paciente_id, profissional_id) "
                        +"VALUES(?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, appointment.getDescription());
            st.setTimestamp(2, Timestamp.valueOf(appointment.getDateTime()));
            st.setTime(3, Time.valueOf(appointment.getDuration()));
            st.setInt(4, appointment.getStatus().getValue());
            st.setLong(5, appointment.getPacient().getId());
            st.setLong(6, appointment.getProfessional().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    appointment.setId(rs.getLong(1));
                    System.out.println("Done! id = " + appointment.getId());
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
    public void update(Appointment appointment) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                "UPDATE tb_atendimentos SET atendimento_descricao = ?, atendimento_data_horario = ?,"
                   + "atendimento_duracao = ?, atendimento_status = ?, paciente_id = ?, profissional_id = ?  "
                   + "WHERE atendimento_id = ?;"
            );
            st.setString(1, appointment.getDescription());
            st.setTimestamp(2, Timestamp.valueOf(appointment.getDateTime()));
            st.setTime(3, Time.valueOf(appointment.getDuration()));
            st.setInt(4, appointment.getStatus().getValue());
            st.setLong(5, appointment.getPacient().getId());
            st.setLong(6, appointment.getProfessional().getId());
            st.setLong(7, appointment.getId());

            st.executeUpdate();
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM tb_atendimentos WHERE atendimento_id = ?");
            st.setLong(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Appointment findById(Long id) {
        PreparedStatement st =  null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT atendimentos.*, pacientes.*, profissionais.* FROM tb_atendimentos AS atendimentos "
                            + "LEFT JOIN tb_pacientes AS pacientes ON atendimentos.paciente_id = pacientes.pacientes_id "
                            + "LEFT JOIN tb_profissionais AS profissionais ON atendimentos.profissional_id = profissionais.profissional_id "
                            + "WHERE atendimentos.atendimento_id = ?"
            );
            st.setLong(1, id);
            rs = st.executeQuery();
            if(rs.next()) {
                Pacient pacient = instantiatePacient(rs);
                Professional professional =  instantiateProfessional(rs);
                return instantiateAppointment(rs,pacient,professional);
            }

            return null;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Appointment> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT atendimentos.*, pacientes.*, profissionais.* FROM tb_atendimentos AS atendimentos "
                       + "LEFT JOIN tb_pacientes AS pacientes ON atendimentos.paciente_id = pacientes.pacientes_id "
                       + "LEFT JOIN tb_profissionais AS profissionais ON atendimentos.profissional_id = profissionais.profissional_id"
            );
            rs = st.executeQuery();

            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                Pacient pacient= instantiatePacient(rs);
                Professional professional =  instantiateProfessional(rs);
                appointments.add(instantiateAppointment(rs,pacient,professional));
            }
            return appointments;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
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

    private Pacient instantiatePacient(ResultSet rs) throws SQLException {
        Pacient pacient = new Pacient();
        pacient.setId(rs.getLong("paciente_id"));
        pacient.setName(rs.getString("paciente_nome"));
        pacient.setNumber(rs.getString("paciente_numero"));
        pacient.setNumberTwo(rs.getString("paciente_segundo_numero"));
        pacient.setBirthDate(rs.getDate("paciente_data_nascimento").toLocalDate());
        pacient.setCpf(rs.getString("paciente_cpf"));
        pacient.setStartTreatment(rs.getDate("paciente_inicio_tratamento").toLocalDate());
        pacient.setEndTreatment(rs.getDate("paciente_termino_tratamento").toLocalDate());
        pacient.setProfession(rs.getString("paciente_profissao"));
        pacient.setMaritalStatus(rs.getString("paciente_estado_civil"));
        pacient.setDlne(rs.getString("paciente_dlne"));
        pacient.setAddress(new Address());
        pacient.getAddress().setId(rs.getLong("endereco_id"));
        pacient.setAnamnese(new Anamnese());
        pacient.getAnamnese().setId(rs.getLong("anamnese_id"));
        return pacient;
    }

    private Appointment instantiateAppointment(ResultSet rs, Pacient pacient, Professional professional) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setDescription(rs.getString("atendimento_descricao"));
        appointment.setDateTime(rs.getTimestamp("atendimento_data_hora").toLocalDateTime());
        appointment.setDuration(rs.getTime("atendimento_duracao").toLocalTime());
        appointment.setPacient(pacient);

        return appointment;
    }

    @Override
    public List<Appointment> findByPatient(Long pacientId) {
        PreparedStatement st =  null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT atendimentos.*, pacientes.*, profissionais.* FROM tb_atendimentos AS atendimentos "
                       + "LEFT JOIN tb_pacientes AS pacientes ON atendimentos.paciente_id = pacientes.pacientes_id "
                       + "LEFT JOIN tb_profissionais AS profissionais ON atendimentos.profissional_id = profissionais.profissional_id "
                       + "WHERE atendimentos.paciente_id = ?"
            );
            st.setLong(1, pacientId);
            rs = st.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                Pacient pacient = instantiatePacient(rs);
                Professional professional =  instantiateProfessional(rs);
                appointments.add(instantiateAppointment(rs,pacient,professional));
            }

            return appointments;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Appointment> findByProfessional(Long professionalId) {
        PreparedStatement st =  null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT atendimentos.*, pacientes.*, profissionais.* FROM tb_atendimentos AS atendimentos "
                       + "LEFT JOIN tb_pacientes AS pacientes ON atendimentos.paciente_id = pacientes.pacientes_id "
                       + "LEFT JOIN tb_profissionais AS profissionais ON atendimentos.profissional_id = profissionais.profissional_id "
                       + "WHERE atendimentos.profissional_id = ?"
            );
            st.setLong(1, professionalId);
            rs = st.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (rs.next()) {
                Pacient pacient = instantiatePacient(rs);
                Professional professional =  instantiateProfessional(rs);
                appointments.add(instantiateAppointment(rs,pacient,professional));
            }

            return appointments;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
