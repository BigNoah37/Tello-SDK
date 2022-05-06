package tello.modes.mode;

import tellolib.command.TelloFlip;

public class CrazyMode extends AbstractMode {
    
    public CrazyMode(String name, String description) {
        super(name, description);
    }

    public void run() {
        stillRunning = true;
        int dis = 25;

        tellocoms.setTimeout(50_000);
        telloControl.connect();

        telloControl.enterCommandMode();

        // Everything else goes inbetween

        telloControl.takeOff();
        telloControl.startStatusMonitor();

        System.out.println(telloControl.getBattery());
        System.out.println(telloControl.getTemp());

        //for (int i = 0; i<5; i++){
    
        //telloControl.setSpeed(10);

        //telloControl.up(75); // hovering 50cm up

        //telloControl.setSpeed(0); 

        telloControl.forward(dis); // goes forward 50cm

        telloControl.right(dis); //right 50cm

        //telloControl.setSpeed(0);

        telloControl.backward(dis);// back 50cm

        telloControl.left(dis); // left 

    //}

        

        //telloControl.forward(200);

        for(int i = 0; i<2; i++){
            telloControl.doFlip(TelloFlip.backward);
            telloControl.backward(18);
            System.out.println(telloControl.getBattery());
            System.out.println(telloControl.getTemp());
        }
        telloControl.rotateLeft(180);

        telloControl.doFlip(TelloFlip.forward);
        System.out.println(telloControl.getBattery());
        
        telloControl.doFlip(TelloFlip.right);
        System.out.println(telloControl.getBattery());

        telloControl.doFlip(TelloFlip.backward);
        System.out.println(telloControl.getBattery());

        telloControl.doFlip(TelloFlip.left);
        System.out.println(telloControl.getBattery());

        telloControl.forward(dis); // goes forward 50cm

        telloControl.right(dis); //right 50cm

        //telloControl.setSpeed(0);

        telloControl.backward(dis);// back 50cm

        telloControl.left(dis); // left

        System.out.println(telloControl.getBattery());
            System.out.println(telloControl.getTemp());

        //
        stopExecution();
    }

    @Override
    public void execute() {
        run();
    }
    
}

