import java.awt.*;
import javax.swing.*;

public class Login {
    static JFrame frm = new JFrame("登入");
    static JLabel userLabel = new JLabel("帳號：", JLabel.RIGHT);
    static JTextField userField = new JTextField(12);
    static JLabel pwdLabel = new JLabel("密碼：", JLabel.RIGHT);
    static JPasswordField pwdField = new JPasswordField(12);  // 修正：密碼改用 JPasswordField，輸入時顯示圓點
    static JButton btn = new JButton("登入");
    static JLabel msgLabel = new JLabel(" ", JLabel.CENTER);

    static void login() {
        String user = userField.getText();
        String pwd = new String(pwdField.getPassword());

        if (user.isEmpty() || pwd.isEmpty()) {
            msgLabel.setForeground(Color.RED);
            msgLabel.setText("請輸入帳號和密碼");
        } else if (user.equals("admin") && pwd.equals("1234")) {  // 修正：字串用 equals 比較內容，不用 ==
            msgLabel.setForeground(new Color(0, 150, 0));
            msgLabel.setText("登入成功");
        } else {
            msgLabel.setForeground(Color.RED);
            msgLabel.setText("帳號或密碼錯誤");
            pwdField.setText("");
        }
    }

    public static void main(String[] args) {
        frm.setSize(300, 200);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 修正：關閉視窗時結束程式
        frm.setLocationRelativeTo(null);  // 置中

        // 中間：帳號、密碼（修正：用 GridLayout 排版，取代沒設位置的 null 版面）
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 40));
        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(pwdLabel);
        formPanel.add(pwdField);

        // 下方：按鈕與結果訊息
        JPanel btnPanel = new JPanel();
        btnPanel.add(btn);
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(btnPanel);
        bottomPanel.add(msgLabel);

        frm.add(formPanel, BorderLayout.CENTER);
        frm.add(bottomPanel, BorderLayout.SOUTH);

        btn.addActionListener(e -> login());
        frm.getRootPane().setDefaultButton(btn);  // 按 Enter 也能登入

        frm.setVisible(true);  // 修正：元件都加完後才顯示視窗
    }
}
