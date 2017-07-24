# Heroku
Root context of production version is available at http://store-ksowa-production.herokuapp.com/store/
#
# API
Swagger-ui
- http://store-ksowa-production.herokuapp.com/store/swagger-ui.html

Notice that swagger mappings contains spring-boot-actuator endpoints.
#
The core domains of this task are:
- item
- receipt
So take a look at ItemController and ReceiptController.
#
# Continuous Integration & Continuous Delivery
- CI 
-- https://circleci.com/gh/antonina18/store
- CD 
-- https://hub.docker.com/r/antonina18/store/tags/ 
-- http://store-ksowa-production.herokuapp.com/
