select * from absence -- Gives you all the data from the absence table
-- (Line 35) put in multiple records of the absence object in the absence table
INSERT INTO Absence (start_date, end_date, reason, class_name, email, full_name, status) VALUES
('2023-11-01', '2023-11-02', 'Sick Leave', 'Maths', 'teacher1@example.com', 'John Doe', 'Pending'),
('2023-11-03', '2023-11-04', 'Personal Leave', 'Physics', 'teacher2@example.com', 'Jane Smith', 'Pending'),
('2023-11-05', '2023-11-06', 'Conference', 'English', 'teacher3@example.com', 'Alice Johnson', 'Pending'),
('2023-11-07', '2023-11-08', 'Vacation', 'French', 'teacher4@example.com', 'Bob Brown', 'Pending'),
('2023-11-09', '2023-11-10', 'Sick Leave', 'Maths', 'teacher5@example.com', 'Charlie Davis', 'Pending'),
('2023-11-11', '2023-11-12', 'Personal Leave', 'Physics', 'teacher6@example.com', 'Diana Miller', 'Pending'),
('2023-11-13', '2023-11-14', 'Conference', 'English', 'teacher7@example.com', 'Eve Wilson', 'Pending'),
('2023-11-15', '2023-11-16', 'Vacation', 'French', 'teacher8@example.com', 'Frank Moore', 'Pending'),
('2023-11-17', '2023-11-18', 'Sick Leave', 'Maths', 'teacher9@example.com', 'Grace Taylor', 'Pending'),
('2023-11-19', '2023-11-20', 'Personal Leave', 'Physics', 'teacher10@example.com', 'Hank Anderson', 'Pending');
