(defproject compojure-api-with-spec "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [metosin/compojure-api "2.0.0-alpha7"]
                 [metosin/spec-tools "0.3.2"]]
  :ring {:handler compojure-api-with-spec.handler/app :async? true}
  :uberjar-name "server.jar"
  :profiles {:dev {:plugins [[lein-ring "0.12.0"]]}})
