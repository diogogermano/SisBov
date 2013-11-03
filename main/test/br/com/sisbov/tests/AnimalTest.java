package br.com.sisbov.tests;

import org.junit.Test;

import br.com.sisbov.dao.impl.AnimalDao;
import br.com.sisbov.persistence.Animal;
import br.com.sisbov.persistence.Lote;
import br.com.sisbov.persistence.Piquete;
import br.com.sisbov.persistence.Raca;
import br.com.sisbov.types.Sexo;

public class AnimalTest {

	@Test
	public void test() {
		Animal boi = new Animal();
		AnimalDao dao = new AnimalDao();
		Lote lote = new Lote();
		Raca nelore = new Raca();
		Piquete piquet = new Piquete();
		
		boi.setIdade(15);
		boi.setLote(lote);
		boi.setPeso(15.5);
		boi.setRaca(nelore);
		boi.setSexo(Sexo.MACHO);
		
		dao.save(boi);
	}

}
