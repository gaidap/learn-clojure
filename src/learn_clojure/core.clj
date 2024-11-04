(ns learn-clojure.core)

(def filename "resources/suspects.csv")

(def vamp-keys [:name :glitter-index])

; The dot after a class name is just special syntax for calling a constructor of the class.
; Example: Integer. "3" => creates an integer object with the value 3.
(defn str->int
  [str]
  (Integer/parseInt str))

(def conversions {:name identity :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def suspects (into () (glitter-filter 3 (mapify (parse (slurp filename))))))

(def validator-fns {:name #(string? %) :glitter-index #(int? %)})

(defn valid?
  [[vamp-key value]]
  ((get validator-fns vamp-key) value))

(defn append-suspect
  [suspects suspect-map]
  (if (every? valid? suspect-map)
    (concat suspects suspect-map)))

(defn maps->csv-string
  [suspect-maps]
  (clojure.string/join "\n" (map (fn [suspect]
                                   (clojure.string/join "," (vals suspect))) suspect-maps)))
