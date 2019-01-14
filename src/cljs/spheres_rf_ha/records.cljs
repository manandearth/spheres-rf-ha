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
                           :self-bias 0
                           :mass 5.97219e24
                           :surface_area 510072000
                           :satelites ["Moon"]
                           :circumference 40075.017
                           :form "planet"
                           :orbital_period 365.256363004
                           :parent "Sun"
                           :vis true
                           :included false
                           }
                  "Mars" {:volume 163180000000 
                          :apoapsis 249200000
                          :self-bias 0
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
                             :self-bias 0
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
                            :self-bias 0
                            :mass 5.6834e+26 
                            :surface_area 42700000000 
                            :satelites ["Mimas" "Enceladus" "Tethys" "Dione" "Rhea" "Titan" "Lapetus" "Pan"]
                            :circumference 378675 
                            :form "planet" 
                            :orbital_period 10759.22 
                            :parent "Sun" 
                            :vis true
                            :included false}
                  "Uranus" {:volume 68330000000000 
                            :apoapsis 3008000000 
                            :periapsis 2742000000
                            :self-bias 0
                            :mass 8.681e+25 
                            :surface_area 8115600000 
                            :satelites [ "Puck" "Ariel" "Umbriel" "Titania" "Oberon" "Miranda"]
                            :circumference 159354.1 
                            :form "Planet" 
                            :orbital_period 30688.5 
                            :parent "Sun" 
                            :vis true
                            :included false}
                  "Neptune" {:volume 62540000000000 
                             :apoapsis 4540000000 
                             :periapsis 4460000000
                             :self-bias 0
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
                           :self-bias 2035
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
                         :self-bias 0
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
                            :mass 1.586e+21
                            :surface_area 4.6e+6
                            :satelites nil
                            :circumference 3792.5
                            :form "moon"
                            :orbital_period 6.3872304
                            :parent "Pluto"
                            :vis true}
                  "Nix" {:volume 79704
                         :apoapsis 48708
                         :periapsis 48708
                         :mass 4.5e+16
                         :surface_area 962.113
                         :satelites nil
                         :circumference 109.9557
                         :form "moon"
                         :orbital_period 24.85463
                         :parent "Pluto"
                         :vis true}
                  "Hydra" {:volume 73125
                           :apoapsis 65000
                           :periapsis 65000
                           :mass 4.8e+16
                           :surface_area 1134.115
                           :satelites nil
                           :circumference 119.3805
                           :form "moon"
                           :orbital_period 38.20177
                           :parent "Pluto"
                           :vis true}
                  "Kerberos" {:volume 1620
                              :apoapsis 57783
                              :periapsis 57783
                              :mass 1.65e+16
                              :surface_area 166.7634 
                              :satelites nil
                              :circumference 45.7778
                              :form "moon"
                              :orbital_period 32.16756
                              :parent "Pluto"
                              :vis true}
                  "Styx" {:volume 1152
                          :apoapsis 42656
                          :periapsis 42656
                          :mass 7.5e+15
                          :surface_area 132.8595  
                          :satelites nil
                          :circumference 40.8603
                          :form "moon"
                          :orbital_period 3.24
                          :parent "Pluto"
                          :vis true}
                  "Europa" {:volume 1.593e+10
                          :apoapsis 676938
                          :periapsis 664862
                          :mass 4.799844e+22
                          :surface_area   3.09e+7
                          :satelites nil
                          :circumference 9806.8
                          :form "moon"
                          :orbital_period 3.551
                          :parent "Jupiter"
                          :vis true}
                  "Io" {:volume 2.53e+10 
                        :apoapsis 423400
                        :periapsis 420000
                        :mass 8.931938e+22
                        :surface_area 41910000
                        :satelites nil
                        :circumference 11445.5
                        :form "moon"
                        :orbital_period 1.769137786
                        :parent "Jupiter"
                        :vis true}
                  "Ganemede" {:volume 76304506998  
                              :apoapsis 1071600
                              :periapsis 1069200
                              :mass 148185846875052000000000 
                              :surface_area 86999665.93
                              :satelites nil
                              :circumference 16532.3
                              :form "moon"
                              :orbital_period 7.155 
                              :parent "Jupiter"
                              :vis true}
                  "Callisto" {:volume   5.9e+10 
                              :apoapsis 1897000
                              :periapsis 1869000 
                              :mass 1.075938e+23
                              :surface_area 7.30e+7
                              :satelites nil
                              :circumference 15144.4
                              :form "moon"
                              :orbital_period 16.6890184
                              :parent "Jupiter"
                              :vis true}
                  "Titan" {:volume 71496320086
                           :apoapsis 1221930
                           :periapsis  1221930
                           :mass 134552523083241000000000
                           :surface_area 83305418.53
                           :satelites nil
                           :circumference 16177.5 
                           :form "moon"
                           :orbital_period 15.94542
                           :parent "Saturn"
                           :vis true}
                  "Enceladus" {:volume 67113076
                               :apoapsis 237950
                               :periapsis  237950
                               :mass 1.08022e+20
                               :surface_area 798648.27
                               :satelites nil
                               :circumference 1584
                               :form "moon"
                               :orbital_period 1.370218 
                               :parent "Saturn"
                               :vis true}
                  "Tethys" {:volume 634264255   
                           :apoapsis 294619
                           :periapsis  294619
                           :mass 617551805221061000000
                           :surface_area 3569967.66
                           :satelites nil
                           :circumference 3348.9  
                           :form "moon"
                           :orbital_period 1.887802
                           :parent "Saturn"
                            :vis true}
                  "Mimas" {:volume 32600000
                           :apoapsis 185404
                           :periapsis 185404
                           :mass 3.7493e+19
                           :surface_area 490000
                           :satelites nil
                           :circumference 1245.3
                           :form "moon"
                           :orbital_period 0.942422
                           :parent "Saturn"
                           :vis true}
                  "Dione" {:volume 742338322   
                           :apoapsis 377396
                           :periapsis  377396
                           :mass 1095745430185280000000
                           :surface_area 3964776.51
                           :satelites nil
                           :circumference 3529.3  
                           :form "moon"
                           :orbital_period 2.736915
                           :parent "Saturn"
                           :vis true}
                  "Rhea" {:volume 1870166133    
                           :apoapsis 527108
                           :periapsis  527108
                           :mass 2307089151289080000000
                           :surface_area 7340701.82 
                           :satelites nil
                           :circumference 4802.2
                           :form "moon"
                           :orbital_period 4.518212
                           :parent "Saturn"
                          :vis true}
                  "Lapetus" {:volume 1667300080
                           :apoapsis 3560820
                           :periapsis  3560820
                           :mass 1.805635e+21
                           :surface_area 6700000
                           :satelites nil
                           :circumference 4621.9
                           :form "moon"
                           :orbital_period 79.3215
                           :parent "Saturn"
                             :vis true}
                  "Pan" {:volume 22402.016
                         :apoapsis 133584
                         :periapsis  133584
                         :mass 4.95e+15
                         :surface_area 960.76
                         :satelites nil
                         :circumference  109.8785
                         :form "moon"
                         :orbital_period 0.57505
                         :parent "Saturn"
                         :vis true}
                  "Ariel" {:volume 812600000
                           :apoapsis 191020
                           :periapsis 191020
                           :mass 1353e+18
                           :surface_area 4211300
                           :satelites nil
                           :circumference 7274.67
                           :form "moon"
                           :orbital_period 2.520379
                           :parent "Uranus"
                           :vis true}
                  "Umbriel" {:volume 837300000
                             :apoapsis 266300
                             :periapsis 266300
                             :mass 1172e+18
                             :surface_area 4296000
                             :satelites nil
                             :circumference 7347.46
                             :form "moon"
                             :orbital_period 4.144177
                             :parent "Uranus"
                             :vis true}
                  "Titania" {:volume 2065000000
                             :apoapsis 435910
                             :periapsis 435910
                             :mass 3527e+18
                             :surface_area 7820000
                             :satelites nil
                             :circumference  9913.07
                             :form "moon"
                             :orbital_period 8.705872
                             :parent "Uranus"
                             :vis true}
                  "Oberon" {:volume 1849000000
                            :apoapsis 583520
                            :periapsis 583520
                            :mass 3014e+18
                            :surface_area 7285000
                            :satelites nil
                            :circumference 9567.97
                            :form "moon"
                            :orbital_period 13.463239
                            :parent "Uranus"
                            :vis true}
                  "Puck" {:volume 2225000
                          :apoapsis 86010
                          :periapsis 86010
                          :mass 2.9e+18
                          :surface_area 82400  
                          :satelites nil
                          :circumference 1017.58
                          :form "moon"
                          :orbital_period 0.761833
                          :parent "Uranus"
                          :vis true}
                  "Miranda" {:volume 54918670 
                             :apoapsis 129390	
                             :periapsis 129390	
                             :mass 65.9e+18
                             :surface_area 698710.82 
                             :satelites nil
                             :circumference 1481.6
                             :form "moon"
                             :orbital_period 1.413479
                             :parent "Uranus"
                             :vis true}
                  "Nereid" {:volume
                            20579526
                            :apoapsis  1372000                 
                            :periapsis 9655000
                            :mass 2700e+16
                            :surface_area 363168.11  
                            :satelites nil
                            :circumference 1068.1
                            :form "moon"
                            :orbital_period 360.1362
                            :parent "Neptune"
                            :vis true}
                  "Triton" {:volume 10384058491
                            :apoapsis 354759
                            :periapsis 354759
                            :mass 2140800e+16
                            :surface_area 23017714.99  
                            :satelites nil
                            :circumference 8503.7
                            :form "moon"
                            :orbital_period 5.877
                            :parent "Neptune"
                            :vis true}
                  "Proteus" {:volume 3.4e+7
                             :apoapsis 117709
                            :periapsis 117584
                            :mass 4.4e+19
                            :surface_area 554176.94
                            :satelites nil
                            :circumference 1319.5
                            :form "moon"
                             :orbital_period 1.12231477
                            :parent "Neptune"
                            :vis true}
                  
                  }
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

