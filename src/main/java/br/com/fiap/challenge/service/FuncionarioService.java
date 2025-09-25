package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.Funcionario;
import br.com.fiap.challenge.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    // CRUD
    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario readFuncionario(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public List<Funcionario> readFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario updateFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
