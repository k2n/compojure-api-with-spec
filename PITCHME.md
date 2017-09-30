---

## clojure.specを活用したコード・ファーストスタイルのRESTサービス開発
<br>
	- D3 Meetup 教養としてのClojure 2017/10/4
	- 株式会社シグニファイア 代表 中村研二

---

## 紹介したいこと

* コードとAPIデザインの乖離を無くし、メンテナンスコストを最小に抑える
* Clojureの動的言語を活用したクイック・ターンアラウンドな開発サイクル
* [Clojure.spec](https://www.thoughtworks.com/radar/tools/clojure-spec)や[Plumatic Schema](https://github.com/plumatic/schema)を活用し、Eric Evansの[Ubiquitous Language](https://www.ogis-ri.co.jp/otc/hiroba/technical/DDDEssence/chap1.html#UbiquitousLanguage), [Model-Driven Design](https://www.ogis-ri.co.jp/otc/hiroba/technical/DDDEssence/chap1.html#ModelDrivenDesign), [Hands-On Modeler](https://www.ogis-ri.co.jp/otc/hiroba/technical/DDDEssence/chap1.html#HandsOnModeler)を指向する。

---

## Out of Scope

* Clojureの文法解説
* Compojure-apiの詳細な使い方
* Clojure.spec/Plumatic Schemaの詳細

> Don't think! Feel. -- Bruce Lee
![Bruce Lee- Don't think, feel!!!](https://i.makeagif.com/media/11-28-2015/I2ALwE.gif)

---

## フレームワーク

- Leiningen ... ビルドツール 
- REPL...対話開発環境 Read, Eval, Print, and Loop |
- Ring...HTTPサーバ抽象化フレームワーク |
- Compojure...Ring上で動作するルーティングライブラリ |
- Compojure-api...Compojure上でREST API用の機能を追加したライブラリ |

---

## フレームワーク

- Swagger...REST APIを定義するOpenAPIスペックに準拠し、設計、ドキュメントなどをサポートするツール群
- clojure.spec...データ構造を定義し、バリデーションなどを行うライブラリ |
- Plumatic Schema...clojure.specと類似のサードパーティ実装 |
- Spec-tools...clojure.specにDynamic conforming(Coercion)やJSONスキーマ、Swaggerサポート機能を付加するライブラリ |

---

## プロジェクトの生成と設定

```bash
lein new compojure-api-with-spec
```

---

---?code=project.clj&lang=clojure

@[1-5]
@[6-10]
@[11-13]

---
