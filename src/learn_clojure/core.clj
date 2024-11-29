(ns learn-clojure.core)

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  "Phrases are courtesy of Hermes Conrad from Futurama"
  [bad good]
  `(do ~(criticize-code "Great squid of Madrid, this is bad code:" bad)
       ~(criticize-code "Sweet gorilla of Manila, this is good code:" good)))
