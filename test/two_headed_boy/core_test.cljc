(ns two-headed-boy.core-test
  (:require [two-headed-boy.core
             :refer [to-json string->base64 read-file
                     test-async]]
            #?@(:clj  [[clojure.test :refer :all]
                       [clojure.core.async :refer [go chan <! >!]]]
                :cljs [[cljs.test :refer-macros [deftest is]]
                       [cljs.core.async :refer [chan <! >!]]]))
  #?(:cljs (:require-macros [cljs.core.async.macros :refer [go]])))

(deftest to-json-test
  (is (= "{\"foo\":\"bar\"}" (to-json {:foo "bar"}))))

(deftest string->base64-test
  (is (= "Zm9vYmFy" (string->base64 "foobar"))))

(deftest read-file-test
  (is (= {:foo "bar"} (read-file "test/edn/data.edn"))))

(deftest test-async-test
  (let [ch (chan)]
    (go (>! ch "foo"))
    (test-async
      (go (is (= "foo" (<! ch)))))))
