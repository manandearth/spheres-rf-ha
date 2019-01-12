(ns spheres-rf-ha.scratch)

(defprotocol Creatures
  (midday-habit [this]))

(defrecord Person [fname lname]
  Creatures
  (midday-habit [this]
    (str fname " " lname " eats his sandwich.")))

(def person-1 (->Person "Bob" "the Builder"))

(def person-2 (map->Person {:fname "Pat" :lname "Postman" }))

(.lname person-2)   ; => "Postman"

(:fname person-1)   ; => "Bob"  

(midday-habit person-1)  ; => "Bob the Builder eats his sandwich."

