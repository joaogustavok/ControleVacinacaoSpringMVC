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

import com.gft.desafiomvc.entities.Lote;
import com.gft.desafiomvc.services.LoteService;
import com.gft.desafiomvc.services.VacinaService;

@Controller
@RequestMapping("/lote")
public class LoteController {
	
	@Autowired
	private LoteService loteService;
	
	@Autowired
	private VacinaService vacinaService;
	
	
	@RequestMapping(path ="editar")
	public ModelAndView editarLote(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("lote/form.html");
		Lote lote;
		
		if(id==null) {
			lote = new Lote();
		} else {
			
			try {
				lote = loteService.obterLote(id);
			} catch (Exception e) {
				lote = new Lote();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("lote", lote);
		mv.addObject("listaVacinas", vacinaService.listarVacina());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarLote(@Valid Lote lote,  BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("lote/form.html");
		
		
		boolean novo = true;
		
		if(lote.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("lote", lote);
			return mv;
		}
		
		loteService.salvarLote(lote);
		
		if(novo) {
			mv.addObject("lote", new Lote());
		} else {
			mv.addObject("lote", lote);
		}
		
	
		mv.addObject("mensagem", "Lote salvo com sucesso");
		mv.addObject("listaVacinas", vacinaService.listarVacina());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarLotes() {
		
		ModelAndView mv = new ModelAndView("lote/listar.html");
		
		mv.addObject("lista", loteService.listarLotes());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirLote(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/lote");
		
		try {
			loteService.excluirLote(id);
			redirectAttributes.addFlashAttribute("mensagem", "Lote excluida com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir lote."+e.getMessage());
		}
	
		return mv;
	}

	
}
