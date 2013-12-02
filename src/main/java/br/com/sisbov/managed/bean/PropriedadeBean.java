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

import br.com.sisbov.dao.impl.PropriedadeDao;
import br.com.sisbov.persistence.Propriedade;


@ManagedBean
@SessionScoped
public class PropriedadeBean {
	
	private Propriedade propriedade;

	private String nome = "";
	private List<Propriedade> listagemPropriedade = new ArrayList<Propriedade>();
	private PropriedadeDao dao = new PropriedadeDao();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	

	public List<Propriedade> getListagemPropriedade() {
		return listagemPropriedade;
	}

	public void setListagemPropriedade(
			List<Propriedade> listagemPropriedade) {
		this.listagemPropriedade = listagemPropriedade;
	}

	public Propriedade getPropriedade() {
		return propriedade;
	}

	public String inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String acao = (propriedade.getId() != null ? "Alterado"
					: "Cadastrado");

			dao.save(propriedade);

			
			nome = propriedade.getNome();
			pesquisarPropriedade();

			if (propriedade.getId() != null) {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			} else {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			}
			return "propriedadeList";
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
			return "propriedade";
		}
	}

	public String novaPropriedade() {
		this.propriedade = new Propriedade();
		return "propriedade";
	}

	public String editar(Propriedade propriedade) {
		this.propriedade = propriedade;
		return "propriedade";
	}

	public void guardarSelecao(Propriedade propriedade) {
		this.propriedade = propriedade;
	}

	
	public void pesquisarPropriedade() {
		listagemPropriedade = dao.pesquisarPropriedadePorNome(nome);
	}
	

	public String remover() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (propriedade == null) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"nenhuma concessionaria selecionada", ""));
			return "propriedadeList";
		}
		try {
			dao.delete(propriedade);
			

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Removido com sucesso", ""));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao remover: " + e.getMessage(), ""));
		}
		return "propriedadeList";
	}


	public Integer getQuantidadeItensLista() {
		return listagemPropriedade.size();
	}

	public void imprimirRelatorio() throws JRException, IOException {

		JasperReport report = JasperCompileManager.compileReport("reports/propriedade.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(listagemPropriedade));

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		ServletOutputStream servletOutputStream = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment; filename=propriedade.pdf");

		JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
		
		
		context.responseComplete();

	}
}
