(ns workqueue.models.task
  (:use monger.operators)
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import com.mongodb.WriteConcern [org.bson.types ObjectId]))

(defn get-task-count
  "Get the number of tasks in the user's queue"
  [user-id]
  (mc/count "tasks" {:user_id user-id}))

(defn generate-task-id
  "Get the next highest task id for the user"
  [user-id]
  (let [task-count (get-task-count user-id)]
    (+ task-count 1)))

(defn add-task
  "Add a task to the user's queue"
  [user-id text]
  (let [task {:user_id user-id :task text :task_id (generate-task-id user-id) :_id (ObjectId.)}]
   (mc/insert-and-return "tasks" task)))

(defn delete-task
  "Remove a task from the user's queue"
  [user-id task-id]
  (mc/remove "tasks" {:task_id task-id :user_id user-id}))

(defn get-task
  "Fetch a single task from the user's queue"
  [user-id task-id]
  (dissoc (mc/find-one-as-map "tasks" {:task_id task-id :user_id user-id}) :_id))

(defn get-queue
  "Fetch all the user's tasks"
  [user-id]
  (map #(dissoc % :_id) (mc/find-maps "tasks" {:user_id user-id})))

(defn update-task
  "Update a given task"
  [user-id task-id text]
  (mc/update "tasks" {:user_id user-id :task_id task-id} {$set {:task text}}))