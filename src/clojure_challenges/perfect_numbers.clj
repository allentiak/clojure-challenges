(ns clojure-challenges.perfect-numbers
  (:gen-class))

;; Source: http://www.4clojure.com/problem/80
;;
;; A number is "perfect" if the sum of its divisors equal the number itself. 6 is a perfect number because 1+2+3=6.
;;
;; Write a function which returns true for perfect numbers and false otherwise.)

(defn- divisible?
  [n m]
  (zero? (mod n m)))

(defn divisors
  [n]
  (set (conj (filter (partial divisible? n) (range 1 (inc (quot n 2)))) n)))

(defn- process
  [n]
  n)

(defn perfect?
  [n]
  true)

(defn -main
  "Perfect numbers challenge."
  [& args]
  (println "OK"))
