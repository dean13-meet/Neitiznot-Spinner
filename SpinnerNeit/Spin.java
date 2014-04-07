package SpinnerNeit;

import org.powerbot.script.rt6.ClientContext;

public class Spin extends Task{
	
	int widget = 1370;
	int child = 38;
	
	int cancelWidget = 1251;
	int cancelChild = 49;
	
	void clickWidget(int a, int b){
		if(ctx.widgets.component(a, b).visible()){
			System.out.println("Visible: " + ctx.widgets.component(a, b).visible());
			ctx.widgets.component(a,b).click(true);
		}
	}
	int random(int min, int max){
		return (int) ((Math.random()*Math.abs(max-min))+Math.min(min, max));
	}
	public Spin(ClientContext arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return (numberOfFlaxInInv()>0)&&  wheel.inViewport() && 
				!ctx.widgets.component(cancelWidget, cancelChild).visible();
	}

	@Override
	public void execute() {
		if(wheel.tile().distanceTo(ctx.players.local().tile())>10){
			ctx.movement.step(wheel);
			sleep(1232);}
		
		if(!ctx.widgets.component(widget, child).visible()){
			
		
		System.out.println("Spinning");
		wheel.interact("Spin");
		sleep(1450);
		
		}
		if(!ctx.widgets.component(cancelWidget, cancelChild).visible()){
			clickWidget(widget, child);
			sleep(1430);
		}
		
		while(ctx.widgets.component(cancelWidget, cancelChild).visible()){
			if(random(0,9)==0){//1 in 10 tries, statistically
			antiBan();}
			sleep(1500);
		}
	}
	private void antiBan() {
		int rand = random(0,5);
		switch(rand){
		case 0:
			System.out.println("AB: Changing camera angel");
			ctx.camera.angle((int) (Math.random()*360));
			break;
			
		case 1:
			System.out.println("AB: Changing camera pitch");
			ctx.camera.pitch((int)(Math.max(Math.random()*100,35*(1+Math.random()))));
			break;
		
		case 2:
			System.out.println("Hovering xp");
			ctx.widgets.component(1213, 13).hover();
			break;
		
		case 3:
			System.out.println("Hovering Money Pouch");
			ctx.widgets.component(1473, 2).hover();
			break;
			
		case 4:
			System.out.println("Hovering Tree");
			ctx.objects.select().id(70057).nearest().poll().hover();
			break;
		
		case 5:
			System.out.println("Hovering Bank");
			bank.nearest().tile().matrix(ctx).hover();
			break;
		}
		
	}

}
