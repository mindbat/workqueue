(ns workqueue.views.common
  (:use
   workqueue.models.task
   [hiccup core page form])
  (:require [noir.session :as session]))

(defn display-task
  "Display for a single task"
  [task]
  [:article [:p (:task task)]])

(defn layout
  "Common layout for pages"
  [title content]
  (html5
   [:head [:title "WorkQueue"]]
   [:body
    [:h1 title]
    content]))

(defn task-form
  "Form for adding a new task"
  [username]
  (form-to [:post "/task"]
           (hidden-field "user" username)
           (label "task" "Task Description")
           (text-field "task")
           (submit-button "Add Task")))

(defn dashboard
  "Display the user dashboard"
  [username]
  (let [task-list (map display-task (get-queue username))
        add-task-form (task-form username)]
    (layout "Dashboard" (conj task-list add-task-form))))

(defn login-form
  "Display the login form"
  []
  (form-to [:post "/login"]
           (label "username" "Username")
           (text-field "username")
           (label "password" "Password")
           (password-field "password")
           (submit-button "Login")))

(defn display-login
  "Display the login page"
  []
  (layout "Login" (login-form)))