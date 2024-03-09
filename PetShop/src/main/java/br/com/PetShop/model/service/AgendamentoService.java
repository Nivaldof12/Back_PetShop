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
    
    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento incluirAgendamento(Agendamento agendamento) {
        // Calcula horaInicio e horaFim com base na observação
        String observacao = agendamento.getObservacao();
        double horaInicio = convertStringToHours(observacao);
        double horaFim = horaInicio + 1; // Adiciona uma hora ao horaInicio

        // Verifica se já existem 3 agendamentos no mesmo dia entre horaInicio e horaFim
        String dia = agendamento.getDia();
        if (contarAgendamentosNoMesmoDiaEHora(dia, horaInicio, horaFim) >= 3) {
            throw new RuntimeException("Já existem 3 agendamentos no mesmo dia entre os horários fornecidos");
        }

        // Define os valores calculados no agendamento
        agendamento.setHoraInicio(horaInicio);
        agendamento.setHoraFim(horaFim);

        // Salva o agendamento
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
    
    // Método para contar agendamentos no mesmo dia entre horaInicio e horaFim
    private int contarAgendamentosNoMesmoDiaEHora(String dia, double horaInicio, double horaFim) {
        // Consulta o banco de dados para contar os agendamentos
        return agendamentoRepository.countByDiaAndHoraInicioBetween(dia, horaInicio, horaFim);
    }
    
	// Método que converte String para Hora
	private double convertStringToHours(String time) {
		if (time == null || time.isEmpty()) {
			return 0;
		}

		String[] parts = time.split(":");
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		return hours + minutes / 60.0;
	}
}
