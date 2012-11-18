(ns workqueue.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]
        hiccup.form-helpers))

(defpartial layout [& content]
            (html5
              [:head
               [:title "workqueue"]
               (include-css "/css/reset.css")]
              [:body
               [:h1 "WorkQueue"]
               [:div#wrapper
                content]]))

(defpartial task-fields []
  (label "description" "Task: ")
  (text-field "description")
  (label "project" "Project: ")
  (text-field "project"))

(defpartial single-task [task]
  [:li (:description task)])

(defpartial list-tasks [task-list]
  [:ul (map single-task task-list)])