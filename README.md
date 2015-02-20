# wad-harkka
Repository for course http://www.cs.helsinki.fi/courses/582688/2012/k/k/1

This repository contains the code for a web application project done for a university course http://www.cs.helsinki.fi/courses/582688/2012/k/k/1. I chose to develop a collaborative music album rating site. The project was developed with the Spring framework and the user interface was developed with the Twitter Bootstrap library.

The application has the following main features:

The main page contains a list of all the albums that have been added to the system. The average of the scores given to each album is also listed on the main page.

* A new user can register an account on the site. The OpenID service is used to handle authentication, so the users can for example use their Google account credentials to sign up.
* A user who has logged in can add an album from the front page to his or her personal collection. 
* A logged in user can rate albums.
* A logged in user can add new albums to the system with a form.
* A user who has not logged in can view the front page and user profiles.
* A user can view an album's details on his or her own user page. The album's details will for example show who has added that album to their collection.
* A user can view an artist's page which will list all albums by that artist.
