---

### clojure.specを活用したコード・ファーストスタイルのRESTサービス開発

	- D3 Meetup 教養としてのClojure 2017/10/4
	- 株式会社シグニファイア 代表 中村研二

---

### 紹介したいこと

* コードとAPIデザインの乖離を無くし、メンテナンスコストを最小に抑える
* Clojureの動的言語を活用したクイック・ターンアラウンドな開発サイクル
* [Clojure.spec](https://www.thoughtworks.com/radar/tools/clojure-spec)や[Plumatic Schema](https://github.com/plumatic/schema)の活用

---

### Out of Scope

* Clojureの文法解説
* Compojure-apiの詳細な使い方
* Clojure.spec/Plumatic Schemaの詳細

> Don't think! Feel. -- Bruce Lee
![Bruce Lee- Don't think, feel!!!](https://i.makeagif.com/media/11-28-2015/I2ALwE.gif)

---

### Clojureって...

- 型がないから心配
- Clojure.specで静的型付き言語とは違ったアプローチでエラーチェック |
- 起動が遅くて... |
- REPL駆動開発+org.clojure/tools.namespaceによるホットリローディング |
- カッコが多くて読みづらそう | 
- Don't think! Feel. |

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
- Plumatic Schema...clojure.specと類似のサードパーティ実装 |
- Spec-tools...clojure.specにDynamic conforming(Coercion)やJSONスキーマ、Swaggerサポート機能を付加するライブラリ |

---

### プロジェクトの生成と設定

```bash
lein new compojure-api-with-spec
```

---

### project.clj 

---?code=project.clj&lang=clojure

@[1-5]
@[6-10]
@[11-13]

---

### handler.clj

---?code=src/compojure_api_with_spec/handler.clj&lang=clojure

@[1-5]
@[7-18]
@[19-21]

---
