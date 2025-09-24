package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Patio;
import br.com.fiap.challenge.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patios")
public class PatioController {
        @Autowired
        PatioService patioService;

        @GetMapping("/lista")
        public String listarPatios(Model model) {
            List<Patio> patios = patioService.readPatios();
            model.addAttribute("listaPatios", patios);
            return "patioLista";
        }

        @GetMapping("/cadastro")
        public String cadastroPatio(Model model) {
            model.addAttribute("patio", new Patio());
            return "patioCadastro";
        }

        @PostMapping("/cadastrar")
        public String cadastrarPatio(@Valid Patio patio, BindingResult result, Model model) {
            if (result.hasErrors()) {
                model.addAttribute("patio", patio);
                return "patioCadastro";
            }
            if (patio.getIdPatio() == null) {
                patioService.createPatio(patio);
            } else {
                patioService.updatePatio(patio);
            }
            return listarPatios(model);
        }

        @GetMapping("/cadastro/{id}")
        public String cadastroPatio(@PathVariable Long id, Model model) {
            Patio patio = patioService.readPatio(id);
            if (patio == null) {
                listarPatios(model);
            }
            model.addAttribute("patio", patio);
            return "patioCadastro";
        }

        @GetMapping("/deletar/{id}")
        public String deletarPatio(@PathVariable Long id, Model model) {
            patioService.deletePatio(id);
            return listarPatios(model);
        }
}

