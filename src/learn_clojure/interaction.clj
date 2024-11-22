(ns learn-clojure.interaction
  (:gen-class))

(declare successful-move prompt-move game-over prompt-rows)

(defn letter->pos
  "Converts a letter string to the corresponding position number"
  [letter]
  (inc (- (int (first letter)) learn-clojure.representation/alpha-start)))

;;(letter->pos "e")
;;=> 5

(defn get-input
  "Waits for user to enter text and hit enter, then cleans up the input"
  ([] (get-input nil))
  ([default]
   (let [input (clojure.string/trim (read-line))]
     (if (empty? input)
       default
       (clojure.string/lower-case input)))))

(defn characters-as-strings
  "Given a string, return a collection consisting of each indivisual character"
  [string]
  (re-seq #"[a-zA-Z]" string))

(defn user-entered-invalid-move
  "Handles the next step after a user has entered an invalid move"
  [board]
  (println "\n!!! That was an invalid move :(\n")
  (prompt-move board))

(defn user-entered-valid-move
  "Handles the next step after a user has entered a valid move"
  [board]
  (if (learn-clojure.core/can-move? board)
    (prompt-move board)
    (game-over board)))

(defn prompt-move
  [board]
  (println "\nHere's your board:")
  (learn-clojure.representation/print-board board)
  (println "Move from where to where? Enter two letters:")
  (let [input (map letter->pos (characters-as-strings (get-input)))]
    (if-let [new-board (learn-clojure.core/make-move board (first input) (second input))]
      (user-entered-valid-move new-board)
      (user-entered-invalid-move board))))

(defn game-over
  "Announce the game is over and prompt to play again"
  [board]
  (let [remaining-pegs (count (filter :pegged (vals board)))]
    (println "Game over! You had" remaining-pegs "pegs left:")
    (learn-clojure.representation/print-board board)
    (println "Play again? y/n [y]")
    (let [input (get-input "y")]
      (if (= "y" input)
        (prompt-rows)
        (do
          (println "Bye!")
          (System/exit 0))))))

(defn prompt-empty-peg [board]
  (println "Here's your board:")
  (learn-clojure.representation/print-board board)
  (println "Remove which peg? [e]")
  (prompt-move (learn-clojure.core/remove-peg board (letter->pos (get-input "e")))))

(defn prompt-rows []
  (println "How many rows? [5]")
  (let [rows (Integer. (get-input 5))
        board (learn-clojure.core/new-board rows)]
    (prompt-empty-peg board)))
