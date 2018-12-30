(ns spheres-rf-ha.views
  (:require
   [re-frame.core :refer [dispatch subscribe]]
   [spheres-rf-ha.subs :as subs]
   ))

(defn background-box
  "Draw a grey box with black border, factoring in the border width"
  [{:keys [x y width height on-click]}]
  [:rect  {:fill "#dddd"
           :stroke "#222222"
           :x (inc x)
           :y (inc y)
           :width  (- width 2) 
           :height (- height 2)
           :on-click on-click }])

(defn y-axis []
  [:svg
   (let [attrs
         @(subscribe [::subs/attributes])
         ;; [:volume :circumference :mass :orbital_period :periapsis :apoapsis]
         y-select @(subscribe [::subs/y-selected])
         ]
     (for [attr attrs]
       (if (= attr y-select)
         [:g {:on-click #(dispatch [:y-selected attr])}
          [:rect#axis {:key (str "y-axis-" attr)
                       :x 200
                       :y (* 60 (.indexOf attrs attr))
                       :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#axis {:x 30
                       :y (- (* 60 (inc (.indexOf attrs attr))) 43)
                       }
           (identity attr)]]
         [:g {:on-click #(dispatch [:y-selected attr])}
          [:rect#no-axis {:key (str "y-axis-" attr)
                       :x 200
                       :y (* 60 (.indexOf attrs attr))
                       :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#no-axis {:x 30
                       :y (- (* 60 (inc (.indexOf attrs attr))) 43)
                       }
           (identity attr)]])))])

(defn x-axis []
  [:svg
   (let [attrs
         @(subscribe [::subs/attributes])
         ;; [:volume :circumference :mass :orbital_period :periapsis :apoapsis]
         x-select @(subscribe [::subs/x-selected])
         ]
     (for [attr attrs]
       (if (= attr x-select)
         [:g {:on-click #(dispatch [:x-selected attr])}
          [:rect#axis {:key (str "x-axis-" attr)
                       :x (+ 100 (* 150 (inc (.indexOf attrs attr))))
                       :y 730
                       :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#axis {:x (+ (* 150 (inc (.indexOf attrs attr))) 113)
                       :y 767
                       }
           (identity attr)]]
         [:g {:on-click #(dispatch [:x-selected attr])}
          [:rect#no-axis {:key (str "x-axis-" attr)
                          :x (+ 100 (* 150 (inc (.indexOf attrs attr))))
                          :y 730
                          :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#no-axis {:x (+ (* 150 (inc (.indexOf attrs attr))) 113)
                       :y 767
                       }
           (identity attr)]])))])


(defn body-menu []
  [:svg
   (let [bodies (subscribe [::subs/spheres-vals])]
     (for [body @bodies]
       (if (:vis body)
         [:g
          {:on-click #(dispatch [:visible (:name body)])}
          [:rect#axis {:key (str "body-list-rect" body)
                       :x 1320
                       :y (* 45 (inc (:id body)))
                       :width 150
                       :height 35
                       :rx 10
                       }]
          [:text#axis {:key (str "body-list-text" body)
                       :x 1340
                       :y (+ (* 45 (inc (:id body))) 25)
                       }
           (:name body)]]
         [:g
          {:on-click #(dispatch [:invisible (:name body)])}
          [:rect#no-axis {:key (str "body-list-rect" body)
                          :x 1320
                          :y (* 45 (inc (:id body)))
                          :width 150
                          :height 35
                          :rx 10
                          }]
          [:text#no-axis {:key (str "body-list-text" body)
                          :x 1340
                          :y (+ (* 45 (inc (:id body))) 25)
                          }
           (:name body)]]
         )))])



;;TODO have a legend-scale on the axis that shows the numbers so as to demonstrate the scale.

(let [spheres (subscribe [::subs/spheres-vals])
      x-select @(subscribe [::subs/x-selected])]
  (apply max (map x-select @spheres)))



(defn bodies []
  (let [spheres (subscribe [::subs/spheres-vals])
        x-select @(subscribe [::subs/x-selected])
        y-select @(subscribe [::subs/y-selected])
        xmin-val (apply min (map x-select (filter #(= true (:vis %)) @spheres)))
        xmax-val (apply max (map x-select (filter #(= true (:vis %)) @spheres)))
        ymin-val (apply min (map y-select (filter #(= true (:vis %)) @spheres)))
        ymax-val (apply max (map y-select (filter #(= true (:vis %)) @spheres)))
        x-fit  (/ xmax-val 900)
        y-fit (/ ymax-val 500)]
    [:g (for [body @spheres
              :when (:vis body) ;toggle visability
              ]
          (let [xtrans (+ 300 (/ (x-select body) x-fit))
                ;(+ 250 (* 40 (.log js/Math (+ 1 (x-select body))))) 
                ytrans (+ 600 (* -1 (/ (y-select body) y-fit)))
                ;(* 4 (.log js/Math (+ 1 (y-select body))) 2)
                size (.log js/Math (:volume body))]
            [:g {:key (str "g-" (:name body))}
             [:circle
              {:r size
               :cx xtrans 
               :cy ytrans
               :fill "#6666"
               :key (str "circle-" (:name body))}]
             [:text#staged
              {:x xtrans
               :y ytrans
               :font-size 10
               :fill "#888888"
               :key (str "text-" (:name body))} (:name body)]]))]))

(defn main-panel []
  [:div
   [:h1 "Project Spheres" ]
   [:h2 "Interplanetary relations"]
   [:svg {:width 1600
          :height 800}
    [background-box {:x 250
                     :y 0
                     :width 1000
                     :height 700
                     :on-click #(dispatch[])}]
    [bodies]
    [y-axis]
    [x-axis]
    [body-menu]
    ]
   ]





  )
