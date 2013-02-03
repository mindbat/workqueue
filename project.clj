(defproject workqueue "0.2.0"
            :description "A GTD approach to project management"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [compojure "1.1.5"]
                           [com.novemberain/monger "1.4.2"]
                           [ring "1.1.8"]
                           [digest "1.3.0"]
                           [cheshire "5.0.1"]]
            :plugins [[lein-ring "0.7.1"]]
            :ring {:handler workqueue.server/app})

