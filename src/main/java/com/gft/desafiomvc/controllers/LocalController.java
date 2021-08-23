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

import com.gft.desafiomvc.entities.Local;
import com.gft.desafiomvc.services.LocalService;

@Controller
@RequestMapping("/local")
public class LocalController {

	@Autowired
	private LocalService localService;
	
	@RequestMapping(path ="editar")
	public ModelAndView editarLocal(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("local/form.html");
		Local local;
		
		if(id==null) {
			local = new Local();
		} else {
			
			try {
				local = localService.obterLocal(id);
			} catch (Exception e) {
				local = new Local();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("local", local);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarLocal(@Valid Local local,  BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("local/form.html");
		
		boolean novo = true;
		
		if(local.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("local", local);
			return mv;
		}
		
		localService.salvarLocal(local);
		
		if(novo) {
			mv.addObject("local", new Local());
		} else {
			mv.addObject("local", local);
		}
		
	
		mv.addObject("mensagem", "Local salvo com sucesso");
	
		return mv;
}
	@RequestMapping
	public ModelAndView listarLocais() {
		
		ModelAndView mv = new ModelAndView("local/listar.html");
		
		mv.addObject("lista", localService.listarLocais());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirLocal(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/local");
		
		try {
			localService.excluirLocal(id);
			redirectAttributes.addFlashAttribute("mensagem", "Local excluido com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir local."+e.getMessage());
		}
	
		return mv;
	}
	
	
}