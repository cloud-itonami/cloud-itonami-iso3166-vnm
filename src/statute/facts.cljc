(ns statute.facts
  "General-law compliance catalog for Vietnam (VNM) -- extends this
  repo's existing `marketentry.facts` (narrow public-procurement
  scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken/-tha/-are's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Reuses this tick-window's already-verified capital/organization data
  from cloud-itonami-municipality-vnm-hanoi (Vietnam Q881, Hanoi
  Q1858, no P36 historical-capital bug despite Vietnam's Hanoi/Hue
  historical capital moves).

  Both entries directly confirmed via english.luatvietnam.vn (the
  same private legal-translation distributor of Vietnam's Official
  Gazette texts used successfully at tick 101 for Hanoi's Law on the
  Capital): Law on Enterprises (Law No. 59/2020/QH14) -- passed 17
  June 2020 by the XIVth National Assembly, promulgated via
  Presidential Order No. 06/2020/L-CTN 1 July 2020, effective 1
  January 2021; Decree No. 13/2023/ND-CP on Personal Data Protection
  -- issued by the Government 17 April 2023, effective 1 July 2023.
  The Decree is tagged :decree rather than :law, a new :kind value
  distinguishing Government decrees (Nghị định, issued by the
  executive branch) from Laws (Luật, passed by the National Assembly)
  in Vietnam's legal system -- Vietnam does not yet have a
  comprehensive National-Assembly-passed Law specifically on data
  protection, only this Decree.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"VNM"
   [{:statute/id "vnm.law-on-enterprises-59-2020-qh14"
     :statute/title "Law on Enterprises"
     :statute/jurisdiction "VNM"
     :statute/kind :law
     :statute/law-number "Law No. 59/2020/QH14"
     :statute/url "https://english.luatvietnam.vn/law-on-enterprises-no-59-2020-qh14-dated-june-17-2020-of-the-national-assembly-186272-doc1.html"
     :statute/url-provenance :luatvietnam-legal-translation-mirror
     :statute/enacted-date "2020-06-17"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "vnm.decree-13-2023-nd-cp-personal-data-protection"
     :statute/title "Decree on Personal Data Protection"
     :statute/jurisdiction "VNM"
     :statute/kind :decree
     :statute/law-number "Decree No. 13/2023/ND-CP"
     :statute/url "https://english.luatvietnam.vn/decree-no-13-2023-nd-cp-dated-april-17-2023-of-the-government-on-personal-data-protection-249791-doc1.html"
     :statute/url-provenance :luatvietnam-legal-translation-mirror
     :statute/enacted-date "2023-04-17"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:data-protection :privacy}}]})

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
      :note (str "cloud-itonami-iso3166-vnm statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "VNM")) " VNM statutes seeded with "
                 "luatvietnam.vn citations. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
