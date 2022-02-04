(ns clojure-challenges.server.scramble-webserver
  (:require
   [liberator.core :refer [defresource]]
   [liberator.dev :refer [wrap-trace]]
   [clojure-challenges.apis.scramble :as scramble]
   [compojure.core :refer [defroutes ANY GET POST]]
   [compojure.route :as route]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.cookies :refer [wrap-cookies]]
   [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [ring.middleware.multipart-params :refer [wrap-multipart-params]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.reload :refer [wrap-reload]]))

;; This fn *has* to be public. The param *has* to be there, even if it is never used (it is passed from the resource by liberator).
(defn default-page [req]
  (if-let [param (:query-params req)]
    (str "Just a RESTful interface for 'scramble?'
    This is the param: " param)
    "Just a RESTful interface for 'scramble?'"))

(defn scramble-handler
  [req]
  (let [params (get-in req [:request :form-params])
        letters (get params "letters")
        word (get params "word")]
    (println "Request (as received by the handler): " req)
    (str (scramble/scramble? letters word))))

(defresource scramble-resource
  [req]
  :available-media-types ["text/plain"]
  :allowed-methods [:post]
  :handle-ok (fn [] scramble-handler (:letters req) (:word req)))

(defresource default-page-resource
  [req]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (default-page (:query-params req))))

(defroutes webserver-routes
  (GET "/" req default-page-resource)
  ;;(GET "/:param" [param] (default-page-resource-with-param param))
  (POST "/scramble" req scramble-resource)
  (route/not-found "This isn't the page you're looking for."))

(def webserver
  (-> webserver-routes
      ;;(wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-keyword-params)
      ;;(wrap-cookies)
      (wrap-params)
      #_(wrap-multipart-params)))

(def dev-webserver
  (-> webserver
      (wrap-trace :header :ui)
      (wrap-reload)))

;; it won't work unless I use the dash
(defn -main [port]
  (jetty/run-jetty
   dev-webserver
   {:port (Integer. port)}))
