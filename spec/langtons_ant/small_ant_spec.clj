(ns langtons-ant.small-ant-spec
  (:require [speclj.core :refer :all]
             [langtons-ant.small-ant :refer :all]))

(def grid-width 10)
(def grid-height 10)

(def starting-grid
  (vec (repeat grid-height (vec (repeat grid-width :white)))))
(def inverse-starting-grid
  (vec (repeat grid-height (vec (repeat grid-width :black)))))

(def initial-state
  {:grid starting-grid
   :ant [(/ grid-width 2) (/ grid-height 2)]
   :orientation :up})

(def first-step
  {:grid (assoc-in starting-grid [5 5] :black)
   :ant [6 5]
   :orientation :right})

(def inverse-initial-state
  {:grid inverse-starting-grid
   :ant [(/ grid-width 2) (/ grid-height 2)]
   :orientation :up})

(def inverse-first-step
  {:grid (assoc-in inverse-starting-grid [5 5] :white)
   :ant [4 5]
   :orientation :left})

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

  (it "changes a black cell to white and turns left, then steps forward"
    (should= inverse-first-step (step inverse-initial-state))))
