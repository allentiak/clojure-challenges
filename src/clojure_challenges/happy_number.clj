(ns clojure-challenges.happy-number
  (:gen-class)
  (:require [clojure.string :as str]))

(defn- process
  [n]
  (reduce + (map #(* % %) (filter pos? (map (comp read-string str) (str n))))))

(defn happy?
  [n]
  (loop [result (process n)
         history #{n}]
    (if (= result 1) true
        (if (contains? history result) false
            (recur result (conj history result))))))

(defn -main
  "Happy number challenge."
  [& args]
  (println "OK"))
