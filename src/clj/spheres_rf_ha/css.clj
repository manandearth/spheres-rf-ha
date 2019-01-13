(ns spheres-rf-ha.css
  (:require [garden.def :refer [defstylesheet]]))

(defstylesheet screen
  {:output-to "resources/public/css/screen.css"}
  [
   ;; [:body {:background-image "url(/images/gray2.png)"
   ;;         :background-size "8px"}]

   [:#app {:margin "0 auto"
           :width "1010px"}]

   [:svg {:margin-left "-25px"}]

   [:h1 {:font-size "4rem"
         :font-family "Baloo Da, Impact, sans-serif"
         :margin-top "0"
         :margin-bottom "-20px"
         :color "#556677"
         :text-shadow "3px 3px #ddd"}]


   [:h2 {:font-size "2rem"
         :font-family "Baloo Da, Impact, sans-serif"
         :color "#447799"
         :text-shadow "3px 3px #ddd"}
         ]

   [:h3 {:font-size "2rem"
         :font-family "Baloo Da, Impact, sans-serif"
         :color "#444477"
         :text-shadow "3px 3px #ddd"}
    ]

   [:h4 {:font-size "1rem"
         :font-family "Baloo Da, Impact, sans-serif"
         :color "#444477"
         :text-shadow "3px 3px #ddd"}]

   [:text#staged {:font-size "1rem"
              :font-family "Baloo Da, Impact, sans-serif"
              :color "#888888"
              :text-shadow "1px 1px #ddd"}
    ]


   [:rect#axis {:fill "#ccccdd"
                :stroke "#444444"
                :stroke-width 2}
    ]

   [:rect#no-axis {:fill "#eeee"
                   :stroke "#9999"
                   :stroke-width 2}
    ]
   

   [:rect#switch {:fill "#222222"
                  :stroke "#ffffff"}]

   [:text#axis {:font-size "1.6rem"
                :font-family "Baloo Da, Impact, sans-serif"
                :fill "#775577"
                :text-shadow "1px 1px #ddd"}
    ]

      [:text#no-axis {:font-size "1.5rem"
                      :font-family "Baloo Da, Impact, sans-serif"
                      :fill "#999999"
                      :text-shadow "1px 1px #ddd"}
       ]
   
   [:.fill--black {:fill "#222200"}]
   [:.fill--gray1 {:fill "#444444"}]
   [:.fill--gray2 {:fill "#888888"}]
   [:.fill--gray3 {:fill "#bbbbbb"}]
   [:.fill--white {:fill "#ffffee"}]

   [:.stroke--black {:stroke "#222200" :stroke-width "2px"}]
   [:.stroke--gray1 {:stroke "#444444" :stroke-width "2px"}]
   [:.stroke--gray2 {:stroke "#888888" :stroke-width "2px"}]
   [:.stroke--gray3 {:stroke "#bbbbbb" :stroke-width "2px"}]
   [:.stroke--white {:stroke "#ffffee" :stroke-width "2px"}]
   [:.stroke--dashed {:stroke-dasharray "5, 5"}]

   [:.fill--color1 {:fill "#d685c5"}]
   [:.fill--color2 {:fill "#4sa90cf"}]
   [:.fill--color3 {:fill "#1fa8a7"}]
   [:.fill--color4 {:fill "#37cd81"}]
   [:.fill--color5 {:fill "#dddb36"}]])
