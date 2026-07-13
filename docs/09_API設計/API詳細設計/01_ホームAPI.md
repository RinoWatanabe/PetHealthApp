# ホームAPI

## ホーム情報取得

### API概要

ホーム画面に表示する、近日の予約およびペットごとの最新情報を取得する。

---

## エンドポイント

GET /api/home

---

## リクエスト

なし

#### Request JSON

なし

---

## レスポンス

### 近日の予約

- 予約ID
- ペット名
- 予約日
- 病院名
- 通院の目的

### ペット別情報

- ペットID
- ペット名
- 種別
- 種別(その他)

#### 前回通院

- 通院日（予約日）
- 病院名
- 通院の目的

#### 最新体重

- 体重ID
- 測定日
- 体重

#### 最新のひとこと

- ひとことID
- 記録日
- ひとこと

### Response JSON

```json
{
  "upcomingReservations": [
    {
      "reservationId": 1,
      "petName": "ポチ",
      "appointmentDate": "2026-08-02",
      "hospitalName": "北動物病院",
      "visitReason": "健康診断"
    },
    {
      "reservationId": 2,
      "petName": "タマ",
      "appointmentDate": "2026-09-03",
      "hospitalName": "南動物病院",
      "visitReason": "お薬処方"
    }
  ],
  "petSummaries": [
    {
        "petId": 1,
        "petName": "ポチ",
        "petType": "犬",
        "customPetType": null,
        "lastVisit": {
            "appointmentDate": "2026-05-12",
            "hospitalName": "北動物病院",
            "visitReason": "お薬処方"
        },
        "latestWeight": {
            "weightRecordId": 2,
            "checkDate": "2026-07-01",
            "weight": 9.7
        },
        "latestNote": {
            "noteId": 10,
            "recordDate": "2026-07-10",
            "note": "最近初めて買ってみた鹿肉ジャーキーが好きみたい。今度たくさん買ってきてあげよう"
        }
    },
    {
        "petId": 2,
        "petName": "タマ",
        "petType": "猫",
        "customPetType": null,
        "lastVisit": {
            "appointmentDate": "2026-04-03",
            "hospitalName": "南動物病院",
            "visitReason": "健康診断"
        },
        "latestWeight": {
        "weightRecordId": 3,
        "checkDate": "2026-07-01",
        "weight": 3.1
        },
        "latestNote": {
        "noteId": 11,
        "recordDate": "2026-07-10",
        "note": "だんだん毛が夏毛になってすっきりしてきた。とってもかわいいけど、毛が部屋にいっぱい舞ってる😹"
        }
    }
  ]
}
```

---

## 備考

- 近日の予約は、登録されている全ペットの予約を予約日が近い順に取得する。
- ペット別情報は、登録されている全ペット分を取得する。
- ホーム画面では、初期表示時にペット別情報の先頭のペットを選択状態として表示する。
- ペット切り替えタブ押下時は、取得済みのペット別情報を画面上で切り替えて表示する。
- 前回通院は表示のみで編集画面へ遷移しないため、予約IDはレスポンスとして返さない。
- 前回通院、最新体重、最新のひとことが存在しない場合は、該当項目にnullを返す。
- 近日の予約が存在しない場合は、upcomingReservationsに空配列を返す。
- ペットが登録されていない場合は、petSummariesに空配列を返す。
- ペット別情報は、ペット一覧と同じ順番で返す。
