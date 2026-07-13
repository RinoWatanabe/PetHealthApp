# ペットAPI

## ペット一覧取得

### API概要

ペット一覧画面に表示する、ペットの一覧情報を取得する。

### エンドポイント

GET /api/pets

### リクエスト

- なし

#### Request JSON

なし

### レスポンス

- ペットID

- ペット名
- 種別
- 種別(その他)
- 年齢
- 性別
- 誕生日

#### Response JSON

```json
[
    {
        "petId": 1,
        "petName": "ポチ",
        "petType": "犬",
        "customPetType": null,
        "age": 3,
        "gender": "オス",
        "birthday": "2023-04-01"
    },
    {
        "petId": 2,
        "petName": "タマ",
        "petType": "猫",
        "customPetType": null,
        "age": 1,
        "gender": "メス",
        "birthday": "2025-06-12"
    },
    {
        "petId": 3,
        "petName": "ハリー",
        "petType": "その他",
        "customPetType": "ハリネズミ",
        "age": 3,
        "gender": "オス",
        "birthday": "2023-09-03"
    }
]
```

### 備考

- ペット切り替えタブに表示するペット名・ペット種別は、本APIのレスポンスを使用する。
- ペットが1件も登録されていない場合は、空の配列を返す。

---

## ペット1件取得

### API概要

ペット登録/編集画面（編集）に表示する、登録済みのペット情報を取得する。

### エンドポイント

GET /api/pets/{id}

### リクエスト

- ペットID（URLのパスパラメータで渡す）

#### Request JSON

なし

### レスポンス

- ペットID
- ペット名
- 種別
- 種別(その他)
- 年齢
- 性別
- 誕生日

#### Response JSON

```json
{
    "petId": 1,
    "petName": "ポチ",
    "petType": "犬",
    "customPetType": null,
    "age": 3,
    "gender": "オス",
    "birthday": "2023-04-01"
}
```

### 備考

- 取得対象のペットは、URLのペットIDで特定する。
- 指定したペットIDに該当するペットが存在しない場合は、404 Not Foundを返す。 

---

## ペット新規登録

### API概要

ペット登録/編集画面（登録）で入力したペット情報を新規登録する。

### エンドポイント

POST /api/pets

### リクエスト

- ペット名
- 種別
- 種別(その他)
- 年齢
- 性別
- 誕生日

#### Request JSON

```json
{
    "petName": "ポチ",
    "petType": "犬",
    "customPetType": null,
    "age": 3,
    "gender": "オス",
    "birthday": "2023-04-01"
}
```

### レスポンス

- 処理結果
- ペットID

#### Response JSON

```json
{
    "result": "SUCCESS",
    "petId": 1  
}
```

### 備考

- 登録後の一覧表示はペット一覧取得APIで行うため、レスポンスでは処理結果とペットIDのみ返す。
- petTypeが「その他」の場合は、customPetTypeを必須とする。
- petTypeが「その他」以外の場合は、customPetTypeをnullとする。

---

## ペット更新

### API概要

ペット登録/編集画面（編集）で登録済みのペット情報を更新する。

### エンドポイント

PUT /api/pets/{id}

### リクエスト

- ペットID（URLのパスパラメータで渡す）
- ペット名
- 種別
- 種別(その他)
- 年齢
- 性別
- 誕生日

#### Request JSON

```json
{
    "petName": "ペコ",
    "petType": "犬",
    "customPetType": null,
    "age": 3,
    "gender": "オス",
    "birthday": "2023-04-01"
}
```

### レスポンス

- 処理結果
- ペットID

#### Response JSON

```json
{
    "result": "SUCCESS",
    "petId": 1  
}
```

### 備考

- 更新後の一覧表示はペット一覧取得APIで行うため、レスポンスでは処理結果とペットIDのみ返す。
- petTypeが「その他」の場合は、customPetTypeを必須とする。
- petTypeが「その他」以外の場合は、customPetTypeをnullとする。
