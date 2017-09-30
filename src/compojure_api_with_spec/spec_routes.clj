(ns compojure-api-with-spec.spec-routes
  (:require [compojure.api.sweet :refer [context GET POST]]
            [compojure.api.coercion.spec :as spec-coercion]
            [ring.util.http-response :refer [ok]]
            [clojure.spec.alpha :as s]
            [spec-tools.conform :as conform]
            [spec-tools.core :as st]
            [spec-tools.spec :as spec]))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")

(s/def ::greeting spec/string?)
(s/def ::comment  spec/string?)
(s/def ::email (s/and spec/string? #(re-matches email-regex %)))
(s/def ::age (s/and spec/int? #(> 140 %) #((complement neg?) %)))
(s/def ::name spec/string?)
(s/def ::amount spec/bigdec?)

(defn string->bigdec [_ x]
  (cond
    (bigdec? x) x
    (string? x) (try
                  (java.math.BigDecimal. x)
                  (catch NumberFormatException e
                    ::s/invalid))
    :else ::s/invalid))

(def string-conforming
  (st/type-conforming
   (merge
    (assoc conform/string-type-conforming :bigdec string->bigdec)
    conform/strip-extra-keys-type-conforming)))

(def json-conforming
  (st/type-conforming
   (merge
    (assoc conform/json-type-conforming :bigdec string->bigdec)
    conform/strip-extra-keys-type-conforming)))

(def custom-spec-coercion
  (spec-coercion/create-coercion
   (-> (assoc-in spec-coercion/default-options [:body :formats "application/json"] json-conforming)
       (assoc-in [:string :default] string-conforming))))

(def spec-validation-routes
  (context "/validation" []
    :coercion :spec
    :tags ["validation"]

    (GET "/" []
      :summary "クエリーパラメタの入力チェック"
      :description "正規表現によるEメールアドレスのフォーマットチェックと、整数の範囲によるバリデーションのデモ"
      :query-params [email-address :- ::email
                     {current-age :- ::age  18}]
      :return (s/keys :req-un [::greeting ::comment])
      (ok {:greeting (str email-address "を登録しました！")
           :comment (if (even? current-age) "あなたの年齢は偶数です" "あなたの年齢は奇数です")}))

    (POST "/" []
      :summary "Bodyの入力チェック"
      :body [{:keys [email age]} (s/keys :req-un [::email ::age])]
      (ok {:greeting (str email "を登録しました！")
           :comment (if (even? age) "あなたの年齢は偶数です" "あなたの年齢は奇数です")}))))

(def spec-coercion-routes
  (context "/coercion" []
    :tags ["coercion"]
    :coercion custom-spec-coercion

    (GET "/" []
      :query-params [name :- ::name amount :- ::amount]
      (ok {:greet (str "Hello, " name "!")
           :amount-type (str (type amount))
           :amount-value amount}))

    (POST "/" []
      :body [amount (s/keys :req-un [::amount])]
      (ok {:greet "Hello!"
           :amount-type (str (type (:amount amount)))
           :amount-value amount}))))
