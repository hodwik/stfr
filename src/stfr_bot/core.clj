
(ns stfr-bot.core
  (:gen-class)
  (:require [clj-http.client :as client] [cheshire.core :as json]))


(defn -main
  "Hello!"
  [& args]
  (println "Hi!"))

(def account "MFB34331494")
(def venue "HEH")
(def stock "WTEOEX")
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






(defn packjson
  []
  (println (json/generate-string {:headers {:X-Starfighter-Authorization apikey} 
        :body { :account account
                :venue venue
                :stock stock
                :qty 100
                :orderType "market"}
           })
                
        ))
        
(defn marketorder
  "Get 100 shares"
  []
  (client/post (str baseurl "venues/" venue "/stocks/" stock) (packjson)
   )) 

(println (marketorder))
