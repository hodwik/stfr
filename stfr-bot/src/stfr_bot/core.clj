
(ns stfr-bot.core
  (:gen-class)
  (:require [clj-http.client :as client] [cheshire.core :as json]))


(defn -main
"Hello!"
[& args]
(println "Hi!"))


(defn get-heartbeat
  "Return true if API heartbeat returns OK"
  []
  (println 
    (get (json/decode (:body (client/get "https://api.stockfighter.io/ob/api/heartbeat"))) "ok") ))


(get-heartbeat)
