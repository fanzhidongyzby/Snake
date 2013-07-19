//Source file: D:\\Snake.java

package snake;

import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * С��
 */
public class Snake 
{
   public static int direction=4;//默认向右
   public static boolean eating=false;//正在吃吗
   public static boolean mouthState=false;//嘴的状态
   private Node nodes[];
   private int length;
   public static int x=Node.scale/2-1;
   public static int y=Node.scale/2-1;
   /**
    * @roseuid 4B5D092E0013
    */
   public Snake() 
   {
       nodes=new Node[Node.scale*Node.scale];
       nodes[0]=new Node(x,y,2);//蛇头
       length=1;
   }

   public void reSet()
   {
       direction = 4;
       eating = false;
       mouthState = false;
       for (int i = 0; i < length; i++)
       {
           nodes[i] = null;
       }
       x=y=Node.scale/2-1;
       nodes[0]=new Node(x,y,2);
       length=1;
   }
   
   /**
    * @param fruit
    * @roseuid 4B5D0AE101FC
    */
   public void grow(Fruit fruit) 
   {
       int x0=0,y0=0;
       //先加上一个
       if(length==1)//第一次吃，直接是头部原来的数据,至少一个
       {
           nodes[length]=new Node(nodes[0].getX(),nodes[0].getY(),1);
           length++;
           move();//需要移近
           Main.manager.updateScore();//避免连续的果实被掠过
           Manager.score++;
       }
       else
       {
           //判断最后两块的方向，计算下一块的位置
           if(nodes[length-2].getX()==nodes[length-1].getX())
           {
               x0=nodes[length-2].getX();
               y0=(nodes[length-2].getY()>nodes[length-1].getY())?nodes[length-1].getY()-1:nodes[length-1].getY()+1;
           }
           else if(nodes[length-2].getY()==nodes[length-1].getY())
           {
               x0=(nodes[length-2].getX()>nodes[length-1].getX())?nodes[length-1].getX()-1:nodes[length-1].getX()+1;
               y0=nodes[length-2].getY();
           }
           nodes[length]=new Node(x0,y0,1);
           length++;
           Manager.score++;
       }
       //value从0开始，直接计算
       for(int i=0;i<fruit.getValue();i++)
       {
           //判断最后两块的方向，计算下一块的位置           
           if(nodes[length-2].getX()==nodes[length-1].getX())
           {
               x0=nodes[length-2].getX();
               y0=(nodes[length-2].getY()>nodes[length-1].getY())?nodes[length-1].getY()-1:nodes[length-1].getY()+1;
           }
           else if(nodes[length-2].getY()==nodes[length-1].getY())
           {
               x0=(nodes[length-2].getX()>nodes[length-1].getX())?nodes[length-1].getX()-1:nodes[length-1].getX()+1;
               y0=nodes[length-2].getY();
           }
           nodes[length]=new Node(x0,y0,1);
           length++;
           Manager.score++;
       }
       //积分策略
       int n=40;
       if(Manager.score>=5*n)//通关
       {
           JOptionPane.showMessageDialog(null, "恭喜你：通关！得分:"+Manager.score);
           return ;
       }
       if(Manager.score/n!=Manager.level)//升级
       {
           Main.manager.setLevel(Manager.level=Manager.score/n);
       }
       
       Main.manager.setLabels();
   }

   //价值，不要用direction就可以判断，避免快速转向咬住自己...
   public int getCurDirection()
   {
       int curDir=0;
       if(length>1)
       {
           //判断前边两块的方向，计算行走方向
           if(nodes[0].getX()==nodes[1].getX())
           {
               curDir=(nodes[0].getY()>nodes[1].getY())?2:1;
           }
           else if(nodes[0].getY()==nodes[1].getY())
           {
               curDir=(nodes[0].getX()>nodes[1].getX())?4:3;
           }
       }
       return curDir;
   }
   /**
    * @param dir
    * @roseuid 4B5D0B5900A8
    */
   public void move() 
   {
       //修改后边
       for(int i=length-1;i>=1;i--)
       {
           nodes[i].setX(nodes[i-1].getX());
           nodes[i].setY(nodes[i-1].getY());
       }
       int x0=nodes[0].getX();
       int y0=nodes[0].getY();
       switch(direction)//修改头部
       {           
           case 1:
               nodes[0].setY(y0-1);
               break;
           case 2:
               nodes[0].setY(y0+1);
               break;
           case 3:
               nodes[0].setX(x0-1);
               break;
           case 4:
               nodes[0].setX(x0+1);
               break;
       }
       x=nodes[0].getX();
       y=nodes[0].getY();       
   }
   
   /**
    * @roseuid 4B5D0C9F0101
    */
   public void draw(Graphics g)
   {
       for(int i=length-1;i>=0;i--)
       {
           nodes[i].draw(g);
       }
   }

   public boolean isCollision(Node node)
   {
       for(int i=1;i<length;i++)//除了脑袋一外
       {
           if(nodes[i]!=null)
           {
               if(node.getX()==nodes[i].getX()&&node.getY()==nodes[i].getY())
                   return true;
           }
       }
       return false;
   }
   
}
