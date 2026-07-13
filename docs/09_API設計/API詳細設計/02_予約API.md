# 予約API

## 予約一覧取得

### API概要

予約一覧画面に表示する、予約の一覧情報を取得する。

### エンドポイント

GET /api/pets/{id}/reservations

### リクエスト

- ペットID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- 予約ID
- 予約日
- 病院名
- 通院の目的

#### Response JSON

```json
[
    {
        "reservationId": 1,
        "appointmentDate": "2026-07-28",
        "hospitalName": "北動物病院",
        "visitReason": "健康診断"
    },
    {
        "reservationId": 2,
        "appointmentDate": "2026-09-15",
        "hospitalName": "北動物病院",
        "visitReason": "ワクチン"
    }
]
```

### 備考

- 予約一覧は選択中のペットに紐づく記録を取得するため、URLのペットIDで対象ペットを特定する。
- ペット切り替えタブに表示するペット名・ペット種別は、ペット一覧取得APIのレスポンスを使用する。
- 各予約を押下して編集画面へ遷移するため、レスポンスでは予約IDを返す。
- 対象の予約が存在しない場合は、空の配列を返す。

---

## 予約1件取得

### API概要

予約登録/編集画面（編集）に表示する、登録済みの予約情報を取得する。

### エンドポイント

GET /api/reservations/{id}

### リクエスト

- 予約ID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- 予約ID
- ペットID
- ペット名
- 予約日
- 病院名
- 通院の目的

#### Response JSON

```json
{
    "reservationId": 1,
    "petId": 1,
    "petName": "ポチ",
    "appointmentDate": "2026-07-28",
    "hospitalName": "北動物病院",
    "visitReason": "健康診断"
}
```

### 備考

- 取得対象の予約は、URLの予約IDで特定する。
- 指定した予約IDに該当する予約が存在しない場合は、404 Not Foundを返す。

---

## 予約新規登録

### API概要

予約登録/編集画面（登録）で入力した予約情報を新規登録する。

### エンドポイント

POST /api/reservations

### リクエスト

- ペットID
- 予約日
- 病院名
- 通院の目的

#### Request JSON

```json
{
    "petId": 2,
    "appointmentDate": "2026-08-01",
    "hospitalName": "南動物病院",
    "visitReason": "お薬処方"
}
```

### レスポンス

- 処理結果
- 予約ID

#### Response JSON

```json
{
    "result": "SUCCESS",
    "reservationId": 2
}
```

### 備考

- 登録後の一覧表示は予約一覧取得APIで行うため、レスポンスでは処理結果と予約IDのみ返す。

---

## 予約更新

### API概要

予約登録/編集画面（編集）で登録済みの予約情報を更新する。

### エンドポイント

PUT /api/reservations/{id}

### リクエスト

- 予約ID（URLのパスパラメータで渡す）
- ペットID
- 予約日
- 病院名
- 通院の目的

#### Request JSON


```json
{
    "petId": 2,
    "appointmentDate": "2026-08-02",
    "hospitalName": "南動物病院",
    "visitReason": "お薬処方"
}
```

### レスポンス

- 処理結果
- 予約ID 

#### Response JSON

```json
{
    "result": "SUCCESS",
    "reservationId": 2
}
```

### 備考

- 更新後の一覧表示は予約一覧取得APIで行うため、レスポンスでは処理結果と予約IDのみ返す。

---

## 予約削除

### API概要

予約登録/編集画面（編集）で登録済みの予約情報を削除する。

### エンドポイント

DELETE /api/reservations/{id}

### リクエスト

- 予約ID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- 処理結果
- 予約ID 

#### Response JSON

```json
{
    "result": "SUCCESS",
    "reservationId": 2
}
```

### 備考

- 削除後の一覧表示は予約一覧取得APIで行うため、レスポンスでは処理結果と予約IDのみ返す。
