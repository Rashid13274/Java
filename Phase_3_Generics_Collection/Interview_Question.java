package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L10: INTERVIEW QUESTIONS
//
// These are the MOST frequently asked Java Collections questions
// in backend developer interviews (2–4 years experience level).
// Read both the question and the explanation carefully.
// ───────────────────────────────────────────────────────────── {

/*
───────────────────────────────────────────────────────────────────────────

Q1. What is the difference between ArrayList and LinkedList?

ANSWER:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Feature          ArrayList              LinkedList
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Internal struct  Dynamic array          Doubly linked nodes
get(index)       O(1) — fast            O(n) — must traverse
add(end)         O(1) amortized         O(1)
add(middle)      O(n) — shifts          O(n) — traversal needed
remove(middle)   O(n) — shifts          O(n) — traversal needed
Memory           Less (no pointers)     More (prev+next pointers)
Cache friendly   ✅ Yes                 ❌ No (scattered memory)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

VERDICT: Use ArrayList 90% of the time.
Use LinkedList only when: frequent add/remove at HEAD,
or using it as a Queue/Deque.
───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────


Q2. What is the difference between HashMap, LinkedHashMap, and TreeMap?
ANSWER:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
              HashMap     LinkedHashMap   TreeMap
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Ordering      None        Insertion order Sorted by key
Null key      1 allowed   1 allowed       ❌ Not allowed
Null value    ✅ Many      ✅ Many         ✅ Many
Performance   O(1)        O(1)            O(log n)
Internal str  Array+List  Array+List      Red-Black Tree
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

When to use:
  HashMap      → fastest, order doesn't matter (most common)
  LinkedHashMap→ need insertion-order (e.g., LRU cache)
  TreeMap      → need sorted keys (e.g., leaderboard, timeline)
───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────
Q3. How does HashMap work internally?

ANSWER (must know for senior/mid-level roles):

HashMap internally uses an ARRAY of buckets (default size 16).

Step-by-step for map.put("name", "Rashid"):
  1. Call "name".hashCode()  → e.g., 3373752
  2. index = hashCode % capacity  → 3373752 % 16 = 8
  3. Go to bucket[8]
  4. If bucket empty → create new Node("name", "Rashid")
  5. If bucket has entries (COLLISION):
       - Check each node's key with .equals()
       - If key matches → UPDATE value
       - If no match   → ADD to chain (LinkedList or Tree)

COLLISION HANDLING:
  Before Java 8 → LinkedList in each bucket
  After  Java 8 → LinkedList converts to Red-Black Tree
                  when bucket size > 8 (O(n) → O(log n))

RESIZING (rehashing):
  Happens when: size > capacity × loadFactor (16 × 0.75 = 12)
  New capacity = 16 × 2 = 32
  All elements re-hashed into new array (expensive O(n))

This is why a good hashCode() matters for HashMap performance!

───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────
Q4. What is the difference between HashSet and TreeSet?

ANSWER:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Feature    HashSet      TreeSet
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Order      Unordered    Sorted (natural/custom)
Null?      1 null ok    ❌ No null (NullPointerException)
Speed      O(1)         O(log n)
Backed by  HashMap      TreeMap (Red-Black Tree)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Extra TreeSet methods (not in HashSet):
  first(), last(), headSet(), tailSet(),
  floor(), ceiling(), subSet()

Use HashSet when: fast lookup, order irrelevant.
Use TreeSet when: always need elements in sorted order.

───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────

Q5. What is the difference between Comparable and Comparator?

ANSWER:

Comparable (java.lang):
  - Class implements it ITSELF
  - One natural ordering per class
  - Method: compareTo(T other)
  - Example: String, Integer implement Comparable
  - Use: Collections.sort(list) — uses compareTo internally

Comparator (java.util):
  - Separate class/lambda — external to the object
  - Can define MULTIPLE orderings
  - Method: compare(T o1, T o2)
  - Use: list.sort(comparator) — uses compare internally

WHEN TO USE WHICH:
  Comparable → You own the class, ONE natural ordering
               e.g., Student naturally ordered by GPA
  Comparator → You don't own class (String, Integer)
               OR you need multiple orderings
               e.g., sort students by name, then by age, then by dept

───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────

Q6. What happens if you modify a list while iterating with for-each?

// ANSWER: ConcurrentModificationException at runtime!

// ❌ WRONG — throws exception
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
for (Integer n : list) {
    if (n % 2 == 0) list.remove(n);  // ❌ ConcurrentModificationException
}

// ✅ CORRECT WAY 1: Iterator.remove()
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    if (it.next() % 2 == 0) it.remove();  // safe
}

// ✅ CORRECT WAY 2: removeIf (Java 8+ — most concise)
list.removeIf(n -> n % 2 == 0);

// ✅ CORRECT WAY 3: collect to new list (functional style)
List<Integer> odds = list.stream()
        .filter(n -> n % 2 != 0)
        .collect(Collectors.toList());

// WHY it happens:
// ArrayList tracks a "modCount" (modification count).
// Iterator stores "expectedModCount" at creation time.
// If modCount != expectedModCount → ConcurrentModificationException.
// Iterator.remove() updates both counts correctly.



───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────

Q7. What is the difference between fail-fast and fail-safe iterators?


ANSWER:

Fail-fast Iterator:
  - Works on the ORIGINAL collection
  - If collection modified during iteration → ConcurrentModificationException
  - Examples: ArrayList, HashMap, HashSet iterators
  - Faster (no copy overhead), but not thread-safe

Fail-safe Iterator:
  - Works on a CLONE/COPY of the collection
  - Modifications during iteration → NO exception
  - Changes made after iterator created may NOT be visible
  - Examples: CopyOnWriteArrayList, ConcurrentHashMap
  - Thread-safe, but uses more memory

CODE EXAMPLE:
  // Fail-safe — no exception even with modification
  CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(
      Arrays.asList("A", "B", "C"));
  for (String s : list) {
      list.add("X");  // ✅ No exception — iterator works on snapshot
  }
───────────────────────────────────────────────────────────────────────────
───────────────────────────────────────────────────────────────────────────

  Q8. What is the difference between List.of() and new ArrayList()?
  ANSWER:

List.of("A", "B", "C")   → Java 9+
  - IMMUTABLE — cannot add, remove, or set
  - Does NOT allow null elements
  - Great for constants, method parameters
  - Slightly more memory efficient
  - UnsupportedOperationException if you try to modify

new ArrayList<>(Arrays.asList("A", "B", "C"))
  - MUTABLE — full add/remove/set support
  - Allows null elements
  - Use when you need to modify the list

ALSO:
  Arrays.asList("A","B","C")
    - Fixed-size mutable (set() ok, add()/remove() not ok)
    - Backed by original array — changes reflect both ways
    - Allows null

RULE OF THUMB:
  Read-only data          → List.of()
  Need to modify later    → new ArrayList<>()
  Bridge from array       → Arrays.asList()


  
  ───────────────────────────────────────────────────────────────────────────
  ───────────────────────────────────────────────────────────────────────────
  Q9. What are Generics and why were they introduced?
  ANSWER:

Before Generics (Java < 5):
  List list = new ArrayList();   // raw type
  list.add("hello");
  list.add(42);
  String s = (String) list.get(1);  // ClassCastException at RUNTIME!

After Generics (Java 5+):
  List<String> list = new ArrayList<>();
  list.add(42);  // COMPILE ERROR — caught before runtime!

Benefits:
  1. Type safety  — errors caught at compile time, not runtime
  2. No casting   — no need for explicit (Type) casts
  3. Reusability  — write once, work for any type

Type Erasure (important for interviews!):
  Generics exist only at COMPILE TIME.
  At runtime, <String> is ERASED — becomes plain List.
  This is why you can't do: new T() or T[] inside generic class.
  And why instanceof List<String> doesn't work at runtime.

  ───────────────────────────────────────────────────────────────────────────
  ───────────────────────────────────────────────────────────────────────────

  Q10. What is the difference between HashMap and Hashtable?
ANSWER:
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Feature          HashMap          Hashtable
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Thread safety    ❌ Not safe      ✅ Synchronized
Null key         ✅ One allowed   ❌ Not allowed
Null value       ✅ Allowed       ❌ Not allowed
Performance      Faster           Slower (sync overhead)
Since            Java 2           Java 1 (legacy)
Iterator         Fail-fast        Enumerator (legacy)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

In modern Java:
  NEVER use Hashtable — it's legacy.
  For single-thread:   HashMap
  For multi-thread:    ConcurrentHashMap (better than Hashtable)
  ConcurrentHashMap uses segment-level locking (not whole map)
  → much better concurrency performance.

  ───────────────────────────────────────────────────────────────────────────
  ───────────────────────────────────────────────────────────────────────────
  Q11. When would you use PriorityQueue? Give a real-world example.
  // ANSWER:
// PriorityQueue processes elements in priority order (min-heap by default).
// It does NOT maintain insertion order.
// poll() always returns the SMALLEST element (or custom priority).

// REAL-WORLD USE CASES:
//   1. Task scheduler — urgent tasks first
//   2. Dijkstra's shortest path algorithm
//   3. Top-K elements problem (keep K largest/smallest)
//   4. Hospital ER queue — critical patients first
//   5. CPU process scheduling

// EXAMPLE: Top 3 highest-scoring students
import java.util.*;

List<int[]> allStudents = Arrays.asList(
        new int[]{95}, new int[]{80}, new int[]{92},
        new int[]{88}, new int[]{76}, new int[]{99}
);

// Min-heap of size K — keeps the K largest
PriorityQueue<Integer> topK = new PriorityQueue<>();  // min-heap
int K = 3;

for (int[] s : allStudents) {
    topK.offer(s[0]);
    if (topK.size() > K) {
        topK.poll();  // remove smallest — keeps top K largest inside
    }
}

System.out.println("Top 3 scores: " + topK);  // [92, 95, 99] — order may vary

  ───────────────────────────────────────────────────────────────────────────
  ───────────────────────────────────────────────────────────────────────────
Q12. What is Collections.unmodifiableList() and when should you use it?

// ANSWER:
// Returns a READ-ONLY VIEW of the original list.
// Any modification attempt throws UnsupportedOperationException.
//
// Use when:
//   - Exposing internal list via getter without allowing modification
//   - Returning data from service layer that should not be mutated
//   - Creating defensive copies of shared data

import java.util.*;

class UserService {
    // Internal mutable list — only this class can modify it
    private List<String> users = new ArrayList<>(Arrays.asList("Rashid", "Ali"));

    // Return read-only view — callers can read but NOT modify
    public List<String> getUsers() {
        return Collections.unmodifiableList(users);
    }

    // ✅ Java 10+ alternative: List.copyOf() — returns immutable copy
    public List<String> getUsersCopy() {
        return List.copyOf(users);  // snapshot — changes to 'users' not reflected
    }
}

// DIFFERENCE:
// unmodifiableList → view of original — if original changes, view reflects it
// List.copyOf()    → independent snapshot — original changes not visible
// List.of()        → immutable from the start — no original to update

*/  



