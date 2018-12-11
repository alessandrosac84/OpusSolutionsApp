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

import br.com.opus.opussolutionsapp.dao.SeguroDao;
import br.com.opus.opussolutionsapp.entity.Seguro;


@Controller
public class SeguroController {
    @Autowired
    private SeguroDao seguroDao;

    @GetMapping("/seguro/list")
    public ModelMap seguro(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("seguro", seguroDao.findByNomeContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("seguro", seguroDao.findAll(pageable));
        }
    }

    @GetMapping("/seguro/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) Seguro seguro ) {
        if (seguro == null) {
        	seguro = new Seguro();
        }
        return new ModelMap("seguro", seguro);
    }

    @PostMapping("/seguro/form")
    public String save(@Valid @ModelAttribute("seguro") Seguro seguro , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "seguro/form";
        }
        seguroDao.save(seguro);
        status.setComplete();
        return "redirect:/seguro/list";
    }

    @GetMapping("/seguro/edit")
    public ModelMap edit(@RequestParam(value = "id", required = true) Seguro seguro) {
        return new ModelMap("seguro", seguro);
    }
    
    @PostMapping("/seguro/edit")
    public String editConfirm(@Valid @ModelAttribute("seguro") Seguro seguro , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "seguro/edit";
        }
        seguroDao.save(seguro);
        status.setComplete();
        return "redirect:/seguro/list";
    }

    @GetMapping("/seguro/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Seguro seguro) {
        return new ModelMap("seguro", seguro);
    }

    @PostMapping("/seguro/delete")
    public Object delete(@ModelAttribute Seguro seguro , SessionStatus status) {
        try{
        	seguroDao.delete(seguro);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", seguro.getNome())
                    .addObject("entityName", "Seguro")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/seguro/list");
        }
        status.setComplete();
        return "redirect:/seguro/list";
    }
}


