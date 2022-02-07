(ns clojure-challenges.server.scramble-webserver
  (:require
    [clojure-challenges.apis.scramble :as scramble]
    [clojure.spec.alpha :as s]
    [muuntaja.core :as m]
    [reitit.coercion.spec :as cs]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.coercion :as coercion]
    [reitit.ring :as ring]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.params :refer [wrap-params]]
    [ring.middleware.reload :refer [wrap-reload]]
    [spec-tools.spec :as st]))

;; this fn *has* to be public, and to have a param
;; (which, by default, is the request itself))
(defn default-page-handler [_]
  {:status 200
   :body "Just a RESTful interface for 'scramble?'"})

(defn scramble-handler
  [letters word]
  (str (scramble/scramble? letters word)))

(s/def ::letters st/string?)
(s/def ::word st/string?)
(s/def ::result st/string?)

(def webserver-routes
 [
  ["/" {:get default-page-handler}]
  ["/scramble"
     {:coercion cs/coercion}
     {:responses
       {200 {:body (s/keys :req-un [::result])}}
       :get {:summary "scramble with query-params"
             :parameters {:query (s/keys :req-un [::letters ::word])}
             :handler
               (fn [{{{:keys [letters word]} :query} :parameters}]
                 {:status 200
                  :body
                   {:result (str (scramble/scramble? letters word))}})}
       :post {:summary "scramble with body-params"
              :parameters {:body (s/keys :req-un [::letters ::word])}
              :handler
                (fn [{{{:keys [letters word]} :body} :parameters}]
                  {:status 200
                   :body
                    {:result (str (scramble/scramble? letters word))}})}}]])

(def webserver
  (ring/ring-handler
    (ring/router
      [webserver-routes]
      {:data {:muuntaja m/instance
              :middleware [wrap-params
                           muuntaja/format-middleware
                           coercion/coerce-exceptions-middleware
                           coercion/coerce-request-middleware
                           coercion/coerce-response-middleware]}})
    (ring/create-default-handler
      {:not-found
         (constantly
           {:status 404, :body "This isn't the page you're looking for.", :headers {}}),
       :method-not-allowed
         (constantly
           {:status 405, :body "Method not allowed.", :headers {}}),
       :not-acceptable
         (constantly
           {:status 406, :body "Not acceptable.", :headers {}})})))

(def dev-webserver
  (-> #'webserver
      (wrap-reload)))

;; it won't work unless I use the dash
(defn -main [port]
  (jetty/run-jetty
   dev-webserver
   {:port (Integer. port)}))
