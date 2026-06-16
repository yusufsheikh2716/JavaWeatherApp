import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import org.json.*;

/**
 * WeatherApp — Real-time Weather Application
 * Built with Java Swing + OpenWeatherMap API
 * Made by Mohd Yusuf | BBD NIIT Lucknow
 */
public class WeatherApp extends JFrame {

    // ── CONFIG ──────────────────────────────────────────────
    private static final String API_KEY  = "f57b50a1eddf9249d3be20d44dc900a5";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    // ── UI Components ────────────────────────────────────────
    private JTextField searchField;
    private JButton    searchBtn;
    private JLabel     cityLabel, countryLabel;
    private JLabel     tempLabel, feelsLikeLabel;
    private JLabel     descLabel, iconLabel;
    private JLabel     humidityLabel, windLabel, pressureLabel, visibilityLabel;
    private JLabel     sunriseLabel, sunsetLabel;
    private JPanel     mainPanel;

    // ── Colors ───────────────────────────────────────────────
    private final Color BG_DARK    = new Color(13, 13, 30);
    private final Color CARD_BG    = new Color(22, 22, 45);
    private final Color ACCENT     = new Color(99, 102, 241);
    private final Color TEXT_WHITE = new Color(241, 245, 249);
    private final Color TEXT_MUTED = new Color(148, 163, 184);
    private final Color SUCCESS    = new Color(34, 197, 94);

    public WeatherApp() {
        setTitle("WeatherApp — Made by Mohd Yusuf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        buildUI();
        setVisible(true);

        // Default city on launch
        fetchWeather("Lucknow");
    }

    private void buildUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(BG_DARK);

        // ── Header ──────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CARD_BG);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("🌤 WeatherApp");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(TEXT_WHITE);

        JLabel subtitle = new JLabel("Made by Mohd Yusuf | BBD NIIT");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        subtitle.setForeground(TEXT_MUTED);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(CARD_BG);
        titlePanel.add(title);
        titlePanel.add(subtitle);
        header.add(titlePanel, BorderLayout.WEST);

        // ── Search Bar ──────────────────────────────────────
        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
        searchPanel.setBackground(BG_DARK);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(16, 20, 8, 20));

        searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBackground(CARD_BG);
        searchField.setForeground(TEXT_WHITE);
        searchField.setCaretColor(TEXT_WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT, 1, true),
            BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        searchField.putClientProperty("JTextField.placeholderText", "Search city...");

        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        searchBtn.setBackground(ACCENT);
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);

        // ── Weather Display ──────────────────────────────────
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.Y_AXIS));
        weatherPanel.setBackground(BG_DARK);
        weatherPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // City Card
        JPanel cityCard = createCard();
        cityCard.setLayout(new GridLayout(3, 1, 0, 4));

        cityLabel    = createLabel("--", 32, Font.BOLD, TEXT_WHITE);
        countryLabel = createLabel("Search for a city", 14, Font.PLAIN, TEXT_MUTED);
        iconLabel    = createLabel("", 48, Font.PLAIN, TEXT_WHITE);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        cityCard.add(cityLabel);
        cityCard.add(countryLabel);

        // Temp Card
        JPanel tempCard = createCard();
        tempCard.setLayout(new GridLayout(3, 1, 0, 6));

        tempLabel      = createLabel("--°C", 56, Font.BOLD, TEXT_WHITE);
        feelsLikeLabel = createLabel("Feels like: --", 14, Font.PLAIN, TEXT_MUTED);
        descLabel      = createLabel("--", 16, Font.PLAIN, new Color(99, 102, 241));

        tempCard.add(tempLabel);
        tempCard.add(feelsLikeLabel);
        tempCard.add(descLabel);

        // Details Grid Card
        JPanel detailCard = createCard();
        detailCard.setLayout(new GridLayout(2, 2, 12, 12));

        humidityLabel   = createStatLabel("💧 Humidity", "--");
        windLabel       = createStatLabel("💨 Wind", "--");
        pressureLabel   = createStatLabel("🔵 Pressure", "--");
        visibilityLabel = createStatLabel("👁 Visibility", "--");

        detailCard.add(humidityLabel);
        detailCard.add(windLabel);
        detailCard.add(pressureLabel);
        detailCard.add(visibilityLabel);

        // Sun Card
        JPanel sunCard = createCard();
        sunCard.setLayout(new GridLayout(1, 2, 12, 0));

        sunriseLabel = createStatLabel("🌅 Sunrise", "--");
        sunsetLabel  = createStatLabel("🌇 Sunset", "--");

        sunCard.add(sunriseLabel);
        sunCard.add(sunsetLabel);

        weatherPanel.add(cityCard);
        weatherPanel.add(Box.createVerticalStrut(10));
        weatherPanel.add(tempCard);
        weatherPanel.add(Box.createVerticalStrut(10));
        weatherPanel.add(detailCard);
        weatherPanel.add(Box.createVerticalStrut(10));
        weatherPanel.add(sunCard);

        // Scroll
        JScrollPane scroll = new JScrollPane(weatherPanel);
        scroll.setBorder(null);
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_DARK);

        // Assemble
        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setBackground(BG_DARK);
        topSection.add(header, BorderLayout.NORTH);
        topSection.add(searchPanel, BorderLayout.CENTER);

        mainPanel.add(topSection, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);

        setContentPane(mainPanel);

        // ── Actions ─────────────────────────────────────────
        searchBtn.addActionListener(e -> search());
        searchField.addActionListener(e -> search());
    }

    private void search() {
        String city = searchField.getText().trim();
        if (!city.isEmpty()) fetchWeather(city);
    }

    private void fetchWeather(String city) {
        searchBtn.setText("...");
        searchBtn.setEnabled(false);

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                String encoded = URLEncoder.encode(city, "UTF-8");
                String url = BASE_URL + "?q=" + encoded + "&appid=" + API_KEY + "&units=metric";
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream()
                ));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);
                br.close();
                return sb.toString();
            }

            @Override
            protected void done() {
                try {
                    String json = get();
                    JSONObject obj = new JSONObject(json);

                    if (obj.has("cod") && obj.optInt("cod", 200) != 200 && !obj.optString("cod","200").equals("200")) {
                        showError("City not found! Try again.");
                        return;
                    }

                    updateUI(obj);
                } catch (Exception ex) {
                    showError("Error: " + ex.getMessage());
                } finally {
                    searchBtn.setText("Search");
                    searchBtn.setEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void updateUI(JSONObject data) {
        try {
            String name    = data.getString("name");
            String country = data.getJSONObject("sys").getString("country");
            double temp    = data.getJSONObject("main").getDouble("temp");
            double feels   = data.getJSONObject("main").getDouble("feels_like");
            int    hum     = data.getJSONObject("main").getInt("humidity");
            int    press   = data.getJSONObject("main").getInt("pressure");
            double wind    = data.getJSONObject("wind").getDouble("speed");
            int    vis     = data.optInt("visibility", 0);
            String desc    = data.getJSONArray("weather").getJSONObject(0).getString("description");
            String icon    = data.getJSONArray("weather").getJSONObject(0).getString("icon");
            long   sunrise = data.getJSONObject("sys").getLong("sunrise");
            long   sunset  = data.getJSONObject("sys").getLong("sunset");

            cityLabel.setText(name);
            countryLabel.setText(country + " • Live Weather");
            tempLabel.setText(String.format("%.1f°C", temp));
            feelsLikeLabel.setText(String.format("Feels like %.1f°C", feels));
            descLabel.setText(capitalize(desc));
            humidityLabel.setText("<html><b style='color:#94a3b8'>💧 Humidity</b><br><span style='font-size:16px;color:white'>" + hum + "%</span></html>");
            windLabel.setText("<html><b style='color:#94a3b8'>💨 Wind</b><br><span style='font-size:16px;color:white'>" + wind + " m/s</span></html>");
            pressureLabel.setText("<html><b style='color:#94a3b8'>🔵 Pressure</b><br><span style='font-size:16px;color:white'>" + press + " hPa</span></html>");
            visibilityLabel.setText("<html><b style='color:#94a3b8'>👁 Visibility</b><br><span style='font-size:16px;color:white'>" + (vis/1000) + " km</span></html>");
            sunriseLabel.setText("<html><b style='color:#94a3b8'>🌅 Sunrise</b><br><span style='font-size:16px;color:white'>" + formatTime(sunrise) + "</span></html>");
            sunsetLabel.setText("<html><b style='color:#94a3b8'>🌇 Sunset</b><br><span style='font-size:16px;color:white'>" + formatTime(sunset) + "</span></html>");
            iconLabel.setText(getWeatherEmoji(icon));

        } catch (Exception e) {
            showError("Parse error: " + e.getMessage());
        }
    }

    private String getWeatherEmoji(String icon) {
        return switch (icon.substring(0, 2)) {
            case "01" -> "☀️";
            case "02" -> "🌤";
            case "03" -> "🌥";
            case "04" -> "☁️";
            case "09" -> "🌧";
            case "10" -> "🌦";
            case "11" -> "⛈";
            case "13" -> "❄️";
            case "50" -> "🌫";
            default   -> "🌡";
        };
    }

    private String formatTime(long unix) {
        java.time.Instant inst = java.time.Instant.ofEpochSecond(unix);
        java.time.LocalTime lt = java.time.LocalTime.ofInstant(inst, java.time.ZoneId.systemDefault());
        return String.format("%02d:%02d", lt.getHour(), lt.getMinute());
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private void showError(String msg) {
        cityLabel.setText("Error");
        countryLabel.setText(msg);
        tempLabel.setText("--°C");
    }

    // ── UI Helpers ───────────────────────────────────────────
    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(99, 102, 241, 40), 1, true),
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));
        return card;
    }

    private JLabel createLabel(String text, int size, int style, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", style, size));
        lbl.setForeground(color);
        return lbl;
    }

    private JLabel createStatLabel(String title, String value) {
        JLabel lbl = new JLabel("<html><b style='color:#94a3b8'>" + title + "</b><br><span style='font-size:16px;color:white'>" + value + "</span></html>");
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return lbl;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(WeatherApp::new);
    }
}
