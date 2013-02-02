(ns workqueue.api
  (:use workqueue.models.task
        cheshire.core))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (generate-string data)})

(defn handle-task-add
  "Handle posting of task data to the server"
  [user-id text]
  (add-task user-id text))

(defn handle-task-delete
  "Handling post of delete to the server"
  [user-id task-id]
  (delete-task user-id task-id))

(defn handle-task-update
  "Handle post of updated task data to the server"
  [user-id task-id text]
  (update-task user-id task-id text))

(defn handle-get-task
  "Fetch a single task for display"
  [user-id task-id]
  (json-response (get-task (Integer/parseInt user-id) (Integer/parseInt task-id))))

(defn handle-get-queue
  "Fetch a user's json queue for display"
  [user-id]
  (json-response (get-queue (Integer/parseInt user-id))))