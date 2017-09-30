(ns compojure-api-with-spec.handler
  (:require [compojure.api.sweet :refer [api]]
            [compojure-api-with-spec.schema-routes :refer [schema-routes]]
            [compojure-api-with-spec.spec-routes :refer [spec-validation-routes
                                                         spec-coercion-routes]]))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Clojureでコード・ファーストなSwagger駆動REST API"}
            :tags [{:name "validation" :description "clojure.specによるバリデーション"}
                   {:name "coercion" :description "clojure.specでコアーション"}
                   {:name "schema" :description "Plumatic Schemaによるバリデーション"}]}}}
   spec-validation-routes
   spec-coercion-routes
   schema-routes))
