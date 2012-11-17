(ns workqueue.views.dashboard
  (:require [workqueue.views.common :as common]
            [workqueue.models.mongo :as mongo])
  (:use [noir.core]
        [hiccup.core :only [html]]
        hiccup.form-helpers))

(defpage "/dashboard" []
  (common/layout
   [:ul [:li "First Task"] [:li "second task"]]
   (form-to [:post "/task/add"]
            (common/task-fields)
            (submit-button "Add Task"))))

(defpage "/error" []
  (common/layout
   [:p "We're sorry, there seems to have been an error"]))

(defpage [:post "/task/add"] {:as task}
  (if (mongo/save-task task)
    (render "/dashboard")
    (render "/error")))