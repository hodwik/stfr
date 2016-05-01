
(ns stfr-bot.core
  (:gen-class)
  (:require [clj-http.client :as client] [cheshire.core :as json]))


(defn -main
  "Hello!"
  [& args]
  (println "Hi!"))

(def venue "TESTEX")

(def baseurl "https://api.stockfighter.io/ob/api/")
 

(defn heartbeat
  "Return true if API heartbeat returns OK"
  []
  (println 
    (get (json/decode (:body (client/get "https://api.stockfighter.io/ob/api/heartbeat"))) "ok")))

(defn heartbeatex
  "Return true if Exchance returns OK"
  []
  (println
    (get (json/decode (:body (client/get (str baseurl "venues/" venue "/heartbeat")))) 
    "ok")))

(defn stocklist
  "Return stocks listed on exchange"
  []
  (println
    (:body (client/get (str baseurl "venues/" venue "/stocks")))))
    

(stocklist)
