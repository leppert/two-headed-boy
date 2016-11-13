(ns two-headed-boy.test-runner
 (:require [doo.runner :refer-macros [doo-tests]]
           [two-headed-boy.core-test]
           [cljs.nodejs :as nodejs]))

(try
  (.install (nodejs/require "source-map-support"))
  (catch :default _))

(doo-tests
 'two-headed-boy.core-test)
