Feature: Login Functionality

  Scenario: Verify login with valid credentials
      Given  user navigates to login page
      When   user clicks on signup login button
      And    user enters username "Scrum2026@gmail.com" and password "Scrum@2026"
      And    user clicks on login button
      Then   user should see logout button