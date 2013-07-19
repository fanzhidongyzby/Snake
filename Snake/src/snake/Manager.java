//Source file: D:\\Manager.java

package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
/**
 * ����С��
 */
public class Manager extends Canvas implements ActionListener
{
   private Snake snake;
   private Fruit fruits[];
   private Block blocks[];

   private int blockNum;
   private int fruitNum;
   private int blockScale;
   private int curfruitNum;

   private Timer timer;
   private boolean gameIsOver=false;
   private boolean gameIsBegin=false;
   public static int score=0;
   public static int level=0;
   
   /**
    * @roseuid 4B5D096802B1
    */
   public Manager() 
   {
       blockScale=3;
       curfruitNum=0;
       timer=new Timer(300,this);
       snake=new Snake();
       blockNum=0;
       fruitNum=0;
       //setLevel(1);
       init();
       //timer.start();
   }
    public static void playBckMusic(String bckMusic)
    {
        AudioPlayer player=AudioPlayer.player;//音乐控制线程
        InputStream inputStream=null;//音乐文件流
        if (player.isAlive())//有音乐线程
        {
            player.stop(inputStream);//停止旧音乐线程
        }
        try {
            inputStream = new FileInputStream(new File(bckMusic));//初始化文件流
        } catch (FileNotFoundException ex) {
        }
        player.start(inputStream);
    }

   public Fruit newFruit()
   {
       int x0,y0;
       while(true)
       {
           Fruit f=new Fruit();
           f.generate();
           //果实冲突决策
           x0=f.getX();
           y0=f.getY();
           boolean collideBlocks=false;
           for(int j=0;j<blockNum;j++)//与障碍冲突
           {
               if(blocks[j].isCollision(new Node(x0,y0,1)))
               {
                   collideBlocks=true;
                   break;
               }
           }
           boolean collideFruits=false;//与其他果实冲突
           for(int j=0;j<curfruitNum;j++)
           {
               if(fruits[j].getX()==x0&&fruits[j].getY()==y0)
               {
                   collideFruits=true;
                   break;
               }
           }
           if(snake.isCollision(new Node(x0,y0,1))||collideBlocks||collideFruits)//或者与蛇冲突
           {
               continue;
           }

           return  f;//产生有效的果实
       }
   }

   public void setLabels()
   {
       //显示
       Main.instance.getLbScore().setText("得分:"+String.valueOf(Manager.score));
       Main.instance.getLbLevel().setText("级别:"+String.valueOf(Manager.level));
       Main.instance.getLbBlock().setText("障碍数:"+String.valueOf(blockNum));
       Main.instance.getLbFruit().setText("果子数:"+String.valueOf(fruitNum));
       Main.instance.getLbBlockScale().setText("障碍复杂度:"+String.valueOf(blockScale));
       Main.instance.getLbSpeed().setText("速度:1000/"+String.valueOf(timer.getDelay())+"s");
   }
   
   public void setLevel(int l)
   {
       if(l==0)
           return ;
       timer.stop();
       snake.reSet();
       curfruitNum=0;
       JOptionPane.showMessageDialog(null, "恭喜你：升至"+level+"级，得分："+score);
       switch(l)
       {
           case 1:
               timer.setDelay(250);
               blockNum=5;
               fruitNum=5;
               break;
           case 2:
               timer.setDelay(200);
               blockNum=10;
               fruitNum=10;
               break;
           case 3:
               timer.setDelay(150);
               blockNum=10;
               fruitNum=10;
               break;
           case 4:
               timer.setDelay(150);
               blockNum=15;
               blockScale=4;
               fruitNum=20;
               break;
       }
       init();
       timer.start();
   }
   public void init()
   {
       int x0,y0;
       //初始化障碍物
       blocks=new Block[blockNum];
       for(int i=0;i<blockNum;i++)
       {
           blocks[i]=new Block();
           blocks[i].generate(blockScale);
           //障碍物冲突决策
           x0=blocks[i].getX();
           y0=blocks[i].getY();
           if(x0>(Snake.x-3)&&x0<(Snake.x+3)&&y0>(Snake.y-3)&&y0<(Snake.y+3))//与蛇冲突，不用考虑障碍与障碍冲突
           {
               i--;
           }
       }
       //初始化果实
       fruits=new Fruit[fruitNum];
       for(int i=0;i<fruitNum;i++)
       {
           fruits[i]=newFruit();
           curfruitNum++;
       }

   }


   /**
    * @roseuid 4B5D0A1E018B
    */
   public void up() 
   {
       if(snake.getCurDirection()==2||snake.getCurDirection()==1)
           return ;
       Snake.direction=1;
       playBckMusic("./sound/ctrl.wav");
   }
   
