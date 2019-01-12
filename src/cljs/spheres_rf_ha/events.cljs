(ns spheres-rf-ha.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx]]
   [spheres-rf-ha.db :as db]
   ))

(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(reg-event-db
 :visible
 (fn [db [_ k]]
   (assoc-in db [:spheres k :vis] false)))

(reg-event-db
 :invisible
 (fn [db [_ k]]
   (assoc-in db [:spheres k :vis] true)))

(reg-event-db
 :x-selected
 (fn [db [_ attr]]
   (assoc db :x-selected attr)))

(reg-event-db
 :y-selected
 (fn [db [_ attr]]
   (assoc db :y-selected attr)))

(reg-event-db
 :sys-selected
 (fn [db [_ sys]]
   (assoc db :sys-selected sys)))

;; (reg-event-db
;;  :including
;;  (fn [db [_ attr]]
;;    (assoc-in db [:spheres attr :included] true)))

;; (reg-event-db
;;  :excluding
;;  (fn [db [_ attr]]
;;    (assoc-in db [:spheres attr :included] false)))
