(ns workqueue.views.common
  (:use [hiccup core page form]))

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

(defn dashboard
  "Display the user dashboard"
  [tasks]
  (layout "Dashboard" (map display-task tasks)))

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
