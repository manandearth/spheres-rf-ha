(ns spheres-rf-ha.records
  (:require [cljs.spec.alpha :as s]))
(defrecord Body [name
                 volume        ; km³
                 apoapsis      ; km  farthest from sun
                 periapsis     ; km  closest to sun
                 mass          ; kg
                 surface_area  ; km²
                 satelites     ; int
                 circumference ; km
                 form          ; (star  planet  dwarf-planet  moon  astroid)
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

(def test-coll [{:name "boo"} {:name "goo"} {:name "choo"}])
(def body-list (append-idx temp-list))


;;SIMPLE MAP
(def spheres-map {"Mercury" {:volume 60830000000 
                             :apoapsis 69816900 
                             :periapsis 46001200 
                             :mass 3.3011e+23 
                             :surface_area 74800000 
                             :satelites nil
                             :circumference 15329 
                             :form "planet" 
                             :orbital_period 58.6458333333 
                             :parent "Sun" 
                             :vis true
                             }
                  "Venus" {:volume 928430000000 
                           :apoapsis 108939000 
                           :periapsis 107477000 
                           :mass 4.8675e+24 
                           :surface_area 460230000 
                           :satelites nil
                           :circumference 38025 
                           :form "planet" 
                           :orbital_period 224.701 
                           :parent "Sun" 
                           :vis true}
                  "Earth" {:volume 1.08321e12   
                           :apoapsis 152098232
                           :periapsis 147098290
                           :mass     5.97219e24
                           :surface_area 510072000
                           :satelites  ["Moon"]
                           :circumference 40075.017
                           :form         "planet"
                           :orbital_period 365.256363004
                           :parent        "Sun"
                           :vis true
                           :included false
                           }
                  "Mars" {:volume 163180000000 
                          :apoapsis 249200000 
                          :periapsis 206700000 
                          :mass 6.4171e+23 
                          :surface_area 144798500 
                          :satelites ["Deimos" "Phobos"]
                          :circumference 21344 
                          :form "planet" 
                          :orbital_period 686.971 
                          :parent "Sun" 
                          :vis true
                          :inluded false}
                  "Jupiter" {:volume 1431300000000000 
                             :apoapsis 816620000 
                             :periapsis 740520000 
                             :mass 1.8982e+27 
                             :surface_area 61419000000 
                             :satelites ["Io" "Europa" "Ganymede" "Callisto"]
                             :circumference 439264 
                             :form "Planet" 
                             :orbital_period 4332.59 
                             :parent "Sun" 
                             :vis true
                             :included false}
                  "Saturn" {:volume 827130000000000 
                            :apoapsis 1514500000 
                            :periapsis 1352550000 
                            :mass 5.6834e+26 
                            :surface_area 42700000000 
                            :satelites ["Mimas" "Enceladus" "Tethys" "Dione" "Rhea" "Titan" "Iapetus" "Hyperion"]
                            :circumference 378675 
                            :form "planet" 
                            :orbital_period 10759.22 
                            :parent "Sun" 
                            :vis true
                            :included false}
                  "Uranus" {:volume 68330000000000 
                            :apoapsis 3008000000 
                            :periapsis 2742000000 
                            :mass 8.681e+25 
                            :surface_area 8115600000 
                            :satelites [ "Puck" "Miranda" "Ariel" "Umbriel" "Titania" "Oberon"]
                            :circumference 159354.1 
                            :form "Planet" 
                            :orbital_period 30688.5 
                            :parent "Sun" 
                            :vis true
                            :included false}
                  "Neptune" {:volume 62540000000000 
                             :apoapsis 4540000000 
                             :periapsis 4460000000 
                             :mass 1.02413e+26 
                             :surface_area 7618300000 
                             :satelites ["Triton" "Nereid" "Naiad" "Neso" "Proteus" "Psamathe" "Larissa" "Thalassa" "Despina" "Galatea" "Halimede" "Sao" "Laomedeia" "Neptune XIV"]
                             :circumference 154704.6 
                             :form "planet" 
                             :orbital_period 60182 
                             :parent "sun" 
                             :vis true
                             :included false}
                  "Pluto" {:volume 7057000000 
                           :apoapsis 7231900000000 
                           :periapsis 4436820000 
                           :mass 1.303e+22 
                           :surface_area 17790000 
                           :satelites ["Charon" "Styx" "Nix" "Kerberos" "Hydra"]
                           :circumference 7231.9 
                           :form "dwarf planet" 
                           :orbital_period 90560 
                           :parent "Sun" 
                           :vis true
                           :included false}
                  "Ceres" {:volume 421000000 
                           :apoapsis 445410000 
                           :periapsis 382620000 
                           :mass 939300000000000000000 
                           :surface_area 2770000 
                           :satelites nil
                           :circumference 2992.1 
                           :form "dwarf planet" 
                           :orbital_period 2992.1 
                           :parent "Sun" 
                           :vis true}
                  "Sun" {:volume 1410000000000000000 
                         :apoapsis 0 
                         :periapsis 0 
                         :mass 1.9885e+30 
                         :surface_area 6090000000000 
                         :satelites ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Ceres" "Saturn" "Halley" "Uranus" "Neptune" "Pluto"]
                         :circumference 4379000 
                         :form "sun" 
                         :orbital_period nil 
                         :parent nil 
                         :vis true
                         :included true}
                  "Halley" {:volume 960 
                            :apoapsis 5248192000 
                            :periapsis 87664350 
                            :mass 220000000000000 
                            :surface_area 380.132711 
                            :satelites nil
                            :circumference 34.5575 
                            :form "comet" 
                            :orbital_period 27510.63 
                            :parent "Sun" 
                            :vis false}
                  "Moon" {:volume 21958000000 
                            :apoapsis 405400 
                            :periapsis 362600 
                            :mass 7.342e+22 
                            :surface_area 37930000 
                            :satelites nil
                            :circumference 10921 
                            :form "moon" 
                            :orbital_period 27.321661 
                            :parent "Earth" 
                            :vis true}   
                  "Deimos" {:volume 999.78 
                              :apoapsis 23470.9 
                              :periapsis 23455.5 
                              :mass 1476200000000000 
                              :surface_area 495.1548 
                              :satelites nil
                              :circumference 39 
                              :form "moon" 
                              :orbital_period 1.2624 
                              :parent "Mars" 
                              :vis true}
                  "Phobos" {:volume 5783.61 
                              :apoapsis 9517.58 
                              :periapsis 9234.42 
                              :mass 10659000000000000 
                              :surface_area 1548.3 
                              :satelites nil
                              :circumference 69.7 
                              :form "moon" 
                              :orbital_period 0.3191 
                              :parent "Mars" 
                              :vis true}
                  "Charon" {:volume 9.32e+8
                            :apoapsis 17536
                            :periapsis 17536
                            :mass 1.586e+8
                            :surface_area 4.6e+6
                            :satelites nil
                            :circumference 3792.5
                            :form "moon"
                            :orbital_period 6.3872304
                            :parent "Pluto"
                            :vis true}}
  )

;;function play

(get spheres-map "Mars")

(get-in spheres-map ["Mars" :mass])

(= (:parent deimos) "Mars")

;; destructuring 1:
(for [[name attributes] spheres-map
      :when #(= (:parent %) "Mars")
      ]
  (:mass attributes))

(for [[_ b] spheres-map]
  (:mass b))
;; destructuring 2:
(filter (fn [s] (let [[name attributes] s] (= (:parent attributes) "Mars"))) spheres-map)

;;3

(for [[name attr] spheres-map]
  {(keyword name) (:mass attr)})

;;systems:
;; Sun
;; Earth
;; Mars
;; Jupiter
;; Saturn
;; Uranus
;; Neptune
;; Pluto



;;SPECS

;(map-indexed #(assoc % :id %) spheres-map)

