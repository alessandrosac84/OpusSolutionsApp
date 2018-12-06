package br.com.opus.opussolutionsapp.controller;

import br.com.opus.opussolutionsapp.dao.EmpregadoDao;
import br.com.opus.opussolutionsapp.dao.EnderecoDao;
import br.com.opus.opussolutionsapp.entity.Endereco;

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
public class EnderecoController {

    @Autowired
    private EmpregadoDao empregadoDao;

    @Autowired
    private EnderecoDao enderecoDao;


    @RequestMapping("/endereco/list")
    public String endereco(Model model, @PageableDefault(size = 5) Pageable pageable,
                         @RequestParam(name = "value", required = false) String value) {
        if (value != null) {
            model.addAttribute("key", value);
            model.addAttribute("data", enderecoDao.findByNomeContainingIgnoreCase(value, pageable));
        } else {
            model.addAttribute("data", enderecoDao.findAll(pageable));
        }
        return "endereco/list";

    }


    @GetMapping("/endereco/form")
    public String showForm(@RequestParam(value = "id", required = false) Endereco endereco, Model m) {
        if (endereco == null) {
            endereco = new Endereco();
        }
        m.addAttribute("endereco", endereco);
        m.addAttribute("empregado", empregadoDao.findAll());
        return "endereco/form";
    }


    @PostMapping("/endereco/form")
    public String save(@ModelAttribute @Valid Endereco endereco, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "endereco/form";
        }
        enderecoDao.save(endereco);
        status.setComplete();
        return "redirect:/endereco/list";
    }



    @GetMapping("/endereco/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Endereco endereco) {
        return new ModelMap("endereco", endereco);
    }



    @PostMapping("/endereco/delete")
    public Object delete(@ModelAttribute Endereco endereco, SessionStatus status) {
        try {
            enderecoDao.delete(endereco);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", endereco.getNome())
                    .addObject("entityName", "Endereco")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/enredeco/list");
        }
        status.setComplete();
        return "redirect:/endereco/list";
    }
}