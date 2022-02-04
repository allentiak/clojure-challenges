(ns clojure-challenges.server.scramble-webserver-test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.mock.request :as mock]
            [ring.util.request :refer [body-string]]
            [clojure-challenges.apis.scramble :refer [scramble?]]
            [clojure-challenges.server.scramble-webserver :refer [default-page webserver]]))

(deftest scramble-webserver-test
  (testing "always valid route"
    (let [request (mock/request :get "/")
          response (webserver request)]
      (is (= (:status response) 200))
      (is (= (:body response) (default-page request)))))
  
  (testing "invalid route"
    (let [response (webserver (mock/request :get "/whatever"))]
      (is (= (:status response) 404))))

  (testing "valid route & valid request"
    (let [valid-request (mock/request :post "/scramble" {:letters "abc" :word "a"})
          request-body (body-string valid-request)
          response (webserver valid-request)]
      (println "Request: " valid-request)
      (println "Body String: " request-body)
      (println "params: " (:params valid-request))
      (println "form-params: " (:form-params valid-request))
      (println "Response: " response)
;;add breakpoint here
      (is (= (:status response) 200))
      (is (= (:body response) (str (scramble? "abc" "a")))))))
