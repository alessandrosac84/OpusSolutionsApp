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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import br.com.opus.opussolutionsapp.dao.ClienteDao;
import br.com.opus.opussolutionsapp.dao.SeguroDao;
import br.com.opus.opussolutionsapp.dao.TipoSeguroDao;
import br.com.opus.opussolutionsapp.entity.Cliente;
import br.com.opus.opussolutionsapp.entity.FileModel;
import br.com.opus.opussolutionsapp.entity.Seguro;


@Controller
public class SeguroController {
  @Autowired
  private SeguroDao seguroDao;

  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private TipoSeguroDao tipoSeguroDao;

  private Cliente cliente;

  @GetMapping("/seguro/list")
  public ModelMap seguro(@PageableDefault(size = 5) Pageable pageable,
      @RequestParam(name = "value", required = false) String value, Model model) {
    if (value != null) {
      model.addAttribute("key", value);
      return new ModelMap().addAttribute("seguro",
          seguroDao.findByNomeContainingIgnoreCase(value, pageable));
    } else {
      return new ModelMap().addAttribute("seguro", seguroDao.findAll(pageable));
    }
  }

  @GetMapping("/seguro/form")
  public ModelMap showForm(@RequestParam(value = "id", required = false) Seguro seguro) {
    if (seguro == null) {
      seguro = new Seguro();
    }

    ModelMap map = new ModelMap();
    map.addAttribute("tiposDeSeguro", tipoSeguroDao.findAll());
    map.addAttribute("seguro", seguro);
    return map;
  }

  @PostMapping("/seguro/form")
  public String save(@Valid @ModelAttribute("seguro") Seguro seguro,
      @ModelAttribute("tipoSeguro") String tipoSeguro, @ModelAttribute("proposta") FileModel proposta, 
      @ModelAttribute("apolice") FileModel apolice,  BindingResult errors, SessionStatus status,
      Pageable pageable) {
    seguro.setCliente(new Cliente());
    if (errors.hasErrors()) {
      return "seguro/form";
    }

    if (seguro.getTipoPessoa().equals("FISICA")) {
      seguro.setCliente(clienteDao.findByCpf(seguro.getCpf()));
    } else if (seguro.getTipoPessoa().equals("JURIDICA")) {
      seguro.setCliente(clienteDao.findByCnpj(seguro.getCnpj()));
    } else {

    }

    seguro.setTipoSeguro(tipoSeguroDao.findByNome(tipoSeguro));

    if(seguro.getCliente() == null) {
      errors.addError(new ObjectError("ClienteNotFound", "Cliente não existe"));
    }
    
    if(seguro.getTipoSeguro() == null) {
      errors.addError(new ObjectError("TipoSeguroNotFound", "Tipo Seguro não existe"));
    }
    
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
  public String editConfirm(@Valid @ModelAttribute("seguro") Seguro seguro, BindingResult errors,
      SessionStatus status) {
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
  public Object delete(@ModelAttribute Seguro seguro, SessionStatus status) {
    try {
      seguroDao.delete(seguro);
    } catch (DataIntegrityViolationException exception) {
      status.setComplete();
      return new ModelAndView("error/errorHapus").addObject("entityId", seguro.getNome())
          .addObject("entityName", "Seguro")
          .addObject("errorCause", exception.getRootCause().getMessage())
          .addObject("backLink", "/seguro/list");
    }
    status.setComplete();
    return "redirect:/seguro/list";
  }

  // @GetMapping("seguro/search")
  // public String search(@ModelAttribute("cpf") String cpf, Model model ) {
  //
  // System.out.println(cpf);
  //
  // return "seguro/form";
  // }

  @RequestMapping(value = "/search")
  public Object postPrintHello(@ModelAttribute("cpfcnpj") String cpfcnpj,
      @ModelAttribute("tipoPessoa") String tipoPessoa, Model model, BindingResult errors,
      SessionStatus status) {
    ModelAndView model1 = new ModelAndView();
    Seguro seguro = new Seguro();

    if (errors.hasErrors()) {
      model1.setViewName("seguro/form");
      return model1;
    }

    try {
      if (tipoPessoa.equals("FISICA")) {
        seguro.setCliente(clienteDao.findByCpf(cpfcnpj));
      } else {
        seguro.setCliente(clienteDao.findByCnpj(cpfcnpj));
      }

      model1.setViewName("seguro/form");

      return "redirect:/seguro/form";

    } catch (Exception e) {
      errors.addError(new ObjectError("ClienteNotFound", "Cliente não existe"));
      model1.setViewName("seguro/form");
      return model1;

    }



    // model1.addObject("cliente", new Cliente());

  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

}


