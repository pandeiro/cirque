(ns cirque.app
  (:require-macros [cljs.core.async.macros :refer [go]]
                   [cirque.config :refer [resolve-config]])
  (:require [cljs.core.async :as async :refer [chan put! <! >!]]
            [reagent.core :as r :refer [render-component]]
            [weasel.repl :as repl]
            [cirque.util.xhr :as xhr :refer [get-edn post-edn! put-edn!]]))

; (repl/connect "ws://localhost:9001" :verbose true)

(def app-state (r/atom {:config (resolve-config)
                        :view   :main
                        :title  (str "Welcome to cirque")}))

(defn main-view [state-atom]
  [:div#main
   [:h1 (:title @state-atom)]])

(defn render-app []
  (let [dom-root (.getElementById js/document "root")]
    (render-component [main-view app-state] dom-root)))

(defn init
  "A single entrypoint for the application"
  []
  (render-app))

(.addEventListener js/window "DOMContentLoaded" init)

