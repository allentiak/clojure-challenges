(ns clojure-challenges.happy-number
  (:gen-class)
  (:require [clojure.string :as str]))

(defn process
  [n]
  (reduce + (map #(* % %) (filter pos? (map (comp read-string str) (str n))))))

(defn- happy?-aux
  [n history]
  (let [result (process n)
        new-history (into history '(result))]
    (if (= result 1) true
           (if (contains? history result) false
               (happy?-aux result new-history)))))

(defn happy?
  [n]
  (let [result (process n)]
    (if (= result 1) true
      (happy?-aux result #{result}))))

(defn -main
  "Happy number challenge."
  [& args]
  (println "OK"))
