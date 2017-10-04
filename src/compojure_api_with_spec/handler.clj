(ns compojure-api-with-spec.handler
  (:require [compojure.api.sweet :refer [api]]
            [compojure-api-with-spec
             [spec-coercion-routes :refer [spec-coercion-routes]]
             [spec-validation-routes :refer [spec-validation-routes]]]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Clojureでコード・ファーストなSwagger駆動REST API"}
            :tags [{:name "validation"
                    :description "clojure.specによるバリデーション"}
                   {:name "coercion"
                    :description "clojure.specでコアーション"}]}}}
   spec-validation-routes
   spec-coercion-routes))
