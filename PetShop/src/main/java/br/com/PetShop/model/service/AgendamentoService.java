package br.com.PetShop.model.service;

import br.com.PetShop.model.domain.Agendamento;

import br.com.PetShop.model.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento incluirAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> obterTodosAgendamentos() {
        return StreamSupport.stream(agendamentoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Agendamento> obterAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    public Agendamento editarAgendamento(Long id, Agendamento novoAgendamento) {
        novoAgendamento.setId(id);
        return agendamentoRepository.save(novoAgendamento);
    }

    public void excluirAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
