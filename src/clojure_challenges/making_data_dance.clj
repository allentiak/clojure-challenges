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


;; CHEATED :-(
;; based on https://github.com/MoyTW/4clojure/blob/8cd6190c5235dd6d492b20df258431218225ba43/Solutions/113-Making_Data_Dance.clj
;; Whereas I could figure out the inner workings of the fn, I could not figure out the use of reify, as I hadn't seen it before.

;; TODO: fully understand `reify`

(defn dance
  ([]
   nil)
  ([args]
   (reify
    java.lang.Object
    (toString [_]
      (str/join ", " (sort args)))
    clojure.lang.ISeq
    (seq [_]
      (distinct args)))))
