package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Agendamento;
import br.com.fiap.challenge.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // Página de novo agendamento
    @GetMapping("/novo")
    public String novoAgendamento(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        return "agendamento";
    }

    // Salvar agendamento
    @PostMapping("/salvar")
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento) {
        agendamento.setStatusAgendamento("PENDENTE");
        agendamentoRepository.save(agendamento);
        return "redirect:/agendamentos/lista";
    }

    // Listar todos os agendamentos
    @GetMapping("/lista")
    public String listarAgendamentos(Model model) {
        model.addAttribute("agendamentos", agendamentoRepository.findAll());
        return "agendamentoLista";
    }

    // Atualizar o status do agendamento
    @PostMapping("/atualizar-status/{id}")
    public String atualizarStatusAgendamento(@PathVariable Long id, @RequestParam("status") String status) {
        Agendamento a = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado: " + id));

        a.setStatusAgendamento(status);
        agendamentoRepository.save(a);

        return "redirect:/agendamentos/lista";
    }
}
