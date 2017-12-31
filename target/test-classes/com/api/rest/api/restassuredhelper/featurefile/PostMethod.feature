Feature: Add a laptop detail to laptop-bag

  Scenario: Add the details
    Given Accept the content in Json Format
    And Content Type as JSON
    When I perform the Post request with BrandName as "Dell", Features as "8GB RAM,1TB Hard Drive", LaptopName as "Latitude"
    Then Status code "200" should returned
    And Response should have integer Id

 Scenario: Add the details with transform data
    Given Accept the content in Json Format
    And Content Type as JSON
    When I perform the Post request with details "Dell,8GB RAM,1TB Hard Drive,15 Inch Lcd,Latitude"
    Then Status code "200" should returned
    And Response should have integer Id

  Scenario: Post the details with invalid json body
    Given Accept the content in Json Format
    And Content Type as JSON
    But I supply invalid json body
    When I perform the Post request
    Then Status code "400" should returned
