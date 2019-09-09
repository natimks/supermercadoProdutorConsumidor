package br.edu.ifsc.supermercado;

import java.util.ArrayList;

public class EsteiraBuffer implements Buffer{
	private ArrayList<Produto> buffer;

	int currentValue = 0;

	int posicoesUsadas = 0;
	int posicaoRemocao = 0; // posGet
	int posicaoInsercao = 0; // posSet

	public EsteiraBuffer(ArrayList<Produto> carrinho) {
			buffer = carrinho;
		}

	// place value into buffer
	public synchronized void set(Produto produto) {
		while (posicoesUsadas == buffer.size()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer.add(posicaoInsercao, produto);
		System.out.printf("Producer writes\t%2d", produto);
		posicoesUsadas++;
		posicaoInsercao = (posicaoInsercao + 1) % buffer.size();
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

		Produto value = new Produto("", 0.0);
		System.out.printf("Consumer reads\t%2d", buffer.get(posicaoRemocao));
		value = buffer.get(posicaoRemocao);
		posicaoRemocao = (posicaoRemocao + 1) % buffer.size();

		notifyAll();
		return value;
	} // end method get

}