(ns clojure-challenges.happy-number
  (:gen-class)
  (:require [clojure.string :as str]))

(reduce + (filter pos? (map (comp read-string str) "130")))

(defn -main
  "Happy number challenge."
  [& args]
  (println "OK"))
