(ns clojure-challenges.apis.balanced-numbers-test
  (:require [clojure.test :refer :all]
            [clojure-challenges.apis.balanced-numbers :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(deftest balanced?-test
  (testing "'balanced?' fn."
    (is (= (balanced? 0) true))
    (is (= (balanced? 11) true))
    (is (= (balanced? 121) true))
    (is (= (balanced? 88088) true))
    (is (= (balanced? 89089) true))
    (is (= (balanced? 89098) true))
    (is (= (balanced? 123) false))
    (is (= (balanced? 88099) false))
    (is (= (balanced? 99088) false))))

;; First implementation.
(gen/sample (gen/such-that balanced? gen/nat 100) 10)

;; Another implementation.
;; It generates vectors of digits; of size 8, whose members' sum ('balance') is 6.
(let [balance 6
      size 8]
  (gen/sample (gen/such-that #(= balance (reduce + %)) (gen/vector (gen/fmap #(mod % 10) gen/nat) size) 200000) 10))
