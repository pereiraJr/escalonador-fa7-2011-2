package br.com.escalonador.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.escalonador.model.Processo;
import br.com.escalonador.model.TipoEscalonamento;

public class Controller {
	
	private static Controller controller =null;
	private TipoEscalonamento tipoEscalonamento;
	private List<Processo> listProcessos;
	
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
	
	public String[][] getLinhasTabela(){
		String[][] linhas = null;
		if(!listProcessos.isEmpty()) {
			linhas = new String[listProcessos.size()][4];
			for(int i=0; i<listProcessos.size(); i++) {
				Processo p = listProcessos.get(i);
				linhas[i][0] = String.valueOf(p.getPid());
				linhas[i][1] = String.valueOf(p.getTamanhoMemoria());
				linhas[i][2] = String.valueOf(p.getTempoProcessamento());
				linhas[i][3] = p.getPrioridade().name();
			}
		}
		else {
			linhas = new String[4][4];
		}
		return linhas;
	}

	public int getPid() {
		// TODO Auto-generated method stub
		return 0;
	}

}
