package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Funcionario;
import br.com.fiap.challenge.service.FuncionarioService;
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
@RequestMapping("/funcionarios")
public class FuncionarioController {

        @Autowired
        FuncionarioService funcionarioService;

        @GetMapping("/lista")
        public String listarFuncionarios(Model model) {
            List<Funcionario> funcionarios = funcionarioService.readFuncionarios();
            model.addAttribute("listaFuncionarios", funcionarios);
            return "funcionarioLista";
        }

        @GetMapping("/cadastro")
        public String cadastroFuncionario(Model model) {
            model.addAttribute("funcionario", new Funcionario());
            return "funcionarioCadastro";
        }

        @PostMapping("/cadastrar")
        public String cadastrarFuncionario(@Valid Funcionario funcionario, BindingResult result, Model model) {
            if (result.hasErrors()) {
                model.addAttribute("funcionario", funcionario);
                return "funcionarioCadastro";
            }
            if (funcionario.getIdFuncionario() == null) {
                funcionarioService.createFuncionario(funcionario);
            } else {
                funcionarioService.updateFuncionario(funcionario);
            }
            return listarFuncionarios(model);
        }

        @GetMapping("/cadastro/{id}")
        public String cadastroFuncionario(@PathVariable Long id, Model model) {
            Funcionario funcionario = funcionarioService.readFuncionario(id);
            if (funcionario == null) {
                listarFuncionarios(model);
            }
            model.addAttribute("funcionario", funcionario);
            return "funcionarioCadastro";
        }

        @GetMapping("/deletar/{id}")
        public String deletarFuncionario(@PathVariable Long id, Model model) {
            funcionarioService.deleteFuncionario(id);
            return listarFuncionarios(model);
        }
}
