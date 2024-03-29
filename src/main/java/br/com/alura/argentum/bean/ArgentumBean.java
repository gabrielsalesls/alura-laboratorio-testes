package br.com.alura.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;

import br.com.alura.argentum.graficos.GeradorDeModeloGrafico;
import br.com.alura.argentum.ws.ClientWebService;
import br.com.brq.argentum.indicadores.Indicador;
import br.com.brq.argentum.indicadores.IndicadorFactory;
import br.com.brq.argentum.indicadores.IndicadorFechamento;
import br.com.brq.argentum.indicadores.MediaMovelSimples;
import br.com.brq.argentum.modelo.Candle;
import br.com.brq.argentum.modelo.CandleFactory;
import br.com.brq.argentum.modelo.Negociacao;
import br.com.brq.argentum.modelo.SerieTemporal;

@ViewScoped
@ManagedBean
public class ArgentumBean implements Serializable {

	private List<Negociacao> negociacoes;
	private LineChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;

	public ArgentumBean() {
		this.negociacoes = new ClientWebService().getNegociacoes();
		geraGrafico();

	}

	public void geraGrafico() {
		System.out.println("PLOTANDO: " + nomeMedia + " de " + nomeIndicadorBase);
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorDeModeloGrafico geradorModelo = new GeradorDeModeloGrafico(serie, 2, serie.getUltimaPosicao());
		IndicadorFactory fabrica = new IndicadorFactory(nomeMedia, nomeIndicadorBase);
		geradorModelo.plotaIndicador(fabrica.defineIndicador());
		this.modeloGrafico = geradorModelo.getModeloGrafico();
	}

	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

}
