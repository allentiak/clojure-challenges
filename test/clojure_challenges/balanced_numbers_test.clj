(ns clojure-challenges.balanced-numbers-test
  (:require [clojure.test :refer :all]
            [clojure-challenges.balanced-numbers :refer :all]))

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
