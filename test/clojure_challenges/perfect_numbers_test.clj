(ns clojure-challenges.perfect-numbers-test
  (:require [clojure.test :refer :all]
            [clojure-challenges.perfect-numbers :refer :all]))

(deftest perfect?-is-test
  (testing "'perfect?' fn (with 'is)."
    (is (= (perfect? 6) true))
    (is (= (perfect? 496) true))
    (is (= (perfect? 8128) true))
    (is (= (perfect? 7) false))
    (is (= (perfect? 500) false))))

(deftest perfect?-are-test
  (testing "'perfect?' fn (with 'are')."
    (are [n truth-value] (= (perfect? n) truth-value)
      6 true
      496 true
      8128 true
      7 false
      500 false)))
