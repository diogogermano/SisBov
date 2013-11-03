package br.com.sisbov.tests;

import org.junit.Test;

import br.com.sisbov.dao.impl.RacaDao;
import br.com.sisbov.persistence.Raca;

public class RacaTest {

	@Test
	public void test() {
		Raca nelore = new Raca();
		RacaDao dao = new RacaDao();
		
		nelore.setNome("Nelore");
		
		dao.save(nelore);
	}

}
