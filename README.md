# Library Management System

The college library management system is a web-based application that allows users to easily manage the library's catalog and borrower information. The frontend of the application is built using HTML, CSS, and JavaScript, providing a user-friendly interface for users to interact with. The backend of the application is powered by Java servlets and JSP, which handle the processing of data and communication with the database. The database itself is built using MySQL, and all sensitive data is encrypted using AES encryption for added security. Overall, this system allows for efficient and secure management of the library's resources.

# Technology used
```bash
  Backend: Java, Servlets, JSP
  Frontend: HTML, CSS, JavaScript
  Database: MySQL
  Server: Apache Tomcat Server
```
# Login Page
```bash
The login page which includes:
•	Login interface for Student and Librarian. 
•	The register column for easy signing up of a user within seconds.
•	The welcome section introducing what the site is about.
•	A section showing browser compatibility.
```
# Issue Book Section (Librarian)
•	Issue the books easily by just providing the Book ID of the book and Student ID, Student Name, Student Mobile No. from the database.

# Return Book Section (Librarian)
•	Return the book by typing in the Book ID of the book and Student ID of Student to be returned.

# Fine Section (Librarian)
•	Fine Amount for Late Return of Book.
•	If the book is not returned within 15 days then fine will be 10Rs. Per day. For this We used a Stored Procedure in a Code given below:

```bash
  create procedure Library(in Rno1 int,bookid1 int)
	begin
		declare diff int;
		declare amt int;
		declare date date;
		declare mob1 bigint;
		declare status1 varchar(50);
		declare name1 varchar(50);

select IssueDate,status,Mon,name into date,status1,mob1,name1 from  IssueBook where Rno=Rno1 and bookid=bookid1;
		set diff=datediff(curdate(),date);
		if diff>15 then
insert into Fine  values(bookid1,Rno1,name1,mob1,date,curdate(),diff,status1,
diff*0);
		end if;
	end;//

```
