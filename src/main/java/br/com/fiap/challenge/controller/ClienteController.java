package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Cliente;
import br.com.fiap.challenge.entity.Moto;
import br.com.fiap.challenge.service.ClienteService;
import br.com.fiap.challenge.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MotoService motoService;

    @GetMapping("/lista")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.readClientes();
        model.addAttribute("listaClientes", clientes);
        return "clienteLista";
    }

    @GetMapping("/cadastro")
    public String cadastroCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("motoLista", motoService.readMotos());
        return "clienteCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarCliente(@Valid Cliente cliente, BindingResult result,
                                   @RequestParam(required = false) Long motoId,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cliente", cliente);
            model.addAttribute("motoLista", motoService.readMotos());
            return "clienteCadastro";
        }

        if (motoId != null) {
            Moto m = motoService.readMoto(motoId);
            cliente.setMoto(m);
        }

        if (cliente.getIdCliente() == null) {
            clienteService.createCliente(cliente);
        } else {
            clienteService.updateCliente(cliente);
        }

        return "redirect:/clientes/lista";
    }

    @GetMapping("/cadastro/{id}")
    public String cadastroCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.readCliente(id);
        if (cliente == null) {
            return "redirect:/clientes/lista";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("motoLista", motoService.readMotos());
        return "clienteCadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes/lista";
    }
}
