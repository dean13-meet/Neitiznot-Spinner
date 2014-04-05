package SpinnerNeitTasks;

import org.powerbot.script.rt6.Bank;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class BankNeit extends Task{
	
	

	public BankNeit(ClientContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return numberOfFlaxInInv()==0&&bank.inViewport();//Runs if we do not have flax in inv.
	}

	@Override
	public void execute() {
		if(bank.nearest().tile().distanceTo(ctx.players.local().tile())>10){
		ctx.movement.step(bank.nearest());
		sleep(1232);}
		
		if(!bank.opened()&&this.scriptRunning){
			bank.open();
			System.out.println("Banked");
			sleep(1400);
		}
	/*	while(!bank.open()){
			sleep(100);
		}*/
		if(true&&this.scriptRunning&&bank.opened()){
			bank.depositInventory();
			System.out.println("Deposited");
			sleep(1453);
		}
	/*	while(!ctx.backpack.isEmpty()){
			sleep(100);
		} */
		if(!(numberOfFlaxInInv()>0)&&this.scriptRunning&&bank.opened()){
			bank.withdraw(flaxId[0], 28);
			System.out.println("Withdrew");
			sleep(1722);
		}
	/*	while(!(numberOfFlaxInInv()>0)){
			sleep(100);
		}*/
		if(bank.opened()&&this.scriptRunning){
			bank.close();
			System.out.println("Closed");
			sleep(2010);
		}
	/*	while(bank.opened()){
			sleep(100);
		}*/
		
	}

}
