(ns clojure-challenges.making-data-dance-test
  (:require [clojure-challenges.making-data-dance :refer :all]
            [clojure.test :refer :all]))


(deftest dance-test
  (testing "dance fn"
    (is (= "1, 2, 3" (str (dance 2 1 3))))
    (is (= '(2 1 3) (seq (dance 2 1 3))))
    (is (= '(2 1 3) (seq (dance 2 1 3 3 1 2))))
    (is (= '(1) (seq (apply dance (repeat 5 1)))))
    (is (= "1, 1, 1, 1, 1" (str (apply dance (repeat 5 1)))))
    (is (and (= nil (seq (dance)))
             (=  "" (str (dance)))))))
