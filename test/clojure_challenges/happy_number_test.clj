(ns clojure-challenges.happy-number-test
  (:require [clojure.test :refer :all]
            [clojure-challenges.happy-number :refer :all]))

(deftest happy?-is-test
  (testing "'happy?' fn (with 'is)."
    (is (= (happy? 1) true))
    (is (= (happy? 10) true))
    (is (= (happy? 100000) true))
    (is (= (happy? 7) true))
    (is (= (happy? 986543210) true))
    (is (= (happy? 3) false))
    (is (= (happy? 2) false))))

(deftest happy?-are-test
  (testing "'happy?' fn (with 'are')."
    (are [n truth-value] (= (happy? n) truth-value)
      1 true
      10 true
      100000 true
      7 true
      986543210 true
      3 false
      2 false)))
