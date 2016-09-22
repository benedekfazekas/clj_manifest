(defproject clj_manifest "0.3.0-SNAPSHOT"
  :description "Reads the manifest file if running as ubjerjar"
  :url "https://github.com/benedekfazekas/clj_manifest.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:resource-paths ["dev-resources"]}}
  :dependencies [[org.clojure/clojure "1.8.0"]])
