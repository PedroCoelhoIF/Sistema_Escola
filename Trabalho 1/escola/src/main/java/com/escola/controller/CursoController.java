package com.escola.controller;


import com.escola.dto.CursoDTO;
import com.escola.model.Categoria;
import com.escola.model.Curso;
import com.escola.model.Professor;
import com.escola.repository.CategoriaRepository;
import com.escola.repository.CursoRepository;
import com.escola.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/img/";

    @GetMapping("/inserir")
    public ModelAndView inserir() {
        ModelAndView mv = new ModelAndView("curso/inserir");

        List<Professor> professores = professorRepository.findAll();
        List<Categoria> categorias = categoriaRepository.findAll();
        mv.addObject("todosProfessores", professores);
        mv.addObject("todasCategorias", categorias);
        mv.addObject("cursoDTO", new CursoDTO(null, null, null, null, null, null)); // Adiciona um DTO vazio para o form
        return mv;
    }

    @PostMapping("/inserir")
    public String inserido(@ModelAttribute @Valid CursoDTO dto, BindingResult result,
                           @RequestParam("file") MultipartFile imagem, RedirectAttributes msg) {

        if (result.hasErrors()) {

            msg.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/curso/inserir";
        }

        Curso curso = new Curso();
        BeanUtils.copyProperties(dto, curso);


        professorRepository.findById(dto.professorId()).ifPresent(curso::setProfessor);
        categoriaRepository.findById(dto.categoriaId()).ifPresent(curso::setCategoria);


        if (!imagem.isEmpty()) {
            try {
                Path caminho = Paths.get(UPLOAD_DIR + imagem.getOriginalFilename());
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, imagem.getBytes());
                curso.setImagem(imagem.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                msg.addFlashAttribute("erro", "Falha ao salvar a imagem!");
                return "redirect:/curso/listar";
            }
        }

        cursoRepository.save(curso);
        msg.addFlashAttribute("inserirok", "Curso inserido com sucesso!");
        return "redirect:/curso/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("curso/listar");
        List<Curso> cursos = cursoRepository.findAll();
        mv.addObject("cursos", cursos);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("curso/editar");
        Optional<Curso> optionalCurso = cursoRepository.findById(id);

        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();

            CursoDTO dto = new CursoDTO(curso.getNome(), curso.getDescricao(), curso.getDataInicio(), curso.getDataFim(),
                    curso.getProfessor() != null ? curso.getProfessor().getId() : null,
                    curso.getCategoria() != null ? curso.getCategoria().getId() : null);
            mv.addObject("cursoDTO", dto);
            mv.addObject("cursoId", curso.getId());

            mv.addObject("todosProfessores", professorRepository.findAll());
            mv.addObject("todasCategorias", categoriaRepository.findAll());
        }
        return mv;
    }

    @PostMapping("/editar/{id}")
    public String editado(@PathVariable("id") Integer id, @ModelAttribute @Valid CursoDTO dto, BindingResult result,
                          @RequestParam("file") MultipartFile imagem, RedirectAttributes msg) {

        if (result.hasErrors()) {
            msg.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/curso/editar/" + id;
        }

        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isEmpty()) {
            msg.addFlashAttribute("erro", "Curso não encontrado!");
            return "redirect:/curso/listar";
        }

        Curso curso = optionalCurso.get();
        BeanUtils.copyProperties(dto, curso);
        professorRepository.findById(dto.professorId()).ifPresent(curso::setProfessor);
        categoriaRepository.findById(dto.categoriaId()).ifPresent(curso::setCategoria);

        if (!imagem.isEmpty()) {
            try {
                Path caminho = Paths.get(UPLOAD_DIR + imagem.getOriginalFilename());
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, imagem.getBytes());
                curso.setImagem(imagem.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cursoRepository.save(curso);
        msg.addFlashAttribute("sucesso", "Curso editado com sucesso!");
        return "redirect:/curso/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes msg) {
        if (!cursoRepository.existsById(id)) {
            msg.addFlashAttribute("erro", "Curso não encontrado!");
            return "redirect:/curso/listar";
        }
        cursoRepository.deleteById(id);
        msg.addFlashAttribute("excluirok", "Curso excluído com sucesso!");
        return "redirect:/curso/listar";
    }
}