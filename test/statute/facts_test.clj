(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest vnm-has-spec-basis
  (let [sb (facts/spec-basis "VNM")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["VNM" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["vnm.decree-13-2023-nd-cp-personal-data-protection"]
         (mapv :statute/id (facts/by-topic "VNM" :privacy))))
  (is (empty? (facts/by-topic "VNM" :labor)))
  (is (empty? (facts/by-topic "ATL" :privacy))))
