(ns compojure-api-with-spec.spec-validation-routes
  (:require [compojure.api.sweet :refer [context GET POST]]
            [ring.util.http-response :refer [ok]]
            [clojure.spec.alpha :as s]
            [spec-tools.spec :as spec]))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(def max-age 140)

(s/def ::greeting (assoc spec/string?
                         :description "このように注釈を定義することができる"))
(s/def ::comment  (assoc spec/string?
                         :description "年齢の偶奇数を判定します"))
(s/def ::response (s/keys :req-un [::greeting ::comment]))
(s/def ::email (s/and spec/string? #(re-matches email-regex %)))
(s/def ::age  (s/and spec/int?
                     #(> max-age %) #((complement neg?) %)))

(defn validation-handler
  [email-address current-age]
  {:pre [(s/valid? ::email email-address)
         (s/valid? ::age current-age)]
   :post [(s/valid? ::response %)]}
  {:greeting (str email-address "を登録しました！")
   :comment (if (even? current-age)
              "あなたの年齢は偶数です"
              "あなたの年齢は奇数です")})

(def spec-validation-routes
  (context "/validation" []
    :coercion :spec
    :tags ["validation"]

    (GET "/" []
      :summary "クエリーパラメタの入力チェック"
      :description "正規表現によるEメールアドレスのフォーマットチェックと、
                   整数の範囲によるバリデーションのデモ"
      :query-params [email-address :- ::email
                     {current-age :- ::age  18}]
      :return ::response
      (ok (validation-handler email-address current-age)))

    (POST "/" []
      :summary "Bodyの入力チェック"
      :return ::response
      :body [{:keys [email age]} (s/keys :req-un [::email ::age])]
      (ok (validation-handler email age)))))
