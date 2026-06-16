# 🌤 WeatherApp — Live Weather Web App

[![Live Demo](https://img.shields.io/badge/Live%20Demo-weather--app--dev--yusufsheikh2716.netlify.app-6366f1?style=for-the-badge&logo=netlify&logoColor=white)](https://weather-app-dev-yusufsheikh2716.netlify.app)
[![GitHub](https://img.shields.io/badge/GitHub-yusufsheikh2716-181717?style=for-the-badge&logo=github)](https://github.com/yusufsheikh2716/JavaWeatherApp)

> **Real-time weather app** — search any city, get live temperature, humidity, wind, sunrise & sunset.
>
> **Made by Mohd Yusuf | BBD NIIT Lucknow**

---

## 🌐 Live Demo

**➡️ [weather-app-dev-yusufsheikh2716.netlify.app](https://weather-app-dev-yusufsheikh2716.netlify.app)**

No install needed — works in any browser on desktop & mobile!

---

## ✨ Features

- 🔍 **Search any city** worldwide
- 🌡️ **Live temperature** + feels like
- 💧 Humidity | 💨 Wind Speed | 🔵 Pressure | 👁 Visibility
- 🌅 Sunrise & 🌇 Sunset times (local timezone)
- 🎨 **Dark glassmorphism UI** with twinkling star background
- ⚡ **Auto-loads Lucknow** weather on startup
- 📱 Fully **mobile responsive**
- ✨ Smooth card animations & hover effects

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| HTML / CSS / JavaScript | Web frontend |
| OpenWeatherMap API | Live weather data |
| Netlify | Hosting & deployment |
| Java Swing *(original)* | Desktop GUI version |

---

## 📁 Project Structure

```
JavaWeatherApp/
├── index.html         ← Web app (deployed on Netlify)
├── WeatherApp.java    ← Original Java Swing desktop app
├── json.jar           ← JSON library (for desktop version)
├── netlify.toml       ← Netlify deployment config
└── README.md
```

---

## 🖥️ Run the Desktop Version (Java Swing)

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
3. Replace `API_KEY` in `WeatherApp.java` (line 18) or `index.html` (top of `<script>`)

---

## 👨‍💻 Author

**Mohd Yusuf**  
BBD NIIT Lucknow  
GitHub: [@yusufsheikh2716](https://github.com/yusufsheikh2716)  
LinkedIn: [mohd-yusuf-sheikh](https://www.linkedin.com/in/mohd-yusuf-sheikh)

---

## 📄 License

MIT License — free to use and modify!
