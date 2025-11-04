package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Diagnostico;
import br.com.fiap.challenge.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoRepository repository;

    @GetMapping("/novo")
    public String novoDiagnostico(Model model) {
        model.addAttribute("diagnostico", new Diagnostico());
        return "diagnostico";
    }

    @PostMapping("/salvar")
    public String salvarDiagnostico(@ModelAttribute Diagnostico diagnostico) {
        diagnostico.setStatus("PENDENTE");
        repository.save(diagnostico);
        return "redirect:/diagnosticos/lista";
    }

    @GetMapping("/lista")
    public String listarDiagnosticos(Model model) {
        model.addAttribute("diagnosticos", repository.findAll());
        return "diagnosticoLista";
    }

    @PostMapping("/atualizar-status/{id}")
    public String atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        Diagnostico d = repository.findById(id).orElseThrow();
        d.setStatus(status);
        repository.save(d);
        return "redirect:/diagnosticos/lista";
    }
}
