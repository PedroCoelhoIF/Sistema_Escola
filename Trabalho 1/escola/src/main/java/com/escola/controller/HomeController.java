package com.escola.controller;

import com.escola.model.Curso;
import com.escola.repository.CursoRepository; // Import necessário
import org.springframework.beans.factory.annotation.Autowired; // Import necessário
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/pesquisar")
    public ModelAndView pesquisarCursos(@RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView("curso/resultados-busca");

        List<Curso> cursosEncontrados = cursoRepository.findCursoByNomeLike("%" + query + "%");

        mv.addObject("cursos", cursosEncontrados);
        mv.addObject("termoPesquisado", query);

        return mv;
    }
}