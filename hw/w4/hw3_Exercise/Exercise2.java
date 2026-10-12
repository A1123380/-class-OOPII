import java.awt.*;
import javax.swing.*;

public class Exercise2 {
    static JFrame frm = new JFrame("單位換算器");
    static JComboBox<String> typeBox = new JComboBox<>(new String[] {"長度", "重量", "溫度"});
    static JTextField fromField = new JTextField();
    static JComboBox<String> fromBox = new JComboBox<>();
    static JTextField toField = new JTextField();
    static JComboBox<String> toBox = new JComboBox<>();
    static JButton btn = new JButton("換算");

    // 每種類型的單位，順序對應 typeBox 的選項
    static String[][] units = {
        {"公尺", "公分", "英吋", "英尺"},
        {"公斤", "公克", "磅", "盎司"},
        {"攝氏", "華氏", "克氏"}
    };

    // 長度、重量：1 單位等於多少公尺、公斤
    static double[][] rate = {
        {1, 0.01, 0.0254, 0.3048},
        {1, 0.001, 0.45359237, 0.028349523125}
    };

    // 切換換算類型時，兩個單位選單的選項跟著換
    static void updateUnits() {
        fromBox.removeAllItems();
        toBox.removeAllItems();
        for (String u : units[typeBox.getSelectedIndex()]) {
            fromBox.addItem(u);
            toBox.addItem(u);
        }
        toBox.setSelectedIndex(1);  // 目標單位預設選第二個，才不會跟來源一樣
        toField.setText("");
    }

    static void convert() {
        int type = typeBox.getSelectedIndex();
        int from = fromBox.getSelectedIndex();
        int to = toBox.getSelectedIndex();
        double value, result;

        try {
            value = Double.parseDouble(fromField.getText());
        } catch (NumberFormatException e) {
            toField.setText("請輸入數字");
            return;
        }

        if (type == 2) {  // 溫度：先換成攝氏，再換成目標單位
            double c = value;
            if (from == 1) c = (value - 32) * 5 / 9;
            if (from == 2) c = value - 273.15;

            result = c;
            if (to == 1) result = c * 9 / 5 + 32;
            if (to == 2) result = c + 273.15;
        } else {  // 長度、重量：先換成公尺、公斤，再換成目標單位
            result = value * rate[type][from] / rate[type][to];
        }

        toField.setText(String.format("%.4f", result));
    }

    public static void main(String[] args) {
        frm.setSize(480, 280);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocationRelativeTo(null);  // 置中

        // 上方：換算類型
        JPanel topPanel = new JPanel();
        topPanel.add(typeBox);

        // 中間：兩列，每列一個文字框 + 一個單位選單
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        centerPanel.add(fromField);
        centerPanel.add(fromBox);
        centerPanel.add(toField);
        centerPanel.add(toBox);

        // 下方：換算按鈕
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btn);

        // 組合佈局
        frm.add(topPanel, BorderLayout.NORTH);
        frm.add(centerPanel, BorderLayout.CENTER);
        frm.add(bottomPanel, BorderLayout.SOUTH);

        updateUnits();  // 一開始先放入長度的單位
        typeBox.addActionListener(e -> updateUnits());
        btn.addActionListener(e -> convert());

        frm.setVisible(true);
    }
}
