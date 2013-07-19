//Source file: D:\\Fruit.java

package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ��ʵ
 */
public class Fruit 
{
   private int value;//果实的价值
   private int style;//果实的类型，决定价值和外观
   private int x;
   private int y;

//   public void initTest(int x,int y,int s,int v)
//   {
//       this.x=x;
//       this.y=y;
//       style=s;
//       value=v;
//   }
   /**
    * @roseuid 4B5D135502C5
    */
   public Fruit() 
   {
       this.generate();
   }
   /**
    * @roseuid 4B5D0D4B021A
    */
   public void draw(Graphics g)
   {
       switch(style)
       {
           case 0:
               g.setColor(Color.red);
               break;
           case 1:
               g.setColor(Color.green);
               break;
           case 2:
               g.setColor(Color.blue);
               break;
           case 3:
               g.setColor(Color.black);
               break;
       }
       g.fillOval(x*Node.size, y*Node.size, Node.size, Node.size);
       g.setColor(Color.white);
       g.drawString(String.valueOf(value+1), (int)((x+0.35)*Node.size), (int)((y+0.7)*Node.size));
   }
   
   /**
    * @roseuid 4B5D13AD0347
    */
   public void generate() 
   {
       this.style=(int) (Math.random() * 4);//产生类型
       this.value=style;                    //修改值
       x=(int) (Math.random() * Node.scale);//产生位置
       y=(int) (Math.random() * Node.scale);
   }

   public boolean isCollision(Node node)
   {
       return(node.getX()==x&&node.getY()==y);
   }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
