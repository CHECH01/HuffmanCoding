package HuffmanCoding;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Canvas;

public class HuffmanTree extends JFrame{

    private static final long serialVersionUID = 1L;
    int matrix[][];
    Canvas c;
    char[] symbols;
    HuffmanTree(int matrix[][], char[] symbols, String[] encryptedSymbols){
        super("Huffmann Tree");
        setBounds(200, 5, 900, 725);
        try{
            remove(c);
        }catch(Exception e){}
        this.matrix = matrix.clone();
        this.symbols = symbols.clone();
        c = new Canvas() {
            private static final long serialVersionUID = 1L;
            public void paint(Graphics g){
                g.setColor(Color.red);
                g.setFont(new Font("Bold", 1, 20));
                drawTree(g,matrix[0].length-1,430,100,200);
                symbolCodes(g, encryptedSymbols);
            }
        };
        c.setBackground(Color.black);
        add(c);
        setVisible(false);
    }
    public void open(){
        setVisible(true);
    }
    public void drawTree(Graphics g, int j, int x,int y, int level){
        if(j < symbols.length){
            drawNode(g, String.valueOf(j), x, y);
            drawLine(g,x+5, y+2, x+5, y+13);
            drawNode(g, String.valueOf(symbols[j]), x+2, y+30);
        }else{
            drawNode(g, String.valueOf(j), x, y);
            drawLine(g,x+10, y+2, x-level+10, y+30);
            drawTree(g, matrix[3][j], x-level, y+50,level/2);
            drawLine(g,x+10, y+2, x+level+10, y+30);
            drawTree(g, matrix[4][j], x+level, y+50,level/2);
        }
    }
    public void symbolCodes(Graphics g, String[] encryptedSymbols){
        int k = 0;
        int j = 0;
        for(int i = 0; i < symbols.length; i++){
            if(i % 3 == 0){
                k ++;
                j = 0;
            }
            g.setColor(Color.orange);
            g.drawString(String.valueOf(symbols[i])+": ",300+(j*100), 400+(k*25));
            g.setColor(Color.white);
            g.drawString(String.valueOf(encryptedSymbols[i]),330+(j*100), 400+(k*25));
            j++;
        }
        
    }
    public void drawNode(Graphics g,String node,int x, int y){
        
        g.setColor(Color.white);
        g.drawString(node, x, y);
    }
    public void drawLine(Graphics g,int x1,int y1,int x2, int y2){
        g.setColor(Color.red);
        g.drawLine(x1, y1, x2, y2);
    }
}