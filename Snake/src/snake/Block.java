//Source file: D:\\Block.java

package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �ϰ���
 */
public class Block 
{
   private Node nodes[];
   private int x;//障碍的位置
   private int y;
   private int nodeNum;//难度级别
   /**
    * @roseuid 4B5D135503A1
    */
   public Block() 
   {
       nodes=new Node[9];
   }
   
   /**
    * @roseuid 4B5D104E0076
    */
   public void draw(Graphics  g)
   {
       //g.setColor(Color.yellow);
       //g.drawRect(x*Node.size, y*Node.size, 3*Node.size, 3*Node.size);
       for(int i=0;i<9;i++)
       {
           if(nodes[i]!=null)
           {
               nodes[i].draw(g);
           }
       }

//备用
//       for (int j = 0; j < 9; j++)
//       {
//           if (nodes[j] == null)
//           {
//               Fruit f = new Fruit();
//               f.initTest(j % 3 + x, j / 3 + y, 0, j);
//               f.draw(g);
//           }
//       }
   }
   
   /**
    * @roseuid 4B5D105502CE
    */
   public void generate(int n)
   {
        nodeNum=n;
        x=(int) (Math.random() * Node.scale);
        y=(int) (Math.random() * Node.scale);
        int row;
        int col;
        int x0,y0;
        for (int i = 0; i < nodeNum; i++)//随机产生nodeNum个方块
        {
            row = (int) (Math.random() * 3);//随机行
            col = (int) (Math.random() * 3);//随机列
            y0=y+row;
            x0=x+col;
            nodes[row*3+col]=new Node(x0,y0,4);
        }

    }

   public boolean isCollision(Node node)
   {
       for(int i=0;i<9;i++)
       {
           if(nodes[i]!=null)
           {
               if(node.getX()==nodes[i].getX()&&node.getY()==nodes[i].getY())
                   return true;
           }
       }
       return false;
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
}






