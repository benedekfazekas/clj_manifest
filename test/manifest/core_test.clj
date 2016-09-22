(ns manifest.core-test
  (:require [clojure.test :refer [deftest run-tests is]]
            [manifest.core :as manifest]))

(def parsed-manifest {:Manifest-Version "1.0"
                      :Release-Version "1.1.0-long-version-name-first-continuation-second-continuation"
                      :Build-Timestamp "2016-09-20 16:42:23+0000"
                      :Built-By "A Clojure Developer"
                      :Created-By "Leiningen 2.6.1"
                      :Build-Jdk "1.8.0_92"
                      :Main-Class "clj_simple.app"})

(deftest read-manifest
  (is (= parsed-manifest (#'manifest/read-manifest "dev-resources/MANIFEST.MF"))))
