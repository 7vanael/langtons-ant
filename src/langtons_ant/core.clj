(ns langtons-ant.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [langtons-ant.small-ant :as small-ant]
            [langtons-ant.ant :as ant]))

(def grid-width 40)
(def grid-height 40)
(def cell-size 18)

(defn setup []
  (q/frame-rate 60)
  (q/background 300)
  {:grid (vec (repeat grid-height (vec (repeat grid-width :white))))
   :ant [(/ grid-width 2) (/ grid-height 2)]
   :orientation :up})

(defn black? [color]
  (= color :black))

(defn draw [state]
  (q/stroke 100)
  (doseq [i (range (inc grid-width))]
    (q/line (* i cell-size) 0 (* i cell-size) (* grid-height cell-size)))
  (doseq [i (range (inc grid-width))]
    (q/line 0 (* i cell-size) (* grid-width cell-size) (* i cell-size)))
  (doseq [row (range grid-width)
          col (range grid-height)]
    (if (black? (get-in (:grid state) [row col]))
      (q/fill 0)
      (q/fill 255))
    (q/rect (* row cell-size) (* col cell-size) cell-size cell-size))
  (q/fill 0 255 0)
  (let [[ant-x ant-y] (get state :ant)]
    (q/rect (* ant-x cell-size) (* ant-y cell-size) cell-size cell-size)))

(defn -main [& args]
  (q/defsketch langtons-ant
               :title "Langton's Ant"
               :setup setup
               :draw draw
               :update ant/step
               :size [(* cell-size grid-width) (* cell-size grid-height)]
               :middleware [m/fun-mode]))