(ns clojure-challenges.balanced-numbers
  (:gen-class))

;; Source: http://www.4clojure.com/problem/115
;;
;; A balanced number is one whose component digits have the same sum on the left and right halves of the number.
;;
;; Write a function which accepts an integer n, and returns true iff n is balanced.)

(defn- process
  [n]
  (let [digits (map (comp read-string str) (str n))
        half-size (inc (quot (count digits) 2))]
    (= (map (reduce +) (partition half-size digits)))))

(defn balanced?
  [n]
  true)

(defn -main
  "balanced numbers challenge."
  [& args]
  (println "OK"))
