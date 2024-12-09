(ns learn-clojure.core)


(defn google-search
  [query]
  (str "Here is your google result for " query "!")
  (slurp (str  "https://www.google.com/search" "?q=" query)))


(defn bing-search
  [query]
  (str "Here is your bing result for " query "!")
  (slurp (str  "https://www.bing.com/search" "?q=" query)))

(defn omni-search
  [query]
  (let [search-promise (promise)]
    (future (if-let [result (not-empty (google-search query))]
              (deliver search-promise result)))
    (future (if-let [result (not-empty (bing-search query))]
              (deliver search-promise result)))
    @search-promise))
