(ns stfr-bot.core
  (:gen-class)
  (:require 
     [clj-http.client :as client] 
     [aleph.http :as http]
     [manifold.stream :as stream]
     [clojure.core.async :as async]
     [cheshire.core :as json]))


(defn -main
  "Hello!"
  [& args]
  (println "Hi!"))

(def account "CAE95302354")
(def venue "IGMCEX")
(def stock "LTIM")
(def apikey "7325649cff460220c2cc7f9e583dc9d71a23f814")
(def baseurl "https://api.stockfighter.io/ob/api/")
(def ws-url "wss://www.stockfighter.io/ob/api/ws/")


(defn open-ticker!
  ([url ch]
   (let [conn (stream/transform (map #(json/parse-string % true)) @(http/websocket-client url))
         ch (or ch (async/chan 10))]
     (stream/connect conn ch)
     ch)))
     
(defn quotes-ticker!
  ([account venue & [stock {chan :channel}]]
   (let [url (str ws-url account "/venues/" venue "/tickertape" (when stock (str "/stocks/" stock)))]
     (open-ticker! url chan))))

(defn execs-ticker!
  ([account venue & [stock {chan :channel}]]
   (let [url (str ws-url account "/venues/" venue "/executions" (when stock (str "/stocks/" stock)))]
     (open-ticker! url chan))))
 
 

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
        :direction "buy"
        :orderType "market"})

(defn api-request [method body]
    (client/request
      {:method method
       :url (str baseurl "venues/" venue "/stocks/" stock "/orders")
       :headers {"X-Starfighter-Authorization" apikey} 
       :content-type "json"
       :body (client/json-encode body)}))

;;(println (api-request :post packjson))

(def ticker (quotes-ticker! account venue))
(loop [x 100]
  (when (> x 1)
  (println (async/<!! ticker))
  (recur (- x 1)))) 

(async/close! ticker) 
