(ns spheres-rf-ha.db
  (:require [spheres-rf-ha.records :refer [body-list]]))

(def default-db
  {:name "Cider"
   :spheres (into {}
                  (map (fn [body] {(:name body) body}) body-list))
   :x-selected :periapsis
   :y-selected :mass
   })
