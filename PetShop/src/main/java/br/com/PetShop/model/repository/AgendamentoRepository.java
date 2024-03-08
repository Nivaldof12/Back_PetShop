package br.com.PetShop.model.repository;

import br.com.PetShop.model.domain.Agendamento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

    // Método para contar os agendamentos no mesmo dia e hora
    long countByDiaAndObservacao(String dia, String observacao);
}