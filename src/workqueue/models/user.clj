(ns workqueue.models.user
  (:use monger.operators)
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :as mr]
            [digest])
  (:import com.mongodb.WriteConcern [org.bson.types ObjectId]))

(defn hash-password
  "Hash the incoming password"
  [password]
  (digest/sha-256 password))

(defn create-user
  "Create a new user in db"
  [username password]
  (let [safe-pass (hash-password password)
        user {:username username :password safe-pass :_id (ObjectId.)}]
    (mc/insert-and-return "users" user)))

(defn user-valid?
  "Verify the username and password match the db"
  [username password]
  (let [safe-pass (hash-password password)]
    (mc/any? "users" {:username username :password safe-pass})))

(defn username-valid?
  "Check that the username is valid: not taken already, no forbidden characters, etc."
  [username]
  (not (mc/any? "users" {:username username})))

(defn update-password
  "Change the user's password"
  [username new-password]
  (let [safe-pass (hash-password new-password)]
    (mr/ok? (mc/update "users" {:username username} {$set {:password safe-pass}}))))
