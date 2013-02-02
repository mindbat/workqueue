(ns workqueue.views.common
  (:use [hiccup core page]))

(defn dashboard
  []
  (html5
   [:head [:title "Dashboard"]]
   [:body
    [:h1 "Dashboard"]]))