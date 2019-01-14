(ns spheres-rf-ha.subs
  (:require
   [re-frame.core :refer [reg-sub subscribe]]))


;;;;;;;;;;SCRATCH_BIT;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(reg-sub
 ::mars-group
 (fn [db]
   (let [spheres (:spheres-map db)]
     (for [[name sphere] spheres :when
           (or (= "Mars" (:parent sphere)) (= "Mars" name))]
       sphere))))

;;;scratchier even:
(def some-map {:spheres-map {"Mars" {:parent "Sun" :mass 10}
                             "Moon1" {:parent "Mars" :mass 1}
                             "Moon2" {:parent "Mars" :mass 2}
                             "Moon3" {:parent "Bogus" :mass 3}
                             "Earth" {:parent "Sun" :mass 11}}})

;; (let [spheres (:spheres-map some-map)]
;;   (for [[name sphere] spheres :when (or (= (:parent sphere) "Mars")
;;                                         (= name "Mars"))]
;;     sphere))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;END_SCRATCH;;;;;;;;;;;;;;;;


;;just a template for reg-sub:
(reg-sub
 ::name
 (fn [db]
   (:name db)))

;;the field that holds the actual db:
(reg-sub
 ::spheres
 (fn [db _]
   (:spheres db))
 )

;;the maps of bodies in a lazy sequence:
(reg-sub
 ::spheres-vals
 (fn [db _]
   (vals (:spheres db))))

(reg-sub
 ::spheres-vals-compounded
 (fn [db _]
   (vals (:spheres (assoc-in db [:spheres (:sys-selected db) :periapsis] (:self-bias (get (:spheres db) (:sys-selected db))))))))

(reg-sub
 ::the-parent
 (fn [db _]
   (get (:spheres db) (:sys-selected db))))

;; @(subscribe [::the-parent])
;; @(subscribe [::spheres-vals-compounded])

;;those out of the seq that will show in the vis:
(reg-sub ::spheres-vals-visible :<- [::spheres-vals]
         (fn [spheres-vals _]
           (filter :vis spheres-vals)))

;;the bodies that form systems (have satelites) for the systems menu:
(reg-sub ::presets :<- [::spheres-vals]
         (fn [spheres-vals]
           (filter :satelites spheres-vals)))

;;the selected preset plus its children make the menu
;;TODO need to improve this using :<- ::spheres-vals-compounded 
(reg-sub ::in-menu 
         (fn [db _]
           (let [spheres
                 (vals
                  (:spheres
                   (assoc-in
                    (assoc-in db [:spheres (:sys-selected db) :periapsis] (:self-bias (get (:spheres db) (:sys-selected db))))
                    [:spheres (:sys-selected db) :apoapsis] (:self-bias (get (:spheres db) (:sys-selected db))))))]
             (filter #(or (= (:sys-selected db) (:parent %)) (= (:sys-selected db) (:name %))) spheres
                     ;; #(= (:sys-selected db) (:parent %)) spheres
                     ))))

;; (count @(subscribe [::in-menu]))
;; @(subscribe [::x-selected])

;;the attributes that were selected on the x
(reg-sub ::x-selected
         (fn [db [_ attr]]
           (:x-selected db)))

;;the attributes that were selected on the y
(reg-sub ::y-selected
         (fn [db [_ attr]]
           (:y-selected db)))

;;the preset that is toggled on
(reg-sub ::sys-selected
         (fn [db [_ sys]]
           (:sys-selected db)))
;; (reg-sub ::attributes
;;          (fn [db _]
;;            (let [all (vec (keys (first (vals (:spheres db)))))
;;                  (merge (subvec all  5)
;;                         (nth all 7)
;;                         (nth all 9))]))
;;          )

;;those attributes that can be demonstarted in the vis:
(reg-sub ::attributes
         (fn [db _]
           (vec '(:circumference :volume :mass :surface_area :apoapsis :periapsis :orbital_period))))

;; (reg-sub
;;  ::volume
;;  (fn  [db body]
;;    (:volume (first (filter #(#{body} (:name %)) (:spheres db))))))

;; (reg-sub
;;  ::vis
;;  (fn [db body]
;;    (:vis (first (filter #(#{body} (:name %)) (:spheres db))))))
