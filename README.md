# 🐍 Snake Game - Final Project PBO

Ini adalah hasil kerja keras (dan begadang) buat Final Project mata kuliah **Pemrograman Berorientasi Objek (PBO)**.
Gamenya simpel — ular makan apel, makin panjang, nabrak mati. Tapi dibaliknya ada OOP yang lumayan serius.

---

## 🎮 Tentang Game

Snake Game klasik berbasis **Java Swing (GUI)**.  
Gerakin ular pake **WASD atau Arrow Keys**, makan apel sebanyak mungkin, jangan sampai nabrak dinding atau tubuh sendiri.

---

## 🏗️ Teknologi & Konsep yang Dipakai

- **Java** — bahasa utama
- **Java Swing** — tampilan GUI
- **OOP** — Inheritance, Encapsulation, Polymorphism, Abstraction
- **Collections** — `LinkedList` untuk body ular, `ArrayList` untuk riwayat skor
- **Exception Handling** — biar gak gampang crash

---

## 📁 Struktur File

```
TA-PBO/
├── Main.java          → Entry point
├── GameFrame.java     → Window JFrame
├── GamePanel.java     → Logic + Render game
├── Snake.java         → Class ular
├── Apple.java         → Class apel
├── Entity.java        → Abstract superclass
├── Movable.java       → Interface gerak
└── SnakeTest.java     → Unit Testing JUnit 5
```

---

## ▶️ Cara Jalankan

```bash
javac *.java
java Main
```

---

## 📝 Catatan

Repo ini adalah bagian dari kontribusi pribadi di kelompok Final Project PBO.
Dikerjain dengan sepenuh hati, sesungguh-sungguhnya, dan sesekali panik sendiri.

> *"It's not a bug, it's a feature."* — Semua programmer pernah bilang ini.