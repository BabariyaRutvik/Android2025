# QuickBazaar 🛒✨

QuickBazaar is a modern Android e-commerce application integrated with an **AI-powered assistant** using the Google Gemini API. It offers a seamless shopping experience with real-time data synchronization via Firebase.

---

## 🚀 Features

- **AI Chat Assistant**: Integrated Gemini AI to help users with product queries, cooking tips, and health advice.
- **User Authentication**: Secure Login and Signup using Firebase Authentication (including Google Sign-In support).
- **Product Catalog**: Browse products by categories with a clean and responsive UI.
- **Shopping Cart**: Add/remove items, manage quantities, and real-time price calculation.
- **Order Management**: Track order history and view order status (Pending/Delivered).
- **Firestore Integration**: All user data and AI chat history are securely stored in Firebase Firestore.
- **Push Notifications**: Integrated Firebase Cloud Messaging (FCM) for updates.
- **Smooth UI**: Powered by ViewBinding, Lottie Animations, and Material Design.

---

## 🛠 Tech Stack

- **Language**: Java
- **UI Framework**: XML, Material Design, ViewBinding
- **Backend**: Firebase (Auth, Firestore, Realtime Database, Cloud Messaging)
- **AI Integration**: Google Gemini API via Retrofit
- **Networking**: Retrofit 2, OkHttp 3, Gson
- **Image Loading**: Glide
- **Architecture**: MVVM (Model-View-ViewModel)

---

## 📦 Setup Instructions

To get this project running locally, follow these steps:

### 1. Clone the Repository
```bash
git clone [YOUR_REPOSITORY_LINK_HERE]
```

### 2. Add Firebase Configuration
- Go to the [Firebase Console](https://console.firebase.google.com/).
- Create a new project and add an Android App.
- Download the `google-services.json` file.
- Place the `google-services.json` file inside the `app/` directory of the project.

### 3. Configure Gemini API Key
For security reasons, the API key is not included in the repository.
- Obtain an API key from [Google AI Studio](https://aistudio.google.com/).
- Open the `local.properties` file in the root directory.
- Add your key at the end of the file:
```properties
GEMINI_API_KEY=your_actual_api_key_here
```

### 4. Build and Run
- Open the project in **Android Studio (Ladybug or newer)**.
- Sync Project with Gradle Files.
- Run the app on an Emulator or Physical Device.

---

## 🛡 Security Note

This project uses `BuildConfig` and `local.properties` to handle sensitive API keys. Ensure that `local.properties` and `google-services.json` are added to your `.gitignore` before making the repository public.

---

## 📸 Screenshots

| Home Screen | AI Chat | Product Details |
| :---: | :---: | :---: |
| _(Add Image)_ | _(Add Image)_ | _(Add Image)_ |

---

## 📄 License

Copyright © 2025 Rutvik Babariya.
Licensed under the [MIT License](LICENSE).
