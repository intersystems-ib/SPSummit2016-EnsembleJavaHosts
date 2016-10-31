package Summit;

import java.util.Random;

import com.intersys.gateway.BusinessService;
import com.intersys.gateway.Production;
import com.intersys.gateway.Production.Severity;

public class helloBS implements BusinessService {
	
	public static final String SETTINGS = "Min,Max";
	static Thread messageThread = null;
    Production production = null;
	int cnt = 0;
    
	@Override
	public boolean onInitBS(Production p) throws Exception {
		production = p;

		if (messageThread == null) {
			Messager messager = new Messager();
			messageThread = new Thread(messager);
			messageThread.start();
		}
	
		return true;
	}

	@Override
	public boolean onTearDownBS() throws Exception {
		if (messageThread != null) {
			if (messageThread.isAlive()) {
				messageThread.interrupt();
				messageThread.join();
			}
			messageThread = null;
		}
		
		return true;
	}

	private class Messager implements Runnable {
		Random rand = new Random();
		
		public void run() {
			try {
				int min = 0, max = 100;
				
				String setting = production.getSetting("Min");
				if (!setting.isEmpty()) {
					min = Integer.parseInt(setting);
				}
				setting = production.getSetting("Max");
				if (!setting.isEmpty()) {
					max = Integer.parseInt(setting);
				}

				production.logMessage("Starting up with min: "+min+" max: "+max, Severity.INFO);
			
				while (cnt<30) {
					++cnt;
					int value = rand.nextInt(max - min) + min;
					String message = "<random>Valor:"+value+" cnt: "+cnt+"</random>";
					production.logMessage("Sending message: "+message, Severity.INFO);
					production.sendRequest(message);
					Thread.sleep(1000);
					
				}
			} catch (InterruptedException e) {
				try {
					production.logMessage("Shutting down", Severity.INFO);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return;
			} catch (Exception e) {
				try {
					production.logMessage(e.toString(), Severity.ERROR);
					production.setStatus(Production.Status.ERROR);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}	
	}

}
