(defproject stfr-bot "0.1.0-SNAPSHOT"
  :description "Starfighter Bot"
  :url "https://hyper.land/"
  :license {:name "All rights reserved"
            :url "http://hyper.land/"}
  :dependencies [[org.clojure/clojure "1.8.0"][clj-http "2.1.0"][cheshire "5.6.1"]]
  :main ^:skip-aot stfr-bot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
