(ns workqueue.api.login
  (:use workqueue.api.common
        workqueue.models.user)
  (:require [noir.session :as session]))

(defn handle-user-login
  "Verify and login a user"
  [username password]
  (if-let [verified (user-valid? username password)]
    (json-response (session/put! :user username))
    (json-response {:success false})))

(defn handle-user-logout
  "Logout user by destroying session"
  [username]
  (json-response (session/remove! :user)))
