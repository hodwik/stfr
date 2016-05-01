(defproject stfr-bot "0.1.0-SNAPSHOT"
  :description "Starfighter Bot"
  :url "https://hyper.land/"
  :license {:name "All rights reserved"
            :url "http://hyper.land/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                  [clj-http "2.1.0"]
                  [cheshire "5.6.1"]
                 [prismatic/schema "1.0.4"]
                 [aleph "0.4.1-beta2"]
                 [org.clojure/core.async "0.2.374"]
                  
                  
                  ]
  :main ^:skip-aot stfr-bot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
