---

### clojure.specを活用したコード・ファーストスタイルのRESTサービス開発

    - D3 Meetup 教養としてのClojure 2017/10/4
    - 株式会社シグニファイア 代表 中村研二

---

### 自己紹介

* 18年間米国の金融・通信系スタートアップでエンジニア・マネージャ・ディレクターをしていました。
* 2015年から開発は100% Clojure。ミッションクリティカルシステムにも使用しました。
* 昨年Clojure+Docker+AWSの開発に特化した、株式会社シグニファイアを設立。

---

### 紹介したいこと

* コードとAPIデザインの乖離を無くし、メンテナンスコストを最小に抑える
* Clojureの動的言語を活用したクイック・ターンアラウンドな開発サイクル
* [Clojure.spec](https://www.thoughtworks.com/radar/tools/clojure-spec)の活用例
* 質問・ディスカッションはGitterで！ https://gitter.im/d3_clojure 

---

### Out of Scope

* Clojureの文法解説
* Compojure-apiの詳細な使い方
* Clojure.specの詳細

> Don't think! Feel. --- Bruce Lee
![Bruce Lee- Don't think, feel!!!](https://i.makeagif.com/media/11-28-2015/I2ALwE.gif)

---

### Clojureって...

- 型がないから心配
    - Clojure.specで静的型付き言語とは違ったアプローチでエラーチェック |
- 起動が遅くて... |
    - REPL駆動開発+org.clojure/tools.namespaceによるホットリローディング |
- カッコが多くて読み書きしづらそう | 
    - Pareditモードを使えばカッコを気にせずコーディングできる！|

---

### フレームワーク

- Leiningen ... ビルドツール 
- REPL...対話開発環境 Read, Eval, Print, and Loop |
- Ring...HTTPサーバ抽象化フレームワーク |
- Compojure...Ring上で動作するルーティングライブラリ |
- Compojure-api...Compojure上でREST API用の機能を追加したライブラリ |

---

### フレームワーク

- Swagger...REST APIを定義するOpenAPIスペックに準拠し、設計、ドキュメントなどをサポートするツール群
- clojure.spec...データ構造を定義し、バリデーションなどを行うライブラリ |
- Spec-tools...clojure.specにDynamic conforming(Coercion)やJSONスキーマ、Swaggerサポート機能を付加するライブラリ |
- Peridot...サーバーを起動することなくRing上のロジックをテストすることができるライブラリ |

---

### プロジェクトの生成と設定

* Java8 (clojure 1.9beta1は9をサポート、Leiningenはまだclojure1.8依存だが2.8でJava9対応予定）
* Leiningen 

```bash
lein new compojure-api-with-spec
```

---

### project.clj 

---?code=project.clj&lang=clojure

@[1-5]
@[6-9]
@[10-13]
@[14-17]

---

### handler.clj

---?code=src/compojure_api_with_spec/handler.clj&lang=clojure

@[1-5]
@[7-18]

---

### spec_validation_routes.clj

---?code=src/compojure_api_with_spec/spec_validation_routes.clj&lang=clojure

@[1-5]
@[29-32]
@[34-41]
@[43-47]
@[7-17]
@[19-27]

---

### spec_coercion_routes.clj

---?code=src/compojure_api_with_spec/spec_coercion_routes.clj&lang=clojure

@[1-8]
@[43-46]
@[10-14]
@[37-41]
@[25-35]
@[16-23]

---

### spec_routes_test.clj

---?code=test/compojure_api_with_spec/spec_routes_test.clj&language=clojure

@[1-6]
@[8-15]
@[71-84]

---

### 終わりに

- 今回の例はマクロを多用したDSL的な使われ方。通常はこれほどマジカルではない。|
- clojure.specとcompojure-api v2はまだα版 |
- GraphQL(Lacinia by Walmart Labs)の統合 | 
- CQRS(Command Query Responsible Separation)への適用 | 
- Clojureを導入したくなったらお声がけください！コンサル・開発承ります。|
