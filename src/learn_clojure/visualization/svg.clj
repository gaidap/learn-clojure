(ns learn-clojure.visualization.svg)

(defn latAndlng->point
  "Convert latitude / longitude to comma-seperated string"
  [latAndlng]
  (str (:lng latAndlng) "," (:lat latAndlng)))

(defn points
  [locations]
  (clojure.string/join "" (map latAndlng->point locations)))


