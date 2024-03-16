Feature: BlazeDemo Test

    Scenario: Booking a flight from Mexico City to Cairo
        When I navigate to 'https://blazedemo.com'
        And I choose my departure city 'Mexico City' and my destination city 'Cairo'
        And I click Find Flights button
        And Page should say 'Flights from Mexico City to Cairo'
        And I choose a flight and click the button
        And I fill in my name as 'Cristiano Ronaldo'
        And I fill in my city as 'Madeira City'
        And I click Purchase Flight button
        Then Page title should be 'BlazeDemo Confirmation'