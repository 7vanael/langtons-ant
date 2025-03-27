(ns langtons-ant.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [langtons-ant.ant :as ant]
            [langtons-ant.small-ant :as small-ant]))

(def grid-width 100)
(def grid-height 100)
(def cell-size 12)

(defn setup []
  (q/frame-rate 300)
  (q/background 300)
  {:grid (vec (repeat grid-height (vec (repeat grid-width :white))))
   :ant [(/ grid-width 2) (/ grid-height 2)]
   :orientation 0});in Quil, [0 0] is the top left corner

(defn draw [state]
  (q/stroke 100)                                            ;color for lines
  (doseq [i (range (inc grid-width))]
    (q/line (* i cell-size) 0 (* i cell-size) (* grid-height cell-size)))
  (doseq [i (range (inc grid-width))]
    (q/line 0 (* i cell-size) (* grid-width cell-size) (* i cell-size)))
  (doseq [row (range grid-width)
          col (range grid-height)]
    (if (zero? (get-in (get state :grid) [row col]))
      (q/fill 255)
      (q/fill 0))
    (q/rect (* row cell-size) (* col cell-size) cell-size cell-size))
  (q/fill 0 255 0)
  (let [[ant-x ant-y] (get state :ant)]
    (q/rect (* ant-x cell-size) (* ant-y cell-size) cell-size cell-size)))

(defn -main [& args]
  (q/defsketch langtons-ant
               :title "Langton's Ant"
               :setup setup
               :draw draw
               :update small-ant/step
               :size [(* cell-size grid-width) (* cell-size grid-height)]
               :middleware [m/fun-mode]))