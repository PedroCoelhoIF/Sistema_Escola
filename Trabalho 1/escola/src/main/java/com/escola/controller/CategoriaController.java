package com.escola.controller;


import com.escola.dto.CategoriaDTO;
import com.escola.model.Categoria;
import com.escola.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;

    @GetMapping("/inserir")
    public String inserir() {
        return "categoria/inserir";
    }

    @PostMapping("/inserir")
    public String inserido(
            @ModelAttribute @Valid CategoriaDTO dto,
            RedirectAttributes msg) {

        var categoria = new Categoria();
        BeanUtils.copyProperties(dto, categoria);

        repository.save(categoria);
        msg.addFlashAttribute("inserirok", "Categoria inserida com sucesso!");
        return "redirect:/categoria/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("categoria/listar");
        List<Categoria> lista = repository.findAll();
        mv.addObject("categorias", lista);
        return mv;
    }

    @PostMapping("/listar")
    public ModelAndView listarCategoriasFind(@RequestParam("busca") String buscar) {
        ModelAndView mv = new ModelAndView("categoria/listar");
        List<Categoria> lista = repository.findCategoriaByNomeLike("%" + buscar + "%");
        mv.addObject("categorias", lista);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable(value = "id") int id, RedirectAttributes msg) {
        Optional<Categoria> categoria = repository.findById(id);
        if (categoria.isEmpty()) {
            msg.addFlashAttribute("erro", "Categoria não encontrada!");
            return "redirect:/categoria/listar";
        }
        repository.deleteById(id);
        msg.addFlashAttribute("excluirok", "Categoria excluída com sucesso!");
        return "redirect:/categoria/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable(value = "id") int id) {
        ModelAndView mv = new ModelAndView("categoria/editar");
        Optional<Categoria> categoria = repository.findById(id);
        mv.addObject("categoria", categoria.get());
        return mv;
    }

    @PostMapping("/editar/{id}")
    public String editado(
            @ModelAttribute @Valid CategoriaDTO dto,
            @PathVariable(value = "id") int id,
            RedirectAttributes msg) {

        Optional<Categoria> optionalCategoria = repository.findById(id);
        if (optionalCategoria.isEmpty()) {
            msg.addFlashAttribute("erro", "Categoria não encontrada!");
            return "redirect:/categoria/listar";
        }

        var categoria = optionalCategoria.get();
        BeanUtils.copyProperties(dto, categoria); // Copia o novo nome

        repository.save(categoria);
        msg.addFlashAttribute("sucesso", "Categoria editada com sucesso!");
        return "redirect:/categoria/listar";
    }
}