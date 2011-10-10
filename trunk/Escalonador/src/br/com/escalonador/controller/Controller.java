package br.com.escalonador.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import br.com.escalonador.model.Processo;
import br.com.escalonador.model.TipoEscalonamento;

public class Controller {
	
	private static Controller controller =null;
	private TipoEscalonamento tipoEscalonamento;
	private List<Processo> listProcessos;
	private int pidCounter = 0;
	
	/**
	 * Construtor.
	 */
	private Controller() {
		super();
		listProcessos = new ArrayList<Processo>();
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
		
	}
	
	public void pararEscalonamneto() {
		
	}
	
	public void limparValores() {
		
	}
	
	public void fecharAplicacao() {
		
		
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

}
