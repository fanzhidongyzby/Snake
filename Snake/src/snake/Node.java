//Source file: D:\\Node.java
package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �ߵĹؽڵ���ϰ���Ľڵ�
 */
public class Node 
{
   public static int size=10;//默认块的大小
   public static int scale=50;//默认块的规模
   private int style;//区分是蛇的还是障碍物的
   private int x;//位置
   private int y;
   /**
    * @roseuid 4B5D092D0329
    */
   public Node()
   {
       this(0,0,1);
   }


   public Node(int x,int y,int style)
   {
       this.style=style;
       this.x=x;
       this.y=y;
   }
   
   /**
    * @roseuid 4B5D0CCA02A5
    */
   public void draw(Graphics g)
   {
       int x0 = x*size;
       int y0 = y*size;
       int []ctrlMouthAngle=new int[]  //嘴的张角
       {
           135, -45,-135,45
       };
       double [][] ctrlEyeDisp=new double[][]//眼睛的偏移
       {
           {0.33,0.66},
           {0.66,0.33},
           {0.66,0.33},
           {0.33,0.33}
       };
       switch(style)
       {
           case 1://绘制小蛇的节点
               g.setColor(Color.yellow);
               g.fillOval(x0 + 1, y0 + 1, size, size);
               break;
           case 2://画蛇头,张开
               if(Snake.eating)
                   g.setColor(Color.pink);
               else
                   g.setColor(Color.red);
               g.fillArc(x0 + 1, y0 + 1, size, size,ctrlMouthAngle[Snake.direction-1],270+90*((Snake.mouthState=!Snake.mouthState)?1:0));
               g.setColor(Color.yellow);
               g.fillOval(x0+(int)(ctrlEyeDisp[Snake.direction-1][0]*size), y0+(int)(ctrlEyeDisp[Snake.direction-1][1]*size),3,3);
               break;
           case 3:
               break;
           case 4://绘制障碍物的节点
               g.setColor(Color.gray);
               g.fillRect(x0 + 1, y0 + 1, size-1, size-1);
               g.setColor(Color.black);
               g.drawLine(x0+1, y0+1, x0+size-1, y0+size-1);
               g.drawLine(x0+1, y0+size-1, x0+size-1, y0+1);
               break;
       }
   }

    /**
     * @return the style
     */
    public int getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(int style) {
        this.style = style;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
