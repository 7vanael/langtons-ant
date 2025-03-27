(ns langtons-ant.ant)

(def orientations
  [[-1 0];up
   [0 1] ;right
   [1 0] ;down
   [0 -1];left
   ])

(defn update-position [state]
  (let [{:keys [ant orientation]} state
        direction (nth orientations orientation)]
    (mapv + direction ant)))

(defn update-orientation [current-color current-orientation]
  (mod
    (if (zero? current-color)
      (dec current-orientation)
      (inc current-orientation))
    4))

(defn update-state [state]
  (let [{:keys [grid ant orientation]} state
        [row column] ant
        current-color (get-in grid [row column])
        new-orientation (update-orientation current-color orientation)
        new-color (if (zero? current-color) 1 0)
        ]
    {:grid        (assoc-in grid [row column] new-color)
     :ant         (update-position (assoc state :orientation new-orientation))
     :orientation new-orientation}))