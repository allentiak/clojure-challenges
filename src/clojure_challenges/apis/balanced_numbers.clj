(ns clojure-challenges.apis.balanced-numbers
  (:gen-class))

;; Source: http://www.4clojure.com/problem/115
;;
;; A balanced number is one whose component digits have the same sum on the left and right halves of the number.
;;
;; Write a function which accepts an integer n, and returns true iff n is balanced.)

(defn balanced?
  [n]
  (let [digits (map (comp read-string str) (str n))
        half-size (quot (count digits) 2)]
    (if (zero? half-size)
      true
      (reduce = (map (partial reduce +) (list (take half-size digits) (take-last half-size digits)))))))
