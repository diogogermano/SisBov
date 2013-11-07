package br.com.sisbov.tests;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

import br.com.sisbov.dao.impl.LoteDao;
import br.com.sisbov.persistence.Animal;
import br.com.sisbov.persistence.Lote;

public class LoteTest {

	@Test
	public void test() {
		LoteDao loteDao = new LoteDao();
		Lote lote1 = new Lote();
		Animal boi = new Animal(); 
		
		lote1.setAnimal(boi);
	}

}
