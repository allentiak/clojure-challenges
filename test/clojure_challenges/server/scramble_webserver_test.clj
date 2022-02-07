(ns clojure-challenges.server.scramble-webserver-test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.mock.request :as mock]
            [clojure-challenges.apis.scramble :refer [scramble?]]
            [clojure-challenges.server.scramble-webserver :refer [default-page-handler webserver]]))

(deftest scramble-webserver-test
  (testing "always valid route"
    (let [req (mock/request :get "/")
          response (webserver req)]
      (is (= (:status response) 200))
      (is (= (:body response) (:body (default-page-handler req))))))

  (testing "invalid route"
    (let [response (webserver (mock/request :get "/whatever"))]
      (is (= (:status response) 404))))

  (testing "valid route & valid request"
    (let [valid-request (mock/request :post "/scramble" {:letters "abc" :word "a"})
          response (webserver valid-request)]
      (is (= (:status response) 200))
      (is (= (:body response) (str (scramble? "abc" "a")))))))
