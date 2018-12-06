package br.com.opus.opussolutionsapp.controller;

import br.com.opus.opussolutionsapp.dao.EmpregadoDao;
import br.com.opus.opussolutionsapp.entity.Empregado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class EmpregadoController {
    @Autowired
    private EmpregadoDao empregadoDao;


    @GetMapping("/empregado/list")
    public ModelMap empregado(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("empregado", empregadoDao.findByNomeContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("empregado", empregadoDao.findAll(pageable));
        }
    }



    @GetMapping("/empregado/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) Empregado empregado ) {
        if (empregado == null) {
            empregado = new Empregado();
        }
        return new ModelMap("empregado", empregado);
    }




    @PostMapping("/empregado/form")
    public String save(@Valid @ModelAttribute("empregado") Empregado empregado , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "empregado/form";
        }
        empregadoDao.save(empregado);
        status.setComplete();
        return "redirect:/empregado/list";
    }




    @GetMapping("/empregado/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Empregado empregado ) {
        return new ModelMap("empregado", empregado);
    }




    @PostMapping("/empregado/delete")
    public Object delete(@ModelAttribute Empregado empregado , SessionStatus status) {
        try{
            empregadoDao.delete(empregado);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", empregado.getNome())
                    .addObject("entityName", "Empregado")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/empregado/list");
        }
        status.setComplete();
        return "redirect:/empregado/list";
    }
}


