(defproject workqueue "0.2.0"
            :description "A GTD approach to project management"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [compojure "1.1.3"]
                           [lib-noir "0.2.0"]
                           [com.novemberain/monger "1.3.4"]]
            :plugins [[lein-ring "0.7.1"]]
            :ring {:handler workqueue.server/handler
                   :auto-reload? false})

