package br.com.opus.opussolutionsapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import br.com.opus.opussolutionsapp.dao.TipoSeguroDao;
import br.com.opus.opussolutionsapp.entity.TipoSeguro;


@Controller
public class TipoSeguroController {
    @Autowired
    private TipoSeguroDao tipoSeguroDao;

    @GetMapping("/tipoSeguro/list")
    public ModelMap tipoSeguro(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("tipoSeguro", tipoSeguroDao.findByNomeContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("tipoSeguro", tipoSeguroDao.findAll(pageable));
        }
    }

    @GetMapping("/tipoSeguro/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) TipoSeguro tipoSeguro ) {
        if (tipoSeguro == null) {
        	tipoSeguro = new TipoSeguro();
        }
        return new ModelMap("tipoSeguro", tipoSeguro);
    }

    @PostMapping("/tipoSeguro/form")
    public String save(@Valid @ModelAttribute("tipoSeguro") TipoSeguro tipoSeguro , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "tipoSeguro/form";
        }
        tipoSeguroDao.save(tipoSeguro);
        status.setComplete();
        return "redirect:/tipoSeguro/list";
    }

    @GetMapping("/tipoSeguro/edit")
    public ModelMap edit(@RequestParam(value = "id", required = true) TipoSeguro tipoSeguro) {
        return new ModelMap("tipoSeguro", tipoSeguro);
    }
    
    @PostMapping("/tipoSeguro/edit")
    public String editConfirm(@Valid @ModelAttribute("tipoSeguro") TipoSeguro tipoSeguro , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "tipoSeguro/edit";
        }
        tipoSeguroDao.save(tipoSeguro);
        status.setComplete();
        return "redirect:/tipoSeguro/list";
    }

    @GetMapping("/tipoSeguro/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) TipoSeguro tipoSeguro) {
        return new ModelMap("tipoSeguro", tipoSeguro);
    }

    @PostMapping("/tipoSeguro/delete")
    public Object delete(@ModelAttribute TipoSeguro tipoSeguro , SessionStatus status) {
        try{
        	tipoSeguroDao.delete(tipoSeguro);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", tipoSeguro.getNome())
                    .addObject("entityName", "TipoSeguro")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/tipoSeguro/list");
        }
        status.setComplete();
        return "redirect:/tipoSeguro/list";
    }
}


