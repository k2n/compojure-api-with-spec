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

> Don't Think! Feel. -- Bruce Lee

---

## 語彙

* Leiningen ... ビルドツール (maven, gradle, sbtの位置づけ）
* REPL... 対話開発環境 Read, Eval, Print, and Loop
* [Ring](https://github.com/ring-clojure/ring) ... HTTPサーバ抽象化フレームワーク (WSGI, Rack, Servletの位置づけ)
* [Compojure](https://github.com/weavejester/compojure) ... Ring上で動作するルーティングライブラリ
* [Compojure-api](https://github.com/metosin/compojure-api) ... Compojure上でREST API用の機能を追加したライブラリ
* [Swagger](https://swagger.io/) ... REST APIを定義するOpenAPIスペックに準拠し、設計、ドキュメントなどをサポートするツール郡
* [clojure.spec](https://clojure.org/about/spec) ... データ構造を定義し、バリデーションなどを行うライブラリ
* Plumatic Schema ... clojure.specと類似のサードパーティ実装
* [Spec-tools](https://github.com/metosin/spec-tools) ... clojure.specにDynamic conforming(Coercion)
やJSONスキーマ、Swaggerサポート機能を付加するライブラリ 

---



