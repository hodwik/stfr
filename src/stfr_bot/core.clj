
(ns stfr-bot.core
  (:gen-class)
  (:require [clj-http.client :as client] [cheshire.core :as json]))


(defn -main
  "Hello!"
  [& args]
  (println "Hi!"))

(def venue "TESTEX")
 

(defn heartbeat
  "Return true if API heartbeat returns OK"
  []
  (println 
    (get (json/decode (:body (client/get "https://api.stockfighter.io/ob/api/heartbeat"))) "ok")))

(defn heartbeatex
  "Return true if Exchance returns OK"
  []
  (println
    (get (json/decode (:body (client/get (str "https://api.stockfighter.io/ob/api/venues/" venue "/heartbeat")))) 
    "ok")))

(defn stocklist
  "Return stocks listed on exchange"
  []
  (println
    (:body (client/get (str "https://api.stockfighter.io/ob/api/venues/" venue "/stocks")))))
    

(stocklist)
