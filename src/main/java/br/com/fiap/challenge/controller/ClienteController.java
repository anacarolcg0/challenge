package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/lista")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.readClientes();
        model.addAttribute("listaClientes", clientes);
        return "clienteLista";
    }

    @GetMapping("/cadastro")
    public String cadastroCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarCliente(@Valid Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return "clienteCadastro";
        }
        if (cliente.getIdCliente() == null) {
            clienteService.createCliente(cliente);
        } else {
            clienteService.updateCliente(cliente);
        }
        return listarClientes(model);
    }

    @GetMapping("/cadastro/{id}")
    public String cadastroCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.readCliente(id);
        if (cliente == null) {
            listarClientes(model);
        }
        model.addAttribute("cliente", cliente);
        return "clienteCadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id, Model model) {
        clienteService.deleteCliente(id);
        return listarClientes(model);
    }
}