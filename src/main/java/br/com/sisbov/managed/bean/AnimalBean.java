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

import br.com.sisbov.dao.impl.AnimalDao;
import br.com.sisbov.persistence.Animal;
import br.com.sisbov.types.Sexo;


@ManagedBean
@SessionScoped
public class AnimalBean {
	
	private Animal animal;

	private String nome = "";
	private List<Animal> listagemAnimal = new ArrayList<Animal>();
	private AnimalDao dao = new AnimalDao();
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Animal> getListagemAnimal() {
		return listagemAnimal;
	}

	public void setListagemAnimal(List<Animal> listagemAnimal) {
		this.listagemAnimal = listagemAnimal;
	}

	public String inserir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String acao = (animal.getId() != null ? "Alterado"
					: "Cadastrado");

			dao.save(animal);

			nome = animal.getNome();
			pesquisarAnimal();

			if (animal.getId() != null) {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			} else {
				context.addMessage(null,
						new FacesMessage(acao + " com sucesso"));
			}
			return "animalList";
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage("Erro ao inserir: " + e.getMessage()));
			return "animal";
		}
	}
	
	public String novaAnimal() {
		this.animal = new Animal();
		return "animal";
	}

	public String editar(Animal animal) {
		this.animal = animal;
		return "animal";
	}

	public void guardarSelecao(Animal animal) {
		this.animal = animal;
	}

	public void pesquisarAnimal() {
		listagemAnimal = dao.pesquisarAnimalPorNome(nome);
	}
	
	public String remover() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (animal == null) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"nenhuma concessionaria selecionada", ""));
			return "concessionariaList";
		}
		try {
			dao.delete(animal);
			pesquisarAnimal();

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Removido com sucesso", ""));
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao remover: " + e.getMessage(), ""));
		}
		return "animalList";
	}


	public Sexo[] getSexoValues() {
		return Sexo.values();
	}
	
	public Integer getQuantidadeItensLista() {
		return listagemAnimal.size();
	}
	
	public void imprimirRelatorio() throws JRException, IOException {

		JasperReport report = JasperCompileManager.compileReport("reports/concessionarias.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(listagemAnimal));

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
