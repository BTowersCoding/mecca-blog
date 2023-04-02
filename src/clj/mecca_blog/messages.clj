(ns mecca-blog.messages
  (:require
   [mecca-blog.db.core :as db]
   [mecca-blog.validation :refer [validate-message]]))

(defn message-list []
  {:messages (vec (db/get-messages))})

(defn save-message! [message]
  (if-let [errors (validate-message message)]
    (throw (ex-info "Message is invalid"
                    {:guestbook/error-id :validation
                     :errors errors}))
    (db/save-message! message)))
