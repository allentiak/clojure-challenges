(ns clojure-challenges.server.scramble-webserver
  (:require
   [liberator.core :refer [defresource]]
   [liberator.dev :refer [wrap-trace]]
   [clojure-challenges.apis.scramble :as scramble]
   [compojure.core :refer [defroutes ANY GET POST]]
   [compojure.route :as route]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
   [ring.middleware.reload :refer [wrap-reload]]))

;; This fn *has* to be public. The param *has* to be there, even if it is never used. It is passed from the resource by Liberator.
(defn default-page [param]
  "Just a RESTful interface for 'scramble?'")

(defn- default-page-with-param [param]
  (str "Just a RESTful interface for 'scramble?'
  This is the param: " param))

(defn- scramble-handler
  [letters word]
  (str (scramble/scramble? letters word)))

(defresource scramble-resource
  [letters word]
  :available-media-types ["text/plain"]
  :allowed-methods [:post]
  :handle-ok (fn [] scramble-handler letters word))

(defresource default-page-resource
  :available-media-types ["text/plain"]
  :handle-ok default-page)

(defresource default-page-resource-with-param
  [param]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (default-page-with-param param)))

(defroutes webserver-routes
  (GET "/" [] default-page-resource)
  (GET "/:param" [param] (default-page-resource-with-param param))
  ;;(POST "/scramble" [letters word] (scramble-resource letters word))
  (route/not-found "This isn't the page you're looking for."))

(def webserver
  (-> webserver-routes
      (wrap-defaults api-defaults)))

(def dev-webserver
  (-> webserver
      (wrap-trace :header :ui)
      (wrap-reload)))

;; it won't work unless I use the dash
(defn -main [port]
  (jetty/run-jetty
   dev-webserver
   {:port (Integer. port)}))
