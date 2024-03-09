package br.com.PetShop.model.repository;

import br.com.PetShop.model.domain.Agendamento;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

	@Query("SELECT COUNT(a) FROM Agendamento a WHERE a.dia = :dia AND ((a.horaInicio >= :horaInicio AND a.horaInicio <= :horaFim) OR (a.horaFim >= :horaInicio AND a.horaFim <= :horaFim) OR (a.observacao >= :horaInicio AND a.observacao <= :horaFim))")
    int countByDiaAndHoraInicioBetween(String dia, double horaInicio, double horaFim);
}