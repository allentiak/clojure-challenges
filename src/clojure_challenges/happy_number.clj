(ns clojure-challenges.happy-number
  (:gen-class)
  (:require [clojure.string :as str]))

;; Source: http://www.4clojure.com/problem/86
;;
;; Happy numbers are positive integers that follow a particular formula: take each individual digit, square it, and then sum the squares to get a new number. Repeat with the new number and eventually, you might get to a number whose squared sum is 1. This is a happy number. An unhappy number (or sad number) is one that loops endlessly.
;;
;; Write a function that determines if a number is happy or not.

(defn- process
  [n]
  (reduce + (map #(* % %) (filter pos? (map (comp read-string str) (str n))))))

(defn happy?
  [n]
  (loop [nmbr n
         history #{}]
    (let [result (process nmbr)]
      (if (= result 1) true
          (if (contains? history nmbr) false
              (recur result (conj history nmbr)))))))

(defn -main
  "Happy number challenge."
  [& args]
  (println "OK"))
