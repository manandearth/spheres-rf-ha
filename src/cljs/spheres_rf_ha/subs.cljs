(ns spheres-rf-ha.subs
  (:require
   [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub
 ::name
 (fn [db]
   (:name db)))

(reg-sub
 ::spheres
 (fn [db _]
   (:spheres db))
 )

(reg-sub
 ::spheres-vals
 (fn [db _]
   (vals (:spheres db))))

(reg-sub ::spheres-vals-visible :<- [::spheres-vals]
         (fn [spheres-vals _]
           (filter :vis spheres-vals)))

(reg-sub ::x-selected
         (fn [db [_ attr]]
           (:x-selected db)))

(reg-sub ::y-selected
         (fn [db [_ attr]]
           (:y-selected db)))

(reg-sub ::attributes
         (fn [db _]
           (let [all (vec (keys (first (vals (:spheres db)))))]
             (merge (subvec all 1 5)
                    (nth all 7)
                    (nth all 9))))
         )

;; (reg-sub
;;  ::volume
;;  (fn  [db body]
;;    (:volume (first (filter #(#{body} (:name %)) (:spheres db))))))

;; (reg-sub
;;  ::vis
;;  (fn [db body]
;;    (:vis (first (filter #(#{body} (:name %)) (:spheres db))))))
