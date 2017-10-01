(ns compojure-api-with-spec.spec-routes-test
  (:require [clojure.test :refer :all]
            [clojure.edn :as edn]
            [cheshire.core :as json]
            [compojure-api-with-spec.handler :refer [app]]
            [peridot.core :refer :all]))

(deftest spec-routes-test
  (testing "validation"
    (testing "get"
      (testing "valid email address"
        (is (= 200 (-> (session app)
                       (request "/validation?email-address=foo@example.com")
                       :response
                       :status))))
      (testing "email address without domain"
        (is (= 400 (-> (session app)
                       (request "/validation?email-address=foo")
                       :response
                       :status))))
      (testing "email address without TLD"
        (is (= 400 (-> (session app)
                       (request "/validation?email-address=foo@example")
                       :response
                       :status))))
      (testing "email address with 1 character TLD, which is invalid."
        (is (= 400 (-> (session app)
                       (request "/validation?email-address=foo@example.c")
                       :response
                       :status))))
      (testing "without email-address"
        (is (= 400 (-> (session app)
                       (request "/validation")
                       :response
                       :status))))
      (testing "minus value for age"
        (is (= 400 (-> (session app)
                       (request "/validation?email-address=foo@example.com&current-age=-3")
                       :response
                       :status))))
      (testing "Age 0 is valid"
        (is (= 200 (-> (session app)
                       (request "/validation?email-address=foo@example.com&current-age=0")
                       :response
                       :status))))
      (testing "Age 150 is valid"
        (is (= 400 (-> (session app)
                       (request "/validation?email-address=foo@example.com&current-age=150")
                       :response
                       :status))))
      (testing "Age 48 is categorized as an even number"
        (is (= "あなたの年齢は偶数です"
               (-> (session app)
                   (header "Accept" "application/edn")
                   (request "/validation?email-address=foo@example.com&current-age=48")
                   :response
                   :body
                   slurp
                   edn/read-string
                   :comment))))
      (testing "Age 49 is categorized as an odd number"
        (is (= "あなたの年齢は奇数です"
               (-> (session app)
                   (header "Accept" "application/edn")
                   (request "/validation?email-address=foo@example.com&current-age=49")
                   :response
                   :body
                   slurp
                   edn/read-string
                   :comment)))))
    (testing "post"
      (testing "Accept valid request"
        (is (= "あなたの年齢は奇数です"
               (-> (session app)
                   (header "Accept" "application/edn")
                   (request "/validation"
                            :request-method :post
                            :body-params {:email "foo@example.com"
                                          :age 49})
                   :response
                   :body
                   slurp
                   edn/read-string
                   :comment))))
      (testing "Minus age is rejected"
        (is (= 400
               (-> (session app)
                   (header "Accept" "application/edn")
                   (request "/validation"
                            :request-method :post
                            :body-params {:email "foo@example.com"
                                          :age -49})
                   :response
                   :status))))
      (testing "Incomplete email addresss is rejected"
        (is (= 400
               (-> (session app)
                   (header "Accept" "application/edn")
                   (request "/validation"
                            :request-method :post
                            :body-params {:email "foo@example"
                                          :age 49})
                   :response
                   :status))))))

  (testing "coercion"
    (testing "get"
      (testing "amount-value is passed in as a string query parameter, and 
               conformed as a BigDecimal."
        (is (= 10.23M
               (-> (session app)
                   (header "Accept" "application/edn")
                   (request "/coercion?name=test&amount=10.23")
                   :response
                   :body
                   slurp
                   edn/read-string
                   :amount-value)))))

    (testing "post"
      (testing "amount is passed in as a BigDecimal in EDN, and it is accepted."
        (is (= 12.34M
               (-> (session app)
                   (header "Content-Type" "application/edn")
                   (header "Accept" "application/edn")
                   (request "/coercion"
                            :request-method :post
                            :body-params {:amount 12.34M})
                   :response
                   :body
                   slurp
                   edn/read-string
                   :amount))))

      (testing "amount is passed in as a BigDecimal in JSON, and it is accepted."
        (is (= 12.34
               (-> (session app)
                   (header "Content-Type" "application/json")
                   (header "Accept" "application/json")
                   (request "/coercion"
                            :request-method :post
                            :body-params {:amount 12.34M})
                   :response
                   :body
                   slurp
                   (json/parse-string true)
                   :amount))))

      (testing "Empty body is passed"
        (is (= 400
               (-> (session app)
                   (header "Content-Type" "application/json")
                   (header "Accept" "application/json")
                   (request "/coercion"
                            :request-method :post)
                   :response
                   :status)))))))
