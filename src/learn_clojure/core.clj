(ns learn-clojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "I'm a little teapot!"))


; 1.
(defn my-function
  []
  (println (str "This" " is" " my" " function..."))
  (println (str "...there" " are" " many" " functions..."))
  (println (str "...but" " this" " one" " is" " mine!")))

; 2.
(defn collection-collection
  []
  (print "A List: ")
  (println '(1 2 3 4))
  (print "A Vetor: ")
  (println [1 2 3 4])
  (print "A Hash-Map: ")
  (println {:key "Nice" :value "One"})
  (print "A Hash-Set: ")
  ;This will not work!
  ;(println #{:a :a :b :c})
  (println (hash-set :a :a :b :c)))

; 3.
(defn dec-maker
  [x]
  #(- % x))
(def dec23 (dec-maker 23))

; 4. (mapset inc [1 1 2 2 3]) => #{2 3 4}
