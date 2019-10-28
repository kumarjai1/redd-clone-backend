# redd-clone-backend

# A Reddit Clone

###### Jai Kumar and Qin Zhu collaborated on building a rest API resembling the app reddit.com. It was built using the Spring framework with Java Web Tokens for authentication.


## Technologies Used
* Spring
* Hibernate
* Junit
* User stories [Pivotal Tracker](https://www.pivotaltracker.com/n/projects/2407485)
* Draw.io for ERD diagram

## Approach & Planning
The app was planned and prepared using Pivotal Tracker for user stories and Draw.io for the ERD diagram. Once we had the rough layout and priority for our project deliverables we worked as a pair in implementing the features. Our approach was to start with user authentication and security. When that was completed we moved on to the posts, comments, and user profiles. We were able to complete most of our objectives and integrated our UI code with minor issues.

#### ERD Diagram
https://www.draw.io/?state=%7B%22ids%22:%5B%2211b3WR3GpF_MjeaouGBV_Qki_D_wijhGz%22%5D,%22action%22:%22open%22,%22userId%22:%22107295804539642791701%22%7D#G11b3WR3GpF_MjeaouGBV_Qki_D_wijhGz
<img src="/assets/ERDdiagram.png" width="500" />

#### UI Layer
<img src="/assets/createpost.png" width="500" />

## Demo and Test Coverage 
The integrated APIs with the frontend include - signup, login, create post, comment, delete comment, view all posts, view posts by user, view comments by user. The remaining APIs (create profile, view profile, delete post) can be seen working via Postman, eventhough they are not integrated fully. 

We covered on average 90% of the code outside config files. Our exceptionhandler package coverage shows 58.3%, because we didn't test the setters and getters in the ErrorResponse file. 

## Proud Of 
* We are proud of actually testing the exception package - it took lot of trial and error to see what we could and couldn't mock. 
* We are proud of figuring out how to associate user to the profile, post, and comment while ignoring the properties we don't need. So essentially proud of our ability to read stackoverflow better. 
* After lot of trial and error, we understood how cascade types work. 

## Challenges Faces
* Testing was rough, but it did help us understand the code we wrote better
* Entity relationships were a challenge. We had issue where we couldn't initially delete a post because of its relationship to comments.
* We were unable to integrate the profile feature and it is still something we have to resolve 

