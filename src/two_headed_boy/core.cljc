(ns two-headed-boy.core
  (:require #?@(:clj  [[cheshire.core :as che]
                       [clojure.data.codec.base64 :as b64]
                       [clojure.core.async :refer [<!!]]]
                :cljs [[cljs.nodejs :as nodejs]
                       [cljs.reader :refer [read-string]]
                       [goog.crypt.base64 :as b64]
                       [cljs.core.async :refer [take!]]
                       [cljs.test :as test]])))

(defn to-json
  [m]
  "Converts a data structure to a json string"
  #?(:clj  (che/generate-string m)
     :cljs (.stringify js/JSON (clj->js m))))

(defn string->base64
  [input]
  "Encodes an UTF-8 string into a base64 UTF-8 string"
  #?(:clj  (String. (b64/encode (.getBytes input)) "UTF-8")
     :cljs (b64/encodeString input)))

(defn read-file
  [path]
  "Reads and EDN data file from the file system"
  (-> path
      #?(:clj slurp
         :cljs (#(.readFileSync (nodejs/require "fs") % "UTF-8")))
      read-string))

;; via http://stackoverflow.com/a/30781278
(defn test-async
  "Asynchronous test awaiting ch to produce a value or close."
  [ch]
  #?(:clj (<!! ch)
     :cljs (test/async done (take! ch (fn [_] (done))))))
