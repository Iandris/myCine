# Application Flow

## User Signup

1. User chooses sign up from landing page (either login, or create acct)
2. User completes registration form and submits
3. Request goes to registration servlet
4. servlet creates a user object and creates entry in database
5. response confirming successful registration

## User sign in
  1.  user chooses sign in from landing page
  2. user enters user_name and password and submit
  3. login servlet authenticates against db, success sends to home
  4. failure is returned to login page
  
## Home Page
  1. Page load sends request to newRelease servlet with criteria
  2. servlet uses criteria to select recent and upcoming titles from release schedule
  3. creates movie objects from select
  4. returns list of movie objects to servlet
  5. servlet returns list to jsp
  6. jsp displays new/upcoming releases
  
## My Library
  1. Page loads, sends request to library servlet with user criteria
  2. servlet uses username to select movie titles where owner = user
  3. servlet creates list of movie objects and returns to jsp
  4. jsp displays list ov movie objects (sorted alphabetically) & paged

## My Wishlist
  1. page loads, sends request to wishlist servlet with user criteria
  2. servlet uses username to select movie titles where owner = user
  3. servlet creates list of movie objects and returns to jsp
  4. jsp displays list of movie objects (sorted alphabetically) & paged
  
## Add Movie Form
  1. User enters movie title, year, price
  2. information is converted to a movie object, passed to addtoLibrary servlet
  3. servlet creates record in database based on movie object
  4. servlet returns success/failure to jsp
  5. jsp displays new movie information in library
  
## My Friends
  1. page loads, sends request to friends servlet with user criteria
  2. servlet uses username to select users from friends where user
  3. servlet returns list of user objects and returns to jsp
  4. jsp displays list of user/friend objects sorted alpha & paged
  
## Settings
  1. Contact information is editable
  2. rental defautls are editable
  
## Support
  1. Static page - FAQ
  2. Support Request form - Email to Admin
  
## Logout
  1. Ends user session after confirmation
  
