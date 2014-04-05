package SpinnerNeitTasks;

import org.powerbot.script.rt6.Bank;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

abstract class Task extends ClientAccessor{

public boolean scriptRunning = false;
//verify:


public int[] flaxId = {1779};
Bank bank = new Bank(ctx);



GameObject wheel = ctx.objects.select().id(21304).nearest().poll();

public Task(ClientContext arg0) {

super(arg0);

// TODO Auto-generated constructor stub

}

public abstract boolean activate();

    public abstract void execute();

    public void sleep(int n){
    	System.out.println("Before " + n);
    	n += 0.1*n*Math.random();
    	System.out.println("After " + n);
    try {

Thread.sleep(n);

} catch (InterruptedException e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

    }
    public int numberOfFlaxInInv(){
		int counter = 0;
		for(org.powerbot.script.rt6.Item item : ctx.backpack.items()) {
			if(item.id() == 1779){
				counter++;
			}
		}
		return counter;
	}

}