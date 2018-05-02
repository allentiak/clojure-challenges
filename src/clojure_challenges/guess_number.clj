(ns clojure-challenges.guess-number
  (:gen-class)
  (:require [clojure.string :as str]))

(defn choose-number
  [limit]
  (inc (rand-int limit)))

(defn guess-number
  [secret]
  (println "Which is the number I chose?")
  (let [guessed (Integer/parseInt (clojure.string/trim (read-line)))]
    (cond (= secret guessed)
          (println "You guessed the number! The secret number is" secret ".")
          (< secret guessed)
          (do
            (println "The number you are looking for is a little bit smaller than " guessed ". Try again.")
            (recur secret))
          (> secret guessed)
          (do
            (println "The number you are looking for is a little bit bigger than " guessed ". Try again.")
            (recur secret)))))

(defn -main
  "Number guessing game."
  [& args]
  (let [_ (println "Number guessing game.")
        _ (println "Try guessing the number chosen by the computer.")
        _ (println "Maximum limit to choose the number? (recommended: 100)")
        limit (Integer. (clojure.string/trim (read-line)))
        _ (println "The chosen number will be between 1 and" limit ".")
        _ (println "Let's play!")]
    (guess-number (choose-number limit))))
