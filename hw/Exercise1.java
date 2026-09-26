import javax.swing.*;
import java.awt.*;

public class Exercise1{
    static JFrame frm = new JFrame("骰子模擬器");
    
    static JPanel tpan = new JPanel();
    static JLabel tlab = new JLabel("以擲骰 N 次，總和 M ，平均 X.XX");
    
    static JPanel dpan = new JPanel();
    static JLabel dlab = new JLabel("0");

    static JPanel bpan = new JPanel();
    static JButton btn = new JButton("擲骰子");

    static int count = 0;
    static int sum = 0;


    static void rollDice(){
        int dice = (int)(Math.random()*6)+1;
        count++;
        sum += dice;
        double avg = (double)sum/count;

        dlab.setText(String.valueOf(dice));
        if(dice == 6){
            dlab.setForeground(new Color(0,255,0));
        }else if(dice == 1){
            dlab.setForeground(new Color(255,0,0));
        }else{
            dlab.setForeground(new Color(0,0,0));
        }
        tlab.setText(String.format("以擲骰 %d 次，總和 %d ，平均 %.2f", count, sum, avg));
    }

    public static void main (String[] args){
        frm.setSize(400,320);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dlab.setFont(new Font("Serif", Font.BOLD, 60));

        frm.add(tpan, BorderLayout.NORTH);
        frm.add(dpan, BorderLayout.CENTER);
        frm.add(bpan, BorderLayout.SOUTH);

        tpan.add(tlab);
        dpan.add(dlab);
        bpan.add(btn);
    
        btn.addActionListener(e -> rollDice());

        frm.setVisible(true);
    }
}