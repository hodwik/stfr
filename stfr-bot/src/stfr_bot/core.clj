
(ns stfr-bot.core
  (:gen-class)
  (:require [clj-http.client :as client] [cheshire.core :as json]))


(defn -main
"Hello!"
[& args]
(println "Hi!")
)


(defn get-heartbeat
  "I don't do a whole lot ... yet."
  []
  (println 
	(get (json/decode (:body (client/get "https://api.stockfighter.io/ob/api/heartbeat"))) 0)

   )
)


(get-heartbeat)
