# 🌤 Java Weather App

A real-time desktop weather application built with **Java Swing** + **OpenWeatherMap API**.

> **Made by Mohd Yusuf | BBD NIIT Lucknow**

---

## ✨ Features

- 🔍 **Search any city** worldwide
- 🌡️ **Live temperature** + feels like
- 💧 Humidity | 💨 Wind Speed | 🔵 Pressure | 👁 Visibility
- 🌅 Sunrise & 🌇 Sunset times
- 🎨 **Dark UI** with glassmorphism cards
- ⚡ **Auto-loads Lucknow** weather on startup
- 📦 No external frameworks — pure Java Swing

---

## 🖥️ Screenshots

> App launches with Lucknow weather automatically!

---

## 🛠️ Setup & Run

### Prerequisites
- Java 17+ installed
- Internet connection

### Step 1 — Download JSON library
```bash
curl -o json.jar https://repo1.maven.org/maven2/org/json/json/20240303/json-20240303.jar
```

### Step 2 — Compile
```bash
javac -cp .:json.jar WeatherApp.java
```

### Step 3 — Run
```bash
java -cp .:json.jar WeatherApp
```

> **Windows users:** Replace `:` with `;`
> ```cmd
> javac -cp .;json.jar WeatherApp.java
> java  -cp .;json.jar WeatherApp
> ```

---

## 🔑 API Key

This app uses [OpenWeatherMap](https://openweathermap.org/api) free tier API.

To use your own API key:
1. Sign up at [openweathermap.org](https://openweathermap.org)
2. Get a free API key
3. Replace `API_KEY` in `WeatherApp.java` line 18

---

## 📁 Project Structure

```
JavaWeatherApp/
├── WeatherApp.java    ← Main application
├── json.jar           ← JSON parsing library
└── README.md
```

---

## 🎨 Tech Stack

| Technology | Purpose |
|-----------|---------|
| Java Swing | Desktop GUI |
| OpenWeatherMap API | Live weather data |
| org.json | JSON parsing |
| SwingWorker | Async API calls |

---

## 👨‍💻 Author

**Mohd Yusuf**  
BBD NIIT Lucknow  
GitHub: [@yusufsheikh2716](https://github.com/yusufsheikh2716)

---

## 📄 License

MIT License — free to use and modify!
