(ns workqueue.views.common
  (:use [hiccup core page]))

(defn display-task
  "Display for a single task"
  [task]
  [:article [:p (:task task)]])

(defn dashboard
  [tasks]
  (html5
   [:head [:title "Dashboard"]]
   [:body
    [:h1 "Dashboard"]
    [:section {:id "tasks"}
     (map display-task tasks)]]))
