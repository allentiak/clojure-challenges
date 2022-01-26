(ns clojure-challenges.scramble-webserver
  (:require [clojure-challenges.scramble :as scramble]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn default-page []
  "Just a RESTful interface for 'scramble?'")

(defroutes webserver-routes
  (GET "/" [] (default-page))
  (POST "/scramble" [letters word] (str (scramble/scramble? letters word)))
  (route/not-found "This isn't the page you're looking for."))

(def webserver
  (-> webserver-routes
      (wrap-defaults api-defaults)))

(def dev-webserver
  (-> #'webserver
      (wrap-reload)))

;; it won't work unless I use the dash
(defn -main [port]
  (jetty/run-jetty
   dev-webserver
   {:port (Integer. port)}))