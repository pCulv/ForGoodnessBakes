# For Goodness Bakes
Baking App for Udacity Android Developer Nanodegree Project 3

[![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=59889f38be66a7000136fa51&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/59889f38be66a7000136fa51/build/latest?branch=master)

## Project Overview
You will productionize an app, taking it from a functional state to a production-ready state. This will involve finding and handling error cases, adding accessibility features, allowing for localization, adding a widget, and adding a library.

## Screenshots
![artboard 1](https://user-images.githubusercontent.com/20853402/29040406-eea5c3ee-7b7c-11e7-84e7-5e7c02518fab.png)

![artboard 2](https://user-images.githubusercontent.com/20853402/29040409-f362cbf2-7b7c-11e7-92eb-270307ea1095.png)


## Why this Project?
As a working Android developer, you often have to create and implement apps where you are responsible for designing and planning the steps you need to take to create a production-ready app. Unlike Popular Movies where we gave you an implementation guide, it will be up to you to figure things out for the Baking App.

## What Will I Learn?
In this project you will:
* Use MediaPlayer/Exoplayer to display videos.
* Handle error cases in Android.
* Add a widget to your app experience.
* Leverage a third-party library in your app.
* Use Fragments to create a responsive design that works on phones and tablets.

## Rubric

### General App Usage
* App should display recipes from provided network resource.
* App should allow navigation between individual recipes and recipe steps.
* App uses RecyclerView and can handle recipe steps that include videos or images.
* App conforms to common standards found in the Android Nanodegree General Project Guidelines.

### Components and Libraries
* Application uses Master Detail Flow to display recipe steps and navigation between them.
* Application uses Exoplayer to display videos.
* Application properly initializes and releases video assets when appropriate.
* Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
* Application makes use of Espresso to test aspects of the UI.
* Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with Content Providers if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.

### Homescreen Widget
* Application has a companion homescreen widget.
* Widget displays ingredient list for desired recipe.
