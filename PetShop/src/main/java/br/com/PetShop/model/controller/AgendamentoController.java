package br.com.PetShop.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.PetShop.model.domain.Agendamento;
import br.com.PetShop.model.service.AgendamentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // Endpoint para incluir um novo agendamento
    @PostMapping("/incluir")
    public ResponseEntity<Agendamento> incluirAgendamento(@RequestBody Agendamento agendamento) {

        Agendamento novoAgendamento = agendamentoService.incluirAgendamento(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(novoAgendamento);
    }

    // Endpoint para obter todos os agendamentos
    @GetMapping("/todos")
    public ResponseEntity<List<Agendamento>> obterTodosAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.obterTodosAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    // Endpoint para obter um agendamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> obterAgendamentoPorId(@PathVariable Long id) {
        Optional<Agendamento> agendamento = agendamentoService.obterAgendamentoPorId(id);
        return agendamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para editar um agendamento existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarAgendamento(@PathVariable Long id, @Validated @RequestBody Agendamento novoAgendamento) {
        // Verificar se o agendamento existe
        Optional<Agendamento> agendamentoExistenteOptional = agendamentoService.obterAgendamentoPorId(id);
        if (agendamentoExistenteOptional.isPresent()) {
            Agendamento agendamentoExistente = agendamentoExistenteOptional.get();
            // Atualiza os atributos do agendamento existente com os valores do novo agendamento
            agendamentoExistente.setPerfilPet(novoAgendamento.getPerfilPet());
            agendamentoExistente.setDia(novoAgendamento.getDia());
            agendamentoExistente.setTipo(novoAgendamento.getTipo());
            agendamentoExistente.setObservacao(novoAgendamento.getObservacao());

            // Salva as alterações no banco de dados
            agendamentoService.editarAgendamento(id, agendamentoExistente);
            return ResponseEntity.ok("Agendamento alterado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Endpoint para excluir um agendamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long id) {
        agendamentoService.excluirAgendamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
