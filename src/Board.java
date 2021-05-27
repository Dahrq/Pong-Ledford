import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Board extends JPanel implements ActionListener{

    final int BallD=20, PADDLEW = 20, PADDLEH = 70, BORDER = 10;

    int ballX = 0, ballY = 0, paddleCX = 0, paddleCY = 0, paddlePX = 0, paddlePY = 0, ballDX = 3, ballDY = 5;
    int paddlePDY = 8, paddleCDY=3;
    int playerScore = 0, computerScore = 0;

    Timer timer;

    public Board(){
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
    }

    //Initialization
    public void init(){
        ballX = getWidth()/2 - BallD/2;
        ballY = getHeight()/2 - BallD/2;

        paddlePX = 0 + BORDER;
        paddlePY = getHeight()/2 - PADDLEH/2;

        paddleCX = getWidth() - BORDER - PADDLEW;
        paddleCY = getHeight()/2 - PADDLEH/2;
        timer.start();
    }

    public void checkCollisions() {

        int partition = PADDLEH / 4;

        Rectangle paddle1 = new Rectangle(paddlePX, paddlePY, PADDLEW, PADDLEH);
        Rectangle paddle2 = new Rectangle(paddleCX, paddleCY, PADDLEW, PADDLEH);
        Rectangle ball = new Rectangle(ballX, ballY, BallD, BallD);

        if (ball.intersects(paddle1) ||ball.intersects(paddle2)) {
            ballDX*=-1;
        }

    }

    public void move(){

        if(ballX + BallD >= getWidth() || ballX <= 0){
            ballDX *= -1;
        }
        if(ballY + BallD >= getHeight() || ballY <= 0){
            ballDY *= -1;
        }

        ballX += ballDX;
        ballY += ballDY;

        if(ballX + BallD/2 > getWidth()/2){
            if(ballY > paddleCY+PADDLEH/2){
                paddleCY += paddleCDY;
            }
            if(ballY <= paddleCY+PADDLEH/2){
                paddleCY -= paddleCDY;
            }
        }


    }

    public void playerUP(){
        if(paddlePY > 0){
            paddlePY -= paddlePDY;
        }
    }

    public void playerDWN(){
        if((paddlePY + PADDLEH) < getHeight()){
            paddlePY += paddlePDY;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        checkCollisions();
        move();
        repaint();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.white);

        g.fillOval(ballX, ballY, BallD, BallD);
        g.fillRect(paddlePX, paddlePY, PADDLEW, PADDLEH);
        g.fillRect(paddleCX, paddleCY, PADDLEW, PADDLEH);
    }

}
