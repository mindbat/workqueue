(ns workqueue.views.welcome
  (:require [workqueue.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        hiccup.form-helpers))

(defpage "/dashboard" []
  (common/layout
   [:ul [:li "First Task"] [:li "second task"]]
   (form-to [:post "/task/add"]
            (common/task-fields)
            (submit-button "Add Task"))))