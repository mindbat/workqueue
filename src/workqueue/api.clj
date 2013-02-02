(ns workqueue.api
  (:use workqueue.models.task
        cheshire.core))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (generate-string data)})

(defn handle-add-task
  "Handle posting of task data to the server"
  [user-id text]
  (add-task (Integer/parseInt user-id) text))

(defn handle-delete-task
  "Handling post of delete to the server"
  [user-id task-id]
  (if-let [success (delete-task (Integer/parseInt user-id) (Integer/parseInt task-id))]
    (json-response {:success success})
    (json-response {:success false})))

(defn handle-update-task
  "Handle post of updated task data to the server"
  [user-id task-id text]
  (if-let [success (update-task (Integer/parseInt user-id) (Integer/parseInt task-id) text)]
    (json-response {:success success})
    (json-response {:success false})))

(defn handle-get-task
  "Fetch a single task for display"
  [user-id task-id]
  (json-response (get-task (Integer/parseInt user-id) (Integer/parseInt task-id))))

(defn handle-get-queue
  "Fetch a user's json queue for display"
  [user-id]
  (json-response (get-queue (Integer/parseInt user-id))))