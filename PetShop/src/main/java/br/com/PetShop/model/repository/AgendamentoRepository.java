package br.com.PetShop.model.repository;

import br.com.PetShop.model.domain.Agendamento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

}