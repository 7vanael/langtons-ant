(ns langtons-ant.ant)

(def direction-map
  {:up    [0 -1]
   :right [1 0]
   :down  [0 1]
   :left  [-1 0]})

(def directions
  ;this order is functionally important
  (cycle [:up :right :down :left]))

(defn white? [color] (= :white color))

(defn turn [color direction]
  (let [directions-current (drop-while #(not= direction %) directions)
        new-direction      (if (white? color)
                             (second directions-current)
                             (nth directions-current 3))]
    new-direction))

(defn invert-color [color]
  (if (white? color) :black :white))

(defn move [[x y] direction]
  (let [[change-x change-y] (get direction-map direction)]
    [(+ x change-x) (+ y change-y)]))

(defn step [{:keys [grid orientation ant]}]
  (let [color (get-in grid ant)
        opposite-color (invert-color color)
        new-orientation (turn color orientation)
        new-position (move ant new-orientation)]
    {:grid (assoc-in grid ant opposite-color)
     :orientation new-orientation
     :ant new-position}))