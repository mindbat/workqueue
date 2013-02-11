(ns workqueue.models.task
  (:use monger.operators)
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.result :as mr])
  (:import com.mongodb.WriteConcern [org.bson.types ObjectId]))

(defn get-task-count
  "Get the number of tasks in the user's queue"
  [username]
  (mc/count "tasks" {:username username}))

(defn generate-task-id
  "Get the next highest task id for the user"
  [username]
  (let [task-count (get-task-count username)]
    (+ task-count 1)))

(defn add-task
  "Add a task to the user's queue"
  [username text]
  (let [task {:username username :task text :task_id (generate-task-id username) :_id (ObjectId.)}]
   (mc/insert-and-return "tasks" task)))

(defn delete-task
  "Remove a task from the user's queue"
  [username task-id]
  (mr/ok? (mc/remove "tasks" {:task_id task-id :username username})))

(defn get-task
  "Fetch a single task from the user's queue"
  [username task-id]
  (dissoc (mc/find-one-as-map "tasks" {:task_id task-id :username username}) :_id))

(defn get-queue
  "Fetch all the user's tasks"
  [username]
  (map #(dissoc % :_id) (mc/find-maps "tasks" {:username username})))

(defn update-task
  "Update a given task"
  [username task-id text]
  (mr/ok? (mc/update "tasks" {:username username :task_id task-id} {$set {:task text}})))