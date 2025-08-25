CREATE DATABASE IF NOT EXISTS haidaiphat_db;

USE haidaiphat_db;

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    full_name VARCHAR(255),
    phone_number VARCHAR(20),
    status ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng news_categories
CREATE TABLE IF NOT EXISTS news_categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

-- Tạo bảng job_departments
CREATE TABLE IF NOT EXISTS job_departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng news
CREATE TABLE IF NOT EXISTS news (
    news_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    image_url VARCHAR(500),
    meta_title VARCHAR(255),
    meta_description TEXT,
    description TEXT,
    content_html LONGTEXT,
    news_category_id INT,
    type ENUM('GENERAL', 'PROPERTY_MANAGEMENT', 'CLEANING_SERVICE', 'SECURITY_SERVICE', 'SPORTS_SERVICE') DEFAULT 'GENERAL',
    tags VARCHAR(500),
    keywords VARCHAR(500),
    view_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (news_category_id) REFERENCES news_categories(id)
);

-- Tạo bảng job_postings
CREATE TABLE IF NOT EXISTS job_postings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title LONGTEXT,
    city VARCHAR(255),
    working_type VARCHAR(100),
    quantity INT,
    salary VARCHAR(255),
    gender_requirement VARCHAR(50),
    experience_requirement LONGTEXT,
    job_detail_html LONGTEXT,
    candidate_requirement_html LONGTEXT,
    benefit_html LONGTEXT,
    working_time VARCHAR(255),
    deadline TIMESTAMP,
    receiver_email LONGTEXT,
    receiver_phone LONGTEXT,
    receiver_name LONGTEXT,
    application_method VARCHAR(255),
    contact_phone LONGTEXT,
    contact_email LONGTEXT,
    insurance_benefit LONGTEXT,
    health_benefit LONGTEXT,
    bonus_benefit LONGTEXT,
    department_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES job_departments(department_id)
);