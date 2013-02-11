(ns workqueue.server
  (:use compojure.core
        workqueue.api.task
        workqueue.api.login
        workqueue.models.task
        workqueue.views.common
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [monger.core :as mg]
            [noir.session :as session]))

(def mongo-host (get (System/getenv)
                     "MONGOLAB_URI" "mongodb://127.0.0.1/workqueue"))

(mg/connect-via-uri! mongo-host)

(defroutes app-routes
  (GET ["/queue/:user/task/:id", :user #"[a-zA-Z0-9]+" :id #"[0-9]+"] [user id] (handle-get-task user id))
  (GET ["/queue/:user", :user #"[a-zA-Z0-9]+"] [user] (handle-get-queue user))
  (POST "/task" [user task] (handle-add-task user task))
  (PUT "/task" [user id task] (handle-update-task user id task))
  (DELETE "/task" [user id] (handle-delete-task user id))
  (GET "/login" [] (display-login))
  (POST "/login" [username password] (handle-user-login username password))
  (GET "/" [] (dashboard (session/get :user)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
     (-> (handler/site app-routes)
         (wrap-base-url)
         (session/wrap-noir-session)))
