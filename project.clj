(defproject compojure-api-with-spec "0.1.0-SNAPSHOT"
  :description "compojure-api v2 and clojure.spec demo"
  :url "https://github.com/k2n/compojure-api-with-spec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[clj-time                 "0.14.0"]
                 [metosin/compojure-api    "2.0.0-alpha7"]
                 [metosin/spec-tools       "0.3.2"]
                 [org.clojure/clojure      "1.9.0-alpha17"]
                 [org.clojure/tools.reader "1.1.0"]]
  :ring {:handler compojure-api-with-spec.handler/app 
         :async? true
         :nrepl {:start? true}}
  :uberjar-name "server.jar"
  :profiles {:dev {:plugins [[lein-ring "0.12.0"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [metosin/spec-tools "0.3.2" :exclusions [org.clojure/spec.alpha]]
                                  [clojure-future-spec "1.9.0-alpha17"]]}})
