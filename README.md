# Deposit-Diary-Mobile-App-2
#
Functionality
#
The main purpose of the Deposit Diary Application is, to allow users who suffer from IBD/IBS to monitor their condition through allowing the user to enter information about how their days are by tracking the date, their motions, pain, type and durations and if the day was good or bad. The information will then be displayed on a card within the recycler view of the entries fragment that display all the information in a neat and clean layout. 
#
The application also features Firebase with such features implemented like different methods of authentication for users to sign into the application to have their own account, along with storage and Realtime database to store the user’s information that they input into their account. Other parts of the application feature implementation of the Google Maps API to display the current location of the user and markers of public toilets (Within the city of Waterford) along with two static fragments explain the Bristol Stool Chart, about the app and then a sign out option within the navigation drawer.
#
Login Activity – The login activity functions as the method of signing into the application through various methods such as Gmail, Email and Mobile depending on which you prefer. The purpose of this functionality is to allow the user to create an account so that each user can store their own individual data and keep it protected. This is done with use of implementing firebase authentication.
#
Home Activity – The home activity functions as the main access of the apps navigation through a Navigation drawer featuring multiple fragments such as the deposit, entries, map, types and about fragments. There is also a sign out action that allows you to sign out of their account. From using the helpers and login activity the user can also apply their name/email along with changing your profile picture.
#
Deposit Fragment – The deposit fragment allows the user to add deposits by inputting information off their condition into the app. The functions for these pieces of information are as follows there is a date which is to be preferably inserted as “AUG26” rather than “26-08-2020” as I found it to look cleaner in terms of layout for the card within the recycler view due to why I did not go with a date picker. There are then 4 number pickers which allow the user to scroll up and down and select a number. The numbers are different depending on the topic, for each of the four categories which are Types, Motions, Pains and Average Duration. After this then there is a radio button option to select weather the day was good or bad for the user. The deposit action button then sends this information and places the information inputted into the card within the recycler view in the entries fragment.
#
Entries Fragment – Within the entries fragment you can view the deposits the user has inserted and edit or delete them through a swipe feature. When you swipe right you can open the edits fragment which allows the user to edit their motions pain and average durations, as there is no need to update the type. These are all done through an EditText field and a note section where you can their information for certain things. For example if the user notices changes within your condition or if the user is on medication and want to track how the progression is going by adding notes about it through your days weather it is improving your condition, worsening or his having no effect this  can be useful for doctors to read.
#
Edit Fragment – The edit fragment allows the user to update their deposits and allow the user to also add any necessary notes if they realise a change within their condition. This can be activated once swiping left in the entries fragment to enter the edit fragment.
#
Maps Fragment – The maps fragment shows the users current location when using the Google maps api. The function of this is to allow users when they are away from their house to see free public toilets that they can access within their current location. This helps the user as if they are suffering through abdominal pains and nee to use a toilet but are not home and don’t want to have to buy an item within a shop to use a toilet, they can use the maps section of the application and then view a toilet that is close to them. Each toilet has a name above the marker naming the toilet, so the user knows exactly where to go.
Types Fragment – The types fragment functions as static information fragment which shows content on what each type of stool is from the Bristol Stool chart. Images where not added into the application as I thought it may look unpleasant to look at the stools as the information is enough to tell you what the type is.
#
About Fragment – The about fragment functions as a static fragment that allows the user to learn about the applications purpose, along with information about the creator of the application.
#
UX/DX Approach
#
The Deposit Diary is a simple and user-friendly application, which is straightforward to understand and use. The application features a dark theme layout to make it easier on the eyes and features a navigation drawer for ease of access with the applications content/features. 
#
The login page is simple and clean featuring a black background with the Deposit Diary logo, title, subtitle and displays 3 buttons to login with which are a standard form of email, mobile number or by Gmail which the user can choose from.
#
The Navigation of the application is done through a navigation drawer which has three sections diary, information and options for their own individual functions within the app. Above this then is the users name and email depending if they signed in with email. The user can also click on their profile picture and change it through firebase storage.
#
The Deposit fragment features a black and white layout, on the top left corner there is a title which displays “Diary” as it is the section for entering your information and a friendly underneath it displays “How Was Your Day?”. The date is displayed on the top right corner of the fragment where the user can enter the date preferably as AUG26 (MMM/DD) it suits the apps layout better rather than having a date picker that display dd-mm-yyyy. The centre of the deposit fragment displays four spinners with different numeric values depending on the section. The reason why I choose to go with number pickers over simple textfeilds is that it is cleaner and simpler to use for the user as they do not have to type in a number rather than scroll and select the number due to each answer would need a number, so a number picker was the most logical option to use for these sections. There is also a good and bad day radio button section below to decide whether the day the user had was good or bad this helps later for the user to highlight/notice changes within their condition.
#
The entries fragment for where the deposit goes once the deposit button is clicked has a sleak and simplistic card view which displays the date and day on the left-hand side with a white line barrier then separating the content of the users motions, pain, type and average duration on the right-hand side. The card view is easy on the user’s eyes and in the aid of a General practitioner or Specialist looking at the app as they can quickly scan to get an understanding at ho your condition is affecting you and in what areas. The user can then swipe right to edit their deposit or swipe left to delete their deposit with a simplistic swipe feature that is easy for the user to use.
#
Within the edit fragment of the application displays a header for the fragment, four text fields that allow the user to update their motions, pain and average duration, but not date, day or type as these fields shouldn’t need to be changed by the user. The final text field is a note section where the user can add a note which helps when they notice a change to why their days started going good or bad and can highlight these with information.
#
The maps fragment is taking and using the Google Maps API and displaying it with the user current location while also showing free public toilets around the user’s area which they can click on and then view where exactly the toilets are.
#
The Types fragment feature a simple title along the top of the fragment and beneath it then information detailing each of the seven types of stool within the Bristol stool chart clearly in big text This fragment is static.
#
The About fragment features a logo of the application along with information of the application and a profile pic of the creator with info to go with it. This fragment is static to show information purposes.
#
References
#
https://firebase.google.com/
#
https://tutors-design.netlify.app/labs/mobile-app-dev-2-2020.netlify.app/
#
https://www.youtube.com/watch?v=SqnzChkVyyM
#
https://stackoverflow.com/questions/22962075/change-the-text-color-of-numberpicker
#
https://stackoverflow.com/questions/24233556/changing-numberpicker-divider-color
#
https://stackoverflow.com/questions/17120199/change-circle-color-of-radio-button
#
https://developers.google.com/maps/documentation/android-sdk/map-with-marker
