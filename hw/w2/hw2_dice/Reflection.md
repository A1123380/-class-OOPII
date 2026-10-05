# Reflection - 骰子模擬器

## AI 給了什麼

這題程式是我自己寫的，卡住的地方才去問 AI（[ai/01.jpg](ai/01.jpg) ~ [ai/04.jpg](ai/04.jpg)）：

- JFrame 和 JPanel 差在哪
- `import java.awt.*`、`javax.swing.*` 是什麼
- 為什麼 Panel 設了文字顏色沒反應
- 按鈕按下去要怎麼觸發動作

## 遇到的問題

- 對 Panel 用 `setForeground` 想改文字顏色，結果沒效果。原來 Panel 本身沒有文字，要設在 Label 上。
- 第一版交出去後才發現文字打成「以擲骰」，而且視窗沒有置中。

## 改了什麼

- 文字改成「已擲」
- 加上 `setLocationRelativeTo(null)` 讓視窗置中
- 顏色改用 `Color.GREEN` 這種寫法，比較好讀

## 學到什麼

Swing 就是 JFrame 裡放 JPanel，JPanel 裡再放元件；按鈕用 `addActionListener` 監聽。算平均時要先轉 `double`，不然小數會不見。還有交作業前要對照題目一條一條檢查。
