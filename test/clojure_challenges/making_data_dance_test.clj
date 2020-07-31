(ns clojure-challenges.making-data-dance-test
  (:require [clojure-challenges.making-data-dance :as mdt]
            [clojure.test :refer :all]))


(deftest dance-test
  (testing "dance fn"
    (is (= "1, 2, 3" (str (dance 2 1 3)) true))
    (is (= '(2 1 3) (seq (dance 2 1 3)) true))
    (is (= '(2 1 3) (seq (dance 2 1 3 3 1 2)) true))
    (is (= '(1) (seq (apply dance (repeat 5 1))) true))
    (is (= "1, 1, 1, 1, 1" (str (apply dance (repeat 5 1))) true))
    (is (and (= nil (seq (dance)))
             (=  "" (str (dance))))
        true)))
