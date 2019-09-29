package br.edu.ifsc.supermercado;

public class ContaBuffer  {
	private double buffer = -1.0; 


	public synchronized void set(double value) {
		while (buffer != -1.0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		buffer = value;
	} 

	public synchronized double get() {
		while (buffer == -1.0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		double value = -1.0;
		value = buffer;
		buffer = -1;
		notifyAll();
		return value;
	} 
}

