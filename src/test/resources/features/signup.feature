Feature: Signup

   Scenario: Verify Signup
       Given user is on login page
       When  user clicks on signup login button
       And   user enters name "test" and email "testamb789@gmail.com"
       And   user clicks on signup button
       Then  user navigates to signup page
