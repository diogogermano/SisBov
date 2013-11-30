package br.com.sisbov.managed.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import br.com.sisbov.dao.impl.RacaDao;
import br.com.sisbov.persistence.Propriedade;
import br.com.sisbov.persistence.Raca;



@ManagedBean
@SessionScoped
public class RacaBean {
	
	private Raca raca;
	
	private String nome = "";
	private List<Raca> listagemRaca = new ArrayList<Raca>();
	private RacaDao dao = new RacaDao();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Raca> getListagemRaca() {
		return listagemRaca;
	}

	public void setListagemRaca(List<Raca> listagemRaca) {
		this.listagemRaca = listagemRaca;
	}
	
	public Raca getRaca() {
		return raca;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String acao = (raca.getId() != null ? "Alterado"
					: "Cadastrado");

			dao.save(raca);

			nome = raca.getNome();
			pesquisarRaca();

			if (raca.getId() != null) {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			} else {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			}
			return "racaList";
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
			return "raca";
		}
	}

	public String novaRaca() {
		this.raca = new Raca();
		return "raca";
	}
	
	public String editar(Raca raca) {
		this.raca = raca;
		return "raca";
	}
	
	public void guardarSelecao(Raca raca) {
		this.raca = raca;
	}
	
	public void pesquisarRaca() {
		listagemRaca = dao.pesquisarRacaPorNome(nome);
	}
	
	public String remover() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (raca == null) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"nenhuma raca selecionada", ""));
			return "racaList";
		}
		try {
			dao.delete(raca);
			pesquisarRaca();

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Removido com sucesso", ""));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao remover: " + e.getMessage(), ""));
		}
		return "racaList";
	}
	
	public Integer getQuantidadeItensLista() {
		return listagemRaca.size();
	}
	
	public void imprimirRelatorio() throws JRException, IOException {

		JasperReport report = JasperCompileManager.compileReport("reports/raca.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(listagemRaca));

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		ServletOutputStream servletOutputStream = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment; filename=raca.pdf");

		JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
		
		
		context.responseComplete();

	}
	
}