(ns culture.facts
  "Country-level regional-culture catalog for Vietnam (VNM) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).
  Vietnamese-language name included via :culture/name-local where it
  differs from the common English name.

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"VNM"
   [{:culture/id "vnm.dish.pho"
     :culture/name "Pho"
     :culture/name-local "Phở"
     :culture/country "VNM"
     :culture/kind :dish
     :culture/summary "Vietnamese noodle soup of broth, rice noodles, herbs and meat (usually beef, sometimes chicken), originating in early 20th-century Northern Vietnam."
     :culture/url "https://en.wikipedia.org/wiki/Pho"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.dish.banh-mi"
     :culture/name "Banh mi"
     :culture/name-local "Bánh mì"
     :culture/country "VNM"
     :culture/kind :dish
     :culture/summary "Short baguette sandwich with a thin, crisp crust that developed as a distinctly Vietnamese style in Saigon during the 1950s, blending French colonial bread with local Vietnamese fillings."
     :culture/url "https://en.wikipedia.org/wiki/B%C3%A1nh_m%C3%AC"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.dish.banh-chung"
     :culture/name "Banh chung"
     :culture/name-local "Bánh chưng"
     :culture/country "VNM"
     :culture/kind :dish
     :culture/summary "Traditional Vietnamese dish of glutinous rice, mung beans and pork, considered an essential element of the family altar during Tết."
     :culture/url "https://en.wikipedia.org/wiki/B%C3%A1nh_ch%C6%B0ng"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.beverage.ca-phe-sua-da"
     :culture/name "Vietnamese iced coffee"
     :culture/name-local "Cà phê sữa đá"
     :culture/country "VNM"
     :culture/kind :beverage
     :culture/summary "Coffee brewed with or served over ice and sweetened condensed milk (rather than fresh milk, for tropical-climate storage reasons), the preferred style established through long practice in Vietnam."
     :culture/url "https://en.wikipedia.org/wiki/Coffee_production_in_Vietnam"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.product.nuoc-mam"
     :culture/name "Vietnamese fish sauce"
     :culture/name-local "Nước mắm"
     :culture/country "VNM"
     :culture/kind :product
     :culture/summary "Fish sauce made from anchovies, mackerel, scabbard fish and salt without additives, commercialized in Vietnam from 1693 in Phan Thiết; Vietnam produced nearly 380 million liters in 2020 across 783 production facilities."
     :culture/url "https://en.wikipedia.org/wiki/Fish_sauce"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.craft.ao-dai"
     :culture/name "Ao dai"
     :culture/name-local "Áo dài"
     :culture/country "VNM"
     :culture/kind :craft
     :culture/summary "Traditional Vietnamese outfit consisting of a long split tunic worn over silk trousers, worn as formal wear and an important symbol of Vietnamese cultural identity."
     :culture/url "https://en.wikipedia.org/wiki/%C3%81o_d%C3%A0i"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.festival.tet"
     :culture/name "Tet"
     :culture/name-local "Tết"
     :culture/country "VNM"
     :culture/kind :festival
     :culture/summary "The most important celebration in Vietnamese culture, marking the arrival of spring on the lunar new year, typically falling between late January and February."
     :culture/url "https://en.wikipedia.org/wiki/T%E1%BA%BFt"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.heritage.ha-long-bay"
     :culture/name "Ha Long Bay"
     :culture/name-local "Hạ Long Bay"
     :culture/country "VNM"
     :culture/kind :heritage
     :culture/summary "Bay in northeastern Vietnam first listed as a UNESCO World Heritage Site in 1994 for its outstanding aesthetic value and limestone karst formations."
     :culture/url "https://en.wikipedia.org/wiki/H%E1%BA%A1_Long_Bay"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "vnm.heritage.hoi-an"
     :culture/name "Hoi An Old Town"
     :culture/name-local "Hội An"
     :culture/country "VNM"
     :culture/kind :heritage
     :culture/summary "Former Vietnamese trading post containing 1,360 ancient monuments, designated a UNESCO World Heritage Site on December 12, 1999."
     :culture/url "https://en.wikipedia.org/wiki/H%E1%BB%99i_An_Old_Town"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-vnm culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "VNM"))
                 " VNM entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
