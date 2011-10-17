package br.com.escalonador.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import br.com.escalonador.model.Estado;
import br.com.escalonador.model.Processo;
import br.com.escalonador.model.TipoEscalonamento;
import br.com.escalonador.model.business.AlgoritmoEscalonamento;
import br.com.escalonador.model.business.AlgoritmoFIFO;
import br.com.escalonador.model.business.AlgoritmoLoteria;
import br.com.escalonador.model.exception.BusinessException;
import br.com.escalonador.util.MessagesResource;

public class Controller {

	private static Controller controller =null;
	private TipoEscalonamento tipoEscalonamento;
	private List<Processo> listProcessos;
	private int pidCounter = 1;

	/**
	 * Construtor.
	 */
	private Controller() {
		super();
		listProcessos = new ArrayList<Processo>();
		tipoEscalonamento = TipoEscalonamento.FIFO;
	}

	/**
	 * Esse método obtém uma instância de {@link Controller}
	 *
	 * @return uma instância de {@link Controller}
	 */
	public static Controller getInstance() {
		if(controller ==null) {
			controller = new Controller();
		}
		return controller;
	}

	/**
	 * @return the tipoEscalonamento
	 */
	public final TipoEscalonamento getTipoEscalonamento() {
		return tipoEscalonamento;
	}

	/**
	 * @param tipoEscalonamento the tipoEscalonamento to set
	 */
	public final void setTipoEscalonamento(TipoEscalonamento tipoEscalonamento) {
		this.tipoEscalonamento = tipoEscalonamento;
	}

	public void sair() {
		System.exit(0);
	}

	public void iniciarEscalonamento() {
		Thread d = new Thread() {
			public void run() {
				try {
					AlgoritmoEscalonamento algoritmo = null;
					switch (tipoEscalonamento) {
					case FIFO:
						algoritmo = new AlgoritmoFIFO();
						break;
					case LOTERIA:
						algoritmo = new AlgoritmoLoteria();

					}
					algoritmo.escalonar(listProcessos);
				} catch (BusinessException e) {
					JOptionPane
							.showMessageDialog(
									null,
									"Não foi possivel iniciar a simulação do escalonamento devido ao seguinte error:\n"
											+ e.getMessage(),
									"Error ao iniciar o escalonamneto",
									JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		d.start();
	}

	public void pararEscalonamneto() {

	}

	public void limparValores() {
		listProcessos.clear();
	}

	public void addProcesso(Processo processo) {
		if(processo != null) {
			listProcessos.add(processo);
		}
	}

	public void removerProcesso(int index) {
		listProcessos.remove(index);
	}

	public Object[][] getLinhasTabela(){
		Object[][] linhas = null;
		if(!listProcessos.isEmpty()) {
			linhas = new Object[listProcessos.size()][5];
			for(int i=0; i<listProcessos.size(); i++) {
				Processo p = listProcessos.get(i);
				linhas[i][0] = new JRadioButton();
				linhas[i][1] = String.valueOf(p.getPid());
				linhas[i][2] = String.valueOf(p.getTamanhoMemoria());
				linhas[i][3] = String.valueOf(p.getTempoProcessamento());
				linhas[i][4] = p.getPrioridade().name();
			}
		}
		return linhas;
	}

	public int getPid() {
		return pidCounter++;
	}
	
	public int getQtdProcessos(){
		return listProcessos.size();
	}

	/**
	 * @return the listProcessos
	 */
	public final List<Processo> getListProcessos() {
		return listProcessos;
	}
	
	public boolean isRunning(){
		boolean retorno = true;
		for(Processo p: listProcessos) {
			if(p.getEstado() == Estado.FINALIZADO) {
				retorno =false;
				break;
			}
		}
		return retorno;
	}

	public int getQtdMemoria() {
		int qtdMemoria = 0;
		if(!listProcessos.isEmpty()) {
			for(Processo p: listProcessos) {
				qtdMemoria += p.getTamanhoMemoria();
			}
		}
		return qtdMemoria;
	}
	
	public long getTempoTotal(){
		long tempoTotal = 0;
		if(!listProcessos.isEmpty()) {
			for(Processo p: listProcessos) {
				tempoTotal += p.getTempoProcessamento();
			}
		}
		return tempoTotal;
	}
	
	public boolean isAlgoritmoFifo(){
		boolean retorno = false;
		if(tipoEscalonamento == TipoEscalonamento.FIFO) {
			retorno = true;
		}
		return retorno;
	}
	
	public boolean isAlgoritmoLoteria(){
		boolean retorno = false;
		if(tipoEscalonamento == TipoEscalonamento.LOTERIA) {
			retorno = true;
		}
		return retorno;
	}
	
	public String getLabelAlgoritmo(){
		return MessagesResource.getString("janela.aba.execucao.titulo.painel.algoritmo") + tipoEscalonamento;
	}
	
	public String getLabelMemoria(){
		return MessagesResource.getString("janela.aba.execucao.titulo.painel.memoria") + getQtdMemoria();
	}
	
	public String getLabelQtdProcesso(){
		return MessagesResource.getString("janela.aba.execucao.titulo.painel.qtd.processo") + getQtdProcessos();
	}
	
	public String getLabelTempoTotal(){
		return MessagesResource.getString("janela.aba.execucao.titulo.painel.tempo.total") + getTempoTotal();
	}
	

}
