---

# 🐍 Snake Game — Java Edition

A modern twist on the **classic Snake game**, built entirely using **Java** and **Swing**. This solo project highlights my understanding of **Object-Oriented Programming (OOP)**, **event-driven architecture**, and **interactive GUI development**.

---

## 🎮 Gameplay Overview

* 🕹️ **Control**: Use arrow keys to move the snake.
* 🍎 **Goal**: Eat apples to grow longer.
* 🚧 **Avoid**: Crashing into walls or your own tail.
* 🏁 **Game Over**: Triggered by any collision.

---

## 🛠 Tech Stack

| Category     | Tools/Technologies           |
| ------------ | ---------------------------- |
| **Language** | Java                         |
| **GUI**      | Java Swing, AWT              |
| **Concepts** | OOP, Event Listeners, Timers |

---

## ✨ Core Features

* 🎨 **Custom GUI**: Built with `JFrame`, `JPanel`, and manual `paintComponent()` rendering.
* ⌨️ **Keyboard Controls**: Smooth arrow-key input via `KeyListener`.
* 🍏 **Randomized Apple Placement**: Apples spawn randomly within a grid-aligned board.
* 📊 **Real-Time Score Tracker**: Score updates dynamically with gameplay.
* ❌ **Collision Detection**: Self and wall collision end the game.
* 🔁 **Game Restart**: Auto-restart prompt with reset score functionality.

---

## 💡 Key Concepts Demonstrated

* **Modular OOP Design**: Clean separation of game logic (`Snake`, `Apple`, `GamePanel`, etc.).
* **Event-Driven Logic**: `KeyListener` and `Timer` for real-time responsiveness.
* **Game Loop Management**: Smooth, consistent updates using Java’s `Timer`.
* **Clean Code Practices**: Emphasis on readability, structure, and reusability.

---

## 🚀 Planned Enhancements

* 🎵 Background music & SFX
* 💾 Persistent high-score tracking using file I/O or SQLite
* 🔥 Difficulty scaling (speed increases with time)
* 🎨 Enhanced graphics (possibly using JavaFX)
* 🤝 Local multiplayer over sockets or LAN

---

## 🧠 What I Learned

* End-to-end GUI application development in Java.
* Handling game state, rendering, and user interactions in real time.
* Writing clean, modular, and maintainable Java code.
* Debugging graphical and logic bugs in event-driven environments.

---

## 📦 How to Run

### 🔧 Prerequisites

* Java JDK 8 or higher

### ▶️ Steps

```bash
# Clone the repository
git clone https://github.com/your-username/snake-game-java.git
cd snake-game-java

# Compile and run
javac SnakeGame.java
java SnakeGame
```

---

## 🙌 Credits

Created with ❤️ by **Ishaan Taneja**
Feel free to ⭐ the repo, fork it, or open issues if you want to collaborate or suggest improvements!

