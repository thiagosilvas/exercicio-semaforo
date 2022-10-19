package semaforo;

public class ThreadSemaforo implements Runnable {

	private CorSemaforo cor;
	private boolean parar;
	private boolean corMudou;

	public ThreadSemaforo() {
		this.cor = CorSemaforo.VERMELHO;
		new Thread(this).start();
		this.parar = false;
		this.corMudou = false;


	}

	public void run() {
		
		while(!parar) {
			
			try {
				
				switch(this.cor) {
				
				case VERMELHO:
					Thread.sleep(2000);
					break;
					
				case AMARELO:
					Thread.sleep(300);
					break;
					
				case VERDE:
					Thread.sleep(1000);
					break;
					
					default:
						break;
						
				}
				this.mudarCor();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}

	public CorSemaforo getCor() {
		return cor;
	}

	

	private synchronized void mudarCor() {
		
		switch(this.cor) {
		
		case VERMELHO:
			this.cor = CorSemaforo.VERDE;
			break;
			
		case AMARELO:
			this.cor = CorSemaforo.VERMELHO;
			break;
			
		case VERDE:
			this.cor = CorSemaforo.AMARELO;
			break;
			
		default:
			break;
		
		}
		
		this.corMudou = true;
		notify();
	}
			
	public synchronized void esperaCorMudar() {
		while(!this.corMudou) {
			try {
				wait();
			
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.corMudou = false;
	}
	
}
