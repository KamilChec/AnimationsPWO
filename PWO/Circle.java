import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Runnable{
    private static Integer xPosMax = 0, yPosMax = 0;
    private int xPos = 50;
    private int yPos = 50;
    private int width = 20;
    private int height = 20;
    private int xVel = 10;
    private int yVel = 10;
    private Color color = Color.BLACK;
    
    public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }

    public int getVelX(){
        return xVel;
    } 

    public int getVelY(){
        return yVel;
    }

    public void setVelX(int xVel) {
		this.xVel = xVel;
	}

	public void setVelY(int yVel) {
		this.yVel = yVel;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void paint(Graphics g){
        g.setColor(getColor());
        // g.fillRect(xPos,yPos,getWidth(),getHeight());
        // g.fillRoundRect(xPos,yPos,getWidth(),getHeight(), 20, 20);
        g.fillOval(xPos,yPos, 50, 50);
        
    }

    public static void setxPosMax(Integer xMax) {
        xPosMax = xMax;
    }

    public static void setyPosMax(Integer yMax) {
        yPosMax = yMax;
    }

    @Override
    public void run() {
        while (true) {
            if(getX() <= xPosMax - getWidth() && getX() >= 0)
            {
                setX(getX() + getVelX());
            }
            else
            {
                setVelX(-getVelX());
                setX(getX() + getVelX());
            }
            if(getY() <= yPosMax - getHeight() && getY() >= 0)
            {
                setY(getY() + getVelY());
            }
            else
            {
                setVelY(-getVelY());
                setY(getY() + getVelY());
            }
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
