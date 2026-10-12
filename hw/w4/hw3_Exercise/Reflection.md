# Reflection - 單位換算器

## AI 給了什麼

題目是 [Exercise2.png](Exercise2.png)（GUI 簡報第 57 頁）。這題我先請 AI 照規格寫出第一版，再自己對照題目一條一條看、實際跑過。看不懂的地方再請 AI 說明（[ai/01.png](ai/01.png) ~ [ai/04.png](ai/04.png)）：

- `JComboBox<String>` 是什麼、有哪些方法可以用
- `JTextField` 的 `getText`、`setText`
- `units`、`rate` 這兩個二維陣列的目的
- `updateUnits()` 每一行在做什麼

## 遇到的問題

- AI 第一版有 107 行，還用了 `DecimalFormat`、`DefaultComboBoxModel` 這些沒學過的東西，看不太懂。
- 長度、重量是乘除就好，但溫度有加減（華氏要 -32、克氏要 -273.15），不能用同一條公式。
- 文字框沒輸入或打了不是數字的東西，`Double.parseDouble` 會直接丟例外。

## 改了什麼

- 請 AI 改成比較簡單的寫法：選單內容改用 `removeAllItems` + `addItem` 一個一個加，小數改用骰子作業用過的 `String.format`。
- 單位用二維陣列存，`typeBox` 選第幾個就拿第幾列。
- 長度、重量各存一組「換成公尺／公斤的倍率」，先換成基準單位再換成目標單位，不用每一組單位都寫一條公式。
- 溫度另外用 `if` 處理，先全部換成攝氏，再換成目標單位。
- 用 `try-catch` 接 `NumberFormatException`，輸入錯就在結果欄顯示「請輸入數字」。

## 學到什麼

`JComboBox` 可以用 `getSelectedIndex` 拿到選第幾個，搭配陣列就不用寫一堆 `if`。另外換算類的題目先統一換成一個基準單位，程式會短很多。AI 給的程式不一定是最好懂的，看不懂可以請它換個寫法。