   /**
    * @roseuid 4B5D0A2E00AF
    */
   public void down() 
   {
       if(snake.getCurDirection()==1||snake.getCurDirection()==2)
           return ;
       Snake.direction=2;
       playBckMusic("./sound/ctrl.wav");
   }
   
   /**
    * @roseuid 4B5D0A30028F
    */
   public void left() 
   {
       if(snake.getCurDirection()==4||snake.getCurDirection()==3)
           return ;
       Snake.direction=3;
       playBckMusic("./sound/ctrl.wav");
   }
   
   /**
    * @roseuid 4B5D0A390253
    */
   public void right() 
   {
       if(snake.getCurDirection()==3||snake.getCurDirection()==4)
           return ;
       Snake.direction=4;
       playBckMusic("./sound/ctrl.wav");
   }
   
   /**
    * @roseuid 4B5D0AAF001C
    */
   public void draw(Graphics g)
   {
       //DrawBkg
//       g.setColor(Color.black);
//       for(int i=1;i<Node.scale;i++)
//       {
//           g.drawLine(Node.size * i, 0, Node.size * i, this.getHeight());
//           g.drawLine(0, Node.size * i, this.getWidth(), Node.size * i);
//       }
       //DrawSnake
       snake.draw(g);
       //DrawBlock
       for(int i=0;i<blockNum;i++)
       {
           blocks[i].draw(g);
       }
       //DrawFruit
       for(int i=0;i<fruitNum;i++)
       {
           fruits[i].draw(g);
       }
       //DrawEnds
       if(gameIsOver)
       {
           gameIsBegin=false;
           timer.stop();
           JOptionPane.showMessageDialog(null, "对不起，挑战失败！级别:"+level+",得分:"+score);
       }
   }
   
   /**
    * @roseuid 4B5D1554026C
    */
   public void updateScore() //可以优化判断
   {
       boolean die = false;
       for (int j = 0; j < blockNum; j++)//与障碍冲突
       {
           if (blocks[j].isCollision(new Node(Snake.x, Snake.y, 1)))
           {
               die = true;
               break;
           }
       }
       if(Snake.x<0||Snake.x>=Node.scale||Snake.y<0||Snake.y>=Node.scale)
       {
           die=true;//碰到边界了
       }
       if(snake.isCollision(new Node(Snake.x,Snake.y,1)))
       {
           die=true;//碰到自己了
       }
       Snake.eating = false;//与其他果实冲突
       int eatingFruit=-1;//记录要吃的果实的索引
       for (int j = 0; j < fruitNum; j++)
       {
           if (fruits[j].getX() == Snake.x && fruits[j].getY() == Snake.y)
           {
               Snake.eating = true;
               eatingFruit=j;
               break;
           }
       }

       //控制决策
       if(die)//游戏结束
       {
           gameIsOver=true;           
       }
       else if(Snake.eating)//得分
       {
           snake.grow(fruits[eatingFruit]);
           fruits[eatingFruit]=this.newFruit();
           playBckMusic("./sound/eat.wav");
       }
   }
   
   /**
    * @roseuid 4B5D16FC0396
    */
   public void saveGame() 
   {
       timer.stop();
   }
   
   /**
    * @roseuid 4B5D17030332
    */
   public void continueGame() 
   {
       timer.start();
   }
   
   /**
    * @roseuid 4B5D171C038C
    */
   public void newGame() 
   {
       gameIsOver=false;
       gameIsBegin=true;
       score=0;
       level=0;
       timer.stop();
       blockScale=3;
       curfruitNum=0;
       timer.setDelay(300);
       snake.reSet();
       blockNum=2;
       fruitNum=5;
       init();
       setLabels();
       timer.start();
   }

   @Override
    public void paint(Graphics g)
    {
        this.draw(g);
    }

    @Override
    public void update(Graphics g)
    {
        Image buffer = createImage(getWidth(), getHeight());
        Graphics GraImage = buffer.getGraphics();
        paint(GraImage);
        GraImage.dispose();
        g.drawImage(buffer,0,0,null);
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==timer)
        {
            snake.move();
            updateScore();
            this.repaint();
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int key=e.getKeyCode();
        switch(key)
        {
            case KeyEvent.VK_UP:
                up();
                break;
            case KeyEvent.VK_DOWN:
                down();
                break;
            case KeyEvent.VK_LEFT:
                left();
                break;
            case KeyEvent.VK_RIGHT:
                right();
                break;
            case KeyEvent.VK_SPACE:
                if(!gameIsBegin)
                    return ;
                if(timer.isRunning())
                {
                    saveGame();
                }
                else
                {
                    continueGame();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                newGame();
        }        
    }
}
