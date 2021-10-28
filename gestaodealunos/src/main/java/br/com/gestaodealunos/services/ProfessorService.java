package br.com.gestaodealunos.services;

import br.com.gestaodealunos.dto.ProfessorDTO;
import br.com.gestaodealunos.entities.Professor;
import br.com.gestaodealunos.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    public Professor editarProfessor(ProfessorDTO professorDTO) throws Exception {
        Optional<Professor> professor = professorRepository.findById(professorDTO.getId());

        try{
            professor.orElseThrow(() -> new UsernameNotFoundException("Professor com id " + professorDTO.getId() + " não encontrado."));

            if(!professorDTO.getEmail().equals(professor.get().getEmail())){
                throw new Exception("Email não correspondente ao cadastrado no banco.");
            }

            Professor professorNoBanco = professor.get();

            professorNoBanco.setNome(professorDTO.getNome());
            professorNoBanco.setSobrenome(professorDTO.getSobrenome());
            professorNoBanco.setSenha(new BCryptPasswordEncoder().encode(professorDTO.getSenha()));
            professorNoBanco.setDataCadastro(professorDTO.getDataCadastro());
            professorNoBanco.setDataUltimaAtualizacao(professorDTO.getDataUltimaAtualizacao());

            return professorRepository.save(professorNoBanco);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void deleteProfessor(ProfessorDTO professorDTO) {
        Optional<Professor> professor = professorRepository.findById(professorDTO.getId());

        try{
            professor.orElseThrow(() -> new UsernameNotFoundException("Professor com id " + professorDTO.getId() + " não encontrado."));

            if(!professorDTO.getEmail().equals(professor.get().getEmail())){
                throw new Exception("Email não correspondente ao cadastrado no banco.");
            }

            professorRepository.deleteById(professorDTO.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Professor> listaProfessores(){
        return professorRepository.findAll();
    }

    public Professor getProfessor(Long id){
        return professorRepository.getById(id);
    }
}
