(ns stfr-bot.core
  (:gen-class)
  (:require [clj-http.client :as client] [cheshire.core :as json]))


(defn -main
  "Hello!"
  [& args]
  (println "Hi!"))

(def account "AAS20159426")
(def venue "TESTEX")
(def stock "FOOBAR")
(def apikey "7325649cff460220c2cc7f9e583dc9d71a23f814")
(def baseurl "https://api.stockfighter.io/ob/api/")
 

(defn heartbeat
  "Return true if API heartbeat returns OK"
  []
  (println 
    (get (json/decode (:body (client/get "https://api.stockfighter.io/ob/api/heartbeat"))) 
    "ok")))

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
    
(defn orderbook
  "Return current orders for stock"
  []
  (println
    (client/get (str baseurl "venues/" venue "/stocks/" stock))))






(def packjson
  {     :account account
        :venue venue
        :stock stock
        :qty 100
        :orderType "market"})

(defn api-request [method body]
    (client/request
      {:method method
       :url (str baseurl "venues/" venue "/stocks/" stock "/orders")
       :headers {"X-Starfighter-Authorization" apikey} 
       :content-type "json"
       :body (client/json-encode body)}))

(println (api-request :post packjson))
