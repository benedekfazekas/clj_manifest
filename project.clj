(defproject clj_manifest "0.1.0"
  :description "Reads the manifest file if running as ubjerjar"
  :url "https://github.com/benedekfazekas/clj_manifest.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :repositories {"snapshots" {:url "http://10.251.76.32:8081/nexus/content/repositories/snapshots"
                              :username "admin" :password "admin123"}
                 "releases" {:url "http://10.251.76.32:8081/nexus/content/repositories/releases"
                             :username "admin" :password "admin123" }
                 "thirdparty" {:url "http://10.251.76.32:8081/nexus/content/repositories/thirdparty"}})
