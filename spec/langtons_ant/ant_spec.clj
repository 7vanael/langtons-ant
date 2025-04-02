(ns langtons-ant.ant-spec
  (:require [speclj.core :refer :all]
            [langtons-ant.ant :refer :all]))

(describe "ant"

  (it "on a white space, turns 90 degrees clockwise"
    (should= :right (turn :white :up))
    (should= :down (turn :white :right))
    (should= :left (turn :white :down))
    (should= :up (turn :white :left)))

  (it "on a black space, turns 90 degrees counter-clockwise"
    (should= :left (turn :black :up)))

  (it "changes white to black"
    (should= :black (invert-color :white)))

  (it "changes black to white"
    (should= :white (invert-color :black)))

  (it "moves one cell in the direction it is facing"
    (should= [5 4] (move [5 5] :up))
    (should= [6 5] (move [5 5] :right))
    (should= [5 6] (move [5 5] :down))
    (should= [4 5] (move [5 5] :left)))

  (it "on a white square, turns 90 degrees clockwise, changes to black, steps forard"
    (should= {:grid (assoc-in (vec (repeat 10 (vec (repeat 10 :white)))) [5 5] :black)
              :orientation :right
              :ant [6 5]}
             (step {:grid (vec (repeat 10 (vec (repeat 10 :white))))
                    :orientation :up
                    :ant [5 5]})))

  (it "on a black square, turns 90 degrees counter-clockwise, changes to white, steps forard"

    (should= {:grid        (assoc-in (vec (repeat 10 (vec (repeat 10 :black)))) [5 5] :white)
              :orientation :left
              :ant         [4 5]}
             (step {:grid        (vec (repeat 10 (vec (repeat 10 :black))))
                    :orientation :up
                    :ant         [5 5]})))
  )