(ns clojure-challenges.balanced-numbers
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
    (reduce = (map (comp (partial reduce +) pos?) (take-nth 2 (partition-all half-size digits))))))

(defn -main
  "balanced numbers challenge."
  [& args]
  (println "OK"))
