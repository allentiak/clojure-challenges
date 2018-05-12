(ns clojure-challenges.happy-number
  (:gen-class)
  (:require [clojure.string :as str]))

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
