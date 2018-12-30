(ns spheres-rf-ha.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [spheres-rf-ha.events :as events]
   [spheres-rf-ha.views :as views]
   [spheres-rf-ha.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
