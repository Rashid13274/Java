package Phase_4_Exceptional_Handling_And_Input_Output;

/**

// ─────────────────────────────────────────────────────────────
// PHASE 4 — L7: JSON with Jackson
//
// Jackson is the most used JSON library in Java/Spring Boot.
// Spring Boot includes it by default via spring-boot-starter-web.
//
// Core class: ObjectMapper
//   serialize   → Java Object  → JSON String/File
//   deserialize → JSON String  → Java Object
//
// Jackson annotations:
//   @JsonProperty      → map field to different JSON key
//   @JsonIgnore        → exclude field from JSON
//   @JsonInclude       → control null handling
//   @JsonFormat        → format dates
//   @JsonAlias         → accept multiple JSON keys for one field
//
// Maven dependency (add to pom.xml):
//   <dependency>
//     <groupId>com.fasterxml.jackson.core</groupId>
//     <artifactId>jackson-databind</artifactId>
//     <version>2.15.0</version>
//   </dependency>
//
// Node.js parallel:
//   JSON.stringify(obj) → objectMapper.writeValueAsString(obj)
//   JSON.parse(str)     → objectMapper.readValue(str, Type.class)
// ─────────────────────────────────────────────────────────────

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.util.*;
import java.io.*;

// ── MODEL CLASS ───────────────────────────────────────────────
@JsonInclude(JsonInclude.Include.NON_NULL)   // exclude null fields from JSON output
public class User {

    private int    id;

    @JsonProperty("full_name")  // JSON key = "full_name", Java field = name
    private String name;

    private String email;

    @JsonIgnore                 // never included in JSON (for passwords, etc.)
    private String password;

    @JsonAlias({"dept", "division"})   // accept any of these keys when parsing
    private String department;

    // Jackson requires a no-arg constructor for deserialization
    public User() {}

    public User(int id, String name, String email,
                String password, String department) {
        this.id         = id;
        this.name       = name;
        this.email      = email;
        this.password   = password;
        this.department = department;
    }

    // Getters & setters (required by Jackson for serialization)
    public int    getId()           { return id; }
    public String getName()         { return name; }
    public String getEmail()        { return email; }
    public String getPassword()     { return password; }
    public String getDepartment()   { return department; }
    public void   setId(int id)                   { this.id = id; }
    public void   setName(String name)             { this.name = name; }
    public void   setEmail(String email)           { this.email = email; }
    public void   setPassword(String password)     { this.password = password; }
    public void   setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return String.format("User[%d | %s | %s | %s]", id, name, email, department);
    }
}

public class JacksonDemo {
    public static void main(String[] args) throws Exception {

        // ObjectMapper — the core Jackson class
        // Create ONCE and reuse — it's expensive to initialize, but thread-safe
        ObjectMapper mapper = new ObjectMapper();

        // Pretty print output (indented JSON)
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Don't fail if JSON has unknown fields not in the Java class
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        User user = new User(1, "Rashid", "rashid@example.com",
                             "secret123", "Backend");

        // ── SERIALIZE: Java Object → JSON String ───────────────
        String json = mapper.writeValueAsString(user);
        System.out.println("=== Object → JSON ===");
        System.out.println(json);
        // {
        //   "id" : 1,
        //   "full_name" : "Rashid",    ← @JsonProperty renamed it
        //   "email" : "...",
        //   "department" : "Backend"
        //   // password EXCLUDED via @JsonIgnore
        // }

        // ── DESERIALIZE: JSON String → Java Object ─────────────
        String incomingJson = """
                {
                  "id": 2,
                  "full_name": "Ali",
                  "email": "ali@example.com",
                  "dept": "Frontend",
                  "role": "developer"
                }
                """;
        // "dept" works because of @JsonAlias
        // "role" ignored because FAIL_ON_UNKNOWN_PROPERTIES = false

        User parsed = mapper.readValue(incomingJson, User.class);
        System.out.println("\n=== JSON → Object ===");
        System.out.println(parsed);

        // ── SERIALIZE: Java Object → JSON File ─────────────────
        File outputFile = new File("user.json");
        mapper.writeValue(outputFile, user);
        System.out.println("\nWritten to user.json");

        // ── DESERIALIZE: JSON File → Java Object ───────────────
        User fromFile = mapper.readValue(outputFile, User.class);
        System.out.println("Read from file: " + fromFile);

        // ── LIST SERIALIZATION ────────────────────────────────
        List<User> users = List.of(
                new User(1, "Rashid", "r@ex.com", "p1", "Backend"),
                new User(2, "Ali",    "a@ex.com", "p2", "Frontend")
        );

        String usersJson = mapper.writeValueAsString(users);
        System.out.println("\n=== List → JSON ===");
        System.out.println(usersJson);

        // Deserialize JSON array back to List
        List<User> userList = mapper.readValue(
                usersJson,
                mapper.getTypeFactory().constructCollectionType(List.class, User.class)
        );
        System.out.println("Parsed " + userList.size() + " users");

        // ── MAP SERIALIZATION ─────────────────────────────────
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status",  "success");
        response.put("data",    user);
        response.put("count",   1);
        System.out.println("\n=== Map → JSON ===");
        System.out.println(mapper.writeValueAsString(response));

        // ── DYNAMIC JSON TREE (no POJO needed) ───────────────
        // Like working with JSON directly without a class
        String raw = """
                {"product": "Java Book", "price": 499, "inStock": true}
                """;
        JsonNode root = mapper.readTree(raw);
        System.out.println("\n=== JsonNode (dynamic) ===");
        System.out.println("Product: " + root.get("product").asText());
        System.out.println("Price:   " + root.get("price").asInt());
        System.out.println("InStock: " + root.get("inStock").asBoolean());

        outputFile.delete();
    }
    
}


 * 
 */