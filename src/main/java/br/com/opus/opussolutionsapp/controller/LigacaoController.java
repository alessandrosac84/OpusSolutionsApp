package br.com.opus.opussolutionsapp.controller;

import br.com.opus.opussolutionsapp.dao.EmpregadoDao;
import br.com.opus.opussolutionsapp.dao.LigacaoDao;

import br.com.opus.opussolutionsapp.entity.Ligacao;
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
public class LigacaoController {

    @Autowired
    private LigacaoDao ligacaoDao;

    @GetMapping("/ligacao/list")
    public ModelMap ligacao(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("ligacao", ligacaoDao.findByNomeContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("ligacao", ligacaoDao.findAll(pageable));
        }
    }

    @GetMapping("/ligacao/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) Ligacao ligacao ) {
        if (ligacao == null) {
            ligacao = new Ligacao();
        }
        return new ModelMap("ligacao", ligacao);
    }

    @PostMapping("/ligacao/form")
    public String save(@Valid @ModelAttribute("ligacao") Ligacao ligacao , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "ligacao/form";
        }
        ligacaoDao.save(ligacao);
        status.setComplete();
        return "redirect:/ligacao/list";
    }

    @GetMapping("/ligacao/edit")
    public ModelMap edit(@RequestParam(value = "id", required = true) Ligacao ligacao) {
        return new ModelMap("ligacao", ligacao);
    }

    @PostMapping("/ligacao/edit")
    public String editConfirm(@Valid @ModelAttribute("ligacao") Ligacao ligacao , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "ligacao/edit";
        }
        ligacaoDao.save(ligacao);
        status.setComplete();
        return "redirect:/ligacao/list";
    }

    @GetMapping("/ligacao/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Ligacao ligacao) {
        return new ModelMap("ligacao", ligacao);
    }

    @PostMapping("/ligacao/delete")
    public Object delete(@ModelAttribute Ligacao ligacao , SessionStatus status) {
        try{
            ligacaoDao.delete(ligacao);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", ligacao.getNome())
                    .addObject("entityName", "Ligacao")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/ligacao/list");
        }
        status.setComplete();
        return "redirect:/ligacao/list";
    }
}