(ns spheres-rf-ha.views
  (:require
   [re-frame.core :refer [dispatch subscribe]]
   [spheres-rf-ha.subs :as subs]
   ))

;;;;;;;;;;SCRATCH_BIT;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;END_SCRATCH;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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
                       :y (+ 150 (* 60 (.indexOf attrs attr)))
                       :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#axis {:x 30
                       :y (+ 150 (- (* 60 (inc (.indexOf attrs attr))) 43))
                       }
           (identity attr)]]
         [:g {:on-click #(dispatch [:y-selected attr])}
          [:rect#no-axis {:key (str "y-axis-" attr)
                       :x 200
                          :y (+ 150 (* 60 (.indexOf attrs attr)))
                       :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#no-axis {:x 30
                          :y (+ 150(- (* 60 (inc (.indexOf attrs attr))) 43))
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
                       :x (+ 100 (* 180 (inc (.indexOf attrs attr))))
                       :y 900
                       :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#axis {:x (+ (* 180 (inc (.indexOf attrs attr))) 113)
                       :y 940
                       }
           (identity attr)]]
         [:g {:on-click #(dispatch [:x-selected attr])}
          [:rect#no-axis {:key (str "x-axis-" attr)
                          :x (+ 100 (* 180 (inc (.indexOf attrs attr))))
                          :y 900
                          :rx 10
                       :width 20
                       :height 20
                       }]
          [:text#no-axis {:x (+ (* 180 (inc (.indexOf attrs attr))) 113)
                       :y 940
                       }
           (identity attr)]])))])

