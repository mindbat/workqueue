(ns workqueue.api.task
  (:use workqueue.models.task
        workqueue.api.common))

(defn handle-add-task
  "Handle posting of task data to the server"
  [username text]
  (add-task username text))

(defn handle-delete-task
  "Handling post of delete to the server"
  [username task-id]
  (if-let [success (delete-task username (Integer/parseInt task-id))]
    (json-response {:success success})
    (json-response {:success false})))

(defn handle-update-task
  "Handle post of updated task data to the server"
  [username task-id text]
  (if-let [success (update-task username (Integer/parseInt task-id) text)]
    (json-response {:success success})
    (json-response {:success false})))

(defn handle-get-task
  "Fetch a single task for display"
  [username task-id]
  (json-response (get-task username (Integer/parseInt task-id))))

(defn handle-get-queue
  "Fetch a user's json queue for display"
  [username]
  (json-response (get-queue username)))

