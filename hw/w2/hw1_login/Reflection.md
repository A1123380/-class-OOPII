# Reflection - 登入視窗

## 1. AI 提供的建議

題目給的 `BadLogin` 本身就是 AI 產生的程式。我請 AI 依題目寫出修正版（[Login.java](Login.java)），再針對看不懂的部分逐一追問：

- **`JPasswordField`、`GridLayout(2, 2, 5, 10)`、`createEmptyBorder(20, 10, 10, 40)` 的用途**（[ai/01.jpg](ai/01.jpg)、[ai/02.jpg](ai/02.jpg)）
- **監聽寫在哪裡**（[ai/03.jpg](ai/03.jpg)）：AI 指出 `btn.addActionListener(e -> login())` 負責註冊監聽，`login()` 是按下後要執行的內容，並和我在 Ch17_9 用 `implements ActionListener` 的寫法做對照。

## 2. 我發現的問題

對照 `BadLogin` 後，整理出它的問題：

- `setLayout(null)` 卻沒有替元件呼叫 `setBounds`，元件位置和大小都是 0，畫面上看不到。
- 在加入元件之前就呼叫 `setVisible(true)`，後來加的元件可能不會顯示。
- 字串用 `==` 比較，比的是物件參考而不是內容，輸入正確帳密也可能判斷失敗。
- 密碼欄用一般的 `JTextField`，輸入的密碼會直接顯示出來。
- 沒有設定 `EXIT_ON_CLOSE`，關掉視窗後程式還在背景執行。
- 登入結果只用 `System.out.println` 印在主控台，使用者在視窗上看不到。

## 3. 我做的修改

- 用 `GridLayout(2, 2)` 排帳號、密碼兩列，外層用 `BorderLayout` 把表單放中間、按鈕和訊息放下方，取代 `null` 版面。
- 用 `EmptyBorder` 加上內距，讓表單不要貼著視窗邊緣。
- `setVisible(true)` 移到最後，所有元件加完才顯示。
- 字串改用 `equals` 比較；密碼改用 `JPasswordField`，以 `getPassword()` 取值。
- 加上 `EXIT_ON_CLOSE` 和 `setLocationRelativeTo(null)` 置中。
- 新增 `msgLabel` 在視窗上顯示結果：空白時提示輸入、錯誤時顯示紅字並清空密碼、成功時顯示綠字「登入成功」。
- 用 `setDefaultButton` 讓按 Enter 也能登入。

## 4. 為什麼修改，以及我學到什麼

- **版面管理器**：用 `null` 版面就得自己計算每個元件的座標；`GridLayout` 只要依序 `add` 就會自動排列。參數是（列數, 行數, 水平間距, 垂直間距），每格大小相同。
- **`EmptyBorder` 的參數順序是上、左、下、右**，和 CSS 的上、右、下、左不同，容易記反。
- **`JPasswordField`** 會把輸入顯示成圓點；`getPassword()` 回傳的是 `char[]`，要轉成 `String` 才能用 `equals` 比較。建構子裡的 `12` 是建議欄寬，不是字數上限。
- **字串比較要用 `equals`**：`==` 比的是兩個變數是不是同一個物件，不是內容是否相同。
- **事件監聽**：`addActionListener` 把監聽器掛到按鈕上，按下時就會執行。lambda `e -> login()` 和自己寫一個類別 `implements ActionListener` 效果相同；因為 `ActionListener` 只有一個方法，才能簡寫成 lambda。
- 這次也體會到，AI 產生的程式（`BadLogin`）看起來完整，實際上有好幾個會讓畫面不顯示或判斷錯誤的問題，必須自己讀懂、執行過才能確認。
