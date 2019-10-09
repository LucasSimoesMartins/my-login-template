# My login template

<img src="https://user-images.githubusercontent.com/25203907/66509971-792f6580-eaaa-11e9-97aa-73c1aaaeac7b.png"/>

## About this project

It's my custom login template with firebase integration.

The idea is to create a login template for use in future projects.

## Why?

This project is part of my personal portfolio. I will be happy if you give me some feedback. I want to become a better developer!

Connect with me on [LinkedIn](https://br.linkedin.com/in/lucassimoesmartins).

You can use this Project as you wish, it's free!

## Observations about this App

There's only login, sign up and logout funcionality, so an empty activity with logout button is shown after authentication.

## Functionalities

- Firebase login, sign up and logout with:
  - Email/password
  - Google account
  - Facebook account

- Email verification in email/password authentication

- Password reset in email/password authentication
  
- Firebase Crashlytics

- Firebase Analytics

- Firebase Performance

## Getting Started

### Prerequisites

To run this project, you need to have a firebase account and create a project. More details [here](https://firebase.google.com/docs/android/setup?#create-firebase-project).

You need to have a facebook developer account. More details [here](https://developers.facebook.com).

### Installing

**Cloning the Repository**

```
$ git clone https://github.com/LucasSimoesMartins/my-login-template.git
```

**Adding google-services.json file**

Get your google-services.json and place it in the "app" folder.

<img src="https://user-images.githubusercontent.com/25203907/66263710-aab7e080-e7cd-11e9-8e49-6b408bb01ba3.png"/>

More details [here](https://developers.google.com/android/guides/google-services-plugin#adding_the_json_file).


**Setting up facebook authentication**

Facebook documentation [here](https://developers.facebook.com/docs/facebook-login/android).

- First, create a new app.

<img src="https://user-images.githubusercontent.com/25203907/66506354-9280e380-eaa3-11e9-935f-54bae21e9eac.png"/>

- Later, open the file app/res/values/credentials.xml and enter the "facebook_app_id" and "fb_login_protocol_scheme" that was given in fourth step of facebook documentation.

<img src="https://user-images.githubusercontent.com/25203907/66506552-eee40300-eaa3-11e9-9cd7-f2bd81d29389.png"/>
<img src="https://user-images.githubusercontent.com/25203907/66506972-b690f480-eaa4-11e9-8cdb-3f8366155ca5.png"/>

- Finally, follow the fifth and sixth steps to enable the Facebook authentication.

<img src="https://user-images.githubusercontent.com/25203907/66507320-61a1ae00-eaa5-11e9-95a2-1214cd66d7a9.png"/>
