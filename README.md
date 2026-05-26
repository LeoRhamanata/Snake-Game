# 🐍 Snake Game - OOP Final Project

This is the result of hard work (and some late nights) for the **Object-Oriented Programming (OOP)** Final Project.
The game is simple — snake eats apples, grows longer, hits a wall and dies. But underneath, there's some pretty serious OOP going on.

---

## 🎮 About the Game

A classic Snake Game built with **Java Swing (GUI)**.
Move the snake using **WASD or Arrow Keys**, eat as many apples as you can, and don't hit the walls or your own body.

---

## 🏗️ Tech & Concepts Used

- **Java** — main language
- **Java Swing** — GUI rendering
- **OOP** — Inheritance, Encapsulation, Polymorphism, Abstraction
- **Collections** — `LinkedList` for snake body, `ArrayList` for score history
- **Exception Handling** — so it doesn't crash on us mid-game

---

## 📁 File Structure

```
TA-PBO/
├── Main.java          → Entry point
├── GameFrame.java     → JFrame window
├── GamePanel.java     → Game logic + rendering
├── Snake.java         → Snake class
├── Apple.java         → Apple class
├── Entity.java        → Abstract superclass
├── Movable.java       → Movement interface
└── SnakeTest.java     → JUnit 5 Unit Tests
```

---

## ▶️ How to Run

```bash
javac *.java
java Main
```

---

## 📝 Notes

This repo is my personal contribution to the OOP Final Project group assignment.
Built with full effort, genuine dedication, and occasional moments of pure panic.

> *"It's not a bug, it's a feature."* — Every programmer ever.
