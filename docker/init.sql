CREATE DATABASE IF NOT EXISTS progress_tracker;

CREATE USER IF NOT EXISTS 'user_progress_tracker'@'%' IDENTIFIED BY 'progress_trackerPW';
GRANT ALL PRIVILEGES ON progress_tracker.* TO 'user_progress_tracker'@'%';
FLUSH PRIVILEGES;
