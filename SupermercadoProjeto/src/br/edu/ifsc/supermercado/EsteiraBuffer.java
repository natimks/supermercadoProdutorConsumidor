package br.edu.ifsc.supermercado;

public class EsteiraBuffer implements Buffer {
	private Produto[] buffer;

	int posicoesUsadas = 0;
	int posicaoRemocao = 0; 
	int posicaoInsercao = 0; 

	public EsteiraBuffer(int max) {
		buffer = new Produto[max];
	}

	public synchronized void set(Produto value) {
		while (posicoesUsadas == buffer.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[posicaoInsercao] = value;
		posicoesUsadas++;
		posicaoInsercao = (posicaoInsercao + 1) % buffer.length;
		notifyAll();
	} 

	public synchronized Produto get() {
		while (posicoesUsadas == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		posicoesUsadas--;
		Produto value = null;
		value = buffer[posicaoRemocao];
		posicaoRemocao = (posicaoRemocao + 1) % buffer.length;

		notifyAll();
		return value;
	} 

	public int getSize() {
		return buffer.length;
	}

}
