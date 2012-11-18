(ns workqueue.models.mongo
  (:require [monger.core :as mcore]
            [monger.collection :as mcoll])
  (:import [org.bson.types ObjectId] [com.mongodb.WriteConcern]))

(def mongo-host {:host "127.0.0.1" :port 27017})

(defn save-task
  "Save a task to mongodb"
  [task]
  (let [task-doc (assoc task :_id (ObjectId.))]
    (mcore/connect! mongo-host)
    (mcore/set-db! (mcore/get-db "workqueue"))
    (mcoll/insert "tasks" task-doc)))

(defn get-tasks
  "Fetch a list of tasks from mongo"
  []
  (let [tasks-coll "tasks"]
    (mcore/connect! mongo-host)
    (mcore/set-db! (mcore/get-db "workqueue"))
    (mcoll/find-maps tasks-coll)))