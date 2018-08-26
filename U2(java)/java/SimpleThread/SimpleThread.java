/*
 * Main.java
 *
 * Created on 8 de noviembre de 2005, 23:12
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author Ricardo
 */

/*
 * SimpleThread
 * Un ejemplo muy simple de Hilos.
 */
public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	private int threadNumber = ++threadCount;
	public SimpleThread() {
		System.out.println("Haciendo el hilo: " + threadNumber);
	}

	public void run() {
		while(true) {
			System.out.println("Hilo: " + threadNumber + "(" + countDown + ")");
			try {
				Thread.sleep(threadNumber*2000);
			} catch	(InterruptedException ie)
				  {
				  System.out.println(ie.getMessage());
				  }
			if(--countDown == 0) return;
		}
	}

	public static void main(String[] args) {
		for(int i = 0; i < 5; i++)
			new SimpleThread().start();
		System.out.println("Todos los Hilos fueron creados");
	}	
} 


