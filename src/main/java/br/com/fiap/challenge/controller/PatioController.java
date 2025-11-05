package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Funcionario;
import br.com.fiap.challenge.entity.Patio;
import br.com.fiap.challenge.service.FuncionarioService;
import br.com.fiap.challenge.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/lista")
    public String listarPatios(Model model) {
        List<Patio> patios = patioService.readPatios();
        model.addAttribute("listaPatios", patios);
        return "patioLista";
    }

    @GetMapping("/cadastro")
    public String cadastroPatio(Model model) {
        model.addAttribute("patio", new Patio());
        model.addAttribute("funcionarioLista", funcionarioService.readFuncionarios());
        return "patioCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarPatio(@Valid Patio patio, BindingResult result,
                                 @RequestParam(required = false) Long funcionarioId,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("patio", patio);
            model.addAttribute("funcionarioLista", funcionarioService.readFuncionarios());
            return "patioCadastro";
        }

        if (funcionarioId != null) {
            Funcionario f = funcionarioService.readFuncionario(funcionarioId);
            patio.setFuncionario(f);
        }

        if (patio.getIdPatio() == null) {
            patioService.createPatio(patio);
        } else {
            patioService.updatePatio(patio);
        }

        return "redirect:/patios/lista";
    }

    @GetMapping("/cadastro/{id}")
    public String cadastroPatio(@PathVariable Long id, Model model) {
        Patio patio = patioService.readPatio(id);
        if (patio == null) {
            return "redirect:/patios/lista";
        }
        model.addAttribute("patio", patio);
        model.addAttribute("funcionarioLista", funcionarioService.readFuncionarios());
        return "patioCadastro";
    }

    @PostMapping("/deletar/{id}")
    public String deletarPatio(@PathVariable Long id) {
        patioService.deletePatio(id);
        return "redirect:/patios/lista";
    }
}
