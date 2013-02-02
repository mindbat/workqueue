(ns workqueue.views.common
  (:use [hiccup core page]))

(defn display-task
  "Display for a single task"
  [task]
  [:article [:p (:task task)]])

(defn layout
  [title content]
  (html5
   [:head [:title "WorkQueue"]]
   [:body
    [:h1 title]
    content]))

(defn dashboard
  [tasks]
  (layout "Dashboard" (map display-task tasks)))
