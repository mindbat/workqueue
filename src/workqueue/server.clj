(ns workqueue.server
  (:use compojure.core
        workqueue.views.common
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes app-routes
  (GET "/" [] (dashboard))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
     (-> (handler/site app-routes)
         (wrap-base-url)))
