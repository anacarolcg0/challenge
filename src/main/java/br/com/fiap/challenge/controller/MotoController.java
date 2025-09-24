package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.Moto;
import br.com.fiap.challenge.service.MotoService;
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
@RequestMapping("/motos")
public class MotoController {
        @Autowired
        MotoService motoService;

        @GetMapping("/lista")
        public String listarMotos(Model model) {
            List<Moto> motos = motoService.readMotos();
            model.addAttribute("listaMotos", motos);
            return "motoLista";
        }

        @GetMapping("/cadastro")
        public String cadastroMoto(Model model) {
            model.addAttribute("moto", new Moto());
            return "motoCadastro";
        }

        @PostMapping("/cadastrar")
        public String cadastrarMoto(@Valid Moto moto, BindingResult result, Model model) {
            if (result.hasErrors()) {
                model.addAttribute("moto", moto);
                return "motoCadastro";
            }
            if (moto.getIdMoto() == null) {
                motoService.createMoto(moto);
            } else {
                motoService.updateMoto(moto);
            }
            return listarMotos(model);
        }

        @GetMapping("/cadastro/{id}")
        public String cadastroMoto(@PathVariable Long id, Model model) {
            Moto moto = motoService.readMoto(id);
            if (moto == null) {
                listarMotos(model);
            }
            model.addAttribute("moto", moto);
            return "motoCadastro";
        }

        @GetMapping("/deletar/{id}")
        public String deletarMoto(@PathVariable Long id, Model model) {
            motoService.deleteMoto(id);
            return listarMotos(model);
        }
}
