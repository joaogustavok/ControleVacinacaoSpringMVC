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

import com.gft.desafiomvc.entities.Pessoa;
import com.gft.desafiomvc.services.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping
	public ModelAndView listarPessoas() {
		ModelAndView mv = new ModelAndView("pessoa/listar.html");
		mv.addObject("lista", pessoaService.listarPessoas());
		
		return mv;
	}

	@RequestMapping(path = "editar")
	public ModelAndView editarPessoa(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("pessoa/form.html");
		Pessoa pessoa;

		if (id == null) {
			pessoa = new Pessoa();
		} else {
			try {
				pessoa = pessoaService.obterPessoa(id);
			} catch (Exception e) {
				pessoa = new Pessoa();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("pessoa", pessoa);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarPessoa(@Valid Pessoa pessoa, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("pessoa/form.html");
		
		boolean novo = true;

		if (pessoa.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("pessoa", pessoa);
			return mv;
		}

		pessoaService.salvarPessoa(pessoa);

		if (novo) {
			mv.addObject("pessoa", new Pessoa());
		} else {
			mv.addObject("pessoa", pessoa);
		}
		mv.addObject("mensagem", "Pessoa salva com sucesso");
		
		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirPessoa(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/pessoa");
		
		try {
			pessoaService.excluirPessoa(id);
			redirectAttributes.addFlashAttribute("mensagem", "Pessoa excluida com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir pessoa."+e.getMessage());
		}
	
		return mv;
	}
}
