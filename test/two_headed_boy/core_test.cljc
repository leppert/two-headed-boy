(ns two-headed-boy.core-test
  (:require [two-headed-boy :refer [read-file]]
            #?@(:clj  [[clojure.test :refer :all]]
                :cljs [[cljs.test :refer-macros [deftest is testing]]])))

(deftest read-file-test
  (is (= {:foo "bar"} (read-file "edn/data.edn"))))
