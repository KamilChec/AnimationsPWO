import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;


class Panel extends JPanel implements Runnable{

	/**
     *
     */
    private static final long serialVersionUID = 1L;
    List<Circle> blueCircle = new ArrayList<Circle>();
    List<Circle> redCircle = new ArrayList<Circle>();
    List<Circle> greenCircle = new ArrayList<Circle>();
	Circle circle = new Circle();

	public Panel() {
        setSize(getPreferredSize());

        Circle.setxPosMax(this.getSize().width);
        Circle.setyPosMax(this.getSize().height);
        Random r = new Random();
        int recNumb = 20;
        for(int i = 0; i<recNumb; i++){
            circle = new Circle();
            circle.setX(r.nextInt(450));
            circle.setY(r.nextInt(450));
            circle.setColor(new Color(0f, 0f, 1f, .5f));
            blueCircle.add(circle);
            circle = new Circle();
            circle.setX(r.nextInt(450));
            circle.setY(r.nextInt(450));
            circle.setColor(new Color(1f, 0f, 0f, .5f));
            redCircle.add(circle);
            circle = new Circle();
            circle.setX(r.nextInt(450));
            circle.setY(r.nextInt(450));
            circle.setColor(new Color(0f, 1f, 0f, .5f));
            greenCircle.add(circle);
        }

        this.execRun(blueCircle, recNumb);
        this.threadRun(redCircle, recNumb);
        // this.woThread(greenRectangle, recNumb);

        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                while(true){
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } 
                }

            }

        });
        thread.start();
	}

	public Dimension getPreferredSize() {
		return new Dimension(450, 450);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Circle rec : blueCircle) rec.paint(g);
        for (Circle rec : redCircle) rec.paint(g);
        for (Circle rec : greenCircle) rec.paint(g);
    }
    
    public void execRun(List<Circle> blueRectangle, int recNumb){
        ExecutorService exec = Executors.newFixedThreadPool(recNumb);
        for (Circle rec : blueRectangle) exec.execute(rec);
        exec.shutdown();
    }

    public void threadRun(List<Circle> blueRectangle, int recNumb){
        for (Circle rec : redCircle) {
            Thread thread = new Thread(rec);
            thread.start();
        }
    }


    //  NIE WIEM CZEMU ALE NIE DZIAŁA
    // public void woThread(List<Prostokat> greenRectangle, int recNumb){
    //     while(true) {
    //         for (Prostokat rec : greenRectangle){
    //             if(rec.getX() <= 450 - rec.getWidth() && rec.getX() >= 0)
    //             {
    //                 rec.setX(rec.getX() + rec.getVelX());
    //             }
    //             else
    //             {
    //                 rec.setVelX(-rec.getVelX());
    //                 rec.setX(rec.getX() + rec.getVelX());
    //             }
    //             if(rec.getY() <= 450 - rec.getHeight() && rec.getY() >= 0)
    //             {
    //                 rec.setY(rec.getY() +rec.getVelY());
    //             }
    //             else
    //             {
    //                 rec.setVelY(-rec.getVelY());
    //                 rec.setY(rec.getY() + rec.getVelY());
    //             }
    //         }
    //         try {
    //             Thread.sleep(80);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //         this.repaint();
    //     }
    // }

    @Override
    public void run() {
        while(true) {
            for (Circle cir : greenCircle){
                if(cir.getX() <= 450 - cir.getWidth() && cir.getX() >= 0)
                {
                    cir.setX(cir.getX() + cir.getVelX());
                }
                else
                {
                    cir.setVelX(-cir.getVelX());
                    cir.setX(cir.getX() + cir.getVelX());
                }
                if(cir.getY() <= 450 - cir.getHeight() && cir.getY() >= 0)
                {
                    cir.setY(cir.getY() +cir.getVelY());
                }
                else
                {
                    cir.setVelY(-cir.getVelY());
                    cir.setY(cir.getY() + cir.getVelY());
                }
            }
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }

    }
}