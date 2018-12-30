(ns spheres-rf-ha.records)
(defrecord Body [name
                 volume        ; km³
                 apoapsis      ; km, farthest from sun
                 periapsis     ; km, closest to sun
                 mass          ; kg
                 surface_area  ; km²
                 satelites     ; int
                 circumference ; km
                 form          ; (star, planet, dwarf-planet, moon, astroid)
                 orbital_period; days
                 parent        ; name
                 image-sharp   ; path string
                 image-pixel   ; path string
                 vis           ; boolean
                 ])


(def earth
  (->Body  "Earth" 1.08321e12 152098232 147098290 5.97219e24
           510072000 1 40075.017 "planet" 365.256363004 "Sun" "/images/earth01.png" "/images/earth02.png" true))

;; (insert-celestial-record! db-spec earth)

(def mercury
  (->Body "Mercury" 6.083e10 69816900 46001200 3.3011e23
          7.48e7 0 15329 "planet" 58.6458333333 "Sun" "/images/mercury01.png" "/images/mercury02.png" true))

(def venus
  (->Body "Venus" 9.2843e11 108939000 107477000 4.8675e24
          4.6023e8 0 38025 "planet" 224.701 "Sun" "/images/venus01.png" "/images/venus02.png" true))

(def mars
  (->Body "Mars" 1.6318e11 249200000 206700000 6.4171e23
          144798500 2 21344 "planet" 686.971 "Sun" "/images/mars01.png" "/images/mars02.png" true))



(def phobos
  (->Body "Phobos" 5783.61 9517.58 9234.42 1.0659e16
          1548.3 0 69.7 "moon" 0.3191 "Mars" "/images/phobos01.png" "/images/phobos02.png" true))

(def deimos
  (->Body "Deimos" 999.78 23470.9 23455.5 1.4762e15
          495.1548 0 39.0 "moon" 1.2624 "Mars" "/images/deimos01.png" "/images/deimos02.png" true))
(def moon
  (->Body "Moon" 2.1958e10 405400 362600 7.342e22
          3.793e7 0  10921 "moon" 27.321661 "Earth" "/images/moon01.png" "/images/moon02.png" true))

(def ceres
  (->Body "Ceres" 421000000 445410000 382620000 9.393e20
          2770000 0 2992.1 "dwarf planet" 2992.1 "Sun" "/images/ceres01.png" "/images/ceres02.png" true))

(def pluto
  (->Body "Pluto" 7.057e9 7231.9e9 4.43682e9 1.303e22 1.779e7 5 7231.9 "dwarf planet" 90560 "Sun" "/images/pluto01.png" "/images/pluto02.png" true))

(def neptune
  (->Body "Neptune" 6.254e13 4.54e9 4.46e9 1.02413e26 7.6183e9 14  154704.6 "planet" 60182 "sun" "/images/neptune01.png" "/images/neptune02.png" true))

(def saturn
  (->Body "Saturn" 8.2713e14 1514500000 1352550000 5.6834e26 4.27e10 62 378675 "planet" 10759.22 "Sun" "/images/saturn01.png" "/images/saturn02.png" true))

(def jupiter
  (->Body "Jupiter" 1.4313e15 816620000 740520000 1.8982e27 6.1419e10 79 439264 "Planet" 4332.59 "Sun" "/images/jupiters01.png" "/images/jupiter02.png" true))

(def uranus
  (->Body "Uranus" 6.833e13 3.008e9 2.742e9 8.6810e25 8.1156e9 27 159354.1 "Planet" 30688.5 "Sun" "/images/uranus01.png" "/images/uranus02.png" true))

(def sun
  (->Body "Sun" 1.41e18 0 0 1.9885e30 6.09e12  1000 4.379e6 "sun" nil nil "/images/sun01.png" "/images/sun02.png" true))

(def halley
  (->Body "Halley" 960 5248192000 87664350 2.2e14 380.132711 0 34.5575 "comet" 27510.63 "Sun" "/images/halley01.png" "/images/halley02.png" false))


(def temp-list [earth mercury venus mars phobos deimos moon sun ceres pluto neptune saturn jupiter uranus halley])


(defn append-idx [coll]
  (map-indexed #(assoc %2 :id %1) coll))

(def body-list (append-idx temp-list))

