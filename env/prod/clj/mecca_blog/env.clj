(ns mecca-blog.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[mecca-blog started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[mecca-blog has shut down successfully]=-"))
   :middleware identity})
