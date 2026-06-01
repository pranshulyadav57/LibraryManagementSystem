DROP DATABASE IF EXISTS Library;

CREATE DATABASE Library;

USE Library;

CREATE TABLE Admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Librarian (
    Empid INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    mob BIGINT,
    username VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE books (
    BookID INT PRIMARY KEY,
    Name VARCHAR(150) NOT NULL,
    Author VARCHAR(100) NOT NULL,
    Quantity INT DEFAULT 0,
    Issued INT DEFAULT 0
);

CREATE TABLE studentlogin (
    Rno INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Branch VARCHAR(50),
    Year VARCHAR(50),
    Address VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE,
    Phone VARCHAR(20),
    username VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE issuebook (
    id INT AUTO_INCREMENT PRIMARY KEY,
    BookID INT,
    Rno INT,
    Name VARCHAR(100),
    mobile BIGINT,
    IssueDate DATE,
    Status VARCHAR(50),

    CONSTRAINT fk_issue_book
    FOREIGN KEY (BookID)
    REFERENCES books(BookID),

    CONSTRAINT fk_issue_student
    FOREIGN KEY (Rno)
    REFERENCES studentlogin(Rno)
);

CREATE TABLE fine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    BookID INT,
    Rno INT,
    Name VARCHAR(100),
    mobile BIGINT,
    IssueDate DATE,
    Return_Date DATE,
    Days INT,
    Status VARCHAR(50),
    Fine BIGINT
);

DROP PROCEDURE IF EXISTS CalculateFine;

DELIMITER //

CREATE PROCEDURE CalculateFine(
    IN Rno1 INT,
    IN bookid1 INT
)
BEGIN

    DECLARE diff INT;
    DECLARE amt INT;
    DECLARE date1 DATE;
    DECLARE mobile1 BIGINT;
    DECLARE status1 VARCHAR(50);
    DECLARE name1 VARCHAR(100);

    SELECT IssueDate, Status, mobile, Name
    INTO date1, status1, mobile1, name1
    FROM issuebook
    WHERE Rno = Rno1
    AND BookID = bookid1;

    SET diff = DATEDIFF(CURDATE(), date1);

    IF diff > 15 THEN

        SET amt = diff * 10;

        INSERT INTO fine (
            BookID,
            Rno,
            Name,
            mobile,
            IssueDate,
            Return_Date,
            Days,
            Status,
            Fine
        )
        VALUES (
            bookid1,
            Rno1,
            name1,
            mobile1,
            date1,
            CURDATE(),
            diff,
            status1,
            amt
        );

    END IF;

END //

DELIMITER ;

INSERT INTO Admin (
    email,
    password,
    name
)
VALUES (
    'admin@library.com',
    'admin123',
    'Library Admin'
);

INSERT INTO Librarian (
    Name,
    email,
    password,
    mob,
    username
)
VALUES (
    'Pranshul Yadav',
    'librarian@library.com',
    'lib123',
    9999999999,
    'librarianuser'
);

INSERT INTO studentlogin (
    Rno,
    Name,
    Branch,
    Year,
    Address,
    password,
    email,
    Phone,
    username
)
VALUES (
    101,
    'John Doe',
    'CSE',
    '3rd',
    'Delhi',
    'student123',
    'student@library.com',
    '9876543210',
    'johndoe'
);

INSERT INTO books (
    BookID,
    Name,
    Author,
    Quantity,
    Issued
)
VALUES
(1, 'The C Programming Language', 'Brian Kernighan', 5, 2),
(2, 'Java The Complete Reference', 'Herbert Schildt', 4, 1),
(3, 'Introduction to Algorithms', 'Thomas Cormen', 3, 0),
(4, 'Design Patterns', 'Gang of Four', 2, 1),
(5, 'Clean Code', 'Robert Martin', 6, 3);

SHOW TABLES;

DESC Librarian;

SELECT * FROM Admin;
SELECT * FROM Librarian;
SELECT * FROM studentlogin;
SELECT * FROM books;