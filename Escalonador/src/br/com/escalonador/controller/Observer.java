package br.com.escalonador.controller;

import br.com.escalonador.model.Processo;
import br.com.escalonador.view.MainWindow;
import br.com.escalonador.view.ProcessoWindow;

public class Observer {
	
	private MainWindow mainWindow;
	private ProcessoWindow  processoWindow;
	private static Observer instance;
	private Controller controller;
	
	/**
	 * Construtor.
	 */
	private Observer(){
		super();
		controller = Controller.getInstance();
	}
	
	public static Observer getInstance(){
		if(instance == null) {
			instance = new Observer();
		}
		return instance;
	}

	/**
	 * @return the mainWindow
	 */
	public final MainWindow getMainWindow() {
		return mainWindow;
	}

	/**
	 * @param mainWindow the mainWindow to set
	 */
	public final void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * @return the processoWindow
	 */
	public final ProcessoWindow getProcessoWindow() {
		return processoWindow;
	}

	/**
	 * @param processoWindow the processoWindow to set
	 */
	public final void setProcessoWindow(ProcessoWindow processoWindow) {
		this.processoWindow = processoWindow;
	}
	
	public void addProcesso(){
		Processo processo = processoWindow.getProcesso();
		controller.addProcesso(processo);
		closeProcessoWindow();
	}
	
	public void closeProcessoWindow(){
		processoWindow.dispose();
		processoWindow.setVisible(false);
		mainWindow.atualizarPainel();
		mainWindow.setVisible(true);
	}
	
	public void bloquearMainWindow(){
		mainWindow.dispose();
	}
	
	public void removerProcesso(int index) {
		controller.removerProcesso(index);
		mainWindow.atualizarPainel();
	}

}
