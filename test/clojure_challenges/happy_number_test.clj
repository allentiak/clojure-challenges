(ns clojure-challenges.happy-number-test
  (:require [clojure.test :refer :all]
            [clojure-challenges.happy-number :refer :all]))

(deftest happy?-test
  (testing "'happy?' fn."
    (is (= (happy? 1) true))
    (is (= (happy? 10) true))
    (is (= (happy? 100000) true))
    (is (= (happy? 7) true))
    (is (= (happy? 986543210) true))
    (is (= (happy? 3) false))
    (is (= (happy? 2) false))))
