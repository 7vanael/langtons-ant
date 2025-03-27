(ns langtons-ant.small-ant-spec
  (:require [speclj.core :refer :all]
             [langtons-ant.small-ant :refer :all]))

(def grid-width 10)
(def grid-height 10)

(def first-step-grid
  [
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :black :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   [:white :white :white :white :white :white :white :white :white :white]
   ])

(def starting-grid
  (vec (repeat grid-height (vec (repeat grid-width :white)))))

(def initial-state
  {:grid starting-grid
   :ant [(/ grid-height 2) (/ grid-width 2)]
   :orientation :up})

(def first-step
  {:grid (assoc-in starting-grid [5 5] :black)
   :ant [6 5]
   :orientation :right})

#_(def inverse-initial-state
  {:grid (vec (repeat grid-height (vec (repeat grid-width :white))))
   :ant [(/ grid-height 2) (/ grid-width 2)]
   :orientation :up})


(describe "small-ant"

  (it "on a white square, turn 90 clockwise"
    (should= :right (turn :up :white))
    (should= :down (turn :right :white))
    (should= :left (turn :down :white))
    (should= :up (turn :left :white)))

  (it "on a black square, turn 90 counter-clockwise"
    (should= :left (turn :up :black))
    (should= :down (turn :left :black))
    (should= :right (turn :down :black))
    (should= :up (turn :right :black)))

  (it "Changes a white cell to black"
    (should= :black (inverse-color :white)))

  (it "Changes a black cell to white"
    (should= :white (inverse-color :black)))

  (it "moves one cell in the direction it faces"
    (should= [5 4] (move [5 5] :up))
    (should= [6 5] (move [5 5] :right))
    (should= [5 6] (move [5 5] :down))
    (should= [4 5] (move [5 5] :left)))

  (it "changes a white cell to black and turns right, then steps forward"
    (should= first-step (step initial-state)))
  )
