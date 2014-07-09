(ns manifest.core
  (:require [clojure.java.io :as io]
            [clojure.walk :as walk])
  (:import [java.util.jar Manifest]
           [java.net URL]))

;; Note that the manifest you want will only be on the classpath if it lives in the uberjar

(defn- read-manifest [url]
  (->> url
       io/reader line-seq
       (remove empty?)
       (map #(clojure.string/split % #":\s*" 2))
       (into {})
       walk/keywordize-keys))

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
