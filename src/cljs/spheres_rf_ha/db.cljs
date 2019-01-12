(ns spheres-rf-ha.db
  (:require [spheres-rf-ha.records :refer [body-list spheres-map]]))

(def default-db
  {:name "Cider"
   :spheres (into {} (for [[name sphere] spheres-map]
                       {name (assoc sphere :name name)}))
   ;; (into {} (map (fn [body] {(:name body) body}) body-list))
   :spheres-map spheres-map
   :x-selected :periapsis
   :y-selected :mass
   :sys-selected "Sun"
   })



(get (:spheres default-db) (:sys-selected default-db))
