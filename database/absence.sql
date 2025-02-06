select * from absence -- Gives you all the data from the absence table
-- (Line 35) put in multiple records of the absence object in the absence table
INSERT INTO Absence (start_date, end_date, reason, class_name, email, full_name, status) VALUES
('2023-11-01', '2023-11-02', 'Sick Leave', 'Maths', 'quinn.robinson1@example.com', 'Quinn Robinson', 'Pending'),
('2023-11-03', '2023-11-04', 'Personal Leave', 'Physics', 'oscar.garcia1@example.com', 'Oscar Garcia', 'Pending'),
('2023-11-05', '2023-11-06', 'Conference', 'English', 'mia.martin1@example.com', 'Mia Martin', 'Pending'),
('2023-11-07', '2023-11-08', 'Vacation', 'French', 'karen.white1@example.com', 'Karen White', 'Pending'),
('2023-11-09', '2023-11-10', 'Sick Leave', 'Maths', 'ivy.thomas1@example.com', 'Ivy Thomas', 'Pending'),
('2023-11-11', '2023-11-12', 'Personal Leave', 'Physics', 'grace.taylor1@example.com', 'Grace Taylor', 'Pending'),
('2023-11-13', '2023-11-14', 'Conference', 'English', 'eve.wilson1@example.com', 'Eve Wilson', 'Pending'),
('2023-11-15', '2023-11-16', 'Vacation', 'French', 'alice.johnson1@example.com', 'ALice Johhnson', 'Pending'),
('2023-11-17', '2023-11-18', 'Sick Leave', 'Maths', 'charlie.davis1@example.com', 'Charlie Davis', 'Pending'),
('2023-11-19', '2023-11-20', 'Personal Leave', 'Physics', 'john.doe1@example.com', 'John Doe', 'Pending');


