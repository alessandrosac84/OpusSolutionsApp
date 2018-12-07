package br.com.opus.opussolutionsapp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import br.com.opus.opussolutionsapp.dao.ClienteDao;
import br.com.opus.opussolutionsapp.entity.Cliente;


@Controller
public class ClienteController {
    @Autowired
    private ClienteDao clienteDao;

    @GetMapping("/cliente/list")
    public ModelMap cliente(@PageableDefault(size = 5) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("cliente", clienteDao.findByNomeContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("cliente", clienteDao.findAll(pageable));
        }
    }

    @GetMapping("/cliente/form")
    public ModelMap showForm(@RequestParam(value = "id", required = false) Cliente cliente ) {
        if (cliente == null) {
        	cliente = new Cliente();
        }
        return new ModelMap("cliente", cliente);
    }

    @PostMapping("/cliente/form")
    public String save(@Valid @ModelAttribute("cliente") Cliente cliente , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "cliente/form";
        }
        clienteDao.save(cliente);
        status.setComplete();
        return "redirect:/cliente/list";
    }

    @GetMapping("/cliente/edit")
    public ModelMap edit(@RequestParam(value = "id", required = true) Cliente cliente) {
        return new ModelMap("cliente", cliente);
    }
    
    @PostMapping("/cliente/edit")
    public String editConfirm(@Valid @ModelAttribute("cliente") Cliente cliente , BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "cliente/edit";
        }
        clienteDao.save(cliente);
        status.setComplete();
        return "redirect:/cliente/list";
    }

    @GetMapping("/cliente/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Cliente cliente) {
        return new ModelMap("cliente", cliente);
    }

    @PostMapping("/cliente/delete")
    public Object delete(@ModelAttribute Cliente cliente , SessionStatus status) {
        try{
        	clienteDao.delete(cliente);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", cliente.getNome())
                    .addObject("entityName", "Cliente")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/cliente/list");
        }
        status.setComplete();
        return "redirect:/cliente/list";
    }
}


