import java.awt.*;
import javax.swing.*;

public class Exercise1 {
    static JFrame frm = new JFrame("骰子模擬器");
    static JLabel topLabel = new JLabel("已擲 N 次，總和 M，平均 X.XX");
    static JLabel diceLabel = new JLabel("0");
    static JButton btn = new JButton("擲骰子");
    
    static int count = 0;
    static int sum = 0;
    
    static void rollDice() {
        int dice = (int)(Math.random() * 6) + 1;
        count++;
        sum += dice;
        double avg = (double)sum / count;

        diceLabel.setText(String.valueOf(dice));
        if (dice == 6) diceLabel.setForeground(Color.GREEN);
        else if (dice == 1) diceLabel.setForeground(Color.RED);
        else diceLabel.setForeground(Color.BLACK);

        topLabel.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", count, sum, avg));
    }
    
    public static void main(String[] args) {

        frm.setSize(400, 320);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);  // 置中
        
        // 上方標籤
        JPanel topPanel = new JPanel();
        topPanel.add(topLabel);
        
        // 中央骰子顯示
        diceLabel.setFont(new Font(null, Font.PLAIN, 60));
        JPanel centerPanel = new JPanel();
        centerPanel.add(diceLabel);
        
        // 下方按鈕
        btn.addActionListener(e -> rollDice());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btn);
        
        // 組合佈局
        frm.add(topPanel, BorderLayout.NORTH);
        frm.add(centerPanel, BorderLayout.CENTER);
        frm.add(bottomPanel, BorderLayout.SOUTH);
        
        frm.setVisible(true);
    }
}
