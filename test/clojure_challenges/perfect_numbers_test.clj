(ns clojure-challenges.perfect-numbers-test
  (:require [clojure.test :refer :all]
            [clojure-challenges.perfect-numbers :refer :all]))

(deftest divisors-test
  (testing "'divisors' fn (with 'are')"
    (are [n divisors-set] (= (divisors n) divisors-set)
      1 #{1}
      2 #{1 2}
      3 #{1 3}
      4 #{1 2 4}
      5 #{1 5}
      6 #{1 2 3 6}
      7 #{1 7}
      8 #{1 2 4 8}
      9 #{1 3 9}
      10 #{1 2 5 10}
      11 #{1 11}
      12 #{1 2 3 4 6 12}
      13 #{1 13}
      14 #{1 2 7 14}
      15 #{1 3 5 15}
      16 #{1 2 4 8 16}
      17 #{1 17}
      18 #{1 2 3 6 9 18}
      19 #{1 19}
      20 #{1 2 4 5 10 20})))

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
