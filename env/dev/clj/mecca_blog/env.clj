(ns mecca-blog.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [mecca-blog.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[mecca-blog started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[mecca-blog has shut down successfully]=-"))
   :middleware wrap-dev})
