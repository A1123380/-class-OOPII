# Reflection - 骰子模擬器

## 1. AI 提供的建議

這次沒有讓 AI 直接寫完整程式，而是邊寫邊把不懂的地方拿去問：

- **JFrame 和 JPanel 的差別**（[ai/01.jpg](ai/01.jpg)）：AI 用房子比喻，JFrame 是外牆、JPanel 是屋內的牆面，按鈕、標籤這些元件再貼到 JPanel 上。
- **`import java.awt.*` 與 `javax.swing.*` 是什麼**（[ai/02.jpg](ai/02.jpg)）：兩者是套件不是類別，`java.awt` 提供 `Color`、`Font`，`javax.swing` 提供 `JFrame`、`JButton`、`JLabel`。
- **為什麼 `topPanel.setForeground(Color.GREEN)` 沒效果**（[ai/03.jpg](ai/03.jpg)）
- **按鈕按下後怎麼執行動作**（[ai/04.jpg](ai/04.jpg)）：使用 `ActionListener`，可以寫成匿名類別，也可以用 lambda 簡寫成 `btn.addActionListener(e -> rollDice());`。

## 2. 我發現的問題

- 我原本想讓上方的文字變色，對 `topPanel` 呼叫 `setForeground`，畫面卻沒有任何變化。AI 說明 `setForeground` 設定的是文字顏色，而 Panel 本身沒有文字，應該設在 `topLabel` 上；如果要改的是面板顏色，要用 `setBackground`。
- 第一次上傳的版本，對照題目後發現有兩個地方不符合規格：
  - 上方文字打成「以擲骰」，題目是「已擲 N 次，總和 M，平均 X.XX」。
  - 沒有呼叫 `setLocationRelativeTo(null)`，視窗開啟時不會置中。

## 3. 我做的修改

- 上方文字改成和題目一致的「已擲 N 次，總和 M，平均 X.XX」。
- 加上 `frm.setLocationRelativeTo(null)`，讓視窗開啟時置中。
- 顏色從 `new Color(0,255,0)` 改用 `Color.GREEN`、`Color.RED`、`Color.BLACK`，閱讀時一看就知道是什麼顏色。
- 用 `setBackground` 測試過面板背景色，弄懂和 `setForeground` 的差別後，因為題目沒有要求就拿掉了。
- 按鈕事件用 lambda 呼叫 `rollDice()`，把擲骰、累計次數與總和、更新畫面都集中在同一個方法裡。

## 4. 為什麼修改，以及我學到什麼

- 置中和顯示文字都是題目明確列出的規格，程式能跑不代表符合要求，要逐條對照。
- 平均值寫成 `(double)sum / count`：`sum` 和 `count` 都是 `int`，直接相除是整數除法，小數會被捨掉，先轉成 `double` 才能正確顯示到小數點後兩位。
- 每次擲骰都要明確設定顏色，包括點數 2～5 時設回黑色，否則上一次的紅色或綠色會留在畫面上。
- Swing 的畫面是一層一層組起來的：JFrame → JPanel → 元件，再搭配 `BorderLayout` 的 NORTH／CENTER／SOUTH 擺放位置。
- 事件處理的流程是：建立按鈕 → 註冊 `ActionListener` → 按鈕被按下時自動呼叫裡面的程式。
