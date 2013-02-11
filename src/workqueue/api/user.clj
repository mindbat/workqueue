(ns workqueue.api.user
  (:use workqueue.api.common))

(defn handle-add-user
  "Handle creation of new user"
  [username password])

(defn handle-get-user
  "Handle request for user profile info"
  [username])

(defn handle-update-user
  "Handle request to update user info"
  [username & args])

(defn handle-delete-user
  "Handle user deletion"
  [username])