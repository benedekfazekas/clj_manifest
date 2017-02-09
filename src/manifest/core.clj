(ns manifest.core
  (:require [clojure.java.io :as io]
            [clojure.string]
            [clojure.walk :as walk])
  (:import [java.util.jar Manifest]
           [java.net URL]))

;; Note that the manifest you want will only be on the classpath if it lives in the uberjar

(defn- join-continuations [input]
  (let [input (vec input)
        continuation-indexes (keep-indexed #(when (= \space (first %2)) %1) input)
        spliced (reduce (fn [v i]
                          (update-in v [(dec i)] str
                                     (clojure.string/replace-first (get v i) " " "")))
                        input
                        (reverse continuation-indexes))
        continuation-indexes (set continuation-indexes)]
    (keep-indexed (fn [i v] (when-not (continuation-indexes i) v)) spliced)))

(defn- read-manifest [url]
  (with-open [r (io/reader url)]
    (->> r
         line-seq
         (remove empty?)
         join-continuations
         (map #(clojure.string/split % #":\s*" 2))
         (into {})
         walk/keywordize-keys)))

(defn manifest [main-class-name]
  (try
    (read-manifest
     (URL.
      (str "jar:"
           (.getLocation
            (.getCodeSource
             (.getProtectionDomain (Class/forName main-class-name))))
           "!/META-INF/MANIFEST.MF")))

    (catch Exception _
      {})))
