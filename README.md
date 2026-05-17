# SI_lab2_215081


### 1. searchBookByTitle 
 prvicno: 1
 DP 1: `if (title.isEmpty())`  +1
 DP 2: `for (Book book : books)` +1
 DP 3: `if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())` +1
 DP `if (results.isEmpty())`+1
 Kompleksnost = 4


### 2. borrowBook 


 Prvicno: 1
DP 2: `if (author.isEmpty())` (counted as part of compound condition in single if statement) 1
DP 1: `if (title.isEmpty())` +1
DP 3: `for (Book book : books)` +1
DP 4: `if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author))` +1
DP 5: `if (!book.isBorrowed())` +1
 Kompleksnost = 6

test for searchBookEveryStatementTest
Za every statement ni trebaat minimalno 3 test primeri

Every Branch test for borrowBook
Za every branch ni trebaat minimalno 5 test primeri.

Multiple Condition for searchBookByTitle
T & T =T
T & F = F
F & T = F
F & F = F
minimalen broj na testovi:3 ako prviot e false drugiot test ne ni e biten.
Multiple Codnition for borrowBook
T & T = T
T & F = T
F & T = T
F & F = F
minimalno mozeme 3 testa, prvite 2 testa ako uslovot title.isEmpty e true, celiot izraz ke bidi true
