# 体重API

## 体重一覧取得

### API概要

体重一覧画面に表示する、選択中のペットに紐づく体重の一覧情報を取得する。

### エンドポイント

GET /api/pets/{petId}/weight-records

### リクエスト

- ペットID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- 体重ID
- 測定日
- 体重

#### Response JSON

```json
[
    {
        "weightRecordId": 1,
        "checkDate": "2026-06-01",
        "weight": 9.5  
    },
    {
        "weightRecordId": 2,
        "checkDate": "2026-07-01",
        "weight": 9.7  
    }
]
```

### 備考

- 体重一覧は選択中のペットに紐づく記録を取得するため、URLのペットIDで対象ペットを特定する。
- ペット切り替えタブに表示するペット名・ペット種別は、ペット一覧取得APIのレスポンスを使用する。
- 各体重記録を押下して編集画面へ遷移するため、レスポンスでは体重IDを返す。
- 体重が1件も登録されていない場合は、空の配列を返す。

---

## 体重1件取得

### API概要

体重登録/編集画面（編集）に表示する、登録済みの体重情報を取得する。

### エンドポイント

GET /api/weight-records/{id}

### リクエスト

- 体重ID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- 体重ID
- ペットID
- ペット名
- 測定日
- 体重

#### Response JSON

```json
{
    "weightRecordId": 1,
    "petId": 1,
    "petName": "ポチ",
    "checkDate": "2026-06-01",
    "weight": 9.5  
}
```

### 備考

- 取得対象の体重は、URLの体重IDで特定する。
- 指定した体重IDに該当する体重情報が存在しない場合は、404 Not Foundを返す。

---

## 体重新規登録

### API概要

体重登録/編集画面（登録）で入力した体重情報を新規登録する。

### エンドポイント

POST /api/weight-records

### リクエスト

- ペットID
- 測定日
- 体重

#### Request JSON

```json
{
    "petId": 2,
    "checkDate": "2026-07-01",
    "weight": 3.1
}
```

### レスポンス

- 処理結果
- 体重ID

#### Response JSON

```json
{
    "result": "SUCCESS",
    "weightRecordId": 2  
}
```

### 備考

- 登録後の一覧表示は体重一覧取得APIで行うため、レスポンスでは処理結果と体重IDのみ返す。

---

## 体重更新

### API概要

体重登録/編集画面（編集）で登録済みの体重情報を更新する。

### エンドポイント

PUT /api/weight-records/{id}

### リクエスト

- 体重ID（URLのパスパラメータで渡す）
- ペットID
- 測定日
- 体重

#### Request JSON

```json
{
    "petId": 1,
    "checkDate": "2026-07-01",
    "weight": 9.6
}
```

### レスポンス

- 処理結果
- 体重ID

#### Response JSON

```json
{
    "result": "SUCCESS",
    "weightRecordId": 1  
}
```

### 備考

- 更新後の一覧表示は体重一覧取得APIで行うため、レスポンスでは処理結果と体重IDのみ返す。

---

## 体重削除

### API概要

体重登録/編集画面（編集）で登録済みの体重情報を削除する。

### エンドポイント

DELETE /api/weight-records/{id}

### リクエスト

- 体重ID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- 処理結果
- 体重ID

#### Response JSON

```json
{
    "result": "SUCCESS",
    "weightRecordId": 1  
}
```

### 備考

- 削除後の一覧表示は体重一覧取得APIで行うため、レスポンスでは処理結果と体重IDのみ返す。
