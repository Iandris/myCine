## myCine

### Elevator Pitch

Ever loan a movie out to someone and can’t remember if they brought it back or even who had it? MyCine is an online inventory of your home movie collection where you can sort by title, year, director, etc. Share your library list with your friends so they know what movies you have. MyCine will help you loan those movies out to your friends and family just like RedBox or Netflix. You can set loan periods, and send them reminders via text when their movies are due back. 


### Problem Statement

Keeping track of your personal movie collection can be an exhausing process, with the complexity of multiple formats (dvd/bluray/etc) and alternate titles (extended cut, directors cut) and you are left with few options. Most people dont even bother and end up purchasing the same title multiple times or not at all. Many consumers will keep hand written logs or track titles in a spreadsheet format stored on their personal computer. If that record is lost due to hardware failure, or simply misplaced your knowledge of your collection could be gone with it. I'd like to build an online video collection repository that will allow you to quicky add/remove titles from your inventory, sort and search. In addition to just cataloging the titles, id like to incorporate an API to pull in movie data from IMDB to populate details like studio, actor, year, etc. You will even be able to rank your favorite titles and rent them out.

With online video rental services like Netflix the days of video rental stores have gone by the way-side. With the exception of stores like Family Video and kiosk businesses like RedBox the consumer can have a very limited selection of rental options. As a result many home video fans resort to borrowing movies from their friends and family relying on an unwritten agreement for loan period and returns. MyCine looks to help not only keep track of your personal movie collection, but allows you to rent them out and know exactly where they are and when they are due back. Send your renter(s) text message reminders when their titles are due back. They will even be able to log into the site and check their outstanding rentals.

Ever bought a movie and after watching it decided maybe it wasnt worth the $$?. With this site, check your friends library and see if anyone already owns it so you can rent before you waste your money on a bad title.

### Technology/Techniques

* Security/Authentication
	* Admin Role: access to all data, ability to read/add/change/archive
	* Users - 2 roles
		* LibraryOwner: build personal collection and add renters, can view current rented out titles
		* Renters: can view current rental titles and return dates.
* Database - MySQL
	* Store users / roles
	* Store video libraries (linked to LibraryOwner users)
	* Store active rentals (LibraryOwner, Renter, ReturnDate)
	* Store all communication details
	* Store all change logs
* WebService / API
	* text alert for renter (return title by date) Twilio https://www.twilio.com/docs/libraries/java 
	* IMDB web service for movie info (omdb API) http://www.omdbapi.com 
* BootStrap/JavaScript for style/animation
* Logging
	* Log all rental transactions and error messages using Log4J
* Site and DB hosted on AWS
* Unit Testing
	* Junit testing to cover all db transactions
	* Junit testing for all authentication functions
* Independent research topic (ideas)
	* Bootstrap
	* Spock (powered by Groovy?)

### Design

* Screen Designs
* Application Flow
* DB Design

### [Project Plan](https://github.com/Iandris/myCine/blob/master/ProjectPlan)

### [Time Log](https://github.com/Iandris/myCine/blob/master/TimeLog.md)
