(ns spheres-rf-ha.css
  (:require [garden.def :refer [defstylesheet]]
            [garden.selectors :as s]))

(defstylesheet screen
  {:output-to "resources/public/css/screen.css"}
  [
   ;; [:body {:background-image "url(/images/gray2.png)"
   ;;         :background-size "8px"}]


   
   ;; [:#app {:margin "0 auto"
   ;;         :width "1010px"}]

   [:svg {:margin-left "-25px"}]

   [:h1 {:font-size "4rem"
         :font-family "Baloo Da, Impact, sans-serif"
         :margin-top "0"
         :margin-bottom "-20px"
         :color "#556677"
         :text-shadow "3px 3px #ddd"}]


   [:h2 {:font-size "2rem"
         :font-family "Roboto Mono, Impact, sans-serif"
         :color "#447799"
         :text-shadow "3px 3px #ddd"}
         ]

   [:h3 {:font-size "1.2rem"
         :font-family "Roboto Mono, Impact, sans-serif"
         :color "#444477"
         :text-shadow "2px 2px #ddd"}
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

   [:text#spheres {:font-size "1rem"
                  :font-family "Roboto Mono, Impact, sans-serif"
                  :fill "darkblue"
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

   [:text#axis {:font-size "1.4rem"
                :font-family "Baloo Da, Impact, sans-serif"
                :fill "#775577"
                :text-shadow "1px 1px #ddd"}
    ]

   [:text#no-axis {:font-size "1.2rem"
                   :font-family "Roboto Mono, Impact, sans-serif"
                   :fill "#999999"
                   :text-shadow "1px 1px #ddd"}
    ]
   ;;this is brilliant! the circle has an adjacent selector
   ;;so it activates the g.tooltip
   [(s/+ :circle#staged:hover :g.tooltip) {
                                           ;;:opacity 1
                                           :display "inline"}]

   [:circle#staged {:fill "#6666"}]
   [:circle#staged:hover {:fill "#4444"}
    ]
    
;;tried :opacity and :visibility as well but :dsplay works best because the space is then unoccupied for the cursor to move upon.
   [:g.tooltip {:position :relative
               ;; :display "inline"
                :cursor "pointer"
                ;;:opacity 0
                ;; :visibility false
                :display "none"
                }
    ;;In order for the tooltip to work also when the cursor is in the tooltip actual space: 
    [:&:hover {:display "inline"
                     }]]

   [:text.tooltip {:font-size 30
                   :position "relative"
                   :font-family "Roboto Mono, Impact, san-serif"
                   :text-shadow "1px 1px #ddd"
                   :fill "#666"
                   }]
   

   [:.footer
    {
     :background "#cac9c6"
     ;:position "absolute"
     :bottom "0"
     :left "0"
     :width "100%"
     :margin-top "4.5em"
     :padding-top "0.75em"
     :padding-bottom "0.75em"
     }]

   ;; [:&:.a
   ;;  {
   ;;   :color: #3f464c;
   ;;   :text-decoration: underline; }
   ;;   .footer a:hover {
   ;;                    color: #3f464c; }
   ;;                    }
   ;;   }]
       












   
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
