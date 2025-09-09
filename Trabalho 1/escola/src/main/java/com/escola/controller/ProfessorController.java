package com.escola.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.escola.dto.ProfessorDTO;
import com.escola.model.Professor;
import com.escola.repository.ProfessorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorRepository repository;

    // Define o diretório de upload de forma robusta
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/img/";

    @GetMapping("/inserir")
    public String inserir() {
        return "professor/inserir";
    }

    @PostMapping("/inserir")
    public String inserido(
            @ModelAttribute @Valid ProfessorDTO dto,
            BindingResult result,
            RedirectAttributes msg,
            @RequestParam("file") MultipartFile imagem) {
        if (result.hasErrors()) {
            msg.addFlashAttribute("erro", "Erro ao inserir: dados inválidos.");
            return "redirect:/professor/inserir";
        }

        var professor = new Professor();
        BeanUtils.copyProperties(dto, professor);

        if (!imagem.isEmpty()) {
            try {
                Path caminho = Paths.get(UPLOAD_DIR + imagem.getOriginalFilename());
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, imagem.getBytes());
                professor.setImagem(imagem.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                msg.addFlashAttribute("erro", "Falha ao salvar a imagem!");
                return "redirect:/professor/listar";
            }
        }

        repository.save(professor);
        msg.addFlashAttribute("inserirok", "Professor inserido com sucesso!");
        return "redirect:/professor/listar";
    }



    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("professor/listar"); // CORREÇÃO: Caminho da view
        List<Professor> lista = repository.findAll();
        mv.addObject("professores", lista);
        return mv;
    }

    @PostMapping("/listar")
    public ModelAndView listarprofessoresFind(@RequestParam("busca") String buscar) {

        ModelAndView mv = new ModelAndView("professor/listar");
        List<Professor> lista = repository.findProfessorByNomeLike("%" + buscar + "%");
        mv.addObject("professores", lista);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable(value = "id") int id) {
        Optional<Professor> professor = repository.findById(id);
        if (professor.isEmpty()) {
            return "redirect:/professor/listar";
        }
        repository.deleteById(id);
        return "redirect:/professor/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable(value = "id") int id) {
        ModelAndView mv = new ModelAndView("professor/editar");
        Optional<Professor> professor = repository.findById(id);
        mv.addObject("professor", professor.get());
        return mv;
    }

    @PostMapping("/editar/{id}")
    public String editado(
            @ModelAttribute @Valid ProfessorDTO dto,
            @RequestParam("file") MultipartFile imagem,
            @PathVariable(value = "id") int id,
            RedirectAttributes msg) {

        Optional<Professor> optionalProfessor = repository.findById(id);
        if (optionalProfessor.isEmpty()) {
            msg.addFlashAttribute("erro", "Professor não encontrado!");
            return "redirect:/professor/listar";
        }

        var professor = optionalProfessor.get();
        BeanUtils.copyProperties(dto, professor);

        if (!imagem.isEmpty()) {
            try {
                Path caminho = Paths.get(UPLOAD_DIR + imagem.getOriginalFilename());
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, imagem.getBytes());
                professor.setImagem(imagem.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                msg.addFlashAttribute("erro", "Falha ao atualizar a imagem!");
                return "redirect:/professor/listar";
            }
        }

        repository.save(professor);
        msg.addFlashAttribute("sucesso", "Professor editado com sucesso!");
        return "redirect:/professor/listar";
    }
}