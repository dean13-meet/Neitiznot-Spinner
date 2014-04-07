package SpinnerNeit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GeItem;

@Manifest(name = "SpinnerNeit", description = "short description of your script")
public class SpinnerNeit  extends PollingScript<ClientContext> implements MessageListener, PaintListener{

	ArrayList<Task> tasks = new ArrayList<Task>();
	
	
	
	double spinned = 0;
	int originalXp = ctx.skills.experience(ctx.skills.CRAFTING);
	double originalTime = System.currentTimeMillis();
	double timerun = 0;
	int flaxPrice = GeItem.price(1779);
	int stringPrice = GeItem.price(1777);

	
	@Override
	public void repaint(Graphics g) {
		spinned = (ctx.skills.experience(ctx.skills.CRAFTING) - originalXp)/15;
		timerun = System.currentTimeMillis() - this.originalTime;
		Date time = new Date((long) this.getRuntime());
		int hours = time.getHours();
		int min = time.getMinutes();
		int sec = time.getSeconds();
		

		g.setColor(Color.WHITE);
	    g.setFont(new Font("Tahoma", Font.BOLD, 16));

	    g.drawString("String Spinned: " + spinned, 50, 50);
	    g.drawString("Gained XP: " + (ctx.skills.experience(ctx.skills.CRAFTING) - originalXp),
	    		50, 75);
	    g.drawString("Time Run: " + hours + "h:" + min + "m:" + sec + "s",
	    		50, 100);
	    g.drawString("Flax/h: " + spinned/(timerun/3600000), 50, 125);
	    g.drawString("Money So Far: " + spinned*(stringPrice-flaxPrice),
	    		50, 150);
	    g.drawString("Money/h: " + (stringPrice-flaxPrice)*(spinned/(timerun/3600000))
	    		, 50, 175);
	    g.drawString("String price: " + stringPrice, 50, 200);
	    g.drawString("Flax price: " + flaxPrice, 50, 225);
	}

	@Override
	public void messaged(MessageEvent arg0) {
		
		
	}

	@Override
	public void poll() {
		for(Task t : tasks){
			if(t.activate()){
				t.execute();
				t.sleep(500);
			}
		}
		
	}
	public void start(){
		
		
		tasks.add(new BankNeit(ctx));
		tasks.add(new goToWheel(ctx));
		tasks.add(new Spin(ctx));
		tasks.add(new walkBack(ctx));
		
		
		for(Task t : tasks){
			t.scriptRunning = true;
		}
	}
	public void stop(){
		for(Task t : tasks){
			t.scriptRunning = false;
		}
	}
	public void suspend(){
		stop();
	}
	public void resume(){
		for(Task t : tasks){
			t.scriptRunning = true;
		}
	}

}
