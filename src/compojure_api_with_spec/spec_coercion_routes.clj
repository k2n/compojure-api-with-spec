(ns compojure-api-with-spec.spec-coercion-routes
  (:require [compojure.api.sweet :refer [context GET POST]]
            [compojure.api.coercion.spec :as spec-coercion]
            [ring.util.http-response :refer [ok]]
            [clojure.spec.alpha :as s]
            [spec-tools.conform :as conform]
            [spec-tools.core :as st]
            [spec-tools.spec :as spec]))

(s/def ::name spec/string?)
(s/def ::amount (assoc spec/bigdec?
                       :json-schema/type "string"
                       :description "整数または小数を文字列として入力"))
(s/def ::amount-type spec/string?)

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
   (-> (assoc-in spec-coercion/default-options
                 [:body :formats "application/json"] json-conforming)
       (assoc-in [:string :default] string-conforming))))

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
      :body [{:keys [amount]} (s/keys :req-un [::amount])]
      :return (s/keys :req-un [::amount-type ::amount])
      (ok {:amount-type (str (type  amount))
           :amount amount}))))
