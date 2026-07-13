# DB設計

## テーブル一覧

- Pets
- Hospitals
- Reservations
- Pet_Weight_Records
- Pet_Notes

---

## テーブルごとの関係性

### 関係性イメージ

Pets
├─ Reservations
├─ Pet_Weight_Records
└─ Pet_Notes

Hospitals
└─ Reservations

### Pets（1対多）

- Pets 1件に対して Reservations は複数件持てる
  - 予約は過去の予約を含めて複数件登録できるため。
- Pets 1件に対して Pet_Weight_Records は複数件持てる
  - 体重は測定のたびに記録が増えるため。
- Pets 1件に対して Pet_Notes は複数件持てる
  - ひとことは1日に複数件登録できる仕様のため。

### Hospitals（1対多）

- Hospitals 1件に対して Reservations は複数件持てる
  - 1つの病院に対して複数の予約が登録されるため。
