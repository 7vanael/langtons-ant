(ns langtons-ant.ant-spec
  (:require [speclj.core :refer :all]
            [langtons-ant.ant :refer :all]))

(def grid-width 10)
(def grid-height 10)

(def initial-state
  {:grid (vec (repeat grid-height (vec (repeat grid-width 0))))
   :ant [(/ grid-height 2) (/ grid-width 2)]
   :orientation 0})

(def inverse-initial-state
  {:grid (vec (repeat grid-height (vec (repeat grid-width 1))))
   :ant [(/ grid-height 2) (/ grid-width 2)]
   :orientation 1})

(def state-top-right
  {:grid (vec (repeat grid-height (vec (repeat grid-width 0))))
   :ant [0 grid-width]
   :orientation 0})

(describe "Langton's Ant"

  (it "in white, it turns right & steps forward"
    (let [new-state (update-state initial-state)
          {:keys [ant orientation]} new-state]
      (should= [5 4] ant)
      (should= 3 orientation)))

  (it "turns white square black"
    (let [new-state (update-state initial-state)
          {:keys [grid]} new-state]
      (should= 1 (get-in grid (:ant initial-state)))))



  (it "turns left & takes a step forward in black"
    (let [new-state (update-state inverse-initial-state)
          {:keys [ant orientation]} new-state]
      (should= [6 5] ant)
      (should= 2 orientation)))

  (it "leaves the black square white"
    (let [new-state (update-state inverse-initial-state)
          {:keys [grid]} new-state]
      (should= 0 (get-in grid (:ant initial-state))))))