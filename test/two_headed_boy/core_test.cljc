(ns two-headed-boy.core-test
  (:require [two-headed-boy.core :refer [to-json string->base64 read-file]]
            #?@(:clj  [[clojure.test :refer :all]]
                :cljs [[cljs.test :refer-macros [deftest is testing]]])))

(deftest to-json-test
  (is (= "{\"foo\":\"bar\"}" (to-json {:foo "bar"}))))

(deftest string->base64-test
  (is (= "Zm9vYmFy" (string->base64 "foobar"))))

(deftest read-file-test
  (is (= {:foo "bar"} (read-file "test/edn/data.edn"))))
