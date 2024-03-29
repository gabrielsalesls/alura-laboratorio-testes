package br.com.brq.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.brq.argentum.modelo.Negociacao;


public class LeitorXMLTest {

	@Test
	public void carregaXMLComApenasUmaNegociacao() {
		
		String xml =
				"<list>\n"
			    + "  <negociacao>\n"
				+ "    <preco>10.0</preco>\n"
			    + "    <quantidade>4</quantidade>\n"
				+ "    <data>\n"
			    + "      <time>1459782000000</time>\n"
				+ "      <timezone>America/Sao_Paulo</timezone>\n"
			    + "    </data>\n"
				+ "  </negociacao>"
			    +" </list>";
		
		LeitorXml leitor = new LeitorXml();
		
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		
		List<Negociacao> negociacoes = leitor.carrega(inputStream);
		
		
		Negociacao negociacaoEsperada = new Negociacao(10.0, 4, LocalDateTime.of(2016, 4, 04, 12, 00));
	
		
		Assert.assertEquals(1, negociacoes.size());
		
		Assert.assertEquals(negociacaoEsperada, negociacoes.get(0));
		


		
	}
	
}
