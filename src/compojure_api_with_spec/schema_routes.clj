(ns compojure-api-with-spec.schema-routes
  (:require [compojure.api.sweet :refer [context GET POST]]
            [ring.swagger.json-schema :as rjs]
            [ring.util.http-response :refer [ok]]
            [schema.core :as sc]))

(sc/defschema GreetingResponseSchema 
  {:greeting (rjs/describe sc/Str "Eメールアドレスを含んだ挨拶文")
   :comment (rjs/describe sc/Str "年齢の偶奇数判定")})

(def schema-routes
  (context "/schema" []
    :tags ["schema"]
    :coercion :schema

    (GET "/validate" []
      :summary "Eメールアドレスのフォーマットをチェック"
      :description "Plumatic Schemaを使ったバリデーション。リリース版のため、Descriptionのサポートなど、まだ
                    clojure.specバージョンがサポートできていない機能がフルに利用できる。"
      :query-params [email-address :- (rjs/describe sc/Str "@の前には英数文字、後ろにドメイン、トップレベルドメインが必要。")
                     {current-age :- (rjs/describe sc/Int "任意項目の例。既定値は10") 10}]
      :return GreetingResponseSchema
      (ok {:greeting (str email-address "を登録しました！")
           :comment (if (even? current-age) "あなたの年齢は偶数です" "あなたの年齢は奇数です")}))))
