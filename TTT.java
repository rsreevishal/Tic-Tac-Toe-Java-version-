import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
//<applet code="TTT.class" width=300 height = 300></applet>
public class TTT  extends JApplet{
    class Cell extends JButton implements ActionListener{
        int x,y,state;
        Border b ;
        Cell(int x,int y,int state){
            this.x = x;
            this.y = y;
            this.state = 0;
            addActionListener(this);
            this.setBackground(Color.white);
            this.b = BorderFactory.createLineBorder(Color.black);
            this.setBorder(this.b);
        }
        @Override
        public void actionPerformed(ActionEvent ae){
            if(GO == 0 && this.state!= 1){
                this.state = 1;
                if(TURN%2==0){
                    this.setText("X");
                }
                else{
                    this.setText("O");
                }
                TURN += 1;
                result.setText("Turn for player:"+((TURN%2)+1));
                if(victory("X")){
                    result.setText("The Winner is Player:1");
                    GO = 1;
                    res.setVisible(true);
                }
                else if(victory("O")){
                    result.setText("The Winner is Player:2");
                    GO = 1;
                    res.setVisible(true);
                }
                else if(draw()){
                    result.setText("Draw..!");
                    GO = 1;
                    res.setVisible(true);
                }
            }
        }
        boolean victory(String p){
           for(int i =0;i<3;i++){
               if(table[i][0].getText().equals(p) && table[i][1].getText().equals(p) && table[i][2].getText().equals(p)){
                   highlight(table[i][0],p);
                   highlight(table[i][1],p);
                   highlight(table[i][2],p);
                   return true;
               }
           }
           for(int i =0;i<3;i++){
               if(table[0][i].getText().equals(p) && table[1][i].getText().equals(p) && table[2][i].getText().equals(p)){
                   highlight(table[0][i],p);
                   highlight(table[1][i],p);
                   highlight(table[2][i],p);
                   return true;
               }
           }
           if(table[0][0].getText().equals(p) && table[1][1].getText().equals(p) && table[2][2].getText().equals(p)){
                   highlight(table[0][0],p);
                   highlight(table[1][1],p);
                   highlight(table[2][2],p);
                   return true;
           }
           if(table[0][2].getText().equals(p) && table[1][1].getText().equals(p) && table[2][0].getText().equals(p)){
                   highlight(table[0][2],p);
                   highlight(table[1][1],p);
                   highlight(table[2][0],p);
                   return true;
           }
           return false;
        }
        boolean draw(){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(table[i][j].state == 1){
                        continue;
                    }
                    else{
                        return false;
                    }
                }
            }
            return true;
        }
        void highlight(Cell c,String txt){
            c.setForeground(Color.red);
            c.setText(txt);
        }
    }
     int GO = 0;
    JFrame main;
    JPanel gArea;
    JPanel status;
    JLabel result,title;
    JButton res = new JButton("Restart");
    Cell[][] table;
    int TURN = 0;
    TTT(){
        main = new JFrame();
        main.setSize(300,300);
        main.setVisible(true);
        table = new Cell[3][3];
        result = new JLabel("Turn for player:"+((TURN%2)+1));
        title = new JLabel("  TIC-TAC-TOE   ");
        Font tle = new Font(Font.SERIF,Font.BOLD,35);
        title.setFont(tle);        
        setLayout(new BorderLayout());
        gArea = new JPanel();
        status = new JPanel();
        gArea.setLayout(new GridLayout(3,3));
        status.setLayout(new GridLayout(1,2));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                table[i][j] = new Cell(i,j,0);
                gArea.add(table[i][j]);
            }
        }
        res.setVisible(false);
        status.add(result);
        status.add(res);
        main.add(title,BorderLayout.NORTH);
        main.add(gArea,BorderLayout.CENTER);
        main.add(status,BorderLayout.SOUTH);  
        res.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                res.setVisible(false);
                GO = 0;
                TURN = 0;
                result.setText("Turn for player:"+((TURN%2)+1));
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        table[i][j].setText("");
                        table[i][j].state = 0;
                        table[i][j].setForeground(Color.black);
                    }
                 }
            }
        });
    }
    public static void main(String args[]){
        new TTT();
    }
}

