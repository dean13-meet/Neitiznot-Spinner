package SpinnerNeitTasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public class goToWheel extends Task{

	public goToWheel(ClientContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		
		return (numberOfFlaxInInv()>0)&&  !wheel.inViewport();
	}

	@Override
	public void execute() {
		System.out.println("Going there");
		ctx.movement.step(wheel);//new Tile(2354, 3796, 0));
		sleep(4320);
	}

}
