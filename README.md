# SIT708_2.1P

## Overview

This project is an Android Travel Companion App developed for SIT708 Task 2.1P. The app provides simple conversion tools that can be useful for travel-related situations.

The application includes three main conversion functions: currency conversion, fuel conversion, and temperature conversion. The main screen works as a navigation page, allowing users to open each conversion page separately.

## Features

- Main menu page for selecting conversion tools
- Currency conversion page
- Fuel conversion page
- Temperature conversion page
- User input using EditText
- Conversion result display using TextView
- Button click events for performing conversions
- Separate Activity for each conversion function
- Page navigation using Intent
- Simple and clear Android user interface

## Project Structure

```text
app/src/main/java/com.example.sit708_21p/

├── MainActivity.java
├── currency.java
├── fuel.java
└── temperature.java

app/src/main/res/layout/

├── activity_main.xml
│   └── Layout file for the main menu page.
│
├── activity_currency.xml
│   └── Layout file for the currency conversion page.
│
├── activity_fuel.xml
│   └── Layout file for the fuel conversion page.
│
└── activity_temperature.xml
    └── Layout file for the temperature conversion page.
