/*
===========================================================
                JVM, JDK & SETUP
===========================================================

Before writing your first Java line, you need to understand
the runtime model — it's fundamentally different from Node.js.
Java compiles to bytecode that runs on the JVM, not directly
on the OS.

-----------------------------------------------------------
🟢 Node.js Parallel
-----------------------------------------------------------
In Node.js, your .js file runs directly via V8 engine.

In Java:
.java → compiles → .class (bytecode) → runs on JVM

Think of JVM like V8, but platform-independent.


===========================================================
                THE JAVA ECOSYSTEM
===========================================================

⚙️ JVM (Java Virtual Machine)
----------------------------------------
- Executes bytecode (like V8 for JS)
- Each OS has its own JVM
- All run the same bytecode
→ "Write Once, Run Anywhere"

🛠️ JDK (Java Development Kit)
----------------------------------------
- Everything needed to DEVELOP Java apps:
  • Compiler (javac)
  • JVM
  • Debugger & tools
→ Like Node.js + npm bundled together

▶️ JRE (Java Runtime Environment)
----------------------------------------
- Only needed to RUN Java apps
- No compiler included
→ Like shipping a Docker image without build tools

📦 Maven / Gradle
----------------------------------------
- Package managers (like npm/yarn)
- Maven → uses pom.xml
- Gradle → uses build.gradle
- Spring Boot supports both


===========================================================
            HOW JAVA COMPILATION WORKS
===========================================================

☕ Java — compile & run flow

// Step 1: Write code in HelloWorld.java
// Step 2: Compile → javac HelloWorld.java
//          → produces HelloWorld.class (bytecode)
// Step 3: Run     → java HelloWorld
//          → JVM executes the .class file

// -------------------------------------------------------
// Every Java program needs:
// 1. A CLASS
// 2. A main() method (entry point)
//
// Node.js comparison:
// No class needed, just write code in index.js
// -------------------------------------------------------


public class HelloWorld {

    
    //  * main() is the entry point — JVM calls this first
    //  * String[] args → like process.argv in Node.js
     
    public static void main(String[] args) {

        System.out.println("Hello, Java World!");   // like console.log()
        System.out.print("No newline at end");      // print() vs println()
        System.err.println("This goes to stderr");  // like console.error()
    }
}


===========================================================
            MAVEN vs NODE DEPENDENCIES
===========================================================

☕ Java — pom.xml (Maven)

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
  </dependency>
</dependencies>


🟢 Node.js — package.json

"dependencies": {
  "express": "^4.18.0",
  "@nestjs/core": "^9.0.0"
}


===========================================================
                SETUP CHECKLIST
===========================================================

✔ Install JDK 21 (LTS)
----------------------------------------
Use sdkman:
    sdk install java 21-tem

✔ Verify Installation
----------------------------------------
    java -version
    javac -version

✔ Install IDE
----------------------------------------
IntelliJ IDEA Community
(best Java IDE — like VS Code but smarter)

✔ Install Maven
----------------------------------------
    sdk install maven
OR download from:
    maven.apache.org

✔ Create First Project
----------------------------------------
IntelliJ → New Project → Maven → Java 21


===========================================================
                    💡 PRO TIP
===========================================================

Use sdkman.io to manage multiple Java versions
→ exactly like nvm for Node.js

Commands:
    sdk install java 21-tem
    sdk use java 21-tem

===========================================================
*/