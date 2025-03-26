(ns langtons-ant.ant-spec
  (:require [speclj.core :refer :all]
            [langtons-ant.ant :refer :all]))

(def grid-width 10)
(def grid-height 10)

(def initial-state
  {:grid (vec (repeat grid-height (vec (repeat grid-width 0))))
   :ant [(/ grid-width 2) (/ grid-height 2)]
   :orientation 0})

(def inverse-initial-state
  {:grid (vec (repeat grid-height (vec (repeat grid-width 1))))
   :ant [(/ grid-width 2) (/ grid-height 2)]
   :orientation 1})

(describe "Langton's Ant"

  (it "in white, it turns right & steps forward"
    (let [new-state (update-state initial-state)
          {:keys [grid ant orientation]} new-state]
      (should= [5 6] ant)
      (should= 1 orientation)))

  (it "turns white square black"
    (let [new-state (update-state initial-state)
          {:keys [grid ant orientation]} new-state]
      (should= 1 (get-in grid (:ant initial-state)))))

  #_(it "turns left & takes a step forward in black, leaving the square white"
    (let [new-state (update-state inverse-initial-state)
          {:keys [grid ant orientation]} new-state]
      (should= [4 5] ant)
      (should= 0 orientation)
      (should= 0 orientation)))

  (it "turns left & takes a step forward in black"
    (let [new-state (update-state inverse-initial-state)
          {:keys [grid ant orientation]} new-state]
      (should= [4 5] ant)
      (should= 0 orientation)))

  (it "leaves the black square white"
    (let [new-state (update-state inverse-initial-state)
          {:keys [grid ant orientation]} new-state]
      (should= 0 (get-in grid (:ant initial-state)))))


  #_(it "turns right on a white square, left on a black one, steps forward and inverts the color"
    ()))