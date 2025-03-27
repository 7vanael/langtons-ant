(ns langtons-ant.small-ant)

(def direction-map
  {:up    [0 -1]
   :right [1 0]
   :down  [0 1]
   :left  [-1 0]})

(def directions
  [:up :right :down :left])

(defn white? [color] (= color :white))

(defn turn [direction color]
  (let [index     (.indexOf directions direction)
        mid-index (if (white? color) (inc index) (dec index))
        new-index (mod mid-index 4)]
    (nth directions new-index)))

(defn inverse-color [color]
  (if (white? color)
    :black
    :white))

(defn move [[x y] orientation]
  (let [[change-x change-y] (get direction-map orientation)]
    [(+ x change-x) (+ y change-y)]))

(defn step [{:keys [grid ant orientation]}]
  (let [[row column] ant
        current-color   (get-in grid [row column])
        opposite-color  (inverse-color current-color)
        new-orientation (turn orientation current-color)
        new-position    (move [row column] new-orientation)]

    {:grid        (assoc-in grid [row column] opposite-color)
     :ant         new-position
     :orientation new-orientation}))