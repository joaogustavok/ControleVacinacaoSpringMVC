package com.gft.desafiomvc.controllers;







import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.gft.desafiomvc.entities.Vacina;
import com.gft.desafiomvc.entities.Vacinacao;
import com.gft.desafiomvc.repositories.LoteRepository;
import com.gft.desafiomvc.services.LocalService;
import com.gft.desafiomvc.services.LoteService;
import com.gft.desafiomvc.services.PessoaService;
import com.gft.desafiomvc.services.VacinacaoService;

@Controller
@RequestMapping("/vacinacao")
public class VacinacaoController {
	
	@Autowired
	private VacinacaoService vacinacaoService;
	@Autowired
	private LocalService localService;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private LoteService loteService;
	@Autowired
	private LoteRepository loteRepository;
	
	
	@RequestMapping(path ="editar")
	public ModelAndView editarVacinacao(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("vacinacao/form.html");
		Vacinacao vacinacao;
		
		if(id==null) {
			vacinacao = new Vacinacao();
		} else {
			
			try {
				vacinacao = vacinacaoService.obterVacinacao(id);
			} catch (Exception e) {
				vacinacao = new Vacinacao();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("vacinacao", vacinacao);
		mv.addObject("listaPessoas", pessoaService.listarPessoas());
		mv.addObject("listaLotes", loteService.listarValidos());
		mv.addObject("listaLocais", localService.listarLocais());
		return mv;
}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarVacinacao(@Valid Vacinacao vacinacao,  BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("vacinacao/form.html");
		
		
		boolean novo = true;
		
		if(vacinacao.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("vacinacao", vacinacao);
			return mv;
		}
		
		vacinacaoService.salvarVacinacao(vacinacao);
		loteService.diminuiVacina(vacinacao.getLote());
		
		
		if(novo) {
			mv.addObject("vacinacao", new Vacinacao());
		} else {
			mv.addObject("vacinacao", vacinacao);
		}
		mv.addObject("mensagem", "Vacinação salva com sucesso");
		mv.addObject("listaPessoas", pessoaService.listarPessoas());
		mv.addObject("listaLotes", loteService.listarValidos());
		mv.addObject("listaLocais", localService.listarLocais());
		return mv;
		
}
	
	@RequestMapping("/relatorio")
	public ModelAndView listar2Dose() {
		
		ModelAndView mv = new ModelAndView("vacinacao/relatorio.html");
		
		mv.addObject("lista", vacinacaoService.listarVacinacao());
		
		return mv;
		
	}
	
	
	
	@RequestMapping
	public ModelAndView listarVacinacao() {
		
		ModelAndView mv = new ModelAndView("vacinacao/listar.html");
		
		mv.addObject("lista", vacinacaoService.listarVacinacao());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirVacinacao(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/vacinacao");
		
		try {
			vacinacaoService.excluirVacinacao(id);
			redirectAttributes.addFlashAttribute("mensagem", "Vacinação excluida com sucesso.");
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir vacinação."+e.getMessage());
		}
	
		return mv;
	}
	
	@RequestMapping(value ="listarVacinaPorLote" , method = RequestMethod.GET)
	@ResponseBody
	public Vacina listarVacinaPorLote(Long idLote) throws Exception{
		
		Vacina listarVacinas = loteRepository.findById(idLote).get().getVacina();
		
		return listarVacinas;
	}
}
