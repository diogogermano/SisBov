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
import br.com.sisbov.dao.impl.ProprietarioDao;
import br.com.sisbov.persistence.Proprietario;

@ManagedBean
@SessionScoped
public class ProprietarioBean {

	private Proprietario proprietario;

	private String nome;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private List<Proprietario> listagemProprietario = new ArrayList<Proprietario>();
	private ProprietarioDao dao = new ProprietarioDao();

	

	public List<Proprietario> getListagemProprietario() {
		return listagemProprietario;
	}

	public void setListagemProprietario(
			List<Proprietario> listagemProprietario) {
		this.listagemProprietario = listagemProprietario;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public String inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String acao = (proprietario.getId() != null ? "Alterado"
					: "Cadastrado");

			dao.save(proprietario);

			
			

			if (proprietario.getId() != null) {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			} else {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			}
			return "proprietarioList";
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
			return "proprietario";
		}
	}

	public String novaProprietario() {
		this.proprietario = new Proprietario();
		return "proprietario";
	}

	public String editar(Proprietario proprietario) {
		this.proprietario = proprietario;
		return "proprietario";
	}

	public void guardarSelecao(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	

	public String remover() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (proprietario == null) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"nenhuma concessionaria selecionada", ""));
			return "proprietarioList";
		}
		try {
			dao.delete(proprietario);
			

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Removido com sucesso", ""));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao remover: " + e.getMessage(), ""));
		}
		return "proprietarioList";
	}


	public Integer getQuantidadeItensLista() {
		return listagemProprietario.size();
	}

	public void imprimirRelatorio() throws JRException, IOException {

		JasperReport report = JasperCompileManager.compileReport("reports/concessionarias.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(listagemProprietario));

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		ServletOutputStream servletOutputStream = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment; filename=concessionarias.pdf");

		JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
		
		
		context.responseComplete();

	}
}
