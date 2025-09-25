package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Moto;
import br.com.fiap.challenge.entity.Patio;
import br.com.fiap.challenge.service.MotoService;
import br.com.fiap.challenge.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @Autowired
    private PatioService patioService;

    @GetMapping("/lista")
    public String listarMotos(Model model) {
        List<Moto> motos = motoService.readMotos();
        model.addAttribute("listaMotos", motos);
        return "motoLista";
    }

    @GetMapping("/cadastro")
    public String cadastroMoto(Model model) {
        model.addAttribute("moto", new Moto());
        model.addAttribute("patioLista", patioService.readPatios());
        return "motoCadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrarMoto(@Valid Moto moto, BindingResult result,
                                @RequestParam(required = false) Long patioId,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("moto", moto);
            model.addAttribute("patioLista", patioService.readPatios());
            return "motoCadastro";
        }

        if (patioId != null) {
            Patio p = patioService.readPatio(patioId);
            moto.setPatio(p);
        }

        if (moto.getIdMoto() == null) {
            motoService.createMoto(moto);
        } else {
            motoService.updateMoto(moto);
        }

        return "redirect:/motos/lista";
    }

    @GetMapping("/cadastro/{id}")
    public String cadastroMoto(@PathVariable Long id, Model model) {
        Moto moto = motoService.readMoto(id);
        if (moto == null) {
            return "redirect:/motos/lista";
        }
        model.addAttribute("moto", moto);
        model.addAttribute("patioLista", patioService.readPatios());
        return "motoCadastro";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMoto(@PathVariable Long id) {
        motoService.deleteMoto(id);
        return "redirect:/motos/lista";
    }
}
