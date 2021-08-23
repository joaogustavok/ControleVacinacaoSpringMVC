package com.gft.desafiomvc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafiomvc.entities.Vacina;

import com.gft.desafiomvc.services.VacinaService;


@Controller
@RequestMapping("/vacina")
public class VacinaController {

	@Autowired
	private VacinaService vacinaService;

	@RequestMapping(path ="editar")
	public ModelAndView editarVacina(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("vacina/form.html");
		Vacina vacina;
		
		if(id==null) {
			vacina = new Vacina();
		} else {
			
			try {
				vacina = vacinaService.obterVacina(id);
			} catch (Exception e) {
				vacina = new Vacina();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("vacina", vacina);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarVacina(@Valid Vacina vacina,  BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("vacina/form.html");
		
		boolean novo = true;
		
		if(vacina.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("vacina", vacina);
			return mv;
		}
		
		vacinaService.salvarVacina(vacina);
		
		if(novo) {
			mv.addObject("vacina", new Vacina());
		} else {
			mv.addObject("vacina", vacina);
		}
		
	
		mv.addObject("mensagem", "Vacina salvo com sucesso");
	
		return mv;
}
	@RequestMapping
	public ModelAndView listarVacinas() {
		
		ModelAndView mv = new ModelAndView("vacina/listar.html");
		
		mv.addObject("lista", vacinaService.listarVacina());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirVacina(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/vacina");
		
		try {
			vacinaService.excluirVacina(id);
			redirectAttributes.addFlashAttribute("mensagem", "Vacina excluida com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir vacina."+e.getMessage());
		}
	
		return mv;
	}
	
}
