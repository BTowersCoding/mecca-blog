(ns mecca-blog.routes.home
  (:require
   [mecca-blog.layout :as layout]
   [mecca-blog.messages :as msg]
   [mecca-blog.middleware :as middleware]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render
   request
   "home.html"))

(defn about-page [request]
  (layout/render request "about.html"))

(defn message-list [_]
  (response/ok (msg/message-list)))

(defn save-message! [{:keys [params]}]
  (try
    (msg/save-message! params)
    (response/ok {:status :ok})
    (catch Exception e
      (let [{id     :guestbook/error-id
             errors :errors} (ex-data e)]
        (case id
          :validation
          (response/bad-request {:errors errors})
          ;;else
          (response/internal-server-error
           {:errors {:server-error ["Failed to save message!"]}}))))))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/messages" {:get message-list}]
   ["/message" {:post save-message!}]
   ["/about" {:get about-page}]])

