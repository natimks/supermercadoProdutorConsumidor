package br.edu.ifsc.supermercado;

public class EsteiraBuffer implements Buffer {
	private Produto[] buffer;
	int currentValue = 0;

	int posicoesUsadas = 0;
	int posicaoRemocao = 0; // posGet
	int posicaoInsercao = 0; // posSet

	public EsteiraBuffer(int max) {
		buffer = new Produto[max];
	}

	// place value into buffer
	public synchronized void set(Produto value) {
		while (posicoesUsadas == buffer.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[posicaoInsercao] = value;
		// System.out.printf("Producer writes %s", value.getNome());
		posicoesUsadas++;
		posicaoInsercao = (posicaoInsercao + 1) % buffer.length;
		notifyAll();
	} // end method set

	// return value from buffer
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
	} // end method get

	public int getSize() {
		return buffer.length;
	}

}
