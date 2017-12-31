Feature: Feature file for Put method

  Scenario: Post and then update the details
    Given Accept the content in Json Format
    And Content Type as JSON
    When I perform the Post request with details "Dell,8GB RAM,1TB Hard Drive,15 Inch Lcd,Latitude"
    Then Status code "200" should returned
    And Response should have integer Id
    When I perform the PUT request with id and Details "Dell,8GB RAM,1TB Hard Drive,15 Inch Lcd,Touch Pad,Latitude"
    Then Status code "200" should returned
    And Details should get updated
