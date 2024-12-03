(ns learn-clojure.core)

(def example-locations [
                        [3 4 2 1 3 3]
                        [4 3 5 3 9 3]
                        ])

(defn distance
  [loc-a loc-b]
  (let [d (abs (- loc-a loc-b))]
    (println loc-a " - " loc-b " = " d)
    d))

(defn distance-total
  [left-locs right-locs]
  (reduce (fn [total-distance loc-pair]
            (+ total-distance (distance (first loc-pair) (second loc-pair))))
          0
          (map vector (sort left-locs) (sort right-locs))))
