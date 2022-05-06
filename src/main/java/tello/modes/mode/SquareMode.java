package tello.modes.mode;

import tellolib.command.TelloFlip;

public class SquareMode extends AbstractMode {
    
    public SquareMode(String name, String description) {
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

        //for (int i = 0; i<5; i++){

        telloControl.forward(dis); // goes forward 50cm

        telloControl.right(dis); //right 50cm

        telloControl.backward(dis);// back 50cm

        telloControl.left(dis); // left 

        telloControl.forward(dis); // goes forward 50cm AND completes square

        telloControl.doFlip(TelloFlip.backward);

    //}

        stopExecution();
    }

    @Override
    public void execute() {
        run();
    }
    
}

