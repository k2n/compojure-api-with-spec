(defproject compojure-api-with-spec "0.1.0-SNAPSHOT"
  :description "compojure-api v2 and clojure.spec demo"
  :url "https://github.com/k2n/compojure-api-with-spec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[clj-time                 "0.14.0"]
                 [metosin/compojure-api    "2.0.0-alpha7"]
                 [metosin/spec-tools       "0.3.2"]
                 [org.clojure/clojure      "1.9.0-beta1"]]
  :ring {:handler compojure-api-with-spec.handler/app 
         :async? true
         :nrepl {:start? true}}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[peridot "0.5.0"]] 
                   :plugins [[lein-ring "0.12.0"]
                             [com.jakemccrary/lein-test-refresh "0.21.1"]]}})
