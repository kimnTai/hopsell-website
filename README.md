# [hopsell 跳躍拍賣](https://kimntai.github.io/hopsell-website/public/home.html) - C2C 購物平台

 * 本專案是建立二手拍賣的 C2C 購物平台，讓目標客群擁有買賣自己商品的管道。
 * 跳躍拍賣的目的在於，將自己使用不到的物品轉售給其他更需要的人。
 * 以衣服為例，在這個快時尚的時代，每週都有新品不斷上市，讓已經穿不到的衣服，能上架給其他人選購。
 * 不僅達到資源有效循環，也減少不必要的浪費。

## Demo
點擊該網址即可前往 hopsell 跳躍拍賣 : https://kimntai.github.io/hopsell-website/public/home.html</br>
P.S.因虛擬機休眠機制，若無畫面請 F5 重新整理或關閉再次訪問。

Demo User : Demo</br>
Password : Demo

Admin User : admin</br>
Password : admin

亦可使用註冊功能，建立自己的帳號使用網站功能。

## 使用技術

 * 前端: Vue3.js、AXIOS、Bootstrap5、ElementPlus、SASS
 * 後端: SpringBoot、MyBatis-Plus
 * 資料庫:MySQL、Firebase
 * RESTful API 架構實踐專案功能
 * 使用 GitHub Pages、Heroku、db4free 進行佈署

## 技術架構流程圖

 * 前端 Vue 框架使用 axios 進行 request 請求至後端，後端使用 SpringBoot 整合開發 API 的架構，應用 MybaitsPlus 完成對資料庫的增刪改查。

  ![image](https://i.imgur.com/pocHGFO.png)

## 功能介紹

### 首頁瀏覽、商品搜尋、商品詳情

 * 滾輪往下滑，可以閱覽所有商品(1次重新列出8筆新商品)

  ![image](https://i.imgur.com/esuNtec.png)

 * 透過關鍵字搜尋商品，並實踐即時分類功能

  ![image](https://i.imgur.com/LkwLzgf.png)

 * 點擊商品可以查看詳情，並顯示賣家資訊與評價

   ![image](https://i.imgur.com/GkV2af6.png)

 * 賣家私訊聊天

   ![image](https://i.imgur.com/Gn4qYnJ.png)


### 用戶帳號管理、個人賣場

 * 使用者可以註冊、登入、登出網站系統

  ![image](https://i.imgur.com/86k6Wzn.png)

 * 用戶設定中可以修改個人資訊、更改密碼

  ![image](https://i.imgur.com/03NpMT6.png)

 * 每位使用者皆有一個屬於自己的專屬賣場

  ![image](https://i.imgur.com/rXgdP82.png)

 * 查看個人交易紀錄，並有出貨、收貨評價功能

  ![image](https://i.imgur.com/ZwMTSMN.png)

 * 個人商品上架、編輯

  ![image](https://i.imgur.com/zc0pA34.png)

### 後台管理系統

 * 商品管理:查詢以及瀏覽所有商品、總覽匯出 Excel 格式、下架與上架維護

  ![image](https://i.imgur.com/9baw8ym.png)

 * 用戶管理:點擊按鈕查看，會員個人商品與個人評價；違規用戶停權

  ![image](https://i.imgur.com/9baw8ym.png)

 * 訂單管理:暫停交易、協助驗收

  ![image](https://i.imgur.com/m5pnjCs.png)

 * 檢舉清單:前台用戶檢舉、違規商品下架 

  ![image](https://i.imgur.com/OnXQOJJ.png)


