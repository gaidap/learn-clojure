(ns learn-clojure.core
  (:require [clojure.core.match :refer [match]]))

#_(
    reduce

    (reduce f coll)(reduce f val coll)

    f should be a function of 2 arguments. If val is not supplied,
    returns the result of applying f to the first 2 items in coll, then
    applying f to that result and the third item, etc. If coll contains no
    items, f must accept no arguments as well, and reduce returns the
    result of calling f with no arguments.  If coll has only 1 item, it
    is returned and f is not called.  If val is supplied, returns the
    result of applying f to val and the first item in coll, then
    applying f to that result and the second item, etc. If coll contains no
    items, returns val and f is not called.)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  "returns a symmetric copy of a matching body part"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(defn simple-symmetrize-body-parts
  "Expects a seq of sets that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(def incomplete-spider-parts [
                              {:name "head" :size 2}
                              {:name "eye" :size 1}
                              {:name "hairy-leg" :size 3}
                              {:name "body" :size 3}
                              {:name "butt" :size 8}])

(defn expand-spider-part
  "multiplies eyes and legs of a spider."
  [part]
  (match part
         {:name "eye" :size 1} (repeat 5 part)
         {:name "hairy-leg" :size 3} (repeat 8 part)
         :else part))

(defn spider-body-expander
  "Expects a seq of sets that have a :name and :size"
  [incomplete-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (expand-spider-part part)))
          []
          incomplete-body-parts))
