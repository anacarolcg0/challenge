package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.Moto;
import br.com.fiap.challenge.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {
    private final MotoRepository motoRepository;

        @Autowired
        public MotoService(MotoRepository motoRepository) {
            this.motoRepository = motoRepository;
        }

        // CRUD
        public Moto createMoto(Moto moto) {
            return motoRepository.save(moto);
        }

        public Moto readMoto(Long id) {
            return motoRepository.findById(id).orElse(null);
        }

        public List<Moto> readMotos() {
            return motoRepository.findAll();
        }

        public Moto updateMoto(Moto moto) {
            return motoRepository.save(moto);
        }

        public void deleteMoto(Long id) {
            motoRepository.deleteById(id);
        }
}

