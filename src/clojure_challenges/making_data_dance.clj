(ns clojure-challenges.making-data-dance
  (:gen-class)
  (:require [clojure.string :as str]))

;; Source: http://www.4clojure.com/problem/113
;;
;; Write a function that takes a variable number of integer arguments.
;;
;; If the output is coerced into a string, it should return a comma (and space) separated list of the inputs sorted smallest to largest.
;; If the output is coerced into a sequence, it should return a seq of unique input elements in the same order as they were entered.
;;


(defn dance
  ([]
   nil)
  ([a & args]
   (str/join ", " (sort (conj args a)))))