(defn x-scale-bars []
  [:svg
   [:g [:line {:x1 280 :y1 150 :x2 280 :y2 850 :stroke "black"}]
    (let [spheres (subscribe [::subs/in-menu])
          x-select @(subscribe [::subs/x-selected])
          xmin-val (apply min (map x-select (filter #(= true (:vis %)) @spheres)))
          xmax-val (apply max (map x-select (filter #(= true (:vis %)) @spheres)))
          x-fit  (/ xmax-val 900)
          ]
      (for [body @spheres :when (:vis body)]
        (if
            (or (= xmin-val (x-select body)) (= xmax-val (x-select body)))
          [:g
           [:line {:x1 (+ 350 (/ (x-select body) x-fit))
                   :y1 885
                   :x2 (+ 350 (/ (x-select body) x-fit))
                   :y2 (- 870 15)
                   :stroke "black"
                   }]
           [:text#spheres {:x (+ 350 (/ (x-select body) x-fit))
                          :y 885
                           }
            (str (.floor js/Math (x-select body)) (case  x-select
                                   :apoapsis " km"
                                   :periapsis " km"
                                   :circumference " km"
                                   :mass " kg"
                                   :volume " km³"
                                   :orbital_period " days"
                                   :surface_area " km²" ""))]]))
      
      )]])


(defn y-scale-bars []
  [:svg
   [:g [:line {:x1 300 :y1 870 :x2 1300 :y2 870 :stroke "black"}]
    (let [spheres (subscribe [::subs/in-menu])
          y-select @(subscribe [::subs/y-selected])
          ymin-val (apply min (map y-select (filter #(= true (:vis %)) @spheres)))
          ymax-val (apply max (map y-select (filter #(= true (:vis %)) @spheres)))
          y-fit (/ ymax-val 500)]
(for [body @spheres :when (:vis body)]
        (if
            (or (= ymin-val (y-select body)) (= ymax-val (y-select body)))
          [:g [:line {:x1 250
                      :y1 (+ 750 (* -1 (/ (y-select body) y-fit)))
                      :x2 290
                      :y2 (+ 750 (* -1 (/ (y-select body) y-fit)))
                      :stroke "black"}]
           [:text#spheres {:x 250 
                          :y (+ 745 (* -1 (/ (y-select body) y-fit)))
                          } (str (.floor js/Math (y-select body))
                                 (case  y-select
                                   :apoapsis " km"
                                   :periapsis " km"
                                   :circumference " km"
                                   :mass " kg"
                                   :volume " km³"
                                   :orbital_period " days"
                                   :surface_area " km²" "")
                                 )]]))
      
      
      )]])

(defn systems []
  "the systems are presets named by ther main body of each
i.e. 'Sun' in the case of the solar system"
  [:svg {:x 180}
   [:text#axis {:x 125 :y 50} "systems:"]
   {:x 175 :y 0} 
   (let [systems (subscribe [::subs/presets])
         sys-selected @(subscribe [::subs/sys-selected])]
     (for [system @systems]
       (if (= sys-selected (:name system))
         [:svg {:x (* 125 (inc (.indexOf @systems system)))
                :y 75}
          [:g
           [:rect#axis {:width 120
                        :height 30
                        :rx 10}]
           [:text#axis {:x 10
                        :y 25}
            (:name system)]]]
         [:svg {:x (* 125 (inc (.indexOf @systems system)))
                :y 75}
          [:g {:on-click #(dispatch [:sys-selected (:name system)])
               }
           [:rect#no-axis {:width 120
                           :height 30
                           :rx 10}]
           [:text#no-axis {:x 10
                           :y 25}
            (:name system)]]])))])


(defn body-menu []
  "the bodies that form the system selected"
  [:svg
   (let [bodies (subscribe [::subs/in-menu])]
     (for [body @bodies]
       (if (:vis body)
         [:g
          {:on-click #(dispatch [:visible (:name body)])}
          [:rect#axis {:key (str "body-list-rect" body)
                       :x 1370
                       :y (+ 150 (* 45 (inc (.indexOf @bodies body))))
                       :width 150
                       :height 35
                       :rx 10
                       }]
          [:text#axis {:key (str "body-list-text" body)
                       :x 1390
                       :y (+ (* 45 (inc (.indexOf @bodies body))) 175)
                       }
           (:name body)]]
         [:g
          {:on-click #(dispatch [:invisible (:name body)])}
          [:rect#no-axis {:key (str "body-list-rect" body)
                          :x 1370
                          :y (+ 150 (* 45 (inc (.indexOf @bodies body))))
                          :width 150
                          :height 35
                          :rx 10
                          }]
          [:text#no-axis {:key (str "body-list-text" body)
                          :x 1390
                          :y (+ (* 45 (inc (.indexOf @bodies body))) 175)
                          }
           (:name body)]]
         )))])




;; (let [spheres (subscribe [::subs/spheres-vals])
;;       x-select @(subscribe [::subs/x-selected])]
;;   (apply max (map x-select @spheres)))



(defn bodies []
  (let [spheres (subscribe [::subs/in-menu])
        x-select @(subscribe [::subs/x-selected])
        y-select @(subscribe [::subs/y-selected])
        sys-selected @(subscribe [::subs/sys-selected])
        xmin-val (apply min (map x-select (filter #(= true (:vis %)) @spheres)))
        xmax-val (apply max (map x-select (filter #(= true (:vis %)) @spheres)))
        ymin-val (apply min (map y-select (filter #(= true (:vis %)) @spheres)))
        ymax-val (apply max (map y-select (filter #(= true (:vis %)) @spheres)))
        x-fit  (/ xmax-val 900)
        y-fit (/ ymax-val 500)
        ]
    [:g (for [body @spheres
              :when (:vis body) ;toggle visability
              ]
          (let [xtrans (+ 350 (/ (x-select body) x-fit))
                ytrans (+ 750 (* -1 (/ (y-select body) y-fit)))
                size (.log js/Math (:volume body))
                ]
            [:g {:key (str "g-" (:name body))}
             [:circle#staged
              {:r size
               :cx xtrans 
               :cy ytrans
               ;; :fill "#6666"
               :key (str "circle-" (:name body))
               :on-mouse-over #() ;TODO event for mouse on sphere
               }
              ]
             [:text#staged
              {:x xtrans
               :y ytrans
               :font-size 10
               :fill "#888888"
               :key (str "text-" (:name body))} (:name body)]]))]))


(defn main-panel []
  [:div 
   [:div {:style {:width "1000px" :text-align "justify" :padding-left 270} } 
    [:h1  "Project Spheres" ]
    [:h2 "Interplanetary relations"]
    [:h3 "Explore the different attributes of the major bodies of our solar system; First select a system at the top. Visibility is selected on the right list, note that the scale changes accordingly. Select the attributes demonstrated on the" [:span {:style {:color "palevioletred"}} " x "] "and" [:span {:style {:color "palevioletred"}} " y "] "axes. The scale of the graph is linear yet the representation of the size of each body is in a log scale (the differences are too great to show linearly)"]]
   
   [:svg {:width 1600
          :height 950}
    [background-box {:x 300
                     :y 150
                     :width 1000
                     :height 700
                     ;:on-click #(dispatch[])
                     }]
    [bodies]
    [y-axis]
    [x-axis]
    [x-scale-bars]
    [y-scale-bars]
    [body-menu]
    [systems]
    ]
   ])
