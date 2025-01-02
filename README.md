# Record Shop Android
Android application that displays album data supplied by the Record Shop API. 

### Summary

This project was made with Java in Android Studio.

Information displayed for each album is: 
- name
- artist
- release year
- set of genres
- stock
- cover image (optional)
  

### Installation
To run this program locally, please fork and clone this repo to your local device. 

## Usage:
Before the application can display any information, [the record shop API](https://github.com/dsciocan/Record-Shop) needs to be running locally or deployed, and data must be added to the database.
This app can currently only be run from within Android Studio, using an Android device emulator. 

If the application is running correctly, it should display a scrollable list of albums, allowing the user to add, update and delete their information, and search for specific entries by name. 
To add a new album, press the + button at the bottom of the screen. Please note that the name, genre list, and artist fields cannot be left empty. 
To update an album, click on the specific entry you want to update, and press the Update button when ready. Please note that the entry cannot be saved if the name, genre list, and artist fields are left empty. 
To delete, an album, click on the specific entry you want to delete, then press the Delete button. 
To search for a specific album by name, press the search icon in the search bar at the top of the screen, then type in the name. 

