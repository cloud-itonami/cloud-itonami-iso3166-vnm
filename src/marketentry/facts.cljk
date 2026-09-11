(ns marketentry.facts "Vietnam market-entry catalog.")
(def catalog
  {"VNM" {:name "Vietnam"
          :owner-authority "MPI / National e-Procurement System (muasamcong)"
          :legal-basis "Law on Bidding"
          :national-spec "muasamcong e-GP + MST/enterprise code"
          :provenance "https://muasamcong.mpi.gov.vn/"
          :required-evidence ["MST/enterprise registration record" "e-GP registration record" "Business registration extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / MPI"
          :rep-legal-basis "Vietnamese legal entity (MST) typically required for e-GP participation"
          :rep-provenance "https://muasamcong.mpi.gov.vn/"
          :corporate-number-owner-authority "General Department of Taxation / Business Registration"
          :corporate-number-legal-basis "MST (tax code) / enterprise code"
          :corporate-number-provenance "https://www.gdt.gov.vn/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "THA" {:name "Thailand" :owner-authority "e-GP" :legal-basis "PPA" :national-spec "e-GP" :provenance "https://www.gprocurement.go.th/"
          :required-evidence ["DBD record" "e-GP registration" "DBD extract" "Authorized-representative record"]}
   "SGP" {:name "Singapore" :owner-authority "GeBIZ" :legal-basis "GFR" :national-spec "GeBIZ" :provenance "https://www.gebiz.gov.sg/"
          :required-evidence ["UEN record" "GeBIZ registration" "GST record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
