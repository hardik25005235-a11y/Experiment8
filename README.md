# Experiment 8 – Implement Menus and WebView in Android Application with Side-by-Side GridView

## 📱 Experiment Title

**Implement Menus and WebView in an Android application with Side-by-Side GridView**

---

## 🎯 Objective

The objective of this experiment is to develop an Android application that demonstrates:

* Options Menu in Android
* GridView for displaying items
* Side-by-side GridView layout
* WebView for displaying web pages inside the application
* Handling click events on GridView items
* Loading different websites using WebView
* Using Internet permission in an Android application

---

## 📝 Introduction

Android provides several UI components that help developers create interactive applications.

In this experiment, we implement three important Android components:

### 1. Menu

A menu provides different options to the user. In this application, an Options Menu is created with:

* Home
* Google
* About

The user can select an option from the menu to perform a particular action.

### 2. GridView

GridView is used to display items in a grid format.

In this application, the GridView displays four websites:

* Google
* YouTube
* GitHub
* Wikipedia

The GridView is configured to display **two items in each row**, creating a side-by-side layout.

### 3. WebView

WebView is an Android component that allows web pages to be displayed directly inside an Android application.

When the user clicks an item in the GridView, the corresponding website is loaded inside the WebView.

---

# 🛠️ Technologies Used

| Technology           | Purpose                         |
| -------------------- | ------------------------------- |
| Android Studio       | Development Environment         |
| Java                 | Programming Language            |
| XML                  | User Interface Design           |
| GridView             | Display items in grid format    |
| WebView              | Display websites inside the app |
| Android Options Menu | Application menu                |
| Android SDK          | Application development         |

---

# 💻 Requirements

The following software is required:

* Android Studio
* Android SDK
* Java
* Android Emulator or Android smartphone
* Internet connection

---

# 📂 Project Structure

```text
Experiment8/
│
├── app/
│   └── src/
│       └── main/
│           │
│           ├── java/
│           │   └── com.example.experiment8/
│           │       └── MainActivity.java
│           │
│           ├── res/
│           │   └── layout/
│           │       ├── activity_main.xml
│           │       └── grid_item.xml
│           │
│           └── AndroidManifest.xml
│
└── README.md
```

---

# 🏗️ Application Design

The application contains the following main components:

```text
                    Android Application
                           │
             ┌─────────────┴─────────────┐
             │                           │
          Options                     GridView
           Menu                          │
             │                  ┌────────┴────────┐
       ┌─────┼─────┐            │                 │
       │     │     │         Google            YouTube
      Home Google About
                                  │                 │
                             ┌────┴────┐       ┌────┴────┐
                             │         │
                           GitHub   Wikipedia
                                  │
                                  ▼
                               WebView
                                  │
                                  ▼
                         Website displayed
                         inside the application
```

---

# 🔐 Step 1 – Add Internet Permission

The application needs Internet access to load websites using WebView.

Open:

```text
app → manifests → AndroidManifest.xml
```

Add the following permission before the `<application>` tag:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Example:

```xml
<?xml version="1.0" encoding="utf-8"?>

<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:label="@string/app_name"
        android:theme="@style/Theme.Experiment8">

        ...

    </application>

</manifest>
```

### Purpose

The `INTERNET` permission allows the application to access web pages through the Internet.

---

# 🎨 Step 2 – Create the Main Layout

The main layout contains:

* TextView
* GridView
* WebView

File:

```text
res/layout/activity_main.xml
```

### Code

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Experiment 8 - Menus and WebView"
        android:textSize="22sp"
        android:textStyle="bold"
        android:gravity="center"
        android:padding="16dp" />

    <GridView
        android:id="@+id/gridView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:numColumns="2"
        android:verticalSpacing="10dp"
        android:horizontalSpacing="10dp"
        android:padding="10dp"
        android:gravity="center" />

    <WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

</LinearLayout>
```

---

# 📊 Side-by-Side GridView

The GridView uses:

```xml
android:numColumns="2"
```

This specifies that two items should be displayed in each row.

The result is:

```text
┌──────────────┬──────────────┐
│    Google    │   YouTube    │
├──────────────┼──────────────┤
│    GitHub    │  Wikipedia   │
└──────────────┴──────────────┘
```

This creates the required **side-by-side GridView**.

---

# 🧩 Step 3 – Create Grid Item Layout

Create:

```text
res/layout/grid_item.xml
```

This layout controls the appearance of each GridView item.

### Code

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="100dp"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="10dp">

    <TextView
        android:id="@+id/gridText"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="Google"
        android:textSize="18sp"
        android:textStyle="bold"
        android:gravity="center" />

</LinearLayout>
```

---

# 👨‍💻 Step 4 – MainActivity.java

The Java file contains the main application logic.

File:

```text
MainActivity.java
```

### Complete Code

```java
package com.example.experiment8;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    WebView webView;

    String[] websites = {
            "Google",
            "YouTube",
            "GitHub",
            "Wikipedia"
    };

    String[] urls = {
            "https://www.google.com",
            "https://www.youtube.com",
            "https://github.com",
            "https://www.wikipedia.org"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        webView = findViewById(R.id.webView);

        // Enable JavaScript in WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Open websites inside WebView
        webView.setWebViewClient(new WebViewClient());

        // Display websites in GridView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                websites
        );

        gridView.setAdapter(adapter);

        // Handle GridView item click
        gridView.setOnItemClickListener((parent, view, position, id) -> {

            webView.loadUrl(urls[position]);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Home");
        menu.add("Google");
        menu.add("About");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title = item.getTit
```
