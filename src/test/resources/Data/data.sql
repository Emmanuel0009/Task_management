DROP TABLE IF EXISTS Task;

CREATE TABLE Task (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    objective VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Comment;

CREATE TABLE Comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    comment_text VARCHAR(255) NOT NULL,
    task_id BIGINT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Task(id)
);

DROP TABLE IF EXISTS Description;

CREATE TABLE Description (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description_text VARCHAR(255) NOT NULL,
    task_id BIGINT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Task(id)
);

DROP TABLE IF EXISTS Estimation;

CREATE TABLE Estimation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    estimation_time BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Task(id)
);

DROP TABLE IF EXISTS Example;

CREATE TABLE Example (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    example_text VARCHAR(255) NOT NULL,
    task_id BIGINT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Task(id)
);

DROP TABLE IF EXISTS ExampleFile;

CREATE TABLE ExampleFile (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file BLOB NOT NULL,
    example_id BIGINT NOT NULL,
    FOREIGN KEY (example_id) REFERENCES Example(id)
);

DROP TABLE IF EXISTS Status;

CREATE TABLE Status (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255) NOT NULL,
    task_id BIGINT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Task(id)
);

DROP TABLE IF EXISTS UrgencyLevel;

CREATE TABLE UrgencyLevel (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    urgency_level VARCHAR(255) NOT NULL,
    task_id BIGINT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Task(id)
);

DROP TABLE IF EXISTS CustomDate;

CREATE TABLE CustomDate (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date VARCHAR(255) NOT NULL,
    type_date VARCHAR(255) NOT NULL,
    task_id BIGINT,
    comment_id BIGINT,
    description_id BIGINT,
    estimation_id BIGINT,
    example_id BIGINT,
    example_file_id BIGINT,
    status_id BIGINT,
    urgency_level_id BIGINT,
    FOREIGN KEY (task_id) REFERENCES Task(id),
    FOREIGN KEY (comment_id) REFERENCES Comment(id),
    FOREIGN KEY (description_id) REFERENCES Description(id),
    FOREIGN KEY (estimation_id) REFERENCES Estimation(id),
    FOREIGN KEY (example_id) REFERENCES Example(id),
    FOREIGN KEY (example_file_id) REFERENCES ExampleFile(id),
    FOREIGN KEY (status_id) REFERENCES Status(id),
    FOREIGN KEY (urgency_level_id) REFERENCES UrgencyLevel(id)
);

INSERT INTO Task (objective)
VALUES
('Complete project report'),
('Prepare client presentation'),
('Fix bug in payment system'),
('Review code for new feature'),
('Conduct user testing');

INSERT INTO Comment (comment_text, task_id)
VALUES
('This task is on track.', 1),
('Client feedback received, updating the presentation.', 1),
('Client feedback received, updating the presentation.', 2),
('Investigating the issue in the payment module.', 3),
('Code review completed, ready for next steps.', 4),
('User testing is ongoing, no major issues so far.', 5);

INSERT INTO Description (description_text, task_id)
VALUES
('This task involves writing a comprehensive project report.', 1),
('Bug identified in the payment system, root cause analysis in progress.', 1),
('The presentation will cover the latest product features and roadmap.', 2),
('Bug identified in the payment system, root cause analysis in progress.', 3),
('Code has been reviewed for the new feature and passed initial tests.', 4),
('Initial results from user testing are positive, refining features.', 5);

INSERT INTO Estimation (estimation_time, task_id)
VALUES
(3600, 1),
(3600, 2),
(7200, 2),
(10800, 3),
(14400, 4),
(18000, 5);

INSERT INTO Example (example_text, task_id)
VALUES
('Example of project scope definition document.', 1),
('Example of code review checklist for feature implementation.', 1),
('Example of client presentation slide layout.', 2),
('Example of a bug report for the payment system.', 3),
('Example of code review checklist for feature implementation.', 4),
('Example of user testing feedback form.', 5);

INSERT INTO ExampleFile (file, example_id)
VALUES
(0x89504E470D0A1A0A0000000D4948445200000100010001000806000000C0C0C0C0000004674D4220102E2023D, 1),
(0x89504E470D0A1A0A0000000D49484452000005000500050006000000D4D4D4D400000004674D4220102E2023D, 1);
(0x89504E470D0A1A0A0000000D49484452000002000200020006000000A1A1A1A100000004674D4220102E2023D, 2),
(0x89504E470D0A1A0A0000000D49484452000003000300030006000000B2B2B2B200000004674D4220102E2023D, 3),
(0x89504E470D0A1A0A0000000D49484452000004000400040006000000C3C3C3C300000004674D4220102E2023D, 4),
(0x89504E470D0A1A0A0000000D49484452000005000500050006000000D4D4D4D400000004674D4220102E2023D, 5);

INSERT INTO Status (status, task_id)
VALUES
('TODO', 1),
('DONE', 2),
('IN_PROGRESS', 3),
('PUT_ON_HOLD', 4),
('TODO', 2),
('ABANDONED', 5),
('CANCELLED', 1),
('BLOCKED', 2),
('REVIEW_PENDING', 3);

INSERT INTO UrgencyLevel (urgency_level, task_id)
VALUES
('VERY_LOW', 1),
('VERY_LOW', 2),
('LOW', 1),
('NORMAL', 3),
('HIGH', 4),
('VERY_HIGH', 5);

-- Pour `comment_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:07', 'CREATION_DATE', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:22:00', 'CREATION_DATE', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 13:00:00', 'CREATION_DATE', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'MODIFIED_DATE', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:23:00', 'MODIFIED_DATE', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:36:41', 'MODIFIED_DATE', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- Pour `task_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 08:00:00', 'CREATION_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-02 08:00:00', 'CREATION_DATE', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 08:36:00', 'CREATION_DATE', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-02 08:41:00', 'CREATION_DATE', NULL, 4, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 08:00:28', 'CREATION_DATE', NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:41:00', 'MODIFIED_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 18:41:00', 'MODIFIED_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-02 10:28:00', 'MODIFIED_DATE', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-02 12:00:00', 'START_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-10 12:00:00', 'START_DATE', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-03 12:00:00', 'CLOSING_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-03 12:36:00', 'CLOSING_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-04 12:00:00', 'REOPENING_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-04 12:50:00', 'REOPENING_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-05 12:00:00', 'END_DATE', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL),
('2024-12-05 12:20:00', 'END_DATE', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL);

-- Pour `description_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL),
('2024-12-02 12:36:00', 'CREATION_DATE', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL),
('2024-12-02 12:36:00', 'CREATION_DATE', NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL),
('2024-12-03 12:31:00', 'MODIFIED_DATE', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL),
('2024-12-03 16:31:00', 'MODIFIED_DATE', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL),
('2024-12-03 12:21:00', 'MODIFIED_DATE', NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);

-- Pour `estimation_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL),
('2024-12-02 16:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL);

-- Pour `example_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, 3, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, 4, NULL, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('2024-12-03 17:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('2024-12-04 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL);

-- Pour `example_file_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, 3, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, 4, NULL, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
('2024-12-03 12:48:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
('2024-12-04 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL);

-- Pour `status_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
('2024-12-02 12:18:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL);

-- Pour `urgency_level_id`
INSERT INTO CustomDate (date, type_date, comment_id, task_id, description_id, estimation_id, example_id, example_file_id, status_id, urgency_level_id)
VALUES
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4),
('2024-12-01 12:00:00', 'CREATION_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('2024-12-02 12:27:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
('2024-12-02 12:00:00', 'MODIFIED_DATE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);



